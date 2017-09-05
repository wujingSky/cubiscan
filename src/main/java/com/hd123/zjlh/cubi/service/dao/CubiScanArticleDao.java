/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	vms
 * 文件名：	OrderDao.java
 * 模块说明：	
 * 修改历史：
 * 2017年7月3日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hd123.zjlh.cubi.api.Article;
import com.hd123.zjlh.cubi.api.CubiScanArticle;
import com.hd123.zjlh.cubi.api.CubiScanArticleQpcStr;

/**
 * @author Jing
 *
 */
@Component
public interface CubiScanArticleDao {

    List<CubiScanArticle> query();

    void saveCubiScanArticle(CubiScanArticleQpcStr article);

    void remove(@Param("uuid") String uuid, @Param("qpcStr") String qpcStr);
    
    List<CubiScanArticleQpcStr> get(@Param("uuid") String uuid);
    
    Article getArticle(String barcode);
    
    List<String> queryArticleQpcStrs(String uuid);
}
