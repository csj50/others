package class06;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点
 * 如果不相交，返回null
 */
public class Code05_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		//两个都是无环链表
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop2, head2, loop2);
		}
		//loop1和loop2一个为空，一个不为空，不可能相交
		
		return null;
	}
	
	/**
	 * 找到链表第一个入环节点，如果无环，返回null
	 * @param head
	 * @return
	 */
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node n1 = head.next; // n1是slow指针
		Node n2 = head.next.next; // n2是fast指针
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		//快指针回到开头
		n2 = head;
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
	
	/**
	 * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		// 如果end1不等于end2，不可能相交
		if (cur1 != cur2) {
			return null;
		}
		
		// end1等于end2
		
		// n：链表1长度减去链表2长度的值
		cur1 = n > 0 ? head1 : head2; //谁长，谁的头部给cur1
		cur2 = cur1 == head1 ? head2 : head1; //谁短，谁的头部给cur2
		
		// n取绝对值
		n = Math.abs(n);
		
		// 长链表先走差值步，让n减成0
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		// 然后短链表再一起，他们相遇的时候，就是第一个相交的节点
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		
		return cur1;
	}
	
	/**
	 * 两个有环链表，返回第一个相交节点，如果不相交返回null
	 * @param head1 第一个链表头节点
	 * @param loop1 第一个链表入环节点
	 * @param head2 第二个链表头节点
	 * @param loop2 第二个链表入环节点
	 * @return
	 */
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) { //遇到loop1停
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) { //遇到loop2停
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			cur1 = loop1.next; // 从loop1的下一个节点走
			while (cur1 != loop1) { // 如果转回自己了
				if (cur1 == loop2) { // 如果转的过程中，遇到了loop2
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		//链表A：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 3
		//链表B：10 -> 11 -> 12 -> 6
		Node a1 = new Node(1);
		Node a2 = new Node(2);
		Node a3 = new Node(3);
		Node a4 = new Node(4);
		Node a5 = new Node(5);
		Node a6 = new Node(6);
		Node a7 = new Node(7);
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = a5;
		a5.next = a6;
		a6.next = a7;
		a7.next = a3;
		
		Node b10 = new Node(10);
		Node b11 = new Node(11);
		Node b12 = new Node(12);
		b10.next = b11;
		b11.next = b12;
		b12.next = a6;
		
		Node result = getIntersectNode(a1, b10);
		System.out.println("Node: " + result.value);
	}
}
