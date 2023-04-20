
public class DynamicArray {
	private LinkedList[] array;
	private int size;
	private int capacity;

	public DynamicArray() {
		size = 0;
		capacity = 10;
		array = new LinkedList[capacity];
	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void add(LinkedList data) {
		if (size == capacity) {
			increaseCapacity();
		}
		array[size] = data;
		size++;
	}

	public LinkedList get(int position) {
		return array[position];
	}

	public void increaseCapacity() {
		// creates a second array with size twice the original
		LinkedList temp[] = new LinkedList[capacity * 2];
		// the values of the original array are transferred to the second
		for (int i = 0; i < capacity; i++) {
			temp[i] = array[i];
		}
		// overwrites the original array with the second
		array = temp;
		// updates the capacity
		capacity = capacity * 2;
	}
}
