package vincent.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public abstract class BaseTask implements Runnable {
    private CountDownLatch latch;

    private int index;

    private CyclicBarrier barrier;

    public BaseTask(CyclicBarrier barrier, int index, CountDownLatch latch) {
        this.barrier = barrier;
        this.index = index;
        this.latch = latch;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public int getIndex() {
        return index;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
