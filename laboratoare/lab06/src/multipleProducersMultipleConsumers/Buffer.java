package multipleProducersMultipleConsumers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer {
	private BlockingQueue<Integer> values = new ArrayBlockingQueue<>(1);

	void put(int value) {
		try {
			values.put(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	int get() {
		Integer number =  values.peek();
		return number == null ? 0 : number;
	}
}
