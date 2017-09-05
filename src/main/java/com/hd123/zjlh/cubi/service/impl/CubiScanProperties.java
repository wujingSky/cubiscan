/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	CubiScanProperties.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月23日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jing
 *
 */
@ConfigurationProperties(prefix = "cubiscan")
public class CubiScanProperties {
    private String ipaddress;
    private String port;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
