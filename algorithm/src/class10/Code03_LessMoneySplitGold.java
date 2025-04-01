package class10;

import java.util.PriorityQueue;

/**
 * 分割最小代价问题
 */
public class Code03_LessMoneySplitGold {

	/**
	 * 暴力方法
	 * @param arr
	 * @return
	 */
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}
	
	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}
	
	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}
	
	/**
	 * 贪心策略
	 * @param arr
	 * @return
	 */
	public static int lessMoney2(int[] arr) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pq.size() > 1) {
			cur = pq.poll() + pq.poll(); // 每次弹出2个数，合成1个数
			sum += cur; // 累加
			pq.add(cur); // 把合成的数塞回小根堆
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,9,6,4,1,2,89,56,4,3};
		System.out.println(lessMoney1(arr));
		System.out.println("==========");
		System.out.println(lessMoney2(arr));
	}
}
