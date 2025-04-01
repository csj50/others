package class12;

import java.util.Stack;

/**
 * 用递归，栈逆序
 */
public class Code04_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = func(stack);
		reverse(stack);
		stack.push(i);
	}
	
	public static int func(Stack<Integer> stack) {
		int result = stack.pop(); //从栈中拿出一个
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = func(stack); //需要一个临时变量存储
			stack.push(result);
			return last;
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}
	
}
