
public class Producer implements Runnable {
	
	private CustomLinkedList<Integer> queue;

	public Producer(CustomLinkedList<Integer> queue) {
		this.queue=queue;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1000; i++) {
			try {
				System.out.println("Inserting value" +" "+i);
				queue.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
