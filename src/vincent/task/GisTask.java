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

            long random = ThreadLocalRandom.current().nextLong(250, 750);
            System.out.println(getIndex() + "gis random " + random);
            Thread.sleep(random);
            System.out.println(getIndex() + "gis task finish " + System.currentTimeMillis());

            this.getLatch().countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
