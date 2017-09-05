/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	MeasureReq.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月25日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.controller;

import java.io.Serializable;

/**
 * @author Jing
 *
 */
public class MeasureReq implements Serializable {
    private static final long serialVersionUID = -8968157818503143986L;

    private String code;
    private String qpcStr;
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQpcStr() {
        return qpcStr;
    }

    public void setQpcStr(String qpcStr) {
        this.qpcStr = qpcStr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
