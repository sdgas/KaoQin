package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.FileVO;
import org.sdgas.model.Administrators;
import org.sdgas.model.Holiday;
import org.sdgas.service.HolidayService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component("file")
@Scope("prototype")
public class FileAction extends MyActionSupport implements ModelDriven<FileVO> {

    @Qualifier("excelUtil")
    @Autowired
    private ExcelUtil excelUtil;
    private HolidayService holidayService;

    private final static Logger logger = LogManager.getLogger(FileAction.class);
    private final FileVO fileVO = new FileVO();
    private List<Object> obj = new ArrayList<Object>();
    private List<Holiday> holidays = new ArrayList<Holiday>();
    private int count = 0;
    private int num = 0;
    private static String SAVE_PATH_DIR_M = "D:/kaoqin/uploadFile/holiday/";

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    //导入信息
    public String recoverExcelByHoliday() {
        count = 0;
        String name = uploadAttachment(fileVO.getUploadFile(), fileVO.getFileName(), SAVE_PATH_DIR_M);
        try {
            obj = excelUtil.readExcelByPath(SAVE_PATH_DIR_M + name, Holiday.class, 0, 0);
            for (Object anObj : obj) {
                holidays.add((Holiday) anObj);
            }
            for (Holiday h : holidays) {
                Holiday holiday = new Holiday();
                if (holidayService.findByName(h.getHolidayName()) == null) {
                    holiday.setHolidayName(h.getHolidayName());
                    holiday.setLongtime(h.getLongtime());
                    holiday.setHolidayBeginDate(h.getHolidayBeginDate());
                    holidayService.save(holiday);
                    logger.info(user.getUserId() + ",导入节假日(" + holiday.getHolidayName() + ") IP:" + ip);
                    ++count;
                } else {
                    holiday = holidayService.findByName(h.getHolidayName());
                    holiday.setHolidayName(h.getHolidayName());
                    holiday.setLongtime(h.getLongtime());
                    holiday.setHolidayBeginDate(h.getHolidayBeginDate());
                    holidayService.update(holiday);
                    ++num;
                    logger.info(user.getUserId() + ",修改节假日(" + holiday.getHolidayName() + ") IP:" + ip);
                }
            }
        } catch (RuntimeException e) {
            if (e.getMessage().contains("格式"))
                fileVO.setResultMessage("要读取的Excel的格式不正确，请查检测格式！");
            logger.error(e);
            return ERROR;
        }
        fileVO.setResultMessage("成功导入物料信息" + count + "条记录，修改" + num + "条记录。");
        return SUCCESS;
    }

    @Resource(name = "holidayServiceImpl")
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }

    public String uploadAttachment(File file, String fileName, String path) {
        // 得到保存上传文件的目录的真实路径
        File dir = new File(path);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] temp = fileName.split("\\\\");
        fileName = temp[temp.length - 1];
        String name = ChangeTime.formatDate() + fileName;
        try {
            FileInputStream is = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(new File(dir, name));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException f) {
            logger.error(f);
        } catch (IOException ioe) {
            logger.error(ioe);
        }
        return name;
    }

}
