package vincent.task;

import java.util.concurrent.CyclicBarrier;

public abstract class BaseTask implements Runnable {


    private int index;

    private CyclicBarrier barrier;

    public BaseTask(CyclicBarrier barrier, int index) {
        this.barrier = barrier;
        this.index = index;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public int getIndex() {
        return index;
    }
}
