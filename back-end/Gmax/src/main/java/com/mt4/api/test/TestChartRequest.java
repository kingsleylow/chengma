package com.mt4.api.test;

import com.mt4.api.ConnectorAPI;
import com.mt4.api.MT4;
import com.mt4.api.bean.EnumChartMode;
import com.mt4.api.bean.EnumChartPeriods;
import com.mt4.api.bean.RateInfo;

public class TestChartRequest {

	public static void main(String[] args) {
		ConnectorAPI mt4 = new MT4();
		mt4.connect("mt4live.shjhkj.com:1996");
		if(mt4.isConnected()){
			System.out.println("connected");
		}

		mt4.login(8090, "tx123456");

		long now  = mt4.serverTime() ;//服务器时间
		long start= now - 1 * 60 * 60;//一个小时前
		long end = now;
		long timesign= 0;
		int mode= EnumChartMode.CHART_RANGE_IN.value();//这些枚举的字段都是什么意思？
		int period = EnumChartPeriods.PERIOD_H1.value();
		RateInfo[] rateInfos = mt4.chartRequest("USDJPY", period, start, end, timesign, mode);//行情指的是什麼

		for(RateInfo rateInfo:rateInfos){
			System.out.println(rateInfo);
		}

	}

}
