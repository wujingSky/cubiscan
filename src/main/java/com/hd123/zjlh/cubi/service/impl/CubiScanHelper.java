/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2014，所有权利保留。
 * 
 * 项目名：	wms-common
 * 文件名：	QpcHelper.java
 * 模块说明：	
 * 修改历史：
 * 2014-5-23 - Gao JingYu - 创建。
 */
package com.hd123.zjlh.cubi.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.hd123.zjlh.cubi.api.CubiScanArticleQpcStr;

/**
 * 包装规格工具类。
 * 
 * @author Gao JingYu
 */
public class CubiScanHelper {
    public static final int CASE_SCALE = 3;
    
    private static int STX = 0x02;
    private static int ETX = 0x03;
    private static int CR = 0x0D;
    private static int LF = 0x0A;
    private static String COMMAND = "C";

    private static int LENGTH = 12;
    private static int WIDTH = 19;
    private static int HEIGHT = 26;
    private static int WEIGHT = 36;

    public static void cubiScanMeasure(CubiScanArticleQpcStr articleQpcStr, String IP, int port) throws Exception {
        OutputStream out = null;
        InputStream in = null;
        Socket socket = new Socket();
        try {
            socket.setTcpNoDelay(true);
            socket.setReuseAddress(true);
            socket.setSoTimeout(30000);
            socket.setSoLinger(true, 5);
            socket.setSendBufferSize(1024);
            socket.setReceiveBufferSize(1024);
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(IP, port), 30000);

            out = socket.getOutputStream();
            out.write(STX);
            out.write(COMMAND.getBytes());
            out.write(ETX);
            out.write(CR);
            out.write(LF);

            in = socket.getInputStream();
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            byte b[] = new byte[100];
            in.read(b);
            bytesOut.write(b);

            articleQpcStr
                    .setLength(BigDecimal.valueOf(Double.valueOf(obtainMeasureResult(b, LENGTH))));
            articleQpcStr
                    .setWidth(BigDecimal.valueOf(Double.valueOf(obtainMeasureResult(b, WIDTH))));
            articleQpcStr
                    .setHeight(BigDecimal.valueOf(Double.valueOf(obtainMeasureResult(b, HEIGHT))));
            articleQpcStr
                    .setWeight(BigDecimal.valueOf(Double.valueOf(obtainMeasureResult(b, WEIGHT))));
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != socket && socket.isConnected() && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String obtainMeasureResult(byte[] data, int type) {
        StringBuilder result = new StringBuilder();
        for (int i = type; i < type + 5; i++) {
            byte b = data[i];
            if (b >= 0) {
                if (b < 32 || b > 126) {
                    result.append("");
                } else {
                    result.append((char) b);
                }
            } else {
                String s = new String(data, i, 2);
                result.append(s);
                i++;
            }
        }

        return result.toString().trim();
    }

    public static BigDecimal qpcStrToQpc(String qpcStr) {
        if (qpcStr.matches("^\\d+\\*\\d+(\\.\\d+)?\\*\\d+(\\.\\d+)?$") == false) {
            throw new IllegalArgumentException("illegal format");
        }

        String[] parts = qpcStr.split("\\*");
        BigDecimal part0 = new BigDecimal(parts[0]);
        BigDecimal part1 = new BigDecimal(parts[1]);
        BigDecimal part2 = new BigDecimal(parts[2]);

        return part0.multiply(part1).multiply(part2);
    }
    
    public static String qpcStrToMidQpcStr(String qpcStr) {
        return "1*1*".concat(qpcStrToMiddleQpc(qpcStr).toString());
    }

    public static BigDecimal qpcStrToMiddleQpc(String qpcStr) {
        if (qpcStr.matches("^\\d+\\*\\d+(\\.\\d+)?\\*\\d+(\\.\\d+)?$") == false) {
            throw new IllegalArgumentException("illegal format");
        }

        String[] parts = qpcStr.split("\\*");
        BigDecimal part2 = new BigDecimal(parts[2]);

        return part2;
    }
}
