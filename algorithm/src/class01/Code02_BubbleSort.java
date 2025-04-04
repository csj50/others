package class01;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Code02_BubbleSort {

	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		//0~n-1
		//0~n-2
		//0~n-3
		//轮数
		for (int e=arr.length-1; e>0; e--) { //0~e
			//每轮需要交换的次数
			for (int i=0; i<e; i++) {
				if (arr[i] > arr[i+1]) {
					swap(arr, i, i+1);
				}
			}
		}
	}
	
	//交换arr的i和j位置上的值
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}
	

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}
	
	// for test
	//随机生成数组
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int arr[] = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i=0; i<arr.length; i++) {
			arr[i]=(int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		
		return arr;
	}
	
	// for test
	//复制数组
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int [arr.length];
		for (int i=0; i<arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}
	
	// for test
	//比较2个数组是否相等
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i=0; i<arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		//冒泡排序验证
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i=0; i<testTime; i++) {
			int arr1[] = generateRandomArray(maxSize, maxValue);
			int arr2[] = copyArray(arr1);
			bubbleSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		
		System.out.println(succeed ? "success" : "fail");
		
		int arr[] = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		bubbleSort(arr);
		printArray(arr);
	}
}
