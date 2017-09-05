/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	vms
 * 文件名：	VendorMergeTest.java
 * 模块说明：	
 * 修改历史：
 * 2017年6月30日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hd123.zjlh.cubi.api.Article;
import com.hd123.zjlh.cubi.api.CubiScanArticleQpcType;
import com.hd123.zjlh.cubi.api.CubiScanArticleService;

/**
 * @author Jing
 *
 */
@MapperScan("com.hd123.zjlh.cubi.service.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional(rollbackFor = Exception.class)
public class CubiTest {
    @Resource
    private CubiScanArticleService service;
    
    @Test
    public void testQuery(){
        Article article = service.getArticle("634529");
        System.out.println(article.getUuid());
    }
    
    @Test
    public void testSave() throws Exception{
        service.measure("634529", "1*1*48", CubiScanArticleQpcType.MIDQPCSTR);
    }
    
    @Test
    public void testget(){
        service.query();
    }
}
