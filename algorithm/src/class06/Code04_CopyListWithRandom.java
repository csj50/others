package class06;

import java.util.HashMap;

/**
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 */
public class Code04_CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 用哈希表的方法
	 * @param head
	 * @return
	 */
	public static Node copyListWithRand1(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			// cur 老节点
			// map.get(cur) 新节点
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}
	
	/**
	 * 不用哈希表的方法
	 * @param head
	 * @return
	 */
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// 克隆出来的node插入到原来node中间
		// 1 -> 2
		// 1 -> 1' -> 2
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null; //克隆节点
		// 设置克隆节点的rand指针
		// 1 -> 1' -> 2 -> 2'
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		// 分离
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}
	
	public static void printLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		
		System.out.print("rand: ");
		while (cur != null) {
			if (cur.rand != null) {
				System.out.print(cur.rand.value + " ");
			} else {
				System.out.print("null ");
			}
			
			cur = cur.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		n1.next = n2;
		n2.next = n3;
		n1.rand = n3;
		n2.rand = n1;
		n3.rand = null;
		Node head = copyListWithRand2(n1);
		printLinkedList(head);
	}
}
