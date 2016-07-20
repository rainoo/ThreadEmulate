
/*
 * Copyright(c) 2016 GITTOY Co.Ltd. All Rights Reserved
 */
package com.gittoy.bank;

/**
 * 模拟简单银行账号类
 * 该账号类包含储户的名称和金额，以及存款和取款两个方法。
 * 注意：synchronized同步加锁的是对象，而不是代码。
 * 
 * @author GaoYu
 * @version $Revision: 1.0.1 2016/07/20
 * 
 * 变更记录
 * NO　　　  日期                           责任人                类型                   内容
 * 01　　      2016/06/20     GaoYu       新建       　　    IR-AA-T1-0039
 * 02　　      2016/07/20     WangQiang   需求变更           IR-AA-T2-0052
 */
public class Account {

	// 姓名
	private String name;

	// 金额
	private float amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}

	/**
	 * 存款方法
	 * 
	 * 多线程下的共享资源需要加入多线程同步锁，以避免多线程下的竞争问题，防止一个线程的改动被另一个线程所覆盖。
	 * 这个程序中，Account中的amount属于一个竞态资源，所以所有对amount的修改访问都要进行同步。
	 * 需要使用synchronized关键字进行修饰该方法：public synchronized void deposit(float amt)
	 * 注意：使用synchronized方法将大大降低程序执行效率，使得执行时间变长。
	 * 
	 * @param amt 存款金额
	 */
	public void deposit(float amt) {
		float tmp = amount;
		tmp += amt;

		try {
			// 模拟其它处理所需要的时间，比如刷新数据库等
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// ignore
		}

		amount = tmp;
	}

	/**
	 * 取款方法
	 * 
	 * 多线程下的共享资源需要加入多线程同步锁，以避免多线程下的竞争问题，防止一个线程的改动被另一个线程所覆盖。
	 * 这个程序中，Account中的amount属于一个竞态资源，所以所有对amount的修改访问都要进行同步。
	 * 需要使用synchronized关键字进行修饰该方法：public synchronized void withdraw(float amt)
	 * 注意：使用synchronized方法将大大降低程序执行效率，使得执行时间变长。
	 * 
	 * @param amt 取款金额
	 */
	public void withdraw(float amt) {
		float tmp = amount;
		tmp -= amt;

		try {
			// 模拟其它处理所需要的时间，比如刷新数据库等
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// ignore
		}

		amount = tmp;
	}
}
