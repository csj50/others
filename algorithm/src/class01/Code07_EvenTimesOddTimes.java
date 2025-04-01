package class01;

/**
 * 异或运算
 */
public class Code07_EvenTimesOddTimes {

	// arr中，只有一种数，出现奇数次
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i=0; i<arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}
	
	// arr中，有两种数，出现奇数次
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for (int i=0; i<arr.length; i++) {
			eor ^= arr[i];
		}
		// eor = a ^ b
		// eor != 0
		// eor必然有一个位置上是1
		int rightOne = eor & (~eor + 1); //获取最右侧的1
		int onlyOne = 0; //eor'
		for (int i=0; i<arr.length; i++) {
			if ((arr[i] & rightOne) != 0) { //分组异或所有这一位是1的数
				onlyOne ^= arr[i];
			}
		}
		System.out.println(onlyOne + " " + (eor ^ onlyOne)); //onlyOne是其中一个数，eor是两个数异或结果
	}
	
	//获取一个数二进制1的个数
	public static int bit1counts(int n) {
		int count = 0;
		
		while(n !=0) {
			int rightOne = n & ((~n) + 1);
			count++;
			n = n ^ rightOne;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		int a = 5;
		int b = 7;
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
		
		int[] arr1 = {3,3,2,3,1,1,1,3,1,1,1};
		printOddTimesNum1(arr1);
		
		int[] arr2 = {4,3,4,2,2,2,4,1,1,1,3,3,1,1,1,4,2,2};
		printOddTimesNum2(arr2);
		
		int c = bit1counts(9);
		System.out.println(c);
		
	}
}
