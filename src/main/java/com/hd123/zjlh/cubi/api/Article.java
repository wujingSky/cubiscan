/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	Article.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月21日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jing
 *
 */
public class Article implements Serializable {
    private static final long serialVersionUID = -553620751757740503L;
    
    private String uuid;
    private String code;
    private String name;
    private String spec;
    private List<String> qpcStrs = new ArrayList<String>();

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public List<String> getQpcStrs() {
        return qpcStrs;
    }

    public void setQpcStrs(List<String> qpcStrs) {
        this.qpcStrs = qpcStrs;
    }
}
