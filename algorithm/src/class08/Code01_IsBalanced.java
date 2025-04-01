package class08;

/**
 * 这棵二叉树是不是平衡二叉树
 */
public class Code01_IsBalanced {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value = data;
		}
	}
	
	/**
	 * 方法一
	 * @param head
	 * @return
	 */
	public static boolean isBalanced1(Node head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}
	
	public static int process1(Node head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * 方法二
	 * @param head
	 * @return
	 */
	public static boolean isBalanced2(Node head) {
		return process2(head).isBalanced;
	}
	
	/**
	 * 对左子树、右子树要求一样
	 * Info是信息返回的结构体
	 */
	public static class Info {
		public boolean isBalanced;
		public int height;
		
		public Info(boolean b, int h) {
			isBalanced = b;
			height = h;
		}
	}
	
	/**
	 * 以X为头的树
	 * @param X
	 * @return
	 */
	public static Info process2(Node X) {
		if (X == null) {
			return new Info(true, 0); //空树，返回是平衡树，深度是0
		}
		
		Info leftInfo = process2(X.left); //左树上是否平衡，左树上高度是多少
		Info rightInfo = process2(X.right); //右树上是否平衡，右树上高度是多少
		
		//X节点的高度，是左树的高度和右树的高度，取最大值，加当前头节点的高度
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		//看是否平衡
		boolean isBalanced = true;
		//左树不平衡，或者右树不平衡，或者左右树高度差大于1
		if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		return new Info(isBalanced, height);
	}
	
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}
	
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.6) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}
	
	public static void main(String[] args) {
		Node head = generateRandomBST(100, 100);
		System.out.println(isBalanced1(head));
		System.out.println(isBalanced2(head));
	}
}
