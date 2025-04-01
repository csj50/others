package class08;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head，返回这棵二叉树中最大的二叉搜索子树的头节点
 */
public class Code04_MaxSubBSTSize {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}
	
	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}
	
	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
	}
	
	public static int maxSubBSTSize2(Node head) {
		if (head == null) {
			return 0;
		}
		return process(head).maxSubBSTSize;
	}
	
	/**
	 * 任何子树都返回四个信息
	 */
	public static class Info {
		public boolean isAllBST; //子树整体是不是BST
		public int maxSubBSTSize; //满足搜索二叉树条件的最大子树的大小
		public int min; //整棵树的最小值
		public int max; //整棵树的最大值
		
		public Info(boolean is, int size, int mi, int ma) {
			isAllBST = is;
			maxSubBSTSize = size;
			min = mi;
			max = ma;
		}
	}
	
	public static Info process(Node X) {
		if (X == null) { //如果是空树
			return null;
		}
		Info leftInfo = process(X.left); //左树可以给我四个信息
		Info rightInfo = process(X.right); //右树可以给我四个信息
		
		//加工出我的四个信息
		boolean isBST = false;
		int min = X.value;
		int max = X.value;
		int maxSubBSTSize = 0;
		
		if (leftInfo != null) { //如果左树不空
			min = Math.min(min, leftInfo.min); //我的最小值，和左树信息中最小值比较
			max = Math.max(max, leftInfo.max); //我的最大值，和左树信息中最大值比较
			maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize); //我的最大子树大小和左树信息中的最大子树大小比较
		}
		if (rightInfo != null) { //如果右树不空
			min = Math.min(min, rightInfo.min); //我的最小值，和右树信息中最小值比较
			max = Math.max(max, rightInfo.max); //我的最大值，和右树信息中最大值比较
			maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize); //我的最大子树大小和右树信息中的最大子树大小比较
		}
		
		//是不是BST？
		//X的值要大于左树的最大值，小于右树的最小值
//		if ((leftInfo == null ? true : (leftInfo.isAllBST && leftInfo.max < X.value))
//				&& (rightInfo == null ? true : (rightInfo.isAllBST && rightInfo.min > X.value))) 
		if (
				//更清晰的写法
				(leftInfo == null ? true : leftInfo.isAllBST) //左树整体需要是搜索二叉树
				&& 
				(rightInfo == null ? true : rightInfo.isAllBST) //右树整体需要是搜索二叉树
				&& 
				(leftInfo == null ? true : leftInfo.max < X.value) //X的值要大于左树的最大值
				&& 
				(rightInfo == null ? true : rightInfo.min > X.value) //X的值要小于右树的最小值
				)
		{
			isBST = true;
			//以X为头整体的树是搜索二叉树时，计算最大子树的大小，左树大小加右树大小加X这一个节点的大小
			maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
					+ (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
		}
		return new Info(isBST, maxSubBSTSize, min, max);
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
		System.out.println(maxSubBSTSize1(head)); //方法一，非常耗时
		System.out.println(maxSubBSTSize2(head)); //方法二，递归，很快
	}
}
