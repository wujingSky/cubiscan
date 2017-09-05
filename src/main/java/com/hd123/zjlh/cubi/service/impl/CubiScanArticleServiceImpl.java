/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	CubiScanArticleServiceImpl.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月21日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd123.zjlh.cubi.api.Article;
import com.hd123.zjlh.cubi.api.CubiScanArticle;
import com.hd123.zjlh.cubi.api.CubiScanArticleQpcStr;
import com.hd123.zjlh.cubi.api.CubiScanArticleQpcType;
import com.hd123.zjlh.cubi.api.CubiScanArticleService;
import com.hd123.zjlh.cubi.service.dao.CubiScanArticleDao;

/**
 * @author Jing
 *
 */
@Service
public class CubiScanArticleServiceImpl implements CubiScanArticleService {

    @Autowired
    private CubiScanArticleDao dao;

    @Autowired
    private CubiScanProperties csp;

    public List<CubiScanArticle> query() {
        List<CubiScanArticle> articles = dao.query();
        if (articles.isEmpty() == false && articles.size() > 10)
            return articles.subList(0, 10);
        return articles;
    }

    @Transactional
    public void measure(String code, String qpcStr, CubiScanArticleQpcType type) throws NumberFormatException, Exception {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(qpcStr) || type == null)
            throw new IllegalArgumentException("参数不能为空。");

        Article article = getArticle(code);
        if (article == null)
            throw new IllegalArgumentException("商品“" + code + "”不存在。");

        if (article.getQpcStrs().contains(qpcStr) == false)
            throw new IllegalArgumentException("商品规格“" + qpcStr + "”不存在。");

        CubiScanArticleQpcStr articleQpcStr = new CubiScanArticleQpcStr();
        articleQpcStr.setCode(article.getCode());
        articleQpcStr.setUuid(article.getUuid());
        articleQpcStr.setType(type);
        articleQpcStr.setName(article.getName());
        articleQpcStr.setMeasureTime(new Date());
        if (CubiScanArticleQpcType.MAXQPCSTR.equals(type)) {
            articleQpcStr.setQpcStr(qpcStr);
            articleQpcStr.setMidQpcStr(CubiScanHelper.qpcStrToMidQpcStr(qpcStr));
        } else if (CubiScanArticleQpcType.MIDQPCSTR.equals(type)) {
            articleQpcStr.setMaxQpcStr(qpcStr);
            articleQpcStr.setQpcStr(CubiScanHelper.qpcStrToMidQpcStr(qpcStr));
        } else if (CubiScanArticleQpcType.MINQPCSTR.equals(type)) {
            articleQpcStr.setMaxQpcStr(qpcStr);
            articleQpcStr.setMidQpcStr(CubiScanHelper.qpcStrToMidQpcStr(qpcStr));
            articleQpcStr.setQpcStr("1*1*1");
        }

        CubiScanHelper.cubiScanMeasure(articleQpcStr, csp.getIpaddress(),
                Integer.valueOf(csp.getPort()));

        dao.remove(articleQpcStr.getUuid(), articleQpcStr.getQpcStr());
        dao.saveCubiScanArticle(articleQpcStr);
    }

    public CubiScanArticle get(String uuid, String qpcStr) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(qpcStr))
            return null;

        List<CubiScanArticleQpcStr> articleQpcStrs = dao.get(uuid);
        if (articleQpcStrs.isEmpty())
            return null;

        CubiScanArticle result = new CubiScanArticle();
        result.setCode(articleQpcStrs.get(0).getCode());
        result.setUuid(uuid);
        result.setName(articleQpcStrs.get(0).getName());
        for (CubiScanArticleQpcStr articleQpcStr : articleQpcStrs) {
            if (articleQpcStr.getQpcStr().equals(qpcStr)
                    && CubiScanArticleQpcType.MAXQPCSTR.equals(articleQpcStr.getType())) {
                result.setMaxHeight(articleQpcStr.getHeight());
                result.setMaxLength(articleQpcStr.getLength());
                result.setMaxWeight(articleQpcStr.getWeight());
                result.setMaxWidth(articleQpcStr.getWidth());
                result.setMaxQpcStr(articleQpcStr.getQpcStr());
            }

            if (CubiScanHelper.qpcStrToMidQpcStr(qpcStr).equals(articleQpcStr.getQpcStr())
                    && CubiScanArticleQpcType.MIDQPCSTR.equals(articleQpcStr.getType())) {
                result.setMidHeight(articleQpcStr.getHeight());
                result.setMidLength(articleQpcStr.getLength());
                result.setMidWeight(articleQpcStr.getWeight());
                result.setMidWidth(articleQpcStr.getWidth());
                result.setMidQpcStr(articleQpcStr.getQpcStr());
            }

            if (CubiScanArticleQpcType.MINQPCSTR.equals(articleQpcStr.getType())) {
                result.setMinHeight(articleQpcStr.getHeight());
                result.setMinLength(articleQpcStr.getLength());
                result.setMinWeight(articleQpcStr.getWeight());
                result.setMinWidth(articleQpcStr.getWidth());
                result.setMinQpcStr(articleQpcStr.getQpcStr());
            }
        }

        return result;
    }

    public Article getArticle(String barcode) {
        if (StringUtils.isEmpty(barcode))
            return null;
        
        Article article = dao.getArticle(barcode);
        if (article != null) {
            List<String> qpcStrs = dao.queryArticleQpcStrs(article.getUuid());
            List<String> midQpcStrs = new ArrayList<String>();
            List<String> maxQpcStrs = new ArrayList<String>();

            for (String qpcStr : qpcStrs) {
                BigDecimal qpc = CubiScanHelper.qpcStrToQpc(qpcStr);
                BigDecimal middelQpc = CubiScanHelper.qpcStrToMiddleQpc(qpcStr);

                if (qpc.compareTo(middelQpc) > 0) {
                    maxQpcStrs.add(qpcStr);
                } else if (middelQpc.compareTo(BigDecimal.ONE) > 0) {
                    midQpcStrs.add(qpcStr);
                }
            }

            if (maxQpcStrs.isEmpty() == false)
                article.setQpcStrs(maxQpcStrs);
            else if (midQpcStrs.isEmpty() == false)
                article.setQpcStrs(midQpcStrs);
            else
                article.setQpcStrs(qpcStrs);
        }

        return article;
    }

}
