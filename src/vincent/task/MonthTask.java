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
            this.getBarrier().await();

            System.out.println(getIndex() + "month task start");
            Thread.sleep(ThreadLocalRandom.current().nextLong(150, 350));
            System.out.println(getIndex() + " month task finish");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
