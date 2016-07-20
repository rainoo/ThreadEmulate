
/*
 * Copyright(c) 2016 GITTOY Co.Ltd. All Rights Reserved
 */
package com.gittoy.bank;

/**
 * ģ��������˺���
 * ���˺���������������ƺͽ��Լ�����ȡ������������
 * ע�⣺synchronizedͬ���������Ƕ��󣬶����Ǵ��롣
 * 
 * @author GaoYu
 * @version $Revision: 1.0.1 2016/07/20
 * 
 * �����¼
 * NO������  ����                           ������                ����                   ����
 * 01����      2016/06/20     GaoYu       �½�       ����    IR-AA-T1-0039
 * 02����      2016/07/20     WangQiang   ������           IR-AA-T2-0052
 */
public class Account {

	// ����
	private String name;

	// ���
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
	 * ����
	 * 
	 * ���߳��µĹ�����Դ��Ҫ������߳�ͬ�������Ա�����߳��µľ������⣬��ֹһ���̵߳ĸĶ�����һ���߳������ǡ�
	 * ��������У�Account�е�amount����һ����̬��Դ���������ж�amount���޸ķ��ʶ�Ҫ����ͬ����
	 * ��Ҫʹ��synchronized�ؼ��ֽ������θ÷�����public synchronized void deposit(float amt)
	 * ע�⣺ʹ��synchronized��������󽵵ͳ���ִ��Ч�ʣ�ʹ��ִ��ʱ��䳤��
	 * 
	 * @param amt �����
	 */
	public void deposit(float amt) {
		float tmp = amount;
		tmp += amt;

		try {
			// ģ��������������Ҫ��ʱ�䣬����ˢ�����ݿ��
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// ignore
		}

		amount = tmp;
	}

	/**
	 * ȡ���
	 * 
	 * ���߳��µĹ�����Դ��Ҫ������߳�ͬ�������Ա�����߳��µľ������⣬��ֹһ���̵߳ĸĶ�����һ���߳������ǡ�
	 * ��������У�Account�е�amount����һ����̬��Դ���������ж�amount���޸ķ��ʶ�Ҫ����ͬ����
	 * ��Ҫʹ��synchronized�ؼ��ֽ������θ÷�����public synchronized void withdraw(float amt)
	 * ע�⣺ʹ��synchronized��������󽵵ͳ���ִ��Ч�ʣ�ʹ��ִ��ʱ��䳤��
	 * 
	 * @param amt ȡ����
	 */
	public void withdraw(float amt) {
		float tmp = amount;
		tmp -= amt;

		try {
			// ģ��������������Ҫ��ʱ�䣬����ˢ�����ݿ��
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// ignore
		}

		amount = tmp;
	}
}
