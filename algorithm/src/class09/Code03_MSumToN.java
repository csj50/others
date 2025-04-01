package class09;

/**
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 */
public class Code03_MSumToN {

	/**
	 * 暴力法
	 * @param num
	 * @return
	 */
	public static boolean isMSum1(int num) {
		for (int i = 1; i <= num; i++) {
			int sum = i;
			for (int j = i + 1; j <= num; j++) {
				if (sum + j > num) {
					break;
				}
				if (sum + j == num) {
					return true;
				}
				sum += j;
			}
		}
		return false;
	}
	
	/**
	 * 打表法
	 * @param num
	 * @return
	 */
	public static boolean isMSum2(int num) {
		if (num < 3) { //n小于3返回false
			return false;
		}
		//(num & (num - 1) == 0 是2的某次方
		//(num & (num - 1) != 0 不是2的某次方
		//核心是判断num的二进制上只有一个1
		//num是000001000
		//num-1是000000111
		//num & (num-1) 结果一定是0
		return (num & (num - 1)) != 0; //n是不是2的某次幂，是则返回false
	}
	
	public static void main(String[] args) {
		for (int num = 1; num < 200; num++) {
			System.out.println(num + " : " + isMSum1(num));
		}
		System.out.println("====================");
		for (int num = 1; num < 200; num++) {
			System.out.println(num + " : " + isMSum2(num));
		}
	}
}
