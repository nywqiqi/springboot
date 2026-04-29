package com.nyw.cateringsystem.util;

import com.nyw.cateringsystem.dto.OrderDTO;
import com.nyw.cateringsystem.repository.TableInfoMapper;
import jakarta.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 宁有为
 * @version 1.0.0
 * @className OrderNumGenerator
 */
public class OrderUtils {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 生成订单号：时间戳(yyyyMMddHHmm) + 桌号 + 4位随机数
     * @param tableNum 桌号
     * @return 订单号字符串
     */
    public static String generateOrderNum(String tableNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(sdf1.format(new Date()));
        sb.append(tableNum);
        sb.append(ThreadLocalRandom.current().nextInt(1000, 9999));
        return sb.toString();
    }

    /**
     * 格式化日期为 yyyy-MM-dd HH:mm:ss 格式
     * @param date 待格式化的日期对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date) {
        return sdf2.format(date);
    }
}
