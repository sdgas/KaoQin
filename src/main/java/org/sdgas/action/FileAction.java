package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.FileVO;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.ExcelUtil;
import org.sdgas.util.WebTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component("file")
@Scope("prototype")
public class FileAction extends MyActionSupport implements ModelDriven<FileVO> {

    @Qualifier("excelUtil")
    @Autowired
    private ExcelUtil excelUtil;
    private HolidayService holidayService;
    private ScheduleInfoService scheduleInfoService;
    private AnnualLeaveService annualLeaveService;
    private UserInfoService userInfoService;
    private DepartmentService departmentService;
    private ReportService reportService;

    private final static Logger logger = LogManager.getLogger(FileAction.class);
    private final FileVO fileVO = new FileVO();
    private List<Object> obj = new ArrayList<Object>();
    private List<Holiday> holidays = new ArrayList<Holiday>();
    private List<AnnualLeave> annualLeaves = new ArrayList<AnnualLeave>();
    private List<ScheduleInfo> scheduleInfos = new ArrayList<ScheduleInfo>();
    private int count = 0;
    private int num = 0;
    private static String SAVE_PATH_DIR_M = "D:/kaoqin/uploadFile/holiday/";
    private static String SAVE_PATH_DIR_S = "D:/kaoqin/uploadFile/Schedule/";
    private static String SAVE_PATH_DIR_A = "D:/kaoqin/uploadFile/annualLeave/";
    private static String SAVE_PATH_DIR = "D:/kaoqin/downloadFile/";

    //获取当前登录用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    Administrators user = (Administrators) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    //导入信息
    public String recoverExcelByHoliday() {
        count = 0;
        num = 0;
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
        fileVO.setResultMessage("成功导入假节日信息" + count + "条记录，修改" + num + "条记录。");
        return SUCCESS;
    }


    //导入信息
    public String recoverExcelByScheduleInfo() {
        count = 0;
        num = 0;
        String name = uploadAttachment(fileVO.getUploadFile(), fileVO.getFileName(), SAVE_PATH_DIR_S);
        try {
            obj = excelUtil.readExcelByPath(SAVE_PATH_DIR_S + name, ScheduleInfo.class, 0, 0);
            for (Object anObj : obj) {
                scheduleInfos.add((ScheduleInfo) anObj);
            }
            for (ScheduleInfo s : scheduleInfos) {
                ScheduleInfo scheduleInfo = scheduleInfoService.findByUserAndDate(s.getUserinfo(), s.getScheduleDate());
                if (scheduleInfo == null) {
                    scheduleInfo = new ScheduleInfo();
                    scheduleInfo.setUserinfo(s.getUserinfo());
                    USERINFO userinfo = userInfoService.find(USERINFO.class, s.getUserinfo());
                    scheduleInfo.setDepId(userinfo.getDEFAULTDEPTID());
                    scheduleInfo.setScheduleDate(s.getScheduleDate());
                    scheduleInfo.set_1st(s.get_1st());
                    scheduleInfo.set_2nd(s.get_2nd());
                    scheduleInfo.set_3rd(s.get_3rd());
                    scheduleInfo.set_4th(s.get_4th());
                    scheduleInfo.set_5th(s.get_5th());
                    scheduleInfo.set_6th(s.get_6th());
                    scheduleInfo.set_7th(s.get_7th());
                    scheduleInfo.set_8th(s.get_8th());
                    scheduleInfo.set_9th(s.get_9th());
                    scheduleInfo.set_10th(s.get_10th());
                    scheduleInfo.set_11st(s.get_11st());
                    scheduleInfo.set_12nd(s.get_12nd());
                    scheduleInfo.set_13rd(s.get_13rd());
                    scheduleInfo.set_14th(s.get_14th());
                    scheduleInfo.set_15th(s.get_15th());
                    scheduleInfo.set_16th(s.get_16th());
                    scheduleInfo.set_17th(s.get_17th());
                    scheduleInfo.set_18th(s.get_18th());
                    scheduleInfo.set_19th(s.get_19th());
                    scheduleInfo.set_20th(s.get_20th());
                    scheduleInfo.set_21st(s.get_21st());
                    scheduleInfo.set_22nd(s.get_22nd());
                    scheduleInfo.set_23rd(s.get_23rd());
                    scheduleInfo.set_24th(s.get_24th());
                    scheduleInfo.set_25th(s.get_25th());
                    scheduleInfo.set_26th(s.get_26th());
                    scheduleInfo.set_27th(s.get_27th());
                    scheduleInfo.set_28th(s.get_28th());
                    scheduleInfo.set_29th(s.get_29th());
                    scheduleInfo.set_30th(s.get_30th());
                    scheduleInfo.set_31st(s.get_31st());
                    scheduleInfoService.save(scheduleInfo);
                    count++;
                    logger.info("管理员：" + user.getUserId() + "，导入员工" + scheduleInfo.getUserinfo() + "的排班信息。IP：" + ip);
                } else {
                    scheduleInfo.set_1st(s.get_1st());
                    scheduleInfo.set_2nd(s.get_2nd());
                    scheduleInfo.set_3rd(s.get_3rd());
                    scheduleInfo.set_4th(s.get_4th());
                    scheduleInfo.set_5th(s.get_5th());
                    scheduleInfo.set_6th(s.get_6th());
                    scheduleInfo.set_7th(s.get_7th());
                    scheduleInfo.set_8th(s.get_8th());
                    scheduleInfo.set_9th(s.get_9th());
                    scheduleInfo.set_10th(s.get_10th());
                    scheduleInfo.set_11st(s.get_11st());
                    scheduleInfo.set_12nd(s.get_12nd());
                    scheduleInfo.set_13rd(s.get_13rd());
                    scheduleInfo.set_14th(s.get_14th());
                    scheduleInfo.set_15th(s.get_15th());
                    scheduleInfo.set_16th(s.get_16th());
                    scheduleInfo.set_17th(s.get_17th());
                    scheduleInfo.set_18th(s.get_18th());
                    scheduleInfo.set_19th(s.get_19th());
                    scheduleInfo.set_20th(s.get_20th());
                    scheduleInfo.set_21st(s.get_21st());
                    scheduleInfo.set_22nd(s.get_22nd());
                    scheduleInfo.set_23rd(s.get_23rd());
                    scheduleInfo.set_24th(s.get_24th());
                    scheduleInfo.set_25th(s.get_25th());
                    scheduleInfo.set_26th(s.get_26th());
                    scheduleInfo.set_27th(s.get_27th());
                    scheduleInfo.set_28th(s.get_28th());
                    scheduleInfo.set_29th(s.get_29th());
                    scheduleInfo.set_30th(s.get_30th());
                    scheduleInfo.set_31st(s.get_31st());
                    scheduleInfoService.update(scheduleInfo);
                    num++;
                    logger.info("管理员：" + user.getUserId() + "，修改员工" + scheduleInfo.getUserinfo() + "的排班信息。IP：" + ip);
                }
            }
        } catch (RuntimeException e) {
            if (e.getMessage().contains("格式"))
                fileVO.setResultMessage("要读取的Excel的格式不正确，请查检测格式！");
            else if (e.getMessage().contains("信息"))
                fileVO.setResultMessage(e.getMessage());
            logger.error(e);
            return ERROR;
        }

        fileVO.setResultMessage("成功导入排班信息" + count + "条记录，修改" + num + "条记录。");
        return SUCCESS;
    }


    //导入年假信息
    public String recoverExcelByAnnualLeave() {
        count = 0;
        num = 0;
        String name = uploadAttachment(fileVO.getUploadFile(), fileVO.getFileName(), SAVE_PATH_DIR_A);
        try {
            obj = excelUtil.readExcelByPath(SAVE_PATH_DIR_A + name, AnnualLeave.class, 0, 0);
            for (Object anObj : obj) {
                annualLeaves.add((AnnualLeave) anObj);
            }

            for (AnnualLeave a : annualLeaves) {
                AnnualLeave annualLeave = annualLeaveService.findByUser(a.getUserId());
                if (annualLeave == null) {
                    annualLeave = new AnnualLeave();
                    annualLeave.setUserId(a.getUserId());
                    annualLeave.setYear(a.getYear());
                    annualLeave.setDays(a.getDays());
                    annualLeaveService.save(annualLeave);
                    count++;
                    logger.info("管理员：" + user.getUserId() + "导入员工：" + a.getUserId() + "年假信息");
                } else {
                    annualLeave.setUserId(a.getUserId());
                    annualLeave.setYear(a.getYear());
                    annualLeave.setDays(a.getDays());
                    annualLeaveService.update(annualLeave);
                    num++;
                    logger.info("管理员：" + user.getUserId() + "修改员工：" + a.getUserId() + "年假信息");
                }

            }
        } catch (RuntimeException e) {
            if (e.getMessage().contains("格式"))
                fileVO.setResultMessage("要读取的Excel的格式不正确，请查检测格式！");
            logger.error(e);
            return ERROR;
        }

        fileVO.setResultMessage("成功导入假节日信息" + count + "条记录，修改" + num + "条记录。");
        return SUCCESS;
    }

    //生成月报表
    public String createExcelBySch() throws UnsupportedEncodingException {
        List<ScheduleInfo> scheduleInfos = new ArrayList<ScheduleInfo>();
        // 得到备份文件的目录的真实路径
        File dir = new File(SAVE_PATH_DIR);
        // 如果该目录不存在，就创建
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，从0开始的

        String tem[] = fileVO.getDepS().split(",");
        int depId;
        if (tem[1].trim().isEmpty())
            depId = departmentService.findByDepName(tem[0]).getDEPTID();
        else
            depId = departmentService.findByID(Integer.valueOf(tem[1].trim())).getDEPTID();

        if (month < 10)
            scheduleInfos = scheduleInfoService.findByDepAndDate(depId, year + "0" + month);
        else
            scheduleInfos = scheduleInfoService.findByDepAndDate(depId, year + "" + month);
        // ScheduleInfo sc = scheduleInfoService.find(ScheduleInfo.class, 21);
        List<USERINFO> userinfos = userInfoService.findByDep(depId);
        for (USERINFO userinfo : userinfos) {
            boolean flag = false;
            for (ScheduleInfo scheduleInfo : scheduleInfos) {
                if (scheduleInfo.getUserinfo() == userinfo.getUSERID())
                    flag = true;
            }
            if (!flag || (depId != 10 && depId != 6)) {
                ScheduleInfo scheduleInfo = createS(userinfo.getUSERID(), userinfo.getDEFAULTDEPTID(), year, month);
                scheduleInfos.add(scheduleInfo);
            }
        }

        String date = ChangeTime.formatDate(ChangeTime.getCurrentDate());
        String dep = departmentService.findByID(depId).getDEPTNAME();
        String fileName = date + dep + ".xlsx";
        String path = SAVE_PATH_DIR + fileName;
        //使用于07以上的版本，03以下的可以修改参数
        excelUtil.exportExcelByPath(path, scheduleInfos, ScheduleInfo.class, false, date, dep);

        Report report = new Report();
        report.setFilePath(fileName);
        report.setReportDate(year + "" + (month > 10 ? month : "0" + month));
        report.setDep(depId);
        reportService.save(report);

        logger.info("管理员：" + user.getUserId() + "成功生成考勤信息文件！文件名为:" + fileName);
        fileVO.setResultMessage("<script>alert('成功生成考勤信息文件:" + fileName + "。请点击确认下载');location.href='FileDownload.action?path=" + fileName + "';</script>");
        return SUCCESS;
    }

    private ScheduleInfo createS(int user, int dep, int year, int month) {
        int days = WebTool.calDayByYearAndMonth(String.valueOf(year), String.valueOf(month));
        ScheduleInfo scheduleInfo = new ScheduleInfo();

        scheduleInfo.set_1st(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-01"));
        scheduleInfo.set_2nd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-02"));
        scheduleInfo.set_3rd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-03"));
        scheduleInfo.set_4th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-04"));
        scheduleInfo.set_5th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-05"));
        scheduleInfo.set_6th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-06"));
        scheduleInfo.set_7th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-07"));
        scheduleInfo.set_8th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-08"));
        scheduleInfo.set_9th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-09"));
        scheduleInfo.set_10th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-10"));
        scheduleInfo.set_11st(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-11"));
        scheduleInfo.set_12nd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-12"));
        scheduleInfo.set_13rd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-13"));
        scheduleInfo.set_14th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-14"));
        scheduleInfo.set_15th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-15"));
        scheduleInfo.set_16th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-16"));
        scheduleInfo.set_17th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-17"));
        scheduleInfo.set_18th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-18"));
        scheduleInfo.set_19th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-19"));
        scheduleInfo.set_20th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-20"));
        month = month - 1;
        scheduleInfo.set_21st(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-21"));
        scheduleInfo.set_22nd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-22"));
        scheduleInfo.set_23rd(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-23"));
        scheduleInfo.set_24th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-24"));
        scheduleInfo.set_25th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-25"));
        scheduleInfo.set_26th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-26"));
        scheduleInfo.set_27th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-27"));
        scheduleInfo.set_28th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-28"));
        scheduleInfo.set_29th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-29"));
        scheduleInfo.set_30th(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-30"));
        scheduleInfo.set_31st(getPeriodId(year + "-" + (month > 10 ? month : "0" + month) + "-31"));

        if (28 == days) {
            scheduleInfo.set_29th(0);
            scheduleInfo.set_30th(0);
            scheduleInfo.set_31st(0);
        } else if (29 == days) {
            scheduleInfo.set_30th(0);
            scheduleInfo.set_31st(0);
        } else if (30 == days)
            scheduleInfo.set_31st(0);
        String before = year + "-" + (month > 10 ? month : "0" + month) + "-21";
        month = month + 1;
        String after = year + "-" + (month > 10 ? month : "0" + month) + "-20";

        scheduleInfo.setDepId(dep);
        scheduleInfo.setUserinfo(user);
        scheduleInfo.setScheduleDate(year + "" + month);
        return scheduleInfo;
    }

    private int getPeriodId(String date) {
        if (WebTool.getWeekOfDate(date) == "六" || WebTool.getWeekOfDate(date) == "日")
            return 0;
        else
            return 21;
    }

    @Resource(name = "holidayServiceImpl")
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Resource(name = "scheduleInfoServiceImpl")
    public void setScheduleInfoService(ScheduleInfoService scheduleInfoService) {
        this.scheduleInfoService = scheduleInfoService;
    }

    @Resource(name = "annualLeaveServiceImpl")
    public void setAnnualLeaveService(AnnualLeaveService annualLeaveService) {
        this.annualLeaveService = annualLeaveService;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Resource(name = "departmentServiceImpl")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Resource(name = "reportServiceImpl")
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
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
