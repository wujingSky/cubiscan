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

/**
 * @author Jing
 *
 */
public class CubiScanArticle implements Serializable {
    private static final long serialVersionUID = -1010778470452651088L;

    private String uuid;
    private String code;
    private String name;

    private String maxQpcStr;
    private BigDecimal maxLength = BigDecimal.ZERO;
    private BigDecimal maxWidth = BigDecimal.ZERO;
    private BigDecimal maxHeight = BigDecimal.ZERO;
    private BigDecimal maxWeight = BigDecimal.ZERO;

    private String midQpcStr;
    private BigDecimal midLength = BigDecimal.ZERO;
    private BigDecimal midWidth = BigDecimal.ZERO;
    private BigDecimal midHeight = BigDecimal.ZERO;
    private BigDecimal midWeight = BigDecimal.ZERO;

    private String minQpcStr = "1*1*1";
    private BigDecimal minLength = BigDecimal.ZERO;
    private BigDecimal minWidth = BigDecimal.ZERO;
    private BigDecimal minHeight = BigDecimal.ZERO;
    private BigDecimal minWeight = BigDecimal.ZERO;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(BigDecimal maxLength) {
        this.maxLength = maxLength;
    }

    public BigDecimal getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(BigDecimal maxWidth) {
        this.maxWidth = maxWidth;
    }

    public BigDecimal getMaxHeight() {
        return maxHeight;
    }

    public String getMaxQpcStr() {
        return maxQpcStr;
    }

    public void setMaxQpcStr(String maxQpcStr) {
        this.maxQpcStr = maxQpcStr;
    }

    public String getMidQpcStr() {
        return midQpcStr;
    }

    public void setMidQpcStr(String midQpcStr) {
        this.midQpcStr = midQpcStr;
    }

    public String getMinQpcStr() {
        return minQpcStr;
    }

    public void setMinQpcStr(String minQpcStr) {
        this.minQpcStr = minQpcStr;
    }

    public void setMaxHeight(BigDecimal maxHeight) {
        this.maxHeight = maxHeight;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public BigDecimal getMidLength() {
        return midLength;
    }

    public void setMidLength(BigDecimal midLength) {
        this.midLength = midLength;
    }

    public BigDecimal getMidWidth() {
        return midWidth;
    }

    public void setMidWidth(BigDecimal midWidth) {
        this.midWidth = midWidth;
    }

    public BigDecimal getMidHeight() {
        return midHeight;
    }

    public void setMidHeight(BigDecimal midHeight) {
        this.midHeight = midHeight;
    }

    public BigDecimal getMidWeight() {
        return midWeight;
    }

    public void setMidWeight(BigDecimal midWeight) {
        this.midWeight = midWeight;
    }

    public BigDecimal getMinLength() {
        return minLength;
    }

    public void setMinLength(BigDecimal minLength) {
        this.minLength = minLength;
    }

    public BigDecimal getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(BigDecimal minWidth) {
        this.minWidth = minWidth;
    }

    public BigDecimal getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(BigDecimal minHeight) {
        this.minHeight = minHeight;
    }

    public BigDecimal getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(BigDecimal minWeight) {
        this.minWeight = minWeight;
    }

}
