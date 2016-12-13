import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable {

	private CustomLinkedList<Integer> queue;

	public Consumer(CustomLinkedList<Integer> queue) {
		// TODO Auto-generated constructor stub
		this.queue=queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10000; i++) {
			try {
			System.out.println("Taking out value" +" "+queue.get());
			
						Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
