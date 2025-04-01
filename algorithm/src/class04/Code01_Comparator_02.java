package class04;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 大根堆按照小根堆排序
 */
public class Code01_Comparator_02 {

	public static void printArray(Integer[] arr) {
		if (arr == null) {
			return;
		}
		for (int i=0; i<arr.length; i++) {
			System.out.println(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 自定义关于整数的比较器
	 * 返回负数，o1放在上面的情况
	 * 返回正数，o2放在上面的情况
	 */
	public static class MyComp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
		
	}
	
	public static class AComp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("====================");
		//大根堆按照小根堆排序
		PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComp());
		heap.add(5);
		heap.add(7);
		heap.add(3);
		heap.add(0);
		heap.add(2);
		heap.add(5);
		
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
		System.out.println("====================");
	}
}
