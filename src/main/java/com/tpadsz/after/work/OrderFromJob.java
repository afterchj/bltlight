package com.tpadsz.after.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.service.OrderFromService;
import com.tpadsz.after.service.ShopService;
import com.tpadsz.after.service.TbkService;
import com.tpadsz.after.util.OrderFromUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @program: blt-light
 * @description: 订单
 * @author: Mr.Ma
 * @create: 2018-11-27 14:12
 **/
@Service("orderFromJob")
public class OrderFromJob {

    @Resource
    private OrderFromService orderFromService;
    @Resource
    private ShopService shopService;
    @Resource
    private TbkService tbkService;
    static final String vekey = "V00000585Y74210916";
    static final String span = "1200";
    static final String url = "http://apiorder.vephp.com/order";
    //    static final String url = "http://47.101.2.136/order";
    static final String order_query_type = "settle_time";
    static int yesterCount = 1;
    static int settleCount = 1;
    static String yesterTime = "00:00:00";
    static String settleTime = "00:00:00";
    static String preFirstDay = OrderFromUtil.getPreFirstDay();
    static String preLastDay = OrderFromUtil.getPreLastDay();
    Logger logger = Logger.getLogger(OrderFromJob.class);

    public void getEveryDayOrder() {
        getEveryOrder(-20);
    }

    public void getYesterDayOrder() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String preDateByDate = OrderFromUtil.getPreDateByDate(date);
        getYesterOrder(preDateByDate);
    }

    public void getSettleDayOrder() {
        getSettleOrder(preFirstDay);
    }

    /**
     * 每一分钟调用前20分钟的接口
     *
     * @param num
     */
    public void getEveryOrder(Integer num) {

        String date;
        String start_time;
//      String start_time = "2018-12-13 17:51:37";
        try {
            date = OrderFromUtil.getTimeByMinute(num);
            start_time = java.net.URLEncoder.encode(date, "utf-8");
//            start_time = java.net.URLEncoder.encode(start_time, "utf-8");
            setOrderFromResult(url, start_time, "-1");
            logger.info(date + " 执行了每天接口," + "当前时间：" + new SimpleDateFormat
                    ("yyyy-MM-dd hh:mm:ss").format(new Date()));
//            System.out.print(date + " 执行了每天接口");
//            System.out.println("当前时间：" + new SimpleDateFormat("yyyy-MM-dd " +
//                    "hh:mm:ss").format(new Date()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第二天0点调用前一天的接口
     *
     * @param preDateByDate
     */
    public void getYesterOrder(String preDateByDate) {
        String start_time;
        yesterTime = preDateByDate + " " + yesterTime;
        try {
            start_time = java.net.URLEncoder.encode(yesterTime, "utf-8");
            setOrderFromResult(url, start_time, "-1");
            logger.info(yesterTime + " 执行了 : " + yesterCount +
                    "次---前一天接口" + "当前时间：" + new SimpleDateFormat("yyyy-MM-dd " +
                    "hh:mm:ss").format(new Date()));
//            System.out.print(yesterTime + " 执行了 : " + yesterCount +
//                    "次---前一天接口");
//            System.out.println("当前时间：" +new SimpleDateFormat("yyyy-MM-dd
// hh:mm:ss").format(new Date()));
            yesterTime = OrderFromUtil.setPreDate(yesterTime, 1200000L);
            yesterCount++;
            if (yesterCount > 72) {
                //调用完一天的接口
                logger.info("---------前一天接口调用完毕------");
//                System.out.println("---------前一天接口调用完毕------");
                return;
            }
        } catch (UnsupportedEncodingException e) {
            yesterTime = "00:00:00";
            yesterCount = 1;
            e.printStackTrace();
        } catch (ParseException e) {
            yesterTime = "00:00:00";
            yesterCount = 1;
            e.printStackTrace();
        } catch (Exception e) {
            yesterTime = "00:00:00";
            yesterCount = 1;
            e.printStackTrace();
        }
    }

    /**
     * 21-26号调用前一月的全部接口
     *
     * @param preDateByDate
     */
    public void getSettleOrder(String preDateByDate) {
        String start_time;
        settleTime = preDateByDate + " " + settleTime;

        try {
            start_time = java.net.URLEncoder.encode(settleTime, "utf-8");
            setOrderFromResult(url, start_time, order_query_type);
            logger.info(settleTime + " 执行了 : " + settleCount +
                    "次-一个月的全部接口" + "当前时间：" + new SimpleDateFormat("yyyy-MM-dd" +
                    " hh:mm:ss").format(new Date()));
//            System.out.print(settleTime + " 执行了 : " + settleCount +
//                    "次-一个月的全部接口");
//            System.out.println("当前时间：" +new SimpleDateFormat("yyyy-MM-dd
// hh:mm:ss").format(new Date()));
            settleCount++;
            //时间推进20分钟
            settleTime = OrderFromUtil.setPreDate(settleTime, 1200000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (settleCount > 72) {
                //一天接口执行完毕
                logger.info("----一" + preDateByDate + "的接口执行完毕---");
//                System.out.println("----一" + preDateByDate + "的接口执行完毕---");
                //重置时分秒
                settleTime = "00:00:00";
                settleCount = 1;//计数器重置
                //后一天
                String preDateDate = OrderFromUtil.getAftDateByDate
                        (preFirstDay);
                preFirstDay = preDateDate;
                Date firstDay = sdf.parse(preFirstDay);
                Date lastDay = sdf.parse(preLastDay);
                if (firstDay.getTime() > lastDay.getTime()) {
                    //循环执行到一个月的最后一天
                    logger.info("-----一个月的全部接口循环完毕----");
//                    System.out.println("-----一个月的全部接口循环完毕----");
                    //回退到前一个月的第一天
                    preFirstDay = OrderFromUtil.getPreFirstDay();
                    return;
                }
                return;
            }
        } catch (ParseException e) {
            settleTime = "00:00:00";
            settleCount = 1;
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            settleTime = "00:00:00";
            settleCount = 1;
            e.printStackTrace();
        } catch (Exception e) {
            settleTime = "00:00:00";
            settleCount = 1;
            e.printStackTrace();
        }
    }

    /**
     * 更新本地订单表 //
     *
     * @param url
     * @param order_query_type 判断是getSettleOrder()方法调用 为"-1"是非该方法调用
     * @param start_time
     */
    public void setOrderFromResult(String url, String start_time, String
            order_query_type) throws Exception {
        JSONArray array;
        String result;
        JSONObject jsonObject;
        String newDate;
        String param;
        int pageNo = 1;
        while (true) {
            if (order_query_type == "-1") {
                param = "vekey=" + vekey + "&start_time=" + start_time +
                        "&span=" + span + "&page_no=" + pageNo;
            } else {
                param = "vekey=" + vekey + "&start_time=" + start_time +
                        "&order_query_type=" + order_query_type +
                        "&span=" + span + "&page_no=" + pageNo;
            }
            result = OrderFromUtil.sendGet(url, param);
            if (result == null) {
                System.out.println("消息异常!");
                return;
            }
            jsonObject = JSONObject.parseObject(result);
            newDate = jsonObject.getString("data");
            if (newDate != null) {
                array = jsonObject.getJSONArray("data");
                OrderFrom orderFrom;//阿里订单
                OrderFrom orderFrom1;//本地订单
                String adzoneId;
                Long tradeId;
                Map<String, Object> map = new HashedMap();
                String uid;
                String num_iid;
                int days = 3;
                for (int i = 0; i < array.size(); i++) {
                    orderFrom = setOrderFrom(array.getJSONObject(i));
                    //根据淘宝订单状态添加本地订单表的订单状态
                    setOrderStatus(orderFrom, order_query_type);
                    adzoneId = orderFrom.getAdzone_id();
                    tradeId = orderFrom.getTrade_id();
                    //查询本地订单表是否有该笔订单
                    orderFrom1 = orderFromService.findOrderFromById(tradeId);
                    //本地表无数据,需要插入数据
                    if (orderFrom1 == null) {
                        //查询pid-uid绑定关系
                        uid = shopService.getUid(adzoneId);
                        if (uid == null) {
                            //uid为空
                            continue;
                        }
                        num_iid = String.valueOf(orderFrom.getNum_iid());
                        map.put("uid", uid);
                        map.put("num_iid", num_iid);
                        Date shareTime = orderFromService
                                .findShareLogByUidAndIid(map);
                        //有分享时间
                        if (shareTime != null) {
                            //分享时间和下单时间日期差
                            days = timeDiff(shareTime, orderFrom);
                        }
                        if (days > 3) {
                            //分享时间超过三天
                            continue;
                        }
                        //有绑定关系&&分享时间不超过三天
//                        if (uid != null) {
//                            if (uid != null && days <= 3) {
                        //插入
                        orderFrom.setUid(uid);
                        orderFromService.insertOrderFrom(orderFrom);
                        //修改预估和结算金额
                        setTbCoins(orderFrom, orderFrom1);
//                            }
                    } else {//本地表有数据
                        //数据需要更新
                        if (!orderFrom.equals(orderFrom1)) {
                            //更新
                            orderFromService.updateOrderFrom(orderFrom);
                            //修改预估和结算金额
                            setTbCoins(orderFrom, orderFrom1);
                        }
                    }
                }
                if (array.size() < 100) {
                    break;
                }
            } else {
                //出错或查询为空
                break;
            }
            pageNo++;
        }
    }

    public OrderFrom setOrderFrom(JSONObject jsonObject) {
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setTrade_id(jsonObject.getLong("trade_id"));
        orderFrom.setNum_iid(jsonObject.getLong("num_iid"));
        orderFrom.setAdzone_id(jsonObject.getString("adzone_id"));
        orderFrom.setSite_id(jsonObject.getString("site_id"));
        orderFrom.setTk_status(jsonObject.getInteger("tk_status"));
        orderFrom.setCreate_time(jsonObject.getDate("create_time"));
        orderFrom.setEarning_time(jsonObject.getDate("earning_time"));
        orderFrom.setAlipay_total_price(jsonObject.getString
                ("alipay_total_price"));
        orderFrom.setPrice(jsonObject.getString("price"));
        orderFrom.setPay_price(jsonObject.getString("pay_price"));
        orderFrom.setItem_num(jsonObject.getInteger("item_num"));
        orderFrom.setTotal_commission_rate(jsonObject.getString
                ("total_commission_rate"));
        orderFrom.setTotal_commission_fee(jsonObject.getString
                ("total_commission_fee"));
//        orderFrom.setAction_type(jsonObject.getString("action_type"));
        orderFrom.setSeller_shop_title(jsonObject.getString
                ("seller_shop_title"));
        orderFrom.setItem_title(jsonObject.getString("item_title"));
        orderFrom.setOrder_type(jsonObject.getString("order_type"));
        return orderFrom;

    }

    /**
     * 根据淘宝订单状态添加本地订单表的订单状态
     *
     * @param orderFrom
     * @param order_query_type 判断是getSettleOrder()方法调用 为"-1"是非该方法调用
     */

    public void setOrderStatus(OrderFrom orderFrom, String
            order_query_type) {
        Integer tbStatus = orderFrom.getTk_status();
        if (order_query_type == "-1") {
            //当天和隔天需要修改的本地订单表的订单状态
            if (tbStatus == 3 || tbStatus == 14) {
                //淘宝订单状态为结算和成功，本地订单状态修改为待返佣
                orderFrom.setStatus(12);
            } else {
                orderFrom.setStatus(orderFrom.getTk_status());
            }
        } else {
            //结算日修改淘宝订单状态为结算的本地订单状态
//            if (tbStatus == 14) {
            //淘宝订单状态为成功的修改本地订单表的订单状态为待返佣
//                orderFrom.setStatus(12);
//            } else {
            orderFrom.setStatus(orderFrom.getTk_status());
//            }
        }
    }

    /**
     * 计算日期差
     *
     * @param shareTime :商品分享时间
     * @param orderFrom
     * @return
     */
    public Integer timeDiff(Date shareTime, OrderFrom orderFrom) throws
            ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String share = sdf.format(shareTime);
        shareTime = sdf.parse(share);
        Date OrderTime = orderFrom.getCreate_time();
        String order = sdf.format(OrderTime);
        OrderTime = sdf.parse(order);
        return (int) ((OrderTime.getTime() - shareTime.getTime()) / (1000 *
                3600 * 24));

    }

    /**
     * 收入记录
     *
     * @param orderFrom  淘宝订单数据
     * @param orderFrom1 本地订单数据
     */
    public void setTbCoins(OrderFrom orderFrom, OrderFrom orderFrom1) {
        if (orderFrom1 == null) {
            //本地无此订单
//            if (orderFrom.getStatus() == 12) {
            //待返佣状态下需要在预估表中插入数据
            tbkService.recordECoins(orderFrom);
            if (orderFrom.getStatus() == 3) {
                //已结算状态下需要在结算表中插入数据
//                tbkService.recordECoins(orderFrom);
                tbkService.settleCoins(orderFrom);
            }
        } else {
            if (orderFrom1.getStatus() != orderFrom.getStatus()) {
                orderFrom.setUid(orderFrom1.getUid());
//                if (orderFrom.getStatus() == 12) {
                //待返佣状态下需要在预估表中插入数据
                tbkService.recordECoins(orderFrom);
                if (orderFrom.getStatus() == 3) {
                    //已结算状态下需要在结算表中插入数据
                    tbkService.settleCoins(orderFrom);
//                    tbkService.recordECoins(orderFrom);
                }
            }
        }
    }
}
