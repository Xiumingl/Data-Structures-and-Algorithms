package RedBlackTreeSpellChecker;

public class Queue {
	private int rear;
	private int front;
	private Object[] data;
	private int length;
	private int capacity;

	public Queue() {
		capacity = 5;
		length = 0;
		rear = 0;
		front = 0;
		data = new Object[capacity];

	}


	public Object deQueue() {
		if (length == 0) {
			return false;
		}
		Object answer = data[front];
		if (length == 1) {
			front = front % capacity;
			length--;
			return answer;
		} else {
			front = (front + 1) % capacity;
			length--;
			return answer;
		}

	}

	public void enQueue(Object x) {
		if (isFull()) {
			Object[] c = this.data;
			int cLength = this.length;
			this.capacity = 2 * this.capacity;
			this.data = new Object[this.capacity];
			this.length = 0;
			this.rear = 0;
			this.front = 0;
			for (int i = 0; i < cLength; i++) {
				this.enQueue(c[i]);
			}
		}

		if (isEmpty()) {
			data[rear] = x;
			length++;
		} else {
			rear = (rear + 1) % capacity;
			data[rear] = x;
			length++;
		}
	}

	public Object getFront() {
		Object answer = data[front];
		return answer;
	}

	public boolean isEmpty() {
		if (length == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isFull() {
		if (length == capacity) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		int cFront = front;
		String content = "";
		for (int i = 0; i < length; i++) {
			content = content + String.valueOf(data[cFront]) + "\t";
			cFront = (cFront + 1) % capacity;
		}
		return content;
	}

	public static void main(String[] args) {
		Queue test = new Queue();
		System.out.println(test.isEmpty());
		test.enQueue(1);
		System.out.println(test.deQueue());
		test.enQueue(1);
		test.enQueue(2);
		test.enQueue(3);
		System.out.println(test.isFull());
		test.enQueue(4);
		test.enQueue(5);
		System.out.println(test.isFull());
		System.out.println("capacity: " + test.capacity);
		System.out.println("length: " + test.length);
		System.out.println("front: " + test.getFront());
		System.out.println("content: " + test.toString());
		test.enQueue(6);
		System.out.println("capacity: " + test.capacity);
		System.out.println("length: " + test.length);
		System.out.println("front: " + test.getFront());
		System.out.println("content: " + test.toString());

	}

}
