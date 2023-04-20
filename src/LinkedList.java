import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkedList {
	static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Node head = null;
	private Node tail = null;
	public DynamicArray histories = new DynamicArray();
	public int version = 1;
	public int length = 0;
	public boolean indentifier = true;

	public void addNode(int num) {
		Node newNode = new Node(num);

		if (head == null) {
			tail = newNode;
			head = tail;
		} else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		
		length++;
		
		if(indentifier) histories.add(copyLinkedList());	

	}
	
	public void deleteNode(int num) {
		if(num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}
		
		Node cur = head;
		int nodeCnt = 1;
		
		while(num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}
		
		if(cur == head && cur == tail) {
			head = null;
			tail = null;
		}else if(cur == tail) {
			tail.getPrev().setNext(null);
			this.tail = tail.getPrev();
		}else if(cur == head) {
			head.getNext().setNext(null);
			head = head.getNext();
		}else if(cur.getPrev() != null && cur.getNext() != null) {
			cur.getPrev().setNext(cur.getNext());
			cur.getNext().setPrev(cur.getPrev());
			
			cur = head;
			
			while(cur.getNext() != null) {
				cur = cur.getNext();
			}
			
			tail = cur;
		}
		
		length--;
		histories.add(copyLinkedList());
		
		
	}

	public LinkedList copyLinkedList() {
		LinkedList ll = new LinkedList();
		ll.indentifier = false;
		Node cur = head;

		while(cur != null) {
			int newInt = cur.getData();
			ll.addNode(newInt);
			cur = cur.getNext();
		}
		
		ll.version = this.version++;
		
		return ll;

	}
	
	public void changeValue(int num) throws IOException {
		if(num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}
		
		Node cur = head;
		int nodeCnt = 1;
		
		while(num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}
		
		System.out.println("Current value of node " + num+ " is " + cur.getData() + ".");
		
		try {
			System.out.print("Enter new value for node " + num+ ": ");
			int data = Integer.parseInt(reader.readLine());
			cur.setData(data);
			System.out.println("Node value has been modified successfully!");
		} catch (NumberFormatException e) {
			System.out.println("The only allowed input for CHOICE is an integer. Please try again.");
		}
			
		histories.add(copyLinkedList());
	}
	
	public void nodeHistory(int num) {
		if(num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}
		
		Node cur = head;
		int nodeCnt = 1;
		
		while(num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}
		
		System.out.println("\nCurrent value of node " + num + " is " + cur.getData());
		
		cur = cur.history.getHead();
		System.out.print("Previous value of node " + num + " are ");
		while(cur != null) {
			System.out.print(cur.getData() + " ");
			cur = cur.getNext();
		}
	}
	
	public void listHistory() {
		for(int i = histories.getSize()-1; i >= 0; i--) {
			Node temp = histories.get(i).getHead();
			
			System.out.print( "State "+ histories.get(i).version +": ");
			while(temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getNext();
			}
			System.out.println();
		}
		
		
	}
	                                                
	public void display() {
		Node cur = head;
		
		System.out.println("Existing nodes values: ");
		while(cur != null) {
			System.out.print(cur.getData()+ " ");
			cur = cur.getNext();
		}
		System.out.println();
		
	}

	public Node getHead() {
		return head;
	}
}
