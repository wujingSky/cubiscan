/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	CubiScanArticleService.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月21日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.api;

import java.util.List;

/**
 * @author Jing
 *
 */
public interface CubiScanArticleService {

    public List<CubiScanArticle> query();

    public void measure(String code, String qpcStr, CubiScanArticleQpcType type) throws Exception;

    public CubiScanArticle get(String uuid, String qpcStr);

    public Article getArticle(String barcode);

}
