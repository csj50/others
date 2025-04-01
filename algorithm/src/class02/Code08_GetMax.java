package class02;

/**
 * 用递归方法求数组arr[L..R]中的最大值
 */
public class Code08_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}
	
	// arr[L..R]范围上求最大值
	public static int process(int[] arr, int L, int R) {
		if (L == R) { // arr[L..R]范围上只有一个数，直接返回
			return arr[L];
		}
		
		// 等同于int mid = (L + R)/2
		// 分成两部分：L..mid  mid+1..R
		int mid = L + ((R - L) >> 1); // 中点
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
		
	}
}
