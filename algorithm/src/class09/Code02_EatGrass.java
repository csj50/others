package class09;

/**
 * 牛和羊吃草问题
 */
public class Code02_EatGrass {

	// n份青草放在一堆
	// 先手后手都绝顶聪明
	// 返回string：“先手”，“后手”
	public static String winner1(int n) { // 第一次调用函数时，我是“先手”
		
		// n小于5的情况下
		// 0 1 2 3 4 
		// 后 先 后 先 先
		if (n < 5) {
			return (n == 0 || n == 2) ? "后手" : "先手";
		}
		
		// n大于等于5时
		int base = 1; // 当前先手决定吃的草量
		
		// 当前是先手在选怎么吃草
		while (base <= n) {
			
			// 当前一共n份草，先手吃掉的是base份，n - base是留给后手的
			// 母过程 先手 在子过程里是 后手
			if (winner1(n - base).equals("后手")) { // 剩下n-base份草料时，胜利者是后手，返回先手
				// 此时我是先手，传进去的是第二个吃的，但是返回后手赢，那后手就是我
				return "先手";
			}
			if (base > n / 4) { // 防止base*4之后溢出
				break;
			}
			base *= 4; // 在while循环中试多种情况
		}
		return "后手";
	}
	
	/**
	 * 打表法
	 * @param n
	 * @return
	 */
	public static String winner2(int n) {
		if (n % 5 == 0 || n % 5 == 2) {
			return "先手";
		} else {
			return "后手";
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <= 50; i++) {
			System.out.println(i + " : " + winner1(i));
		}
		System.out.println("====================");
		for (int i = 0; i <= 50; i++) {
			System.out.println(i + " : " + winner2(i));
		}
	}
}
