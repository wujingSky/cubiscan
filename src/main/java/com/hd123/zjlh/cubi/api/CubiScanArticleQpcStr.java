/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	CubiScanArticle.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月21日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jing
 *
 */
public class CubiScanArticleQpcStr implements Serializable {
    private static final long serialVersionUID = -1010778470452651088L;

    private String uuid;
    private String code;
    private String name;
    private String qpcStr;
    private String midQpcStr;
    private String maxQpcStr;
    private CubiScanArticleQpcType type;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal weight;
    
    private Date measureTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMaxQpcStr() {
        return maxQpcStr;
    }

    public void setMaxQpcStr(String maxQpcStr) {
        this.maxQpcStr = maxQpcStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getMidQpcStr() {
        return midQpcStr;
    }

    public void setMidQpcStr(String midQpcStr) {
        this.midQpcStr = midQpcStr;
    }

    public String getQpcStr() {
        return qpcStr;
    }

    public void setQpcStr(String qpcStr) {
        this.qpcStr = qpcStr;
    }

    public CubiScanArticleQpcType getType() {
        return type;
    }

    public void setType(CubiScanArticleQpcType type) {
        this.type = type;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
