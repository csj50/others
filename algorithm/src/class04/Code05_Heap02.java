package class04;

import java.util.PriorityQueue;

/**
 * 系统自带的堆结构
 */
public class Code05_Heap02 {

	public static void main(String[] args) {
		// 优先级队列，默认是小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		heap.add(5);
		heap.add(7);
		heap.add(3);
		heap.add(0);
		heap.add(2);
		heap.add(5);
		
		while(!heap.isEmpty()) {
			System.out.println(heap.poll());
		}
	}
}
