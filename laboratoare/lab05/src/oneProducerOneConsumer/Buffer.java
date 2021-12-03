package oneProducerOneConsumer;
/**
 * @author cristian.chilipirea
 *
 */
public class Buffer {
	int a, condition = 0;

	void put(int value) {
		synchronized (this) {
			if(condition != 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			a = value;
			condition = 1;
			this.notify();
		}

	}

	int get() {
		synchronized (this) {
			if(condition == 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			condition = 0;
			this.notify();
			return a;
		}

	}
}
