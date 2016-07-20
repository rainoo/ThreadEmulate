/*
 * Copyright(c) 2016 GITTOY Co.Ltd. All Rights Reserved
 */
package com.gittoy.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟转账测试类
 * 多线程同时启动时，如果不是线程安全的则会造成转账混乱。
 * synchronized关键字测试
 * 
 * @author GaoYu
 * @version $Revision: 1.0.1 2016/07/20
 * 
 * 变更记录
 * NO　　　  日期                           责任人                类型                   内容
 * 01　　      2016/06/20     GaoYu       新建       　　    IR-AA-T1-0039
 * 02　　      2016/07/20     WangQiang   需求变更           IR-AA-T2-0052
 */
public class BankingTest {

	// 模拟1000个线程同时启动
	private static int NUM_OF_THREAD = 100;
	static Thread[] threads = new Thread[NUM_OF_THREAD];

	// 入口函数
	public static void main(String[] args) {
		// 王强的银行账号
		final Account acc = new Account("王强", 1000.0f);

		// 记录操作开始的时间：格式例如：【2016年07月20日：10时30分56秒637毫秒】
		long startTimeMillis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日：HH时mm分ss秒SSS毫秒");
		Date date = new Date(startTimeMillis);
		System.out.println("操作开始：" + formatter.format(date));

		// 模拟1000个账号同时启动
		for (int i = 0; i < NUM_OF_THREAD; i++) {

			// 第N个账号进行了存款和取款的操作
			threads[i] = new Thread(new Runnable() {
				public void run() {
					acc.deposit(100.0f);
					acc.withdraw(100.0f);
				}
			});

			// 线程启动
			threads[i].start();
		}

		for (int i = 0; i < NUM_OF_THREAD; i++) {
			try {
				// 等待所有线程运行结束
				threads[i].join();
			} catch (InterruptedException e) {
				// ignore
			}
		}

		// 记录操作结束的时间：格式例如：【2016年07月20日：10时30分58秒759毫秒】
		long endTimeMillis = System.currentTimeMillis();
		date = new Date(endTimeMillis);
		System.out.println("操作结束：" + formatter.format(date));

		// 计算操作总共用时多少毫秒
		long timeMillis = endTimeMillis - startTimeMillis;
		System.out.println("操作用时：" + timeMillis + "毫秒");

		// 打印银行账号经过多线程操作后的当前账户金额
		System.out.println("最终, 王强的银行账户内金额为: " + acc.getAmount() + " 元人民币");
	}

}
