package class12;

/**
 * 汉诺塔问题
 */
public class Code01_Hanoi {

	/**
	 * 方法一
	 * @param n
	 */
	public static void hanoi1(int n) {
		leftToRight(n);
	}
	
	/**
	 * 主函数：把1~N层圆盘，从左->右
	 * @param n
	 */
	public static void leftToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(n - 1); //1到n-1的圆盘从最左移到中间
		System.out.println("Move " + n + " from left to right"); //第n层圆盘从最左移到右边
		midToRight(n - 1); //把n-1层圆盘从中间移到右边来
	}
	
	/**
	 * 把N层圆盘，从左->中
	 * @param n
	 */
	public static void leftToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(n - 1); //把n-1层圆盘，从最左移到最右
		System.out.println("Move " + n + " from left to mid"); //第n层圆盘从最左移到中间
		rightToMid(n - 1); //把n-1层圆盘，从最右移到中间
	}
	
	public static void rightToMid(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(n - 1);
		System.out.println("Move " + n + " from right to mid");
		leftToMid(n - 1);
	}
	
	public static void midToRight(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(n - 1);
		System.out.println("Move " + n + " from mid to right");
		leftToRight(n - 1);
	}
	
	public static void midToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(n - 1);
		System.out.println("Move " + n + " from mid to left");
		rightToLeft(n - 1);
	}
	
	public static void rightToLeft(int n) {
		if (n == 1) {
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(n - 1);
		System.out.println("Move " + n + " from right to left");
		midToLeft(n - 1);
	}
	
	/**
	 * 方法二
	 * @param n
	 */
	public static void hanoi2(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid");
		}
	}
	
	/**
	 * 1~i 圆盘
	 * 目标是from -> to
	 * other是另外一个
	 * @param N
	 * @param from
	 * @param to
	 * @param other
	 */
	public static void func(int N, String from, String to, String other) {
		if (N == 1) {
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to);
			System.out.println("Move " + N + " from " + from + " to " + to);
			func(N - 1, other, to, from);
		}
	}
	
	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("====================");
		hanoi1(n);
		System.out.println("====================");
	}
}
