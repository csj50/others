package class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印全排列
 */
public class Code03_PrintAllPermutations {

	/**
	 * 打印一个字符串的全部排列
	 * @param str
	 * @return
	 */
	public static ArrayList<String> permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		return res;
	}
	
	/**
	 * str[i]及其往后的所有字符，都有机会来到i位置
	 * str[0]到[i]位置已经做好决定的
	 * i终止位置，str当前的样子，就是一种结果，放入ans
	 * @param str
	 * @param i
	 * @param ans
	 */
	public static void process(char[] str, int i, ArrayList<String> ans) {
		if (i == str.length) {
			ans.add(String.valueOf(str));
		}
		//如果i没有到终止位置，从i往后所有的位置都可以来到i位置
		for (int j = i; j < str.length; j++) {
			swap(str, i, j);
			process(str, i + 1, ans);
			swap(str, i, j); //恢复现场
		}
	}
	
	/**
	 * 打印一个字符串的全部排列，要求不要出现重复的排列
	 * @param str
	 * @return
	 */
	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process2(chs, 0, res);
		return res;
	}
	
	public static void process2(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
		}
		boolean[] visit = new boolean[26]; //分支限界，这个字符是否已经出现过
		for (int j = i; j < str.length; j++) {
			if (!visit[str[j] - 'a']) {
				visit[str[j] - 'a'] = true; //这种字符没出现过，登记它
				swap(str, i, j);
				process2(str, i + 1, res);
				swap(str, i, j);
			}
		}
	}
	
	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}
	
	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutation(s);
		for (String str : ans1) {
			System.out.print(" " + str);
		}
		System.out.println();
		List<String> ans2 = permutationNoRepeat(s);
		for (String str : ans2) {
			System.out.print(" " + str);
		}
	}
}
