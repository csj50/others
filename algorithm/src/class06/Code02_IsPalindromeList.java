package class06;

import java.util.Stack;

/**
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构
 * 回文结构：正着念和返着念都一样，例如：12aa21、12321
 */
public class Code02_IsPalindromeList {

	public static class Node {
		public int value;
		public Node next;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	// need n extra space
	public static boolean isPalindrome1(Node head) {
		Stack<Node> stack = new Stack<Node>(); //准备一个栈
		Node cur = head; //引用从头节点开始
		while (cur != null) {
			stack.push(cur); //所有节点加到栈里去
			cur = cur.next;
		}
		while (head != null) {
			//从头开始遍历，和栈中弹出的值比较
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	// need n/2 extra space（省一点空间的方法）
	public static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		//使用快慢指针，奇数定位到唯一终点，偶数定位到上中点
		Node right = head.next;
		Node cur = head;
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while (right != null) {
			stack.push(right); //把右半部分加到栈里去
			right = right.next;
		}
		while (!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	//不用容器的方法
	//把有右半部分逆序，左指针和右指针比较
	//need O(1) extra space
	public static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		
		Node n1 = head; //慢指针
		Node n2 = head; //快指针
		//找中点
		while (n2.next != null && n2.next.next != null) { // find mid node
			n1 = n1.next; // n1 -> mid
			n2 = n2.next.next; // n2 -> end
		}
		
		//右半部分逆序
		n2 = n1.next; // n2 -> right part first node
		n1.next = null; // mid.next -> null
		Node n3 = null;
		while (n2 != null) { // right part convert
			n3 = n2.next; // n3 -> save next node
			n2.next = n1; // next of right node convert
			n1 = n2; // n1 move
			n2 = n3; // n2 move
		}
		n3 = n1; // n3 -> save last node
		n2 = head; // n2 -> left first node
		
		//比较左右指针
		boolean res = true;
		while (n1 != null && n2 != null) { //check palindrome
			if (n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next; // left to mid
			n2 = n2.next; // right to mid
		}
		
		//右半部分还原
		n1 = n3.next;
		n3.next = null;
		while (n1 != null) { // recover list
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 3, 2, 1};
		Node head = new Node(1);
		Node tail = head;
		for (int i = 1; i < arr.length; i++) {
			Node node = new Node(arr[i]);
			tail.next = node;
			tail = tail.next;
		}
		
		System.out.println(isPalindrome1(head));
		System.out.println(isPalindrome2(head));
		System.out.println(isPalindrome3(head));
		
	}
}
