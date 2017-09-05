/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	vms
 * 文件名：	OrderController.java
 * 模块说明：	
 * 修改历史：
 * 2017年7月3日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd123.zjlh.cubi.api.Article;
import com.hd123.zjlh.cubi.api.CubiScanArticle;
import com.hd123.zjlh.cubi.api.CubiScanArticleQpcType;
import com.hd123.zjlh.cubi.api.CubiScanArticleService;

/**
 * @author Jing
 *
 */
@Controller
@RequestMapping("api/cubiscan")
public class CubiScanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CubiScanController.class);

    @Autowired
    private CubiScanArticleService service;

    @RequestMapping(value = "/query")
    public @ResponseBody RestResponse<List<CubiScanArticle>> query() {
        List<CubiScanArticle> result = new ArrayList<CubiScanArticle>();
        try {
            result = service.query();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("查询失败。" + e.getMessage());
            return RestResponse.fail(403, "查询测量商品失败。");
        }
        return RestResponse.ok(result, 200);
    }

    @RequestMapping(value = "/get")
    public @ResponseBody RestResponse<Article> get(
            @RequestParam(value = "barcode", required = true) String barcode) {
        try {
            return RestResponse.ok(service.getArticle(barcode), 200);
        } catch (Exception e) {
            LOGGER.info("查询失败。");
            return RestResponse.fail(403, "查询商品基本信息失败。");
        }
    }

    @RequestMapping(value = "/getcubiscan")
    public @ResponseBody RestResponse<CubiScanArticle> getCubiScan(
            @RequestParam(value = "uuid", required = true) String uuid,
            @RequestParam(value = "qpcStr", required = true) String qpcStr) {
        try {
            return RestResponse.ok(service.get(uuid, qpcStr), 200);
        } catch (Exception e) {
            LOGGER.info("查询失败。");
            return RestResponse.fail(403, "查询商品测量结果失败。");
        }
    }

    @RequestMapping(value = "/measure", method = RequestMethod.POST)
    public @ResponseBody RestResponse measure(@RequestBody MeasureReq req) {
        try {
            service.measure(req.getCode(), req.getQpcStr(),
                    CubiScanArticleQpcType.valueOf(req.getType()));
            return RestResponse.ok(200);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("测量商品发生错误。" + e.getMessage());
            return RestResponse.fail(403, "测量商品发生错误" + e.getMessage());
        }
    }
}
