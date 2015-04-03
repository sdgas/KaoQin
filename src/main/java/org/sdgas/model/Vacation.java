package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class Vacation {


    /**
     * 标识列
     */
    private int id;
    /**
     * 休假类型
     */
    private String vacation;
    /**
     * 代表符号
     */
    private String symbol;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 12, nullable = false)
    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    @Column(length = 5, nullable = false, unique = true)
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
