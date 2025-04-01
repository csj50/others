package class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层级遍历二叉树
 */
public class Code03_LevelTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
		}
	}
	
	public static void level(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head); //加入队列
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); //弹出一个
			System.out.println(cur.value); //打印
			if (cur.left != null) { //先加左
				queue.add(cur.left);
			}
			if (cur.right != null) { //再加右
				queue.add(cur.right);
			}
		}
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		
		level(head);
		
	}
}
