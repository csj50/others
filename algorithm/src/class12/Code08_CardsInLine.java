package class12;

/**
 * 拿纸牌问题
 */
public class Code08_CardsInLine {

	/**
	 * 方法一（递归）
	 * @param arr
	 * @return
	 */
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}
	
	/**
	 * 先手的函数
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	public static int f(int[] arr, int L, int R) {
		if (L == R) { //只剩一张牌
			return arr[L];
		}
		//有牌能选
		return Math.max(
				//当我拿走左边的牌，轮到后手拿L+1到R的牌
				arr[L] + s(arr, L + 1, R), 
				//当我拿走右边的牌，轮到后手拿L到R-1的牌
				arr[R] + s(arr, L, R - 1));
	}
	
	/**
	 * 后手的函数
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	public static int s(int[] arr, int L, int R) {
		if (L == R) { //只剩一张牌
			return 0;
		}
		//对手肯定会扔给你最差的
		return Math.min(
				f(arr, L + 1, R), //对手拿了arr[L]，扔给你的在L+1到R范围拿的牌，相当于你先手
				f(arr, L, R - 1)); //对手拿了arr[R]，扔给你的在L到R-1范围拿的牌，相当于你先手
	}
	
	/**
	 * 方法二（非递归）
	 * @param arr
	 * @return
	 */
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int [][] f = new int[arr.length][arr.length];
		int [][] s = new int[arr.length][arr.length];
		for (int j = 0; j < arr.length; j++) {
			f[j][j] = arr[j];
			for (int i = j - 1; i >= 0; i--) {
				f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
				s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
			}
		}
		return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
	}
	
	public static void main(String[] args) {
		int[] arr = {70, 100, 1, 4};
		System.out.println(win1(arr));
		System.out.println(win2(arr));
	}
	
}
