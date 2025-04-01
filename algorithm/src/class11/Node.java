package class11;

import java.util.ArrayList;

/**
 * 点结构的描述
 */
public class Node {
	public int value; //值
	public int in; //入度
	public int out; //出度
	public ArrayList<Node> nexts; //从这个点向外指向的直接邻居的点
	public ArrayList<Edge> edges; //从这个点出发的边
	
	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
		
	}
}
