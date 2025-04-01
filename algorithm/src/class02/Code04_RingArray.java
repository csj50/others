package class02;

/**
 * 环形数组-实现栈
 */
public class Code04_RingArray {

	public static class MyStack {
		private int[] arr;
		private int pushi;
		private int polli;
		private int size;
		private final int limit;
		
		public MyStack(int limit) {
			arr = new int[limit];
			pushi = 0;
			polli = 0;
			size = 0;
			this.limit = limit; // 最大的大小
		}
		
		// 加元素
		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("栈满了，不能再加了");
			}
			size++;
			arr[pushi] = value;
			pushi = nextIndex(pushi);
		}
		
		// 减元素
		public int pop() {
			if (size == 0) {
				throw new RuntimeException("栈空了，不能再拿了");
			}
			size--;
			int ans = arr[polli]; //从poll index给出答案
			polli = nextIndex(polli); //poll index来到下一个位置
			return ans;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {
			return i < limit - 1 ? i + 1 : 0;
		}
	}
	
}
