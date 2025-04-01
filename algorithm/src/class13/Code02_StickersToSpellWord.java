package class13;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 贴纸问题
 */
public class Code02_StickersToSpellWord {

	/**
	 * 方式一
	 * @param stickers
	 * @param target
	 * @return
	 */
	public static int minStickers1(String[] stickers, String target) {
		// 贴纸的数量
		int n = stickers.length;
		// 每个贴纸都转化成26个字母的数组
		int[][] map = new int[n][26]; //map是永远不变的
		// 每个贴纸的词频map生成
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++; // 1位置表达a，2位置表达2...
			}
		}
		
		// 缓存
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0); // 初始化，空字符返回需要0张贴纸
		
		return process1(dp, map, target);
	}
	
	/**
	 * 
	 * @param dp 缓存，如果t已经算过了，直接返回dp中的值
	 * @param map 每个贴纸所含字符的词频统计
	 * @param rest 剩余的字符串
	 * @return 如果返回值-1，表示map中的贴纸怎么都无法满足剩余的rest
	 */
	public static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
		// 如果缓存中有，直接从dp表中拿到答案
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		
		// 以下就是正式的递归调用过程
		
		int ans = Integer.MAX_VALUE; // 初始化ans，是要返回的贴纸数量
		int n = map.length; // n是贴纸数量
		
		// 把target转成词频的形式
		int[] tmap = new int[26]; // tmap去替代rest
		char[] target = rest.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++; // a剩几个，b剩几个，c剩几个...
		}
		
		for (int i = 0; i < n; i++) {
			// 枚举当前第一张贴纸是谁
			
			// 假设i是第一张贴纸
			if (map[i][target[0] - 'a'] == 0) { // 第i张贴纸的，target第一个字符位置词频是0，则遍历下一张贴纸
				// 说明i号贴纸不包含这个字符，用i+1号贴纸
				continue;
			}
			
			// sb是还差多少个字符
			StringBuilder sb = new StringBuilder();
			
			// i是i号贴纸，j是每一个字符的变化
			for (int j = 0; j < 26; j++) {
				if (tmap[j] > 0) { // j这个字符是target需要的
					for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
						// target词频j位置的字符数，比map里第i张贴纸j位置的字符数要大，计算剩余的字符数
						sb.append((char)('a' + j));
					}
				}
			}
			
			// s是i贴纸搞定后剩余的字符
			String s = sb.toString();
			int tmp = process1(dp, map, s);
			if (tmp != -1) {
				// ans取，之前的ans和1加后续的贴纸数，取最小值
				ans = Math.min(ans, 1 + tmp);
			}
		}
		
		// 经历过for循环之后，发现ans没有被设置过，表示没有解，返回-1，否则存正常值
		dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
		// 从缓存中返回值
		return dp.get(rest);
	}
	
	/**
	 * 方式二
	 * @param stickers
	 * @param target
	 * @return
	 */
	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		char[] str = target.toCharArray();
		int[] tmap = new int[26];
		for (char c : str) {
			tmap[c - 'a']++;
		}
		HashMap<String, Integer> dp = new HashMap<>();
		
		// 枚举每张贴纸，所有可能使用的张数，以后这张贴纸再也不碰
		// PS：方法一比方法二好，方法一只有1个可变参数，方法二有2个可变参数
		int ans = process2(map, 0, tmap, dp);
		return ans;
	}
	
	/**
	 * 
	 * @param map 每个贴纸所含字符的词频统计
	 * @param i 当前位置的贴纸用i张时
	 * @param tmap 把target转成词频的形式
	 * @param dp 缓存
	 * @return
	 */
	public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(i + "_");
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				keyBuilder.append((char)(asc + 'a') + "_" + tmap[asc] + "_");
			}
		}
		String key = keyBuilder.toString();
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		boolean finish = true;
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				finish = false;
				break;
			}
		}
		if (finish) {
			dp.put(key, 0);
			return 0;
		}
		if (i == map.length) {
			dp.put(key, -1);
			return -1;
		}
		int maxZhang = 0;
		for (int asc = 0; asc < 26; asc++) {
			if (map[i][asc] != 0 && tmap[asc] != 0) {
				maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc]));
			}
		}
		int[] backup = Arrays.copyOf(tmap, tmap.length);
		int min = Integer.MAX_VALUE;
		int next = process2(map, i + 1, tmap, dp);
		tmap = Arrays.copyOf(backup, backup.length);
		if (next != -1) {
			min = next;
		}
		for (int zhang = 1; zhang <= maxZhang; zhang++) {
			for (int asc = 0; asc < 26; asc++) {
				tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
			}
			next = process2(map, i + 1, tmap, dp);
			tmap = Arrays.copyOf(backup, backup.length);
			if (next != -1) {
				min = Math.min(min, zhang + next);
			}
		}
		int ans = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(key, ans);
		return ans;
	}
	
	public static void main(String[] args) {
		String[] arr = {"aaaa", "bbaa", "ccddd"};
		String str = "abcccccdddddbbbaaaaa";
		System.out.println(minStickers1(arr, str));
		System.out.println("====================");
		System.out.println(minStickers2(arr, str));
	}
	
}
