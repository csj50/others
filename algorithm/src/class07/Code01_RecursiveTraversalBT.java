package class07;

/**
 * 二叉树的先序、中序、后序遍历
 */
public class Code01_RecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			value = v;
		}
	}
	
	/**
	 * 先序遍历方式打印
	 * @param head
	 */
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		pre(head.left);
		pre(head.right);
	}
	
	/**
	 * 中序遍历方式
	 * @param head
	 */
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.println(head.value);
		in(head.right);
	}
	
	/**
	 * 后序遍历方式
	 * @param head
	 */
	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}
	
	/**
	 * 是不是发现上面三个函数结构一样，只是打印位置不同
	 * @param head
	 */
	public static void f(Node head) {
		if (head == null) {
			return;
		}
		// 把打印行为放在这儿就是先序打印
		f(head.left);
		// 把打印行为放在这儿就是中序打印
		f(head.right);
		// 把打印行为放在这儿就是后序打印
	}
	
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n6.left = null;
		n6.right = null;
		n7.left = null;
		n7.right = null;
		
		pre(n1);
		System.out.println();
		in(n1);
		System.out.println();
		pos(n1);
		
	}
}
