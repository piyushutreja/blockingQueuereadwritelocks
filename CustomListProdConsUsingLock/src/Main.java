
public class Main {
	
	public static void main (String [] args)
	{
	 CustomLinkedList<Integer> list = new CustomLinkedList<>(5);
	 Producer producer = new Producer(list);
		Consumer consume = new Consumer(list);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consume);
		producerThread.start();
		consumerThread.start();
	
	 
	
	 
		
	}

}
