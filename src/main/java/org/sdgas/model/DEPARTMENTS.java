package org.sdgas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 120378 on 2015-04-02.
 */
@Entity
public class DEPARTMENTS {

    /**
     * 部门ID
     */
    private int DEPTID;
    /**
     * 部门名称
     */
    private String DEPTNAME;
    /**
     * 上级部门ID
     */
    private int SUPDEPTID;

    @Id
    @GeneratedValue
    public int getDEPTID() {
        return DEPTID;
    }

    public void setDEPTID(int DEPTID) {
        this.DEPTID = DEPTID;
    }

    @Column(length = 30)
    public String getDEPTNAME() {
        return DEPTNAME;
    }

    public void setDEPTNAME(String DEPTNAME) {
        this.DEPTNAME = DEPTNAME;
    }

    @Column(nullable = false, columnDefinition = "INT default 1")
    public int getSUPDEPTID() {
        return SUPDEPTID;
    }

    public void setSUPDEPTID(int SUPDEPTID) {
        this.SUPDEPTID = SUPDEPTID;
    }
}
