package class04;

/**
 * 不用递归实现堆排序
 */
public class Code04_HeapSort {

	// 堆排序，整个里面没有递归，额外空间复杂度O(1)
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		System.out.println("step1...start");
		
		// 经典堆排序
		// 第一步
		// 总体是O(N*logN)
//		for (int i = 0; i < arr.length; i++) { //这一步是O(N)
//			heapInsert(arr, i); //这一步是O(logN)
//		}
		
		// 这是上面堆排序的优化
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		
		System.out.println("step1...end");
		
		int heapSize = arr.length;
		swap(arr, 0, --heapSize);
		
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println("step2...start");
		
		// 第二步
		// 总体是O(N*logN)
		while(heapSize > 0) { //这一步是O(N)
			heapify(arr, 0, heapSize); //这一步是O(logN)
			swap(arr, 0, --heapSize); //这一步是O(1)
			
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
		System.out.println("step2...end");
	}
	
	public static void heapInsert(int[] arr, int index) {
		while(arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
			
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
			
		}
	}
	
	/**
	 * 从index位置往下看，树不断的下沉
	 * 停止条件：我的孩子都不再比我大，或者已经没有孩子了
	 * @param arr
	 * @param index
	 * @param heapSize
	 */
	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1; //左孩子的下标
		while (left < heapSize) { //左孩子没越界
			// 左右两个孩子中，谁大，谁把自己的下标给largest
			int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
			// 判断值有没有父节点大
			largest = arr[largest] > arr[index] ? largest : index;
			// 说明左右孩子都没有index大
			if (largest == index) {
				break;
			}
			swap(arr, largest, index);
			index = largest; //index位置往下沉
			left = index * 2 + 1; //继续找下一步的左孩子
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		int[] arr = {6, 5, 3, 2, 1, 0, 9};
		heapSort(arr);
		
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
}
