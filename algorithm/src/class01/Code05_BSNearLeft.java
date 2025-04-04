package class01;

import java.util.Arrays;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 */
public class Code05_BSNearLeft {

	// 在arr上，找满足>=value的最左位置
	public static int nearestIndex(int[] arr, int value) {
		int L = 0;
		int R = arr.length - 1;
		int index = -1; // 记录最左的对号
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}
	
	// for test
	// 对数器
	public static int test(int[] arr, int value) {
		for (int i=0; i<arr.length; i++) {
			if (arr[i] >= value) {
				return i;
			}
		}
		return -1;
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
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i=0; i<testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			Arrays.sort(arr);
			int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
			if (test(arr, value) != nearestIndex(arr, value)) {
				printArray(arr);
				System.out.println(value);
				System.out.println(test(arr, value));
				System.out.println(nearestIndex(arr, value));
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "success" : "fail");
	}
	
}
