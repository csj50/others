package class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */
public class Code04_SerializeAndReconstructTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 先序遍历的序列化
	 * @param head
	 * @return
	 */
	public static Queue<String> preSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		pres(head, ans);
		return ans;
	}
	
	public static void pres(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}
	
	/**
	 * 先序遍历的反序列化
	 * @param prelist
	 * @return
	 */
	public static Node buildByPreQueue(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		return preb(prelist);
	}
	
	public static Node preb(Queue<String> prelist) {
		String value = prelist.poll();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = preb(prelist);
		head.right = preb(prelist);
		return head;
	}
	
	/**
	 * 按层序列化
	 * @param head
	 * @return
	 */
	public static Queue<String> levelSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value)); //加入队列的时候，序列化进去
			Queue<Node> queue = new LinkedList<>();
			queue.add(head);
			while(!queue.isEmpty()) {
				head = queue.poll();
				if (head.left != null) { //左孩子不等于空，既序列化也加队列
					ans.add(String.valueOf(head.left.value));
					queue.add(head.left);
				} else {
					ans.add(null); //左孩子为空，只序列化不加队列
				}
				if (head.right != null) { //右孩子不等于空，既序列化也加队列
					ans.add(String.valueOf(head.right.value));
					queue.add(head.right);
				} else {
					ans.add(null); //右孩子为空，只序列化不加队列
				}
			}
		}
		return ans;
	}
	
	/**
	 * 按层反序列化
	 * @param head
	 * @return
	 */
	public static Node buildByLevelQueue(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		Node head = generateNode(levelList.poll());
		Queue<Node> queue = new LinkedList<>();
		if (head != null) {
			queue.add(head);
		}
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNode(levelList.poll()); //左孩子建出来，null是占位符
			node.right = generateNode(levelList.poll()); //右孩子建出来，null是占位符
			if (node.left != null) { //左孩子不等于空，队列加左孩子
				queue.add(node.left);
			}
			if (node.right != null) { //右孩子不等于空，队列加右孩子
				queue.add(node.right);
			}
		}
		return head;
	}
	
	public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		
		Queue<String> queue = preSerial(head);
		queue.forEach(str -> {
			System.out.print(str + " ");
		});
		
		head = buildByPreQueue(queue);
		System.out.println();
		
		queue = levelSerial(head);
		queue.forEach(str -> {
			System.out.print(str + " ");
		});
		
		head = buildByLevelQueue(queue);
		System.out.println();
		
		queue = preSerial(head);
		queue.forEach(str -> {
			System.out.print(str + " ");
		});
	}
}
