
package com.irish.lock;


public class OrderService implements Runnable {
	private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
	private ExtLock extLock = new ZookeeperDistrbuteLock();

	public void run() {
		getNumber();
	}

	public void getNumber() {
		try {
			extLock.getLock();
			String number = orderNumGenerator.getNumber();
			System.out.println("线程:" + Thread.currentThread().getName() + ",生成订单id:" + number);
		} catch (Exception e) {

		} finally {
			extLock.unLock();
		}
	}

	/**
	 * 
	 * 这里是模拟
	 */
	public static void main(String[] args) {
		System.out.println("多线程生成number");
		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
		}
	}

}
