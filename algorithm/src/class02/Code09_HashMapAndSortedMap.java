package class02;

import java.util.TreeMap;

/**
 * 带排序的哈希表
 */
public class Code09_HashMapAndSortedMap {

	public static void main(String[] args) {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		
		treeMap.put(1, "我是1");
		treeMap.put(8, "我是8");
		treeMap.put(9, "我是9");
		treeMap.put(2, "我是2");
		treeMap.put(7, "我是7");
		treeMap.put(3, "我是3");
		treeMap.put(10, "我是10");
		treeMap.put(6, "我是6");
		treeMap.put(4, "我是4");
		treeMap.put(5, "我是5");
		
		//获取第一个key
		System.out.println(treeMap.firstKey());
		//获取最后一个key
		System.out.println(treeMap.lastKey());
		//获取<=5的，但离5最近的key
		System.out.println(treeMap.floorKey(5));
		//获取>=5的，但离5最近的key
		System.out.println(treeMap.ceilingKey(5));
		
		//不如哈希表的地方，所有接口，时间复杂度为O(logN)
		
	}
}
