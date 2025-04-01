package class05;

import java.util.Arrays;

/**
 * 基数排序
 */
public class Code04_RadixSort {

	// only for no-negative value
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}
	
	/**
	 * 找到最大值占几位
	 * @param arr
	 * @return 返回位数
	 */
	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10;
		}
		return res;
	}
	
	/**
	 * 在arr的L到R范围内排序
	 * @param arr
	 * @param L
	 * @param R
	 * @param digit：在L到R范围内最大值有多少位
	 */
	public static void radixSort(int[] arr, int L, int R, int digit) {
		final int radix = 10; //以10为基底
		int i = 0, j = 0;
		//有多少个数准备多少个辅助空间
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit; d++) { //有多少位就进出几次
			
			// 10个空间
			// count[0] 当前位（d位）是0的数字有多少个
			// count[1] 当前位（d位）是（0和1）的数字有多少个
			// count[2] 当前位（d位）是（0、1和2）的数字有多少个
			// count[i] 当前位（d位）是（0~i）的数字有多少个
			int[] count = new int[radix];
			
			for (i = L; i <= R; i++) {
				j = getDigit(arr[i], d);
				count[j]++;
			}
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			for (i = R; i >= L; i--) { //i从最后一个位置出发，往前看
				j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			for (i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}
	
	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}
	

	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}
	
	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = {101,003,202,41,302};
		int[] arr2 = {101,003,202,41,302};
		radixSort(arr);
		comparator(arr2);
		printArr(arr);
		printArr(arr2);
	}
}
