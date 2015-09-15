package org.sdgas.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.sdgas.model.*;
import org.sdgas.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;


/**
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 *
 * @author wilson.he
 */

@Service
@Transactional
public class ExcelUtil {

    private static ExcelUtil eu = new ExcelUtil();
    private final static Logger logger = Logger.getLogger(ExcelUtil.class);

    private DepartmentService departmentService;
    private PeriodService periodService;
    private ScheduleInfoService scheduleInfoService;
    private UserInfoService userInfoService;
    private OverTimeService overTimeService;
    private VacationInfoService vacationInfoService;
    private CheckInOutService checkInOutService;
    private HolidayService holidayService;


    /**
     * 根据标题获取相应的方法名称
     *
     * @param eh
     * @return
     */
    private String getMethodName(ExcelHeader eh) {
        String mn = eh.getMethodName().substring(3);
        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
        return mn;
    }

    //中晚班补贴报表
    public void createExcel(String dep, String outPath) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("中晚班补贴");   //取excel工作表对象

        //设置默认列宽
        sheet.setDefaultColumnWidth(14);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 4));
        Row r0 = sheet.createRow(0);
        Cell cell = r0.createCell(0);
        r0.setHeightInPoints(28);

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，从0开始的

        cell.setCellValue(year + "年" + month + "月 " + dep + "中晚班补贴");
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 16); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度

        XSSFCellStyle cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cell.setCellStyle(cellStyle);

        //设置单元格样式
        font = wb.createFont();
        font.setFontHeightInPoints((short) 12); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); // 宽度
        cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        Row r = sheet.createRow(1);
        cell = r.createCell(0);
        cell.setCellValue("工号");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(2);
        cell.setCellValue("补贴天数/天");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(3);
        cell.setCellValue("补贴标准/元");
        cell.setCellStyle(cellStyle);

        cell = r.createCell(4);
        cell.setCellValue("补贴金额/元");
        cell.setCellStyle(cellStyle);

        int depId = departmentService.findByDepName(dep).getDEPTID();
        String date = month >= 10 ? year + "" + month : year + "0" + month;
        List<ScheduleInfo> scheduleInfos = scheduleInfoService.findByDepAndDate(depId, date);

        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        int count = 2;
        for (ScheduleInfo sc : scheduleInfos) {
            USERINFO userinfo = userInfoService.find(USERINFO.class, sc.getUserinfo());
            r = sheet.createRow(count);

            int num = 0;
            --month;
            int monthDays = WebTool.calDayByYearAndMonth(year + "", month + "");
            ++month;
            Integer[] s = this.change(sc, monthDays);
            for (int i = 0; i < s.length; i++) {
                switch (s[i]) {
                    case 8:
                    case 9:
                    case 11:
                    case 12:
                    case 15:
                    case 17:
                    case 19:
                    case 10:
                        num++;
                        break;
                    default:
                        break;
                }
            }
            if (num != 0) {
                cell = r.createCell(0);
                cell.setCellValue(userinfo.getBADGENUMBER());
                cell.setCellStyle(cs);

                cell = r.createCell(1);
                cell.setCellValue(userinfo.getNAME());
                cell.setCellStyle(cs);

                cell = r.createCell(2);
                cell.setCellType(CELL_TYPE_NUMERIC);
                cell.setCellValue(num);
                cell.setCellStyle(cs);

                cell = r.createCell(3);
                cell.setCellValue("15");
                cell.setCellStyle(cs);

                cell = r.createCell(4);
                cell.setCellStyle(cs);
                cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                String exp = "C" + (count + 1) + "*15";
                cell.setCellFormula(exp);
                count++;
            }
        }

        //创建excel，并保存
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    //考勤报表导出
    private Workbook handleExcel(List objs, Class clz, boolean isXssf, String message, String dep) {
        XSSFWorkbook wb = new XSSFWorkbook();
        int count = 0;

        XSSFSheet sheet = wb.createSheet("考勤月报表");   //取excel工作表对象

        XSSFPrintSetup ps = sheet.getPrintSetup();
        ps.setLandscape(true); //打印方向，true：横向，false：纵向(默认)
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张类型

        List<ExcelHeader> headers = getHeaderList(clz);
        Collections.sort(headers);

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) (headers.size() + 8)));
        Row r0 = sheet.createRow(count);
        Cell cell = r0.createCell(0);
        r0.setHeightInPoints(28);

        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，从0开始的

        cell.setCellValue("佛山市顺德区港华燃气有限公司" + year + "年" + month + "月 " + dep + "考勤月报表");
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 20); // 字体高度
        font.setFontName("宋体"); // 字体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 宽度

        XSSFCellStyle cellStyle = wb.createCellStyle(); //设置excel单元格样式
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderRight((short) 1);
        cell.setCellStyle(cellStyle);

        Row r = sheet.createRow(++count);
        r.setHeightInPoints(25);

        cellStyle = wb.createCellStyle();
        Cell c = r.createCell(1);
        c.setCellValue("日期");

        --month;
        int cellNum = 2;
        int monthDays = WebTool.calDayByYearAndMonth(year + "", month + "");
        for (int j = 1; j < headers.size() - 1; j++) {
            int num = Integer.valueOf(this.matchNum(headers.get(j).getTitle()));
            Cell c1 = r.createCell(cellNum++);
            c1.setCellStyle(cellStyle);
            String week = WebTool.getWeekOfDate(year + "-" + month + "-" + num);
            c1.setCellValue(week);
            if (num == monthDays && monthDays == 30) {
                ++month;
                j++;
            } else if (num == monthDays && monthDays == 31) {
                ++month;
            } else if (num == monthDays && monthDays == 29) {
                ++month;
                j = j + 2;
            } else if (num == monthDays && monthDays == 28) {
                ++month;
                j = j + 3;
            }
        }

        r = sheet.createRow(++count);
        c = r.createCell(0);
        c.setCellStyle(cellStyle);
        c.setCellValue("姓名");

        //输出标题
        int i = 1;
        --month;
        cellNum = 2;
        for (; i < headers.size() - 1; i++) {
            int num = Integer.valueOf(this.matchNum(headers.get(i).getTitle()));
            Cell c1 = r.createCell(cellNum++);

            c1.setCellStyle(cellStyle);
            c1.setCellValue(num);
            if (num == monthDays && monthDays == 30) {
                ++month;
                i++;
            } else if (num == monthDays && monthDays == 31) {
                ++month;
            } else if (num == monthDays && monthDays == 29) {
                ++month;
                i = i + 2;
            } else if (num == monthDays && monthDays == 28) {
                ++month;
                i = i + 3;
            }
        }

        i = cellNum - 1;//使i值等于当前单元格位置
        c = sheet.getRow(1).createCell(i + 1);
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cs.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cs.setBorderTop((short) 1);
        cs.setBorderBottom((short) 1);
        cs.setBorderLeft((short) 1);
        cs.setBorderRight((short) 1);
        c.setCellStyle(cs);
        c.setCellValue("合计");
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, i + 1, (short) i + 8));

        String type[] = {"病假天数", "事假天数", "年假天数", "平时加班", "周末加班", "假日加班", "补休小时", "出勤天数"};
        for (String tep : type) {
            c = sheet.getRow(count).createCell(++i);
            c.setCellStyle(cs);
            c.setCellValue(tep);
        }

        c = sheet.getRow(1).createCell(++i);
        c.setCellStyle(cs);
        c.setCellValue("签名确认");
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 2, i, (short) i));
        int sick = 4;//病假取值范围
        for (ScheduleInfo s : (List<ScheduleInfo>) objs) {
            Integer sc[] = change(s, monthDays);
            USERINFO userinfo = userInfoService.find(USERINFO.class, s.getUserinfo());
            r = sheet.createRow(++count);
            c = r.createCell(0);
            c.setCellStyle(cs);
            c.setCellValue(userinfo.getNAME());

            c = r.createCell(1);
            c.setCellStyle(cs);
            c.setCellValue("上午");

            r = sheet.createRow(++count);
            c = r.createCell(1);
            c.setCellStyle(cs);
            c.setCellValue("下午");

            r = sheet.createRow(++count);
            c = r.createCell(1);
            c.setCellStyle(cs);
            c.setCellValue("加班");

            r = sheet.createRow(++count);
            c = r.createCell(1);
            c.setCellStyle(cs);
            c.setCellValue("补休");

            //姓名列合并单元格
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, 0, (short) 0));

            //合计列合并单元格
            c = sheet.getRow(count - 3).createCell(monthDays + 2);
            c.setCellType(XSSFCell.CELL_TYPE_FORMULA);
            String exp = "";
            if (monthDays == 28)
                exp = "COUNTIF(C" + sick + ":AD" + (sick + 1) + ",\"B\")/2";
            else if (monthDays == 29)
                exp = "COUNTIF(C" + sick + ":AE" + (sick + 1) + ",\"B\")/2";
            else if (monthDays == 30)
                exp = "COUNTIF(C" + sick + ":AF" + (sick + 1) + ",\"B\")/2";
            else if (monthDays == 31)
                exp = "COUNTIF(C" + sick + ":AG" + (sick + 1) + ",\"B\")/2";
            c.setCellFormula(exp);
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 2, (short) (monthDays + 2)));//病假天数

            c = sheet.getRow(count - 3).createCell(monthDays + 3);
            c.setCellType(XSSFCell.CELL_TYPE_FORMULA);
            if (monthDays == 28)
                exp = "COUNTIF(C" + sick + ":AD" + (sick + 1) + ",\"S\")/2";
            else if (monthDays == 29)
                exp = "COUNTIF(C" + sick + ":AE" + (sick + 1) + ",\"S\")/2";
            else if (monthDays == 30)
                exp = "COUNTIF(C" + sick + ":AF" + (sick + 1) + ",\"S\")/2";
            else if (monthDays == 31)
                exp = "COUNTIF(C" + sick + ":AG" + (sick + 1) + ",\"S\")/2";
            c.setCellFormula(exp);
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 3, (short) (monthDays + 3)));//事假天数

            c = sheet.getRow(count - 3).createCell(monthDays + 4);
            c.setCellType(XSSFCell.CELL_TYPE_FORMULA);
            if (monthDays == 28)
                exp = "COUNTIF(C" + sick + ":AD" + (sick + 1) + ",\"A\")/2";
            else if (monthDays == 29)
                exp = "COUNTIF(C" + sick + ":AE" + (sick + 1) + ",\"A\")/2";
            else if (monthDays == 30)
                exp = "COUNTIF(C" + sick + ":AF" + (sick + 1) + ",\"A\")/2";
            else if (monthDays == 31)
                exp = "COUNTIF(C" + sick + ":AG" + (sick + 1) + ",\"A\")/2";
            c.setCellFormula(exp);
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 4, (short) (monthDays + 4)));//年假天数

            /*各类型加班*/
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 5, (short) (monthDays + 5)));//平时加班
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 6, (short) (monthDays + 6)));//周末加班
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 7, (short) (monthDays + 7)));//假日加班

            c = sheet.getRow(count - 3).createCell(monthDays + 8);
            c.setCellType(XSSFCell.CELL_TYPE_FORMULA);

            if (monthDays == 28)
                exp = "SUM(B" + (count + 1) + ":AD" + (count + 1) + ")";
            else if (monthDays == 29)
                exp = "SUM(B" + (count + 1) + ":AE" + (count + 1) + ")";
            else if (monthDays == 30)
                exp = "SUM(B" + (count + 1) + ":AG" + (count + 1) + ")";
            else if (monthDays == 31)
                exp = "SUM(B" + (count + 1) + ":AG" + (count + 1) + ")";
            c.setCellFormula(exp);
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 8, (short) (monthDays + 8)));//补休小时

            c = sheet.getRow(count - 3).createCell(monthDays + 9);
            c.setCellType(XSSFCell.CELL_TYPE_FORMULA);

            if (monthDays == 28)
                exp = "COUNTIF(C" + sick + ":AD" + (sick + 1) + ",\"√\")/2";
            else if (monthDays == 29)
                exp = "COUNTIF(C" + sick + ":AE" + (sick + 1) + ",\"√\")/2";
            else if (monthDays == 30)
                exp = "COUNTIF(C" + sick + ":AF" + (sick + 1) + ",\"√\")/2";
            else if (monthDays == 31)
                exp = "COUNTIF(C" + sick + ":AG" + (sick + 1) + ",\"√\")/2";
            c.setCellFormula(exp);
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 9, (short) (monthDays + 9)));//出勤天数
            sheet.addMergedRegion(new CellRangeAddress(count - 3, (short) count, monthDays + 10, (short) (monthDays + 10)));//签名确认
            sick += 4;
            int d = 16;
            --month;

            String before = year + "-" + month + "-16";
            String after = year + "-" + (month + 1) + "-15";
            List<Holiday> holidays = holidayService.findByDate(before, after); //考勤月的节假日
            int num = 2;//单元格数目

            double pingshi = 0;//平时加班
            double zhoumo = 0;//周末加班
            double jiari = 0;//节假日加班

            for (int j = 0; j < monthDays; j++) {

                Period period = periodService.find(Period.class, sc[j]); //当日排班情况
                String day = month > 10 ? year + "-" + month : year + "-0" + month;
                List<CHECKINOUT> checkinouts = checkInOutService.findByUserAndDate(userinfo.getUSERID(), day, d);  //当日打卡情况
                day = d >= 10 ? day + "-" + d : day + "-0" + d;
                List<Overtime> overtime = overTimeService.findByUserAndDate(userinfo.getUSERID(), day); //当日加班情况
                List<VacationInfo> vacationInfos = vacationInfoService.findByUserAndDate(userinfo.getUSERID(), day);  //当日休假情况

                String msg[] = just(holidays, checkinouts, period, overtime, vacationInfos, day);

                //上午
                r = sheet.getRow(count - 3);
                c = r.createCell(num);
                c.setCellStyle(cs);
                c.setCellValue(msg[0]);

                //下午
                r = sheet.getRow(count - 2);
                c = r.createCell(num);
                c.setCellStyle(cs);
                c.setCellValue(msg[1]);

                //加班时间
                r = sheet.getRow(count - 1);
                c = r.createCell(num);
                c.setCellStyle(cs);
                if (!msg[2].trim().isEmpty())
                    c.setCellValue(Double.valueOf(msg[2]));

                //补休
                r = sheet.getRow(count);
                c = r.createCell(num);
                c.setCellStyle(cs);
                if (!msg[3].trim().isEmpty())
                    c.setCellValue(Double.valueOf(msg[3]));

                pingshi = pingshi + Double.valueOf(msg[4]);
                zhoumo = zhoumo + Double.valueOf(msg[5]);
                jiari = jiari + Double.valueOf(msg[6]);

                ++num;
                int days = WebTool.calDayByYearAndMonth(year + "", month + "");
                //下一个日期

                if (d == days) {
                    d = 1;
                    month += 1;
                } else d += 1;
            }

            cs = wb.createCellStyle();
            cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cs.setAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cs.setBorderTop((short) 1);
            cs.setBorderBottom((short) 1);
            cs.setBorderLeft((short) 1);
            cs.setBorderRight((short) 1);

            r = sheet.getRow(count - 3);
            c = r.createCell(monthDays + 5);
            c.setCellStyle(cs);
            c.setCellValue(pingshi);

            c = r.createCell(monthDays + 6);
            c.setCellStyle(cs);
            c.setCellValue(zhoumo);

            c = r.createCell(monthDays + 7);
            c.setCellStyle(cs);
            c.setCellValue(jiari);
        }

        r = sheet.createRow(count + 1);
        c = r.createCell(0);
        c.setCellStyle(cs);
        c.setCellValue("备注：加班以小时为单位 出勤√ 迟到★ 早退▲ 年假A 病假B 丧假C 婚假D 产假E 未打卡F 补休G 陪产假H 事假S 工伤+ 补休G  学习X ");
        sheet.addMergedRegion(new CellRangeAddress((count + 1), (short) (count + 1), 0, (short) (monthDays + 7)));

        r = sheet.createRow(count + 2);
        c = r.createCell(3);
        c.setCellStyle(cs);
        c.setCellValue("制表人：");
        sheet.addMergedRegion(new CellRangeAddress((count + 2), (short) (count + 2), 3, 5));

        c = r.createCell(7);
        c.setCellStyle(cs);
        c.setCellValue("部门负责人：");
        sheet.addMergedRegion(new CellRangeAddress((count + 2), (short) (count + 2), 7, 11));

        c = r.createCell(15);
        c.setCellStyle(cs);
        c.setCellValue("分管领导审核：");
        sheet.addMergedRegion(new CellRangeAddress((count + 2), (short) (count + 2), 15, 20));

        //自动调整宽度
        for (int a = 2; a < 45; a++) {
            sheet.autoSizeColumn((short) a);
        }

        sheet.setMargin(XSSFSheet.TopMargin, 0);// 页边距（上）
        sheet.setMargin(XSSFSheet.BottomMargin, 0);// 页边距（下）
        sheet.setMargin(XSSFSheet.LeftMargin, 0);// 页边距（左）
        sheet.setMargin(XSSFSheet.RightMargin, 0);// 页边距（右

        return wb;
    }

    private String[] just(List<Holiday> holidays, List<CHECKINOUT> checkinouts, Period period, List<Overtime> overtime, List<VacationInfo> vacationInfo, String today) {
        String msg[] = {"", "", "", "", "0", "0", "0"};//上午，下午，加班，补休，平时，周末，假日

        if (period == null) {
            if (overtime.size() == 0) {
                return msg;
            } else {
                double temp = 0;
                for (Overtime ot : overtime) {
                    temp = temp + ot.getLongTime();
                }
                msg[2] = temp == 0 ? "" : String.valueOf(temp);
                // return msg;
            }
        } else {

            if (overtime != null) {
                double temp = 0;
                for (Overtime ot : overtime) {
                    temp = temp + ot.getLongTime();
                }
                msg[2] = temp == 0 ? "" : String.valueOf(temp);
            }

            int size = checkinouts.size();
            if (size > 0) {
                //08:30~17:30
                String periodCome = period.getPeriod().split("~")[0];//上班时间
                String periodGo = period.getPeriod().split("~")[1];//下班时间
                if (size == 2) {
                    String in = ChangeTime.formatWholeDate(checkinouts.get(0).getCHECKTIME()).substring(11, 19);
                    String out = ChangeTime.formatWholeDate(checkinouts.get(1).getCHECKTIME()).substring(11, 19);
                    //判断迟到
                    if (in.compareToIgnoreCase(periodCome) <= 0)
                        msg[0] = "√";
                    else
                        msg[0] = "★";
                    //判断早退
                    if (out.compareToIgnoreCase(periodGo) >= 0)
                        msg[1] = "√";
                    else
                        msg[1] = "▲";
                    for (VacationInfo v : vacationInfo) {
                        if (0.5 == v.getLongTime()) {
                            if (v.getRemarks().contains("上午"))
                                msg[0] = v.getVacationSymbol();
                            else
                                msg[1] = v.getVacationSymbol();
                        } else {
                            msg[0] = v.getVacationSymbol();
                            msg[1] = v.getVacationSymbol();
                        }
                        if ("G".equals(v.getVacationSymbol())) {
                            msg[3] = String.valueOf(v.getLongTime() * 7);
                        }
                    }
                } else {
                    for (CHECKINOUT checkinout : checkinouts) {
                        String date = ChangeTime.formatWholeDate(checkinout.getCHECKTIME()).substring(11, 19);
                        if (date.compareToIgnoreCase(periodCome) <= 0 && date.compareToIgnoreCase(periodGo) < 0) {
                            msg[0] = msg[0] == "" || msg[0] == "F" ? "√" : msg[0];
                            msg[1] = msg[1] == "" || msg[1] == "F" ? "F" : msg[1];
                        } else if (date.compareToIgnoreCase(periodCome) > 0 && date.compareToIgnoreCase(periodGo) >= 0) {
                            msg[1] = msg[1] == "" || msg[1] == "F" ? "√" : msg[1];
                            msg[0] = msg[0] == "" || msg[0] == "F" ? "F" : msg[0];
                        } else if (date.compareToIgnoreCase(periodCome) > 0 && date.compareToIgnoreCase(periodGo) < 0) {
                            msg[0] = msg[0] == "" || msg[0] == "F" ? "★" : msg[0];
                            msg[1] = msg[1] == "" || msg[1] == "F" ? "F" : msg[1];
                        } else if (date.compareToIgnoreCase(periodCome) > 0 && date.compareToIgnoreCase(periodGo) < 0) {
                            msg[0] = msg[0] == "" || msg[0] == "F" ? "F" : msg[0];
                            msg[1] = msg[1] == "" || msg[1] == "F" ? "▲" : msg[1];
                        } else {
                            msg[0] = "异常";
                            msg[1] = "异常";
                        }
                    }
                    for (VacationInfo v : vacationInfo) {
                        if (0.5 == v.getLongTime()) {
                            if (v.getRemarks().contains("上午"))
                                msg[0] = v.getVacationSymbol();
                            else
                                msg[1] = v.getVacationSymbol();
                        } else {
                            msg[0] = v.getVacationSymbol();
                            msg[1] = v.getVacationSymbol();
                        }
                        if ("G".equals(v.getVacationSymbol())) {
                            msg[3] = String.valueOf(v.getLongTime() * 7);
                        }
                    }
                }
            } else {
                if (vacationInfo == null) {
                    if (holidays.size() < 0) {
                        msg[0] = "未上班/打卡";
                        msg[1] = "未上班/打卡";
                    }
                } else {
                    for (VacationInfo v : vacationInfo) {
                        if (0.5 == v.getLongTime()) {
                            if (v.getRemarks().contains("上午"))
                                msg[0] = v.getVacationSymbol();
                            else
                                msg[1] = v.getVacationSymbol();
                        } else {
                            msg[0] = v.getVacationSymbol();
                            msg[1] = v.getVacationSymbol();
                        }
                        if ("G".equals(v.getVacationSymbol())) {
                            msg[3] = String.valueOf(v.getLongTime() * 7);
                        }
                    }
                }
            }
        }

        double pingshi = 0;//平时加班
        double zhoumo = 0;//周末加班
        double jiari = 0;//节假日加班

        if (overtime.size() >= 1) {

            for (Overtime ot : overtime) {
                if (period != null) {
                    if (holidays.size() < 1)
                        pingshi = pingshi + ot.getLongTime();
                    else {
                        boolean f = false;
                        for (Holiday holiday : holidays) {
                            String hd = ChangeTime.formatRealDate(holiday.getHoliday());
                            if ("国庆".equals(holiday.getHolidayName()) || "春节".equals(holiday.getHolidayName())) {

                                String[] days = new String[3];
                                int d = Integer.valueOf(hd.split("-")[2]);
                                String ym = hd.split("-")[0] + "-" + hd.split("-")[1] + "-";
                                for (int i = 0; i < 3; i++) {
                                    days[i] = d < 10 ? ym + "0" + d : ym + d;
                                    d = d + 1;
                                }
                                for (String s : days) {
                                    if (today.equals(s)) {
                                        jiari = jiari + ot.getLongTime();
                                        f = true;
                                        break;
                                    }
                                }
                            } else if (hd.equals("2015-09-03")) {
                                zhoumo = zhoumo + ot.getLongTime();
                                f = true;
                            } else if (today.equals(hd)) {
                                jiari = jiari + ot.getLongTime();
                                f = true;
                            }
                        }
                        if (!f)
                            pingshi = pingshi + ot.getLongTime();
                    }
                } else {
                    if (holidays.size() < 1) {
                        zhoumo = zhoumo + ot.getLongTime();
                    } else {
                        for (Holiday holiday : holidays) {
                            String hd = ChangeTime.formatRealDate(holiday.getHoliday());
                            if ("国庆".equals(holiday.getHolidayName()) || "春节".equals(holiday.getHolidayName())) {

                                String[] days = new String[3];
                                int d = Integer.valueOf(hd.split("-")[2]);
                                String ym = hd.split("-")[0] + "-" + hd.split("-")[1] + "-";
                                for (int i = 0; i < 3; i++) {
                                    days[i] = d < 10 ? ym + "0" + d : ym + d;
                                    d = d + 1;
                                }

                                boolean f2 = false;
                                for (String s : days) {
                                    if (today.equals(s)) {
                                        jiari = jiari + ot.getLongTime();
                                        f2 = true;
                                        break;
                                    }
                                }
                                if (!f2)
                                    zhoumo = zhoumo + ot.getLongTime();

                            } else if (today.equals(hd) && !hd.equals("2015-09-03"))
                                jiari = jiari + ot.getLongTime();
                            else
                                zhoumo = zhoumo + ot.getLongTime();
                        }
                    }
                }
            }
        } else {
            for (Holiday holiday : holidays) {
                String hd = ChangeTime.formatRealDate(holiday.getHoliday());
                if (today.equals(hd) & hd.equals("2015-09-03"))
                    zhoumo = zhoumo + 7;
                else if ("国庆".equals(holiday.getHolidayName()) || "春节".equals(holiday.getHolidayName())) {

                    String[] days = new String[3];
                    int d = Integer.valueOf(hd.split("-")[2]);
                    String ym = hd.split("-")[0] + "-" + hd.split("-")[1] + "-";
                    for (int i = 0; i < 3; i++) {
                        days[i] = d < 10 ? ym + "0" + d : ym + d;
                        d = d + 1;
                    }
                    for (String s : days) {
                        if (today.equals(s)) {
                            jiari = jiari + 7;
                            break;
                        }
                    }
                } else if (today.equals(hd))
                    jiari = jiari + 7;
            }
        }
        msg[4] = String.valueOf(pingshi);
        msg[5] = String.valueOf(zhoumo);
        msg[6] = String.valueOf(jiari);

        return msg;
    }

    private Integer[] change(ScheduleInfo scheduleInfo, int monthdays) {
        Integer str[] = new Integer[0];
        if (monthdays == 28) {
            str = new Integer[28];
            str[5] = scheduleInfo.get_21st();
            str[5] = scheduleInfo.get_21st();
            str[6] = scheduleInfo.get_22nd();
            str[7] = scheduleInfo.get_23rd();
            str[8] = scheduleInfo.get_24th();
            str[9] = scheduleInfo.get_25th();
            str[10] = scheduleInfo.get_26th();
            str[11] = scheduleInfo.get_27th();
            str[12] = scheduleInfo.get_28th();
            str[13] = scheduleInfo.get_1st();
            str[14] = scheduleInfo.get_2nd();
            str[15] = scheduleInfo.get_3rd();
            str[16] = scheduleInfo.get_4th();
            str[17] = scheduleInfo.get_5th();
            str[18] = scheduleInfo.get_6th();
            str[19] = scheduleInfo.get_7th();
            str[20] = scheduleInfo.get_8th();
            str[21] = scheduleInfo.get_9th();
            str[22] = scheduleInfo.get_10th();
            str[23] = scheduleInfo.get_11st();
            str[24] = scheduleInfo.get_12nd();
            str[25] = scheduleInfo.get_13rd();
            str[26] = scheduleInfo.get_14th();
            str[27] = scheduleInfo.get_15th();
            str[0] = scheduleInfo.get_16th();
            str[1] = scheduleInfo.get_17th();
            str[2] = scheduleInfo.get_18th();
            str[3] = scheduleInfo.get_19th();
            str[4] = scheduleInfo.get_20th();
        } else if (monthdays == 29) {
            str = new Integer[29];
            str[5] = scheduleInfo.get_21st();
            str[6] = scheduleInfo.get_22nd();
            str[7] = scheduleInfo.get_23rd();
            str[8] = scheduleInfo.get_24th();
            str[9] = scheduleInfo.get_25th();
            str[10] = scheduleInfo.get_26th();
            str[11] = scheduleInfo.get_27th();
            str[12] = scheduleInfo.get_28th();
            str[13] = scheduleInfo.get_29th();
            str[14] = scheduleInfo.get_1st();
            str[15] = scheduleInfo.get_2nd();
            str[16] = scheduleInfo.get_3rd();
            str[17] = scheduleInfo.get_4th();
            str[18] = scheduleInfo.get_5th();
            str[19] = scheduleInfo.get_6th();
            str[20] = scheduleInfo.get_7th();
            str[21] = scheduleInfo.get_8th();
            str[22] = scheduleInfo.get_9th();
            str[23] = scheduleInfo.get_10th();
            str[24] = scheduleInfo.get_11st();
            str[25] = scheduleInfo.get_12nd();
            str[26] = scheduleInfo.get_13rd();
            str[27] = scheduleInfo.get_14th();
            str[28] = scheduleInfo.get_15th();
            str[0] = scheduleInfo.get_16th();
            str[1] = scheduleInfo.get_17th();
            str[2] = scheduleInfo.get_18th();
            str[3] = scheduleInfo.get_19th();
            str[4] = scheduleInfo.get_20th();
        } else if (monthdays == 30) {
            str = new Integer[30];
            str[5] = scheduleInfo.get_21st();
            str[6] = scheduleInfo.get_22nd();
            str[7] = scheduleInfo.get_23rd();
            str[8] = scheduleInfo.get_24th();
            str[9] = scheduleInfo.get_25th();
            str[10] = scheduleInfo.get_26th();
            str[11] = scheduleInfo.get_27th();
            str[12] = scheduleInfo.get_28th();
            str[13] = scheduleInfo.get_29th();
            str[14] = scheduleInfo.get_30th();
            str[15] = scheduleInfo.get_1st();
            str[16] = scheduleInfo.get_2nd();
            str[17] = scheduleInfo.get_3rd();
            str[18] = scheduleInfo.get_4th();
            str[19] = scheduleInfo.get_5th();
            str[20] = scheduleInfo.get_6th();
            str[21] = scheduleInfo.get_7th();
            str[22] = scheduleInfo.get_8th();
            str[23] = scheduleInfo.get_9th();
            str[24] = scheduleInfo.get_10th();
            str[25] = scheduleInfo.get_11st();
            str[26] = scheduleInfo.get_12nd();
            str[27] = scheduleInfo.get_13rd();
            str[28] = scheduleInfo.get_14th();
            str[29] = scheduleInfo.get_15th();
            str[0] = scheduleInfo.get_16th();
            str[1] = scheduleInfo.get_17th();
            str[2] = scheduleInfo.get_18th();
            str[3] = scheduleInfo.get_19th();
            str[4] = scheduleInfo.get_20th();
        } else if (monthdays == 31) {
            str = new Integer[31];
            str[5] = scheduleInfo.get_21st();
            str[6] = scheduleInfo.get_22nd();
            str[7] = scheduleInfo.get_23rd();
            str[8] = scheduleInfo.get_24th();
            str[9] = scheduleInfo.get_25th();
            str[10] = scheduleInfo.get_26th();
            str[11] = scheduleInfo.get_27th();
            str[12] = scheduleInfo.get_28th();
            str[13] = scheduleInfo.get_29th();
            str[14] = scheduleInfo.get_30th();
            str[15] = scheduleInfo.get_31st();
            str[16] = scheduleInfo.get_1st();
            str[17] = scheduleInfo.get_2nd();
            str[18] = scheduleInfo.get_3rd();
            str[19] = scheduleInfo.get_4th();
            str[20] = scheduleInfo.get_5th();
            str[21] = scheduleInfo.get_6th();
            str[22] = scheduleInfo.get_7th();
            str[23] = scheduleInfo.get_8th();
            str[24] = scheduleInfo.get_9th();
            str[25] = scheduleInfo.get_10th();
            str[26] = scheduleInfo.get_11st();
            str[27] = scheduleInfo.get_12nd();
            str[28] = scheduleInfo.get_13rd();
            str[29] = scheduleInfo.get_14th();
            str[30] = scheduleInfo.get_15th();
            str[0] = scheduleInfo.get_16th();
            str[1] = scheduleInfo.get_17th();
            str[2] = scheduleInfo.get_18th();
            str[3] = scheduleInfo.get_19th();
            str[4] = scheduleInfo.get_20th();
        }
        return str;
    }

    /**
     * 导出对象到Excel，直接新建一个Excel完成导出，基于路径的导出，不基于模板
     *
     * @param outPath 导出路径
     * @param objs    对象列表
     * @param clz     对象类型
     * @param isXssf  是否是2007版本
     */

    public void exportExcelByPath(String outPath, List objs, Class clz, boolean isXssf, String message, String dep) {
        Workbook wb = handleExcel(objs, clz, isXssf, message, dep);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.toString().getBytes("GB2312");
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    /**
     * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于流
     *
     * @param os     输出流
     * @param objs   对象列表
     * @param clz    对象类型
     * @param isXssf 是否是2007版本
     */
    public void exportExcelByPath(OutputStream os, List objs, Class clz, boolean isXssf, String message, String dep) {
        try {
            Workbook wb = handleExcel(objs, clz, isXssf, message, dep);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表
     *
     * @param path     文件路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     * @return
     */
    public List<Object> readExcelByPath(String path, Class clz, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File(path));
            return readExcel(wb, clz, readLine, tailLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     * @return 对象列表
     */
    public List<Object> readExcelByPath(String path, Class clz) {
        return this.readExcelByPath(path, clz, 0, 0);
    }

    /**
     * 取对应单元格类型的值
     *
     * @param c 列数
     * @return 单元格的值
     */
    private String getCellValue(Cell c) {
        String o = null;
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(c)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (c.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = c.getDateCellValue();
                    o = sdf.format(date);
                } else if (c.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = c.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    o = sdf.format(date);
                } else {
                    double value = c.getNumericCellValue();
                    CellStyle style = c.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    o = format.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }

    //读取excel表格信息
    public List<Object> readExcel(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);     //取第一张表
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);  //开始行，主题栏
            if (row.getCell(0).getStringCellValue().trim().contains("排班"))
                objs = readExcel_Schedule(wb, clz, readLine + 1, tailLine); // 读取排班信息
            else if (row.getCell(0).getStringCellValue().trim().contains("年假")) {
                objs = readExcel_AnnualLeave(wb, clz, readLine, tailLine); // 读取年假信息
            } else {
                objs = new ArrayList<Object>();
                Map<Integer, String> maps = getHeaderMap(row, clz);   //设定对应的字段顺序与方法名
                if (maps == null || maps.size() <= 0)
                    throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");//与order顺序不符
                for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {     //取数据
                    row = sheet.getRow(i);
                    Object obj = clz.newInstance();        //   调用无参结构
                    for (Cell c : row) {
                        int ci = c.getColumnIndex();
                        String mn = maps.get(ci).substring(3);  //消除get
                        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
                        if ("workDate".equals(mn))
                            BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                        else if (this.getCellValue(c).matches("\\d{4}-\\d{2}-\\d{2}"))
                            BeanUtils.copyProperty(obj, mn, ChangeTime.parseStringToShortDate(this.getCellValue(c)));
                        else
                            BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                    }
                    objs.add(obj);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return objs;
    }

    //读取排班信息
    public List<Object> readExcel_Schedule(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);     //取第一张表
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);  //开始行，主题栏
            String tep = row.getCell(0).getStringCellValue().trim();
            String dep = tep.split("：").length > 1 ? tep.split("：")[1] : tep.split(":")[1]; //部门信息
            tep = row.getCell(2).getStringCellValue().trim();
            String schedule = tep.split("：").length > 1 ? tep.split("：")[1] : tep.split(":")[1];  //组别
            tep = row.getCell(7).getStringCellValue().trim();
            String month = tep.split("：").length > 1 ? tep.split("：")[1] : tep.split(":")[1]; //排班月份
            objs = new ArrayList<Object>();
            row = sheet.getRow(readLine + 1);//读取下一行
            Map<Integer, String> maps = getHeaderMap(row, clz);   //设定对应的字段顺序与方法名

            if (maps == null || maps.size() <= 0) throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");//与order顺序不符
            for (int i = readLine + 2; i <= sheet.getLastRowNum() - tailLine; i++) {     //取数据
                row = sheet.getRow(i);
                Object obj = clz.newInstance();        //   调用无参结构
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci).substring(3);  //消除get
                    mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
                    if ("休".equals(this.getCellValue(c)))
                        BeanUtils.copyProperty(obj, mn, "");
                    else if (this.getCellValue(c).matches("[A-Z]*[0-9]*[早|中|午|晚|正]{0,1}[常]*")) {
                        DEPARTMENTS departments = departmentService.findByDepName(dep);
                        Period period = periodService.findByDepAndSymbol(departments.getDEPTID(), this.getCellValue(c), schedule);
                        BeanUtils.copyProperty(obj, mn, period.getId());
                    } else {
                        USERINFO userinfo = userInfoService.findByName(this.getCellValue(c));
                        if (userinfo != null)
                            BeanUtils.copyProperty(obj, mn, userinfo.getUSERID());
                        else
                            throw new RuntimeException("用户：" + this.getCellValue(c) + "信息不存在！");
                    }
                }
                BeanUtils.copyProperty(obj, "scheduleDate", month);
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return objs;
    }

    private List<ExcelHeader> getHeaderList(Class clz) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        //获取全部get/is方法
        Method[] ms = clz.getDeclaredMethods();
        for (Method m : ms) {
            String mn = m.getName();
            if (mn.startsWith("get") || mn.startsWith("is")) {
                if (m.isAnnotationPresent(ExcelResources.class)) {
                    ExcelResources er = m.getAnnotation(ExcelResources.class);
                    headers.add(new ExcelHeader(er.title(), er.order(), mn));
                }
            }
        }
        return headers;
    }

    //读取年假信息
    public List<Object> readExcel_AnnualLeave(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);     //取第一张表
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);  //开始行，主题栏
            String tep = row.getCell(0).getStringCellValue().trim();
            String year = this.matchNum(tep); //年份
            objs = new ArrayList<Object>();
            row = sheet.getRow(readLine + 1);//读取下一行
            Map<Integer, String> maps = getHeaderMap(row, clz);   //设定对应的字段顺序与方法名
            if (maps == null || maps.size() <= 0 || "ERR".equals(year))
                throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");//与order顺序不符

            for (int i = readLine + 2; i <= sheet.getLastRowNum() - tailLine; i++) {     //取数据
                row = sheet.getRow(i);
                Object obj = clz.newInstance();        //   调用无参结构
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci).substring(3);  //消除get
                    mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
                    if ("暂不享受".equals(this.getCellValue(c))) {
                        BeanUtils.copyProperty(obj, mn, -1);
                        break;
                    } else if (this.getCellValue(c).matches("\\d+")) {
                        BeanUtils.copyProperty(obj, mn, this.getCellValue(c));
                        break;
                    } else {
                        USERINFO userinfo = userInfoService.findByName(this.getCellValue(c));
                        if (userinfo != null)
                            BeanUtils.copyProperty(obj, mn, userinfo.getUSERID());
                        /*else
                            throw new RuntimeException("用户：" + this.getCellValue(c) + "信息不存在！");*/

                    }
                }
                BeanUtils.copyProperty(obj, "year", year);
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return objs;
    }

    /**
     * 判断excel标题与对象的标签标题是否一致
     *
     * @param titleRow 开始行
     * @param clz      对象
     * @return 判断类型
     */

    private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
        List<ExcelHeader> headers = getHeaderList(clz);    //取后台标题
        Map<Integer, String> maps = new HashMap<Integer, String>();
        for (Cell c : titleRow) {
            String title = this.getCellValue(c);        //取excel的标题栏
            for (ExcelHeader eh : headers) {
                if (eh.getTitle().equals(title.trim())) {      //相等则设定对应的字段顺序与方法名
                    maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
                    break;
                }
            }
        }
        return maps;
    }

    private void copyDefaultCellStyle(XSSFDataFormat format, Cell cell, XSSFCellStyle cs, int i) {
        cs.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
        cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cs.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
        cs.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
        if (i == 1)
            cs.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:s"));
        if (i == 2)
            cs.setDataFormat(format.getFormat("0"));
        cell.setCellStyle(cs);
    }

    public ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        return eu;
    }

    @Resource(name = "departmentServiceImpl")
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Resource(name = "periodServiceImpl")
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @Resource(name = "userInfoServiceImpl")
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Resource(name = "overTimeServiceImpl")
    public void setOverTimeService(OverTimeService overTimeService) {
        this.overTimeService = overTimeService;
    }

    @Resource(name = "vacationInfoServiceImpl")
    public void setVacationInfoService(VacationInfoService vacationInfoService) {
        this.vacationInfoService = vacationInfoService;
    }

    @Resource(name = "checkInOutServiceImpl")
    public void setCheckInOutService(CheckInOutService checkInOutService) {
        this.checkInOutService = checkInOutService;
    }

    @Resource(name = "scheduleInfoServiceImpl")
    public void setScheduleInfoService(ScheduleInfoService scheduleInfoService) {
        this.scheduleInfoService = scheduleInfoService;
    }

    @Resource(name = "holidayServiceImpl")
    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    private String matchNum(String str) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group();
        }
        return "ERR";
    }
}