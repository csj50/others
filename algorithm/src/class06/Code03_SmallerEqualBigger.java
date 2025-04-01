package class06;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class Code03_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		int i = 0; //数一遍个数
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		
		Node[] nodeArr = new Node[i];
		i = 0;
		cur = head;
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur; //放入数组
			cur = cur.next;
		}
		arrPartition(nodeArr, pivot); //在数组上分割
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
	}
	
	public static void arrPartition(Node[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				swap(nodeArr, --big, index);
			}
		}
	}
	
	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}
	
	public static Node listPartition2(Node head, int pivot) {
		Node sH = null; // 小于区域的头
		Node sT = null; // 小于区域的尾
		Node eH = null; // 等于区域的头
		Node eT = null; // 等于区域的尾
		Node mH = null; // 大于区域的头
		Node mT = null; // 大于区域的尾
		Node next = null; // save next node
		// every node distributed to three lists
		while (head != null) {
			next = head.next; //提前记录head.next
			head.next = null;
			if (head.value < pivot) { //当前节点小于划分值
				if (sH == null) { //头为空
					sH = head; //当前节点既做头又做尾
					sT = head;
				} else {
					sT.next = head; //老尾巴的next指向当前节点
					sT = head; //当前节点变成新尾巴
				}
			} else if (head.value == pivot) {
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (mH == null) {
					mH = head;
					mT = head;
				} else {
					mT.next = head;
					mT = head;
				}
			}
			head = next; //head来到next的位置
		}
		// 小于区域的尾巴连等于区域的头
		if (sT != null) { //如果小于区域的尾巴不为空
			sT.next = eH; //小于区域的尾巴指向等于区域的头
			//如果等于区域是空的话，等于区域的尾巴变成小于区域的尾巴
			//如果不为空，保持等于区域的尾巴
			eT = eT == null ? sT : eT; //下一步，谁去连大于区域的头，谁就变成eT
		}
		// 等于区域的尾巴连大于区域的头
		if (eT != null) {
			eT.next = mH;
		}
		//返回串起来所有链表的最左的节点
		return sH != null ? sH : (eH != null ? eH : mH);
	}
	
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		//4 -> 2 -> 3 -> 5 -> 6 -> 1 -> 3 -> 0
		Node n1 = new Node(4);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(5);
		Node n5 = new Node(6);
		Node n6 = new Node(1);
		Node n7 = new Node(3);
		Node n8 = new Node(0);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = null;
		Node head = listPartition2(n1, 3);
		printLinkedList(head);
	}
	
}
