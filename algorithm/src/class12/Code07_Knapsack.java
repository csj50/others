package class12;

/**
 * 背包问题
 */
public class Code07_Knapsack {

	/**
	 * 方式一
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int getMaxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, 0, bag);
	}
	
	/**
	 * 
	 * @param w 物品的重量
	 * @param v 物品的价值
	 * @param index 当前物品
	 * @param alreadyW 之前做的选择，已经达到的重量是多少
	 * @param bag 背包的总载重
	 * @return
	 */
	public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
		if (alreadyW > bag) {
			return -1; //返回-1表示没有这种方案
		}
		//到了终止位置，重量没超
		if (index == w.length) {
			return 0;
		}
		//两种选择
		//第一种选择，没有要当前物品的情况下，后续的最大价值
		int p1 = process(w, v, index + 1, alreadyW, bag);
		//第二种选择，要了当前的物品，后续的最大价值
		int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
		int p2 = -1;
		if (p2next != -1) { //如果后面的过程不是无效的
			p2 = v[index] + p2next; //可能性二的价值
		}
		return Math.max(p1, p2);
	}
	
	/**
	 * 方式二
	 * @param w
	 * @param v
	 * @param bag
	 * @return
	 */
	public static int maxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, bag);
	}
	
	//只剩下rest的空间了
	//index及其往后的货物自由选择，但是剩余空间不要小于0
	//返回能够获得的最大价值
	public static int process(int[] w, int[] v, int index, int rest) {
		if (rest <= 0) { //base case 1
			return -1;
		}
		if (index == w.length) {//rest>=0，base case 2
			return 0;
		}
		//有货也有空间，base case 3
		int p1 = process(w, v, index + 1, rest);
		int p2 = Integer.MIN_VALUE; //p2先认为是一个无效方案
		if (rest >= w[index]) {
			p2 = v[index] + process(w, v, index + 1, rest - w[index]);
		}
		return Math.max(p1, p2);
	}
}
