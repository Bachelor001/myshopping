package com.shopping.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 生成订单编号工具类
 */
public class GenarateOrderId {
    private static int num=0;
    private static final int MIN_NUM=0;
    private static final int MAX_NUM=1000000;

    /**
     * 获得订单编号
     */
    public static String getOrderId() {
        String timestr = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        if (num>=MAX_NUM){
            num=MIN_NUM;
        }
        String orderId = timestr+num;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num++;
        return orderId;

    }

}
