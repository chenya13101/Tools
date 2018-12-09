package vincent.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class MonthTask extends BaseTask {

    //TODO 这个任务最好能被封装在future内返回数据
    public MonthTask(CyclicBarrier barrier, int index, CountDownLatch latch) {
        super(barrier, index, latch);
    }

    @Override
    public void run() {
        try {
            super.getBarrier().await();

            System.out.println(getIndex() + "month task start " + System.currentTimeMillis());
            long random = ThreadLocalRandom.current().nextLong(150, 400);
            System.out.println(getIndex() + "month random " + random);
            Thread.sleep(random);
            System.out.println(getIndex() + "month task finish " + System.currentTimeMillis());

            super.getLatch().countDown();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
