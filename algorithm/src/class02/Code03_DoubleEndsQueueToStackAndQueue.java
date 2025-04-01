package class02;

/**
 * 双向链表-实现栈和队列
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

	// 双向链表的节点类型
	public static class Node<T> {
		public T value;
		public Node<T> last;
		public Node<T> next;
		
		public Node(T data) {
			value = data;
		}
	}
	
	// 实现双向链表
	public static class DoubleEndsQueue<T> {
		public Node<T> head;
		public Node<T> tail;
		
		// 从头部加节点
		public void addFromHead(T value) {
			Node<T> cur = new Node<T>(value);
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				cur.next = head;
				head.last = cur;
				head = cur;
				
			}
		}
		
		// 从尾部加节点
		public void addFromBottom(T value) {
			Node<T> cur = new Node<T>(value);
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				cur.last = tail;
				tail = cur;
				
			}
		}
		
		// 从头部获取节点
		public T popFromHead() {
			if (head == null) {
				return null;
			}
			Node<T> cur = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				cur.next = null;
				head.last = null;
			}
			return cur.value;
		}
		
		// 从尾部获取节点
		public T popFromBottom() {
			if (head == null) {
				return null;
			}
			Node<T> cur = tail;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.next;
				tail.next = null;
				cur.next = null;
			}
			return cur.value;
		}
		
		// 判断是否为空
		public boolean isEmpty() {
			return head == null;
		}
		
	}
	
	// 实现栈
	public static class MyStack<T> {
		private DoubleEndsQueue<T> queue;
		
		public MyStack() {
			queue = new DoubleEndsQueue<T>();
		}
		
		// 从头部进
		public void push(T value) {
			queue.addFromHead(value);
		}
		
		// 从头部出
		public T pop() {
			return queue.popFromHead();
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
		}
	}
	
	// 实现队列
	public static class MyQueue<T> {
		private DoubleEndsQueue<T> queue;
		
		public MyQueue() {
			queue = new DoubleEndsQueue<T>();
		}
		
		// 从头部进
		public void push(T value) {
			queue.addFromHead(value);
		}
		
		// 从尾部出
		public T poll() {
			return queue.popFromBottom();
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
		}
	}
	
	public static boolean isEqual(Integer o1, Integer o2) {
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		if (o1 == null && o2 == null) {
			return true;
		}
		return o1.equals(o2);
	}
	
	
}
