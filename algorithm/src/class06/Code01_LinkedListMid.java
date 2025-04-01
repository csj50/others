package class06;

import java.util.ArrayList;

/**
 * 快慢指针
 */
public class Code01_LinkedListMid {

	public static class Node {
		public int value;
		public Node next;
		
		public Node(int v) {
			value = v;
		}
	}
	
	/**
	 * 奇数情况返回中点，偶数情况返回上中点
	 * @param head：头节点
	 * @return
	 */
	public static Node midOrUpMidNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 链表有3个点或以上
		Node slow = head.next;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/**
	 * 奇数情况返回中点，偶数情况返回下中点
	 * @param head：头节点
	 * @return
	 */
	public static Node midOrDownMidNode(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 初始设置不一样
		Node slow = head.next;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/**
	 * 奇数情况返回中点前一个，偶数情况返回上中点前一个
	 * @param head：头节点
	 * @return
	 */
	public static Node midPreOrUpMidPreNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		
		Node slow = head;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/**
	 * 奇数情况返回中点前一个，偶数情况返回下中点前一个
	 * @param head：头节点
	 * @return
	 */
	public static Node midPreOrDownMidPreNode(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		if (head.next.next == null) {
			return head;
		}
		Node slow = head;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	public static Node right1(Node head) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		ArrayList<Node> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 1) / 2);
	}
	
	public static Node right2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		ArrayList<Node> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get(arr.size() / 2);
	}
	
	public static Node right3(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node cur = head;
		ArrayList<Node> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 3) / 2);
	}
	
	public static Node right4(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		Node cur = head;
		ArrayList<Node> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 2) / 2);
	}
	
	public static void main(String[] args) {
		
		Node head = new Node(0);
		Node tail = head;
		
		for (int i = 1; i < 10; i++) {
			Node node = new Node(i);
			tail.next = node;
			tail = tail.next;
		}
		
		System.out.println(midOrUpMidNode(head).value);
		System.out.println(right1(head).value);
		
		System.out.println(midOrDownMidNode(head).value);
		System.out.println(right2(head).value);
		
		System.out.println(midPreOrUpMidPreNode(head).value);
		System.out.println(right3(head).value);
		
		System.out.println(midPreOrDownMidPreNode(head).value);
		System.out.println(right4(head).value);
	}
}
