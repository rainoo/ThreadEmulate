/*
 * Copyright(c) 2016 GITTOY Co.Ltd. All Rights Reserved
 */
package com.gittoy.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ģ��ת�˲�����
 * ���߳�ͬʱ����ʱ����������̰߳�ȫ��������ת�˻��ҡ�
 * synchronized�ؼ��ֲ���
 * 
 * @author GaoYu
 * @version $Revision: 1.0.1 2016/07/20
 * 
 * �����¼
 * NO������  ����                           ������                ����                   ����
 * 01����      2016/06/20     GaoYu       �½�       ����    IR-AA-T1-0039
 * 02����      2016/07/20     WangQiang   ������           IR-AA-T2-0052
 */
public class BankingTest {

	// ģ��1000���߳�ͬʱ����
	private static int NUM_OF_THREAD = 100;
	static Thread[] threads = new Thread[NUM_OF_THREAD];

	// ��ں���
	public static void main(String[] args) {
		// ��ǿ�������˺�
		final Account acc = new Account("��ǿ", 1000.0f);

		// ��¼������ʼ��ʱ�䣺��ʽ���磺��2016��07��20�գ�10ʱ30��56��637���롿
		long startTimeMillis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�գ�HHʱmm��ss��SSS����");
		Date date = new Date(startTimeMillis);
		System.out.println("������ʼ��" + formatter.format(date));

		// ģ��1000���˺�ͬʱ����
		for (int i = 0; i < NUM_OF_THREAD; i++) {

			// ��N���˺Ž����˴���ȡ��Ĳ���
			threads[i] = new Thread(new Runnable() {
				public void run() {
					acc.deposit(100.0f);
					acc.withdraw(100.0f);
				}
			});

			// �߳�����
			threads[i].start();
		}

		for (int i = 0; i < NUM_OF_THREAD; i++) {
			try {
				// �ȴ������߳����н���
				threads[i].join();
			} catch (InterruptedException e) {
				// ignore
			}
		}

		// ��¼����������ʱ�䣺��ʽ���磺��2016��07��20�գ�10ʱ30��58��759���롿
		long endTimeMillis = System.currentTimeMillis();
		date = new Date(endTimeMillis);
		System.out.println("����������" + formatter.format(date));

		// ��������ܹ���ʱ���ٺ���
		long timeMillis = endTimeMillis - startTimeMillis;
		System.out.println("������ʱ��" + timeMillis + "����");

		// ��ӡ�����˺ž������̲߳�����ĵ�ǰ�˻����
		System.out.println("����, ��ǿ�������˻��ڽ��Ϊ: " + acc.getAmount() + " Ԫ�����");
	}

}
