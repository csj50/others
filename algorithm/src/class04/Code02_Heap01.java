package class04;

/**
 * 数组作为堆
 * 堆排序
 */
public class Code02_Heap01 {

	public static class MyMaxHeap {
		private int[] heap; //一个数组作为堆
		private final int limit;
		private int heapSize;
		
		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}
		
		public boolean isEmpty() {
			return heapSize == 0;
		}
		
		public boolean isFull() {
			return heapSize == limit;
		}
		
		public void push(int value) {
			if (heapSize == limit) { //heapSize到极限了
				throw new RuntimeException("heap is full");
			}
			heap[heapSize] = value; //value放在heapSize位置
			heapInsert(heap, heapSize++);
		}
		
		/**
		 * 用户获取最大值，返回最大值并且在大根堆中把最大值删掉
		 * 剩下的数，依然保持大根堆组织
		 * @return
		 */
		public int pop() {
			int ans = heap[0]; //返回0位置的数
			swap(heap, 0, --heapSize); //把最后一个位置的值和0位置的值交换
			heapify(heap, 0, heapSize); //从0位置开始，做heapify
			return ans;
		}
		
		public void heapInsert(int[] arr, int index) {
			// arr[index]是新进来的数
			// 停止条件：
			// （1）arr[index]不比arr[index父]大了，停
			// （2）当heapSize=0，arr[(index - 1) / 2]是它自己，停
			while (arr[index] > arr[(index - 1) / 2]) { //比较arr[index]节点值和它的父亲节点的值
				swap(arr, index, (index - 1) / 2); //交换
				index = (index - 1) / 2; //然后index来到它父亲节点的位置
			}
		}
		
		/**
		 * 从index位置往下看，树不断的下沉
		 * 停止条件：我的孩子都不再比我大，或者已经没有孩子了
		 * @param arr
		 * @param index
		 * @param heapSize
		 */
		private void heapify(int[] arr, int index, int heapSize) {
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
		
		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}
	
	/**
	 * 绝对对的大根堆
	 * 用来比较
	 */
	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;
		
		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public boolean isFull() {
			return size == limit;
		}
		
		//加的时候直接加在最后面
		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}
		
		//弹出最大值的时候，每次都全部遍历一遍
		public int pop() {
			int maxIndex = 0;
			for (int i = 0; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}
	}
	
	public static void main(String[] args) {
		int value = 1000;
		int limit = 100;
		int testTimes = 1000000; //测试100万次，随机的次数加入和弹出
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MyMaxHeap my = new MyMaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) { //如果我是空的，共同加入
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) { //如果我是满的，共同弹出
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else { //如果不空，不满
					if (Math.random() < 0.5) { //以0.5的概率加入
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else { //以剩下0.5的概率弹出
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}
	
}
