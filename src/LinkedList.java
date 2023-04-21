import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkedList {
	static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private Node head = null;
	private Node tail = null;
	public DynamicArray histories = new DynamicArray();
	public Node deletedNode = null;
	public int version = 1;
	public int length = 0;
	public boolean indentifier = true;
	
	/*
	 * This parameterized addNode method is designed to add
	 * a new node to the end of the linked list, 
	 * and update the length of the list 
	 * and its history if necessary.
	 */
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

		if (indentifier)
			histories.add(copyLinkedList());

	}
	/*
	 * Overall, this method is designed to delete a node 
	 * from the linked list at the specified position, 
	 * and handle errors gracefully if necessary.
	 */
	public void deleteNode(int num) {
		if (num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}
		
		/*
		 * this is designed to delete a node from the linked list 
		 * at the specified position, update the necessary links, 
		 * and keep track of past states of the list.
		 */
		LinkedList pastll;
		Node cur = head;
		Node temp = null;

		int nodeCnt = 1;
		
		while (num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}

		if (cur == head && cur == tail) {
			head = null;
			tail = null;
		} else if (cur == tail) {
			tail.getPrev().setNext(null);
			this.tail = tail.getPrev();
		} else if (cur == head) {
			head = head.getNext();
		} else if (cur.getPrev() != null && cur.getNext() != null) {
			cur.getPrev().setNext(cur.getNext());
			cur.getNext().setPrev(cur.getPrev());

			temp = head;

			while (temp.getNext() != null) {
				temp = temp.getNext();
			}

			tail = temp;
		}

		length--;

		pastll = copyLinkedList();
		pastll.deletedNode = cur;
		histories.add(pastll);

		System.out.println("The node was successfully deleted!");

	}

	/*
	 * This method creates and returns a copy of the current linked list.
	 * It creates a new LinkedList object,
	 * iterates over the nodes of the current list 
	 * and adds them to the new list one by one, and then returns the new list.
 	 * The version number of the new list is incremented by 1.
	 * @return a copy of the current linked list.
	 */
	public LinkedList copyLinkedList() {
		LinkedList ll = new LinkedList();
		ll.indentifier = false;
		Node cur = head;

		while (cur != null) {
			int newInt = cur.getData();
			ll.addNode(newInt);
			cur = cur.getNext();
		}

		ll.version = this.version++;

		return ll;

	}
	
	/*
	 * Modifies the value of a node at a given position 
	 * and updates the history of the linked list.
	 * @param num the position of the node to modify
	 * @throws IOException if there is an error in the input stream
	 */
	public void changeValue(int num) throws IOException {
		if (num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}

		Node cur = head;
		int nodeCnt = 1;

		while (num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}

		System.out.println("Current value of node " + num + " is " + cur.getData() + ".");

		try {
			System.out.print("Enter new value for node " + num + ": ");
			int data = Integer.parseInt(reader.readLine());
			cur.setData(data);
		} catch (NumberFormatException e) {
			System.out.println("The only allowed input for CHOICE is an integer. Please try again.");
		}

		histories.add(copyLinkedList());
		System.out.println("Node value has been modified successfully!");
	}
	
	/*
	 * Retrieves the history of a specific node by its position in the linked list.
	 * @param num the position of the node to retrieve history from.
	 */
	public void nodeHistory(int num) {
		if (num < 1 || num > length) {
			System.out.println("Invalid Position");
			return;
		}

		Node cur = head;
		int nodeCnt = 1;
	
		while (num != nodeCnt) {
			cur = cur.getNext();
			nodeCnt++;
		}

		System.out.println("\nCurrent value of node " + num + " is " + cur.getData());

		cur = cur.history.getHead();
		if (cur == null) {
			System.out.println("This node has no previous values.");
		} else {
			System.out.print("Previous value of node " + num + " are ");
			while (cur != null) {
				System.out.print(cur.getData() + (cur.getNext() != null ? ", " : "\n"));
				cur = cur.getNext();
			}
		}
	}
	
	/*
	 * This method displays the history of the linkedlist along with the recent values of deleted nodes.
	 * the method checks each state in the histories list for any deleted nodes and prints the version number
	 * and recent value of each deleted node found. If no deleted nodes are found
	 * the method prints a message indicating that no nodes were deleted.
	 */ 
	public void listHistory() {
		Boolean haveDeletedNodes = false;

		System.out.println("States of the linkedlist: ");
		for (int i = histories.getSize() - 1; i >= 0; i--) {
			Node temp = histories.get(i).getHead();

			System.out.print("State " + histories.get(i).version + ": ");
			while (temp != null) {
				System.out.print(temp.getData() + (temp.getNext() != null ? ", " : "\n"));
				temp = temp.getNext();
			}
		}

		System.out.println("-----------------------------------------------");

		display();

		System.out.println("Displaying recent values of the deleted nodes values...");
		for (int i = histories.getSize() - 1; i >= 0; i--) {
			Node temp = histories.get(i).deletedNode;

			if (temp != null) {
				System.out.print("State " + histories.get(i).version + " deleted node with the recent value of: "
						+ temp.getData() + "\n");
				haveDeletedNodes = true;
			}

		}

		if (!haveDeletedNodes)
			System.out.println("No nodes were deleted.");

	}

	public void display() {
		Node cur = head;

		System.out.println("Displaying values of the existing nodes values...");
		while (cur != null) {
			System.out.print(cur.getData() + (cur.getNext() != null ? ", " : "\n"));
			cur = cur.getNext();
		}

	}

	public Node getHead() {
		return head;
	}
}
