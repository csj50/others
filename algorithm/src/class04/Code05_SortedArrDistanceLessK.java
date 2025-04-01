package class04;

import java.util.PriorityQueue;

/**
 * 与堆有关的题目
 */
public class Code05_SortedArrDistanceLessK {

	public void sortedArrDistanceLessK(int[] arr, int k) {
		//默认小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		//把前k+1个数放入小根堆
		for (; index <= Math.min(arr.length - 1, k); index++) {
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) {
			//先弹出
			arr[i] = heap.poll();
			//再加一个
			heap.add(arr[index]);
		}
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
	
	public static void main(String[] args) {
		
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		
	}
}
