package org.sdgas.util;

import org.sdgas.base.PageIndex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WebTool {

    // 保留小数位,dout为输入实数,n为保留的位数
    public static double keepSmallDigital(double dout, int n) {
        double p = Math.pow(10, n);
        return Math.round(dout * p) / p;

    }

    //对字符串的简单检查
    public static boolean checkStatus(String param) {
        return null != param && !"".equals(param.trim());
    }

    public static boolean isDigit(String str) {
        return str.replaceAll("\\d", "").equals("");
    }

    //对字符串的简单检查
    public static double stringToDouble(String s) {
        if ("".equals(s.trim()))
            return 0.0d;
        else
            return Double.parseDouble(s.trim());
    }


    /**
     * 页面分页功能的工具
     */
    public static PageIndex getPageIndex(long viewPageCount, int currentPage, long totalPage) {
        long startPage = currentPage - (viewPageCount % 2 == 0 ? viewPageCount / 2 - 1
                : viewPageCount / 2);
        long endPage = currentPage + viewPageCount / 2;
        if (startPage < 1) {
            startPage = 1;
            if (totalPage >= viewPageCount) endPage = viewPageCount;
            else endPage = totalPage;
        }
        if (endPage > totalPage) {
            endPage = totalPage;
            if ((endPage - viewPageCount) > 0) startPage = endPage - viewPageCount + 1;
            else startPage = 1;
        }
        return new PageIndex(startPage, endPage);
    }

    //计算YY-MM-DD为星期几
    public static String getWeekOfDate(String date) {
        Date d = ChangeTime.parseStringToShortDate(date);
        String[] weekOfDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(d);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    //计算当前月天数
    public static int calDayByYearAndMonth(String dyear, String dmouth) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
        Calendar rightNow = Calendar.getInstance();
        try {
            rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);//根据年月 获取月份天数
    }
}
