package com.tpadsz.after.entity;

import java.io.Serializable;

/**
 * Created by chenhao.lu on 2018/12/3.
 */
public class CoinsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private float avail;
    private float todayEcoins;
    private float presentMonthEcoins;
    private float lastMonthEcoins;
    private float lastMonthCoins;
    private float consume;
    private int todayOrders;
    private int yesterdayOrders;
    private float yesterdayEcoins;

    public CoinsInfo() {
        super();
    }

    public CoinsInfo(float avail,float todayEcoins, float presentMonthEcoins, float lastMonthEcoins, float lastMonthCoins, float consume, int todayOrders,int yesterdayOrders, float yesterdayEcoins) {
        super();
        this.avail = avail;
        this.todayEcoins = todayEcoins;
        this.presentMonthEcoins = presentMonthEcoins;
        this.lastMonthEcoins = lastMonthEcoins;
        this.lastMonthCoins = lastMonthCoins;
        this.consume = consume;
        this.todayOrders = todayOrders;
        this.yesterdayOrders = yesterdayOrders;
        this.yesterdayEcoins = yesterdayEcoins;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public float getAvail() {
        return avail;
    }

    public void setAvail(float avail) {
        this.avail = avail;
    }

    public float getTodayEcoins() {
        return todayEcoins;
    }

    public void setTodayEcoins(float todayEcoins) {
        this.todayEcoins = todayEcoins;
    }

    public float getPresentMonthEcoins() {
        return presentMonthEcoins;
    }

    public void setPresentMonthEcoins(float presentMonthEcoins) {
        this.presentMonthEcoins = presentMonthEcoins;
    }

    public float getLastMonthEcoins() {
        return lastMonthEcoins;
    }

    public void setLastMonthEcoins(float lastMonthEcoins) {
        this.lastMonthEcoins = lastMonthEcoins;
    }

    public float getLastMonthCoins() {
        return lastMonthCoins;
    }

    public void setLastMonthCoins(float lastMonthCoins) {
        this.lastMonthCoins = lastMonthCoins;
    }

    public float getConsume() {
        return consume;
    }

    public void setConsume(float consume) {
        this.consume = consume;
    }

    public int getTodayOrders() {
        return todayOrders;
    }

    public void setTodayOrders(int todayOrders) {
        this.todayOrders = todayOrders;
    }

    public int getYesterdayOrders() {
        return yesterdayOrders;
    }

    public void setYesterdayOrders(int yesterdayOrders) {
        this.yesterdayOrders = yesterdayOrders;
    }

    public float getYesterdayEcoins() {
        return yesterdayEcoins;
    }

    public void setYesterdayEcoins(float yesterdayEcoins) {
        this.yesterdayEcoins = yesterdayEcoins;
    }
}
