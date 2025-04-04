package class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 */
public class Code08_MaxDistance {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 方法一（比较用）
	 * @param head
	 * @return
	 */
	public static int maxDistance1(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = getPrelist(head);
		HashMap<Node, Node> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}
	
	public static ArrayList<Node> getPrelist(Node head) {
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		return arr;
	}
	
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}
	
	public static HashMap<Node, Node> getParentMap(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}
	
	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}
	
	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		Node lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}
	
	/**
	 * 方法二（用递归）
	 * @param head
	 * @return
	 */
	public static int maxDistance2(Node head) {
		return process(head).maxDistance;
	}
	
	public static class Info {
		public int maxDistance; //最大距离
		public int height; //高度
		
		public Info(int dis, int h) {
			this.maxDistance = dis;
			this.height = h;
		}
	}
	
	public static Info process(Node X) {
		if (X == null) {
			return new Info(0, 0);
		}
		//左树给我的信息
		Info leftInfo = process(X.left);
		//右树给我的信息
		Info rightInfo = process(X.right);
		
		//加工高度
		//左树和右树的最大高度，加上我自己
		int height = Math.max(leftInfo.height , rightInfo.height) + 1;
		
		//加工最大距离
		//左树的最大距离、右树的最大距离、左树的高度+右树的高度+我自己，求最大值
		int maxDistance = Math.max(Math.max(
				leftInfo.maxDistance, rightInfo.maxDistance), 
				leftInfo.height + rightInfo.height + 1);
		return new Info(maxDistance, height);
	}
	
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}
	
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.2) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}
	
	public static void main(String[] args) {
		Node head = generateRandomBST(20, 100);
		System.out.println(System.currentTimeMillis());
		System.out.println(maxDistance1(head)); //方法一，暴力方法用了近300秒
		System.out.println(System.currentTimeMillis());
		System.out.println(maxDistance2(head)); //方法二，递归，只用了2毫秒
		System.out.println(System.currentTimeMillis());
	}
}
