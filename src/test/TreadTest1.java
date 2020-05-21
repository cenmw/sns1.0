package test;

public class TreadTest1 {
	private int j;

	private void inc() {
		j++;
		System.out.println(Thread.currentThread().getName() + "-inc:" + j);
	}

	private void dec() {
		j--;
		System.out.println(Thread.currentThread().getName() + "-dec:" + j);
	}

	class Inc implements Runnable {
		public void run() {
			for (int i = 0; i < 100; i++) {
				inc();
			}
		}
	}

	class Dec implements Runnable {
		public void run() {
			for (int i = 0; i < 100; i++) {
				dec();
			}
		}
	}

	public static void main(String str[]) {
//		TreadTest1 tt = new TreadTest1();
//		Inc inc = tt.new Inc();
//		Dec dec = tt.new Dec();
//		for (int i = 0; i < 2; i++) {
//			Thread t = new Thread(inc);
//			t.start();
//			t = new Thread(dec);
//			t.start();
//		}
		int a=2<<3;
		System.out.println("====a==="+a);
	}
}
