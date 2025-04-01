package class05;

import java.util.Arrays;

/**
 * 计数排序
 */
public class Code03_CountSort {

	// only for 0~200 value
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
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
		countSort(arr);
		comparator(arr2);
		printArr(arr);
		printArr(arr2);
	}
}
