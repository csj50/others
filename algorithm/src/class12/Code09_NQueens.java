package class12;

/**
 * N皇后问题
 */
public class Code09_NQueens {

	public static int num1(int n) {
		if (n < 1) {
			return 0;
		}
		int[] record = new int[n]; //record[i] -> i行的皇后，放在了第几行
		return process1(0, record, n);
	}
	
	// 潜台词：record[0...i-1]的皇后，任何两个皇后一定都不共行、不共列、不共斜线
	// 目前来到了第i行
	// record[0...i-1]表示之前的行，放了皇后的位置
	// n代表整体一共有多少行
	// 返回值是，摆完所有的皇后，合理的摆法有多少种
	public static int process1(int i, int[] record, int n) {
		if (i == n) { //终止行
			return 1;
		}
		//没有到终止位置，还有皇后要摆
		int res = 0;
		for (int j = 0; j < n; j++) {
			// 当前行在i行，尝试i行所有的列j
			// 当i行的皇后放在j列，会不会和之前[0...i-1]的皇后，不共行共列或共斜线
			// 如果是，认为有效
			// 如果不是，认为无效
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process1(i + 1, record, n);
			}
		}
		return res;
	}
	
	/**
	 * 判断当前摆的皇后，是否共行共列或共斜线
	 * @param record [0...i-1]的皇后
	 * @param i 要摆的皇后在i行
	 * @param j 要摆的皇后在j列
	 * @return 返回i行皇后，放在了j列，是否有效
	 */
	public static boolean isValid(int[] record, int i, int j) {
		for (int k = 0; k < i; k++) {
			// 肯定不共行
			// j == record[k]：判断是否共列
			// |行-行|=|列-列|：判断是否共斜线
			if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int n = 13;
		long start = System.currentTimeMillis();
		System.out.println(num1(n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
	}
}
