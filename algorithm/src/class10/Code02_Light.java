package class10;

import java.util.HashSet;

/**
 * 点亮灯安排
 */
public class Code02_Light {

	/**
	 * 暴力方法
	 * @param road
	 * @return
	 */
	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}
	
	// str[index]以后的位置，自由选择放灯还是不放灯
	// str[0..index-1]的位置，已经做完决定了，哪些位置放了灯的，存在lights里
	// 返回：要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		
		if (index == str.length) { // 当index来到最后一个位置的时候
			// 收集到的方案，能否把所有居民点都照亮
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') {
					// i这个点是否被照亮？i-1位置有灯，或者i位置有灯，或者i+1位置有灯
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE; // 返回最大值作为无效解
					}
				}
			}
			return lights.size(); // 所有点被照亮，返回这个有效解的大小
		} else { // str还没结束
			
			// 当前i位置不放灯，返回后续的最小值
			int no = process(str, index + 1, lights);
			
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') { // 如果i位置是点，做出放灯的决定，yes才有效
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index); // 恢复现场，因为始终用一个lights来搞
			}
			return Math.min(no, yes);
		}
	}
	
	/**
	 * 贪心策略
	 * @param road
	 * @return
	 */
	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int index = 0;
		int light = 0;
		while (index < str.length) {
			if (str[index] == 'X') { // index位置是X
				index++;
			} else { // index位置是点
				light++; // 这个位置一定会放灯，可能放i位置，也可能放i+1位置
				if (index + 1 == str.length) {
					break;
				} else {
					if (str[index + 1] == 'X') {
						index = index + 2;
					} else {
						index = index + 3;
					}
				}
			}
		}
		return light;
	}
	
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}
	
	public static void main(String[] args) {
		String road = randomString(100);
		System.out.println(minLight1(road));
		System.out.println("==========");
		System.out.println(minLight2(road));
	}
	
}
