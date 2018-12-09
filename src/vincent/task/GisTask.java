package vincent.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class GisTask extends BaseTask {

    public GisTask(CyclicBarrier barrier, int index, CountDownLatch latch) {
        super(barrier, index, latch);
    }

    @Override
    public void run() {
        try {
            this.getBarrier().await();

            System.out.println(getIndex() + "git task start");
            Thread.sleep(ThreadLocalRandom.current().nextLong(250, 750));
            System.out.println(getIndex() + "git task finish");

            this.getLatch().countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
