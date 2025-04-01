package class10;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 做项目花费和利润问题
 */
public class Code05_IPO {

	/**
	 * 
	 * @param K  你只能串行的最多做K个项目
	 * @param W  你初始的资金
	 * @param Profits  表示i号项目在扣除花费之后还能挣到的钱（利润）
	 * @param Capitals  表示i号项目的花费
	 * @return
	 */
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capitals) {
		//根据花费的小根堆
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		//根据利润的大根堆
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capitals[i]));
		}
		//控制做多少轮
		for (int i = 0; i < K; i++) {
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			W += maxProfitQ.poll().p;
		}
		return W;
	}
	
	public static class Program {
		public int p;
		public int c;
		
		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
	
	/**
	 * 根据花费组织的小根堆的比较器
	 */
	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}
	}
	
	/**
	 * 根据利润组织的大根堆的比较器
	 */
	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}
	}
	
	public static void main(String[] args) {
		int K = 10;
		int W = 5;
		int[] Capitals = {10, 1, 4, 5, 3, 6, 3, 2, 1};
		int[] Profits = {1, 1, 1, 1, 1, 1, 1, 1, 1};
		int result = findMaximizedCapital(K, W, Profits, Capitals);
		System.out.println(result);
		
	}
}
