package class07;

import java.util.Stack;

/**
 * 非递归方式实现二叉树的先序、中序、后序遍历
 */
public class Code02_UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node (int v) {
			value = v;
		}
	}
	
	/**
	 * 用非递归方式实现先序遍历
	 * @param head
	 */
	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.add(head); //把头节点放进去
			while (!stack.isEmpty()) {
				head = stack.pop(); //弹出就打印
				System.out.print(head.value + " ");
				if (head.right != null) { //右孩子不为空，压右孩子
					stack.push(head.right);
				}
				if (head.left != null) { //左孩子不为空，压左孩子
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * 用非递归方式实现中序遍历
	 * @param head
	 */
	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			while (!stack.isEmpty() || head != null) { //栈不等于空或者头节点不等于空
				if (head != null) {
					stack.push(head); //压入头节点
					head = head.left; //头节点往左窜
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * 用非递归方式实现后序遍历
	 * @param head
	 */
	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> s2 = new Stack<Node>(); //准备一个辅助栈
			s1.push(head); //头节点进入到s1里去
			while (!s1.isEmpty()) {
				head = s1.pop(); //没一个节点从s1弹出了，不打印，加到s2里去
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}
	
	/**
	 * 用一个栈搞定后序遍历
	 * @param h
	 */
	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h); //头节点压入栈
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek(); //c指向顶部节点，没有弹出
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left); //栈中压入c的左孩子
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n6.left = null;
		n6.right = null;
		n7.left = null;
		n7.right = null;
		
		pre(n1);
		in(n1);
		pos1(n1);
		pos2(n1);
		
	}
}
