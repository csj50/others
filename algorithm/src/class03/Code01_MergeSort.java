package class03;

/**
 * 归并排序
 */
public class Code01_MergeSort {

	/**
	 * 变成整体有序
	 * @param arr
	 * @param L 数组下标
	 * @param M 数组下标
	 * @param R 数组下标
	 */
	public static void merge(int[] arr, int L, int M, int R) {
		int [] help = new int[R - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = M + 1;
		while (p1 <= M && p2 <= R) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		//要么p1越界了，要么p2越界了
		//看左边小于等于M
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		//还是右边小于等于R
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
	}
	
	/**
	 * 递归方法实现
	 * arr[L...R]范围上，变成有序
	 * @param arr
	 */
	public static void mergeSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}
	
	public static void process(int[] arr, int L, int R) {
		if (L == R) { // base case
			return;
		}
		int mid = L + ((R - L) >> 1);
		process(arr, L, mid);
		process(arr, mid + 1, R);
		merge(arr, L, mid, R);
	}
	
	/**
	 * 非递归方法实现
	 * @param arr
	 */
	public static void mergeSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		int mergeSize = 1;
		while (mergeSize < N) {
			int L = 0;
			while (L < N) {
				int M = L + mergeSize - 1;
				if (M >= N) {
					break;
				}
				int R = Math.min(M + mergeSize, N - 1);
				merge(arr, L, M, R);
				L = R + 1;
			}
			if (mergeSize > N / 2) { //防止溢出
				break;
			}
			mergeSize <<= 1; //左移后赋值，相当于乘2后赋值
		}
	}
	
	public static void main(String[] args) {
		int[] aaa = {99, 100, 50, 70, 88, 10, 9, 35, 1, 98};
		int[] bbb = {99, 100, 50, 70, 88, 10, 9, 35, 1, 98};
		
		mergeSort1(aaa);
		for (int i: aaa) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		mergeSort2(bbb);
		for (int i: bbb) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
}
