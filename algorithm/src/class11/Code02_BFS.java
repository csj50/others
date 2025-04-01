package class11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 */
public class Code02_BFS {

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>(); //图会有环的结构，用一个set过滤
		queue.add(node);
		set.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); //队列中弹出一个节点
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!set.contains(next)) { //它的直接邻居没有进过set的，才进去
					set.add(next);
					queue.add(next);
				}
			}
		}
	}
	
	
}
