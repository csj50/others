package class01;

/**
 * 在一个有序数组中，找某个数是否存在
 */
public class Code04_BSExist {

	public static boolean exist(int[] sortedArr, int num) {
		if (sortedArr == null || sortedArr.length == 0) {
			return false;
		}
		
		int L = 0;
		int R = sortedArr.length - 1;
		int mid = 0;
		
		while (L < R) {
			// 左移就是乘以二，右移就是除以二的意思
			// L 10亿 R 18亿，mid是整数，会溢出
			// N / 2，一个数除2，就等于这个数二进制形式带符号右移一位 N >> 1
			mid = L + ((R - L) >> 1); // 等于mid = (L + R) / 2
			if (sortedArr[mid] == num) {
				return true;
			} else if (sortedArr[mid] > num) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return sortedArr[L] == num;
	}
	
	public static void main(String[] args) {
		int arr[] = {-100,-88,-53,0,34,67,89,100};
		System.out.println(exist(arr, -90));
	}
}
