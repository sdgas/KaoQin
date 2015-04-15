package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sdgas.VO.FileVO;
import org.sdgas.model.Report;
import org.sdgas.service.ReportService;
import org.sdgas.util.ChangeCharset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-8-22
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */

//文件下载
@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<FileVO> {

    private final FileVO fileVO = new FileVO();
    private Logger logger = LogManager.getLogger(FileDownloadAction.class);
    private static String SAVE_PATH_DIR = "D:/kaoqin/downloadFile/";
    private ReportService reportService;

    private String contentType;
    private String fileName;

    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
    public InputStream getDownloadFile() throws Exception {

        Report report = reportService.fineByFileName(fileVO.getPath());
        String path = SAVE_PATH_DIR + fileVO.getPath();
        fileName = ChangeCharset.toGBK(fileVO.getPath());
        File file = new File(ChangeCharset.toGBK(path));
        return new FileInputStream(file);
    }

    @Override
    public String execute() throws Exception {
        this.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
        return SUCCESS;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }

    @Resource(name = "reportServiceImpl")
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}