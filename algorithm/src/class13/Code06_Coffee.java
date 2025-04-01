package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 洗咖啡杯问题
 */
public class Code06_Coffee {

//	/**
//	 * 方法一：暴力方法
//	 * @param arr
//	 * @param n
//	 * @param a
//	 * @param b
//	 * @return
//	 */
//	public static int minTime1(int[] arr, int n, int a, int b) {
//		int[] times = new int[arr.length];
//		int[] drink = new int[n];
//		return forceMake(arr, times, 0, drink, n, a, b);
//	}
//	
//	public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
//		if (kth == 0) {
//			int[] drinkSorted = Arrays.copyOf(drink, kth);
//			Arrays.sort(drinkSorted);
//			return forceWash(drinkSorted, a, b, 0, 0, 0);
//		}
//		int time = Integer.MAX_VALUE;
//		for (int i = 0; i < arr.length; i++) {
//			int work = arr[i];
//			int pre = times[i];
//			drink[kth] = pre + work;
//			times[i] = pre + work;
//			time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
//			drink[kth] = 0;
//			times[i] = pre;
//		}
//		return time;
//	}
//	
//	// 方法一，暴力尝试洗咖啡杯的方式
//	public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
//		if (index == drinks.length) {
//			return time;
//		}
//		
//		// 选择一，当前index号咖啡杯，选择用洗咖啡机刷干净
//		int wash = Math.max(drinks[index], washLine) + a;
//		int ans1 = forceWash(drinks, a, b , index + 1, wash, Math.max(wash, time));
//		
//		// 选择二，当前index号咖啡杯，选择自然挥发
//		int dry = drinks[index] + b;
//		int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
//		return Math.min(ans1, ans2);
//	}
//	
//	// 方法二，稍微好一点的解法
//	/**
//	 * 咖啡机类
//	 */
//	public static class Machine {
//		public int timePoint; // 空闲的时间点
//		public int workTime; // 制作一杯咖啡的时间
//		
//		public Machine(int t, int w) {
//			timePoint = t;
//			workTime = w;
//		}
//	}
//	
//	public static class MachineComparator implements Comparator<Machine> {
//
//		@Override
//		public int compare(Machine o1, Machine o2) {
//			// 咖啡机开始工作的时间加上这个咖啡机制作一杯咖啡的时间之和越小的在堆顶
//			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
//		}
//	}
//	
//	// 方法二，每个人暴力尝试用每一个咖啡机给自己做咖啡，优化成贪心
//	/**
//	 * 
//	 * @param arr 咖啡机的数量和制作咖啡的时间
//	 * @param n 喝咖啡人的数量
//	 * @param a 咖啡机洗杯子的时间
//	 * @param b 咖啡杯自己挥发的时间
//	 * @return
//	 */
//	public static int minTime2(int[] arr, int n, int a, int b) {
//		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
//		for (int i = 0; i < arr.length; i++) {
//			heap.add(new Machine(0, arr[i])); // 初始化咖啡机，加入小根堆
//		}
//		int[] drinks = new int[n]; // 需要n杯咖啡
//		for (int i = 0; i < n; i++) {
//			Machine cur = heap.poll(); // 咖啡机制作咖啡
//			cur.timePoint += cur.workTime; // 空闲时间往后加
//			drinks[i] = cur.timePoint; // 喝完的时间，就是制作完咖啡的时间
//			heap.add(cur); // 咖啡机放回小根堆
//		}
//		return process(drinks, a, b, 0, 0);
//	}
	
	// 方法二，洗咖啡杯的方式和原来一样，只是这个暴力版本减少了一个可变参数
	/**
	 * 例子：process(drinks, 3, 10, 0, 0)
	 * @param drinks 每一个员工喝完咖啡的时间
	 * @param a 放到洗咖啡杯机器洗，需要多少时间，固定变量
	 * @param b 咖啡杯自己挥发干净的时间，固定变量
	 * @param index 假设drinks[0...index-1]的咖啡杯都已经决定好了
	 * @param washLine 洗咖啡的机器，在washLine这个时间点才可用
	 * @return 变干净所有的咖啡杯最早的时间点
	 */
	public static int process(int[] drinks, int a, int b, int index, int washLine) {
		/**
		 * base case当我来到最后一个咖啡杯的时候
		 */
		if (index == drinks.length - 1) {
			return Math.min(
					// 洗咖啡杯机有空的时间点和我喝完的时间点的最大值，去加上a
					Math.max(washLine, drinks[index]) + a, 
					// 我喝完的时间点，去加上b
					drinks[index] + b
					);
		}
		
		// 剩不止一杯咖啡
		
		/**
		 * 情况一：index这个咖啡杯，决定用洗咖啡杯机器洗
		 */
		// wash是我当前的咖啡杯，洗完的时间
		// 洗咖啡杯机器有空的时间点和我喝完的时间点的最大值，加上a
		int wash = Math.max(washLine, drinks[index]) + a; //洗完当前一杯咖啡杯，结束的时间点
		// next1是让index+1及其后面所有的咖啡杯变干净的时间点
		int next1 = process(drinks, a, b, index + 1, wash);
		// p1是从index往后咖啡杯变干净的最早时间点
		int p1 = Math.max(wash, next1);
		
		/**
		 * 情况二：index这个咖啡杯，决定用自己挥发干净
		 */
		// dry是我喝完的时间点，加上b
		int dry = drinks[index] + b;
		// next2是让index+1及其后面所有的咖啡杯变干净的时间点
		int next2 = process(drinks, a, b, index + 1, washLine);
		// p2是从index往后咖啡杯变干净的最早时间点
		int p2 = Math.max(dry, next2);
		
		// 每个咖啡杯有两种选择，要么选择用咖啡机来洗，要么选择挥发
		return Math.min(p1, p2);
	}
	
	/**
	 * 优化成动态规划
	 * @param drinks 每一个员工喝完咖啡的时间
	 * @param a 放到洗咖啡杯机器洗，需要多少时间，固定变量
	 * @param b 咖啡杯自己挥发干净的时间，固定变量
	 * @return
	 */
	public static int dp(int[] drinks, int a, int b) {
		if (a >= b) { // 如果洗咖啡杯的时间大于挥发的时间，全部都挥发
			return drinks[drinks.length - 1] + b;
		}
		
		// 认为 a < b 的
		int N = drinks.length; // 咖啡杯的数量
		int limit = 0; // 当前咖啡机什么时候可用
		for (int i = 0; i < N; i++) {
			limit = Math.max(limit, drinks[i]) + a;
		}
		// 建一张dp表
		int[][] dp = new int[N][limit + 1];
		// N-1行，所有的值
		for (int washLine = 0; washLine <= limit; washLine++) {
			dp[N - 1][washLine] = Math.min(
					Math.max(washLine, drinks[N - 1]) + a, 
					drinks[N - 1] + b
					);
		}
		for (int index = N - 2; index >= 0; index--) {
			for (int washLine = 0; washLine <= limit; washLine++) {
				
				int p1 = Integer.MAX_VALUE;
				int wash = Math.max(washLine, drinks[index]) + a;
				if (wash <= limit) {
					p1 = Math.max(wash, dp[index+1][wash]);
				}
				int p2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);
				
				dp[index][washLine] =  Math.min(p1, p2);
			}
		}
		
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		// arr数组是有序增加的
		int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,15};
		int a = 3;
		int b = 10;
		
		System.out.println(process(arr,a,b,0,0));
		System.out.println(dp(arr,a,b));
		
	}
}
