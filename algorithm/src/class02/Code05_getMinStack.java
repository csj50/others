package class02;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 */
public class Code05_getMinStack {

	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;
		
		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}
		
		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum <= this.getMin()) {
				this.stackMin.push(newNum);
			} else {
				this.stackMin.push(this.getMin());
			}
			
			this.stackData.push(newNum);
		}
		
		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("栈是空的");
			}
			
			this.stackMin.pop();
			return this.stackData.pop();
			
		}
		
		public int getMin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("栈是空的");
			}
			return this.stackMin.peek();
		}
		
		
	}
}
