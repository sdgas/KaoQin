package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-02.
 */

@Entity
public class Period {

    /**
     * 标识列
     */
    private int id;

    /**
     * 时间段
     */
    private String period;

    /**
     * 代替符号
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

    public String getPeriod() {
        return period;
    }

    @Column(unique = true)
    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
