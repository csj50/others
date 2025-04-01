package class02;

/**
 * 把给定值都删除
 */
public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;
		
		public Node(int data) {
			value = data;
		}
	}
	
	public static Node removeValue(Node head, int num) {
		// 先看头部要删多少
		// 遍历链表找到第一个不是num值的节点
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		
		// head来到第一个不需要删的位置
		// 后面用cur来遍历，head你就别动了
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next; // 跳过值为num的节点
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		
		return head;
	}
}
