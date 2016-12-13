import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomLinkedList<T> {
	
	ReadWriteLock lock = new ReentrantReadWriteLock(true);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();
	final Condition empty  = readLock.newCondition(); 
	   final Condition full = writeLock.newCondition(); 
	


	private int maxSize, size;
	Node<T> head;

	public CustomLinkedList(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		
	}


	private class Node<T> {

		Node<T> next;
		T data;

		Node(T data) {
			this.next = null;
			this.data = data;
		}

	}

	public  void put(T data) throws InterruptedException {
		
	try{	
		writeLock.lock();
		
		if(size==maxSize){
			 System.out.println("Queue full.Waiting for queue to become empty");
			 full.wait();
		}
				
		if (head == null) {
			head = new Node<T>(data);
			this.size++;
			empty.notifyAll();
		}else {
			// crawl to last node
			Node<T> last = head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = new Node<T>(data);
			this.size++;
			empty.notifyAll();;

		}
	}
	finally
	{
		writeLock.unlock();
	}
	}

	public  T get() throws InterruptedException {

		try{   readLock.lock();
	

		if(size==0)
		{
			 System.out.println("Queue empty.Waiting for queue to have soem elements");
				empty.wait();
			
		}
		
		// crawl to last node
		Node<T> last = head;
		Node<T> temp = null;
		while (last != null) {
			temp = last;
			last = last.next;

		}
		temp.next = null;
		this.size--;
		full.notifyAll();
		return temp.data;
		}
		finally
		{
			readLock.unlock();
		}
	}

	public void printAll() {

		Node<T> last = head;

		while (last.next != null) {
			System.out.println(last.data);
			last = last.next;

		}

	}

	public int size() {
		return size;

	}

	

}
