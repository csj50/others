package class12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印子序列
 */
public class Code02_PrintAllSubsquences {

	/**
	 * 打印一个字符串的全部子序列
	 * @param s
	 * @return
	 */
	public static List<String> subs(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process1(str, 0, ans, path);
		return ans;
	}
	
	/**
	 * 
	 * @param str 固定参数
	 * @param index 现在来到的位置，当前字符 要or不要 做决策
	 * @param ans 如果index来到了str中的终止位置，要把沿途路径形成的答案扔到answer里去
	 * @param path 之前作出的选择，就是path，沿途路径
	 */
	public static void process1(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) { //当index到终止位置
			ans.add(path);
			return;
		}
		String no = path;
		//所有路径都是存到同一个ans变量里
		process1(str, index + 1, ans, no); //不把index的字符往下传
		String yes = path + String.valueOf(str[index]);
		process1(str, index + 1, ans, yes); //把index的路径往下传
	}
	
	/**
	 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
	 * @param s
	 * @return
	 */
	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		List<String> ans = new ArrayList<>();
		for (String cur : set) {
			ans.add(cur);
		}
		return ans;
	}
	
	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, set, yes);
	}
	
	public static void main(String[] args) {
		String str1 = "abc";
		List<String> list1 = subs(str1);
		for(String s : list1) {
			System.out.print(" " + s);
		}
		System.out.println();
		String str2 = "aaa";
		List<String> list2 = subsNoRepeat(str2);
		for (String s : list2) {
			System.out.print(" " + s);
		}
	}
	
}
