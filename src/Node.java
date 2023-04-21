class Node {
	// A linked list to keep track of the node's history
	public LinkedList history = new LinkedList();
	// The integer value stored in the node
	private int data;
	// The next node in the linked list
	private Node next;
	// The previous node in the linked list
	private Node prev;

	// Constructor for creating a new node with the given data
	public Node(int data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	// Getters and Setters
	public int getData() {
		return data;
	}
	
	public void setData(int data) {
		history.addNode(this.data);
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}
}
