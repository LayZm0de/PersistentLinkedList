class Node {
	public LinkedList history = new LinkedList(); 
    private int data;
    private Node next;
    private Node prev;
    private boolean indentifier = false;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
    	if(!indentifier) {
    		history.addNode(this.data);
    		indentifier = true;
    	}
        history.addNode(data);
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
