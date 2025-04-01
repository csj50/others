package class13;

/**
 * 最长公共子序列问题
 */
public class Code05_lcse {

	public static int lcse(char[] str1, char[] str2) {
		
		//准备一个dp表
		int[][] dp = new int[str1.length][str2.length];
		
		//dp[0][0]位置
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		
		for (int i = 1; i < str1.length; i++) {
			//填i行0列的所有值
			//一旦某个str1的字符等于str2[0]，后面的都是1
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		for (int j = 1; j < str2.length; j++) {
			//一旦某个str2的字符等于str1[0]，后面的都是1
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[str1.length - 1][str2.length -1];
	}
	
	public static void main(String[] args) {
		String str1 = "ab1cd2ef345gh";
		String str2 = "opq123rs4tx5yz";
		System.out.println(lcse(str1.toCharArray(), str2.toCharArray()));
	}
}
