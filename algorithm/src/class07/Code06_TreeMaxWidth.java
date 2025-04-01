package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树的最大宽度
 */
public class Code06_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 用map实现计算二叉树的宽度
	 * @param head
	 * @return
	 */
	public static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		//value是Node，key是一个整数，表示key在哪一层
		HashMap<Node, Integer> levelMap = new HashMap<>();
		levelMap.put(head, 1);
		int curLevel = 1; //当前在第几层
		int curLevelNodes = 0; //当前层宽度目前是多少
		int max = 0; //所有层的宽度的最大值
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curNodeLevel = levelMap.get(cur); //当前节点在哪一层
			if (cur.left != null) {
				levelMap.put(cur.left, curNodeLevel + 1);
				queue.add(cur.left);
			}
			if (cur.right != null) {
				levelMap.put(cur.right, curNodeLevel + 1);
				queue.add(cur.right);
			}
			//当前节点所在层数和目前统计的层数一样
			if (curNodeLevel == curLevel) {
				curLevelNodes++; //当前层的宽度加1
			} else {
				max = Math.max(max, curLevelNodes);
				curLevel++;
				curLevelNodes = 1;
			}
		}
		max = Math.max(max, curLevelNodes);
		return max;
	}
	
	/**
	 * 不用map实现计算二叉树的宽度
	 * @param head
	 * @return
	 */
	public static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		Node curEnd = head; //当前层，最右节点是谁
		Node nextEnd = null; //如果有下一层，最右节点是谁
		int max = 0;
		int curLevelNodes = 0; //当前层的节点数
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); //队列弹出一个节点
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevelNodes++; //当前层加加
			if (cur == curEnd) { //如果当前节点是当前层最右的节点
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		
		System.out.println(maxWidthUseMap(head));
		System.out.println(maxWidthNoMap(head));
		
	}
}
