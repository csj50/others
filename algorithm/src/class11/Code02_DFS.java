package class11;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>(); //set结构保证不重复进
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) { //当前没有进过栈的邻居节点
					stack.push(cur); //把它的父节点重新压回去
					stack.push(next); //在把这个邻居压倒栈里
					set.add(next); //在set里登记这个邻居
					System.out.println(next.value);
					break; //剩下的邻居不遍历了
				}
			}
		}
	}
}
