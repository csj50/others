package class11;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图结构的描述
 */
public class Graph {
	public HashMap<Integer, Node> nodes; //点的集合（记录编号为0的点,编号为1点...）
	public HashSet<Edge> edges; //边的集合
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}