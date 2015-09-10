package org.sdgas.model;

import org.sdgas.util.ExcelResources;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class ScheduleInfo {

    /**
     * 标识列
     */
    private int id;

    /**
     * 人员信息
     */
    private int userinfo;

    /**
     * 部门编号
     */
    private int depId;

    /**
     * 排班日期
     */
    private String scheduleDate;

    //每个月的21~20号排班情况

    private int _16th;
    private int _17th;
    private int _18th;
    private int _19th;
    private int _20th;
    private int _21st;
    private int _22nd;
    private int _23rd;
    private int _24th;
    private int _25th;
    private int _26th;
    private int _27th;
    private int _28th;
    private int _29th;
    private int _30th;
    private int _31st;
    private int _1st;
    private int _2nd;
    private int _3rd;
    private int _4th;
    private int _5th;
    private int _6th;
    private int _7th;
    private int _8th;
    private int _9th;
    private int _10th;
    private int _11st;
    private int _12nd;
    private int _13rd;
    private int _14th;
    private int _15th;


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ExcelResources(order = 1, title = "日期姓名")
    public int getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(int userinfo) {
        this.userinfo = userinfo;
    }

    @ExcelResources(order = 33, title = "排班月份")
    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @ExcelResources(order = 7, title = "21")
    public int get_21st() {
        return _21st;
    }

    public void set_21st(int _21st) {
        this._21st = _21st;
    }

    @ExcelResources(order = 8, title = "22")
    public int get_22nd() {
        return _22nd;
    }

    public void set_22nd(int _22nd) {
        this._22nd = _22nd;
    }

    @ExcelResources(order = 9, title = "23")
    public int get_23rd() {
        return _23rd;
    }

    public void set_23rd(int _23rd) {
        this._23rd = _23rd;
    }

    @ExcelResources(order = 10, title = "24")
    public int get_24th() {
        return _24th;
    }

    public void set_24th(int _24th) {
        this._24th = _24th;
    }

    @ExcelResources(order = 11, title = "25")
    public int get_25th() {
        return _25th;
    }

    public void set_25th(int _25th) {
        this._25th = _25th;
    }

    @ExcelResources(order = 12, title = "26")
    public int get_26th() {
        return _26th;
    }

    public void set_26th(int _26th) {
        this._26th = _26th;
    }

    @ExcelResources(order = 13, title = "27")
    public int get_27th() {
        return _27th;
    }

    public void set_27th(int _27th) {
        this._27th = _27th;
    }

    @ExcelResources(order = 14, title = "28")
    public int get_28th() {
        return _28th;
    }

    public void set_28th(int _28th) {
        this._28th = _28th;
    }

    @ExcelResources(order = 15, title = "29")
    public int get_29th() {
        return _29th;
    }

    public void set_29th(int _29th) {
        this._29th = _29th;
    }

    @ExcelResources(order = 16, title = "30")
    public int get_30th() {
        return _30th;
    }

    public void set_30th(int _30th) {
        this._30th = _30th;
    }

    @ExcelResources(order = 17, title = "31")
    public int get_31st() {
        return _31st;
    }

    public void set_31st(int _31st) {
        this._31st = _31st;
    }

    @ExcelResources(order = 18, title = "1")
    public int get_1st() {
        return _1st;
    }

    public void set_1st(int _1st) {
        this._1st = _1st;
    }

    @ExcelResources(order = 19, title = "2")
    public int get_2nd() {
        return _2nd;
    }

    public void set_2nd(int _2nd) {
        this._2nd = _2nd;
    }

    @ExcelResources(order = 20, title = "3")
    public int get_3rd() {
        return _3rd;
    }

    public void set_3rd(int _3rd) {
        this._3rd = _3rd;
    }

    @ExcelResources(order = 21, title = "4")
    public int get_4th() {
        return _4th;
    }

    public void set_4th(int _4th) {
        this._4th = _4th;
    }

    @ExcelResources(order = 22, title = "5")
    public int get_5th() {
        return _5th;
    }

    public void set_5th(int _5th) {
        this._5th = _5th;
    }

    @ExcelResources(order = 23, title = "6")
    public int get_6th() {
        return _6th;
    }

    public void set_6th(int _6th) {
        this._6th = _6th;
    }

    @ExcelResources(order = 24, title = "7")
    public int get_7th() {
        return _7th;
    }

    public void set_7th(int _7th) {
        this._7th = _7th;
    }

    @ExcelResources(order = 25, title = "8")
    public int get_8th() {
        return _8th;
    }

    public void set_8th(int _8th) {
        this._8th = _8th;
    }

    @ExcelResources(order = 26, title = "9")
    public int get_9th() {
        return _9th;
    }

    public void set_9th(int _9th) {
        this._9th = _9th;
    }

    @ExcelResources(order = 27, title = "10")
    public int get_10th() {
        return _10th;
    }

    public void set_10th(int _10th) {
        this._10th = _10th;
    }

    @ExcelResources(order = 28, title = "11")
    public int get_11st() {
        return _11st;
    }

    public void set_11st(int _11st) {
        this._11st = _11st;
    }

    @ExcelResources(order = 29, title = "12")
    public int get_12nd() {
        return _12nd;
    }

    public void set_12nd(int _12nd) {
        this._12nd = _12nd;
    }

    @ExcelResources(order = 30, title = "13")
    public int get_13rd() {
        return _13rd;
    }

    public void set_13rd(int _13rd) {
        this._13rd = _13rd;
    }

    @ExcelResources(order = 31, title = "14")
    public int get_14th() {
        return _14th;
    }

    public void set_14th(int _14th) {
        this._14th = _14th;
    }

    @ExcelResources(order =32 , title = "15")
    public int get_15th() {
        return _15th;
    }

    public void set_15th(int _15th) {
        this._15th = _15th;
    }

    @ExcelResources(order = 2, title = "16")
    public int get_16th() {
        return _16th;
    }

    public void set_16th(int _16th) {
        this._16th = _16th;
    }

    @ExcelResources(order = 3, title = "17")
    public int get_17th() {
        return _17th;
    }

    public void set_17th(int _17th) {
        this._17th = _17th;
    }

    @ExcelResources(order = 4, title = "18")
    public int get_18th() {
        return _18th;
    }

    public void set_18th(int _18th) {
        this._18th = _18th;
    }

    @ExcelResources(order = 5, title = "19")
    public int get_19th() {
        return _19th;
    }

    public void set_19th(int _19th) {
        this._19th = _19th;
    }

    @ExcelResources(order = 6, title = "20")
    public int get_20th() {
        return _20th;
    }

    public void set_20th(int _20th) {
        this._20th = _20th;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }
}
