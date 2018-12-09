package vincent.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MonthTask extends BaseTask {

    public MonthTask(CyclicBarrier barrier, int index) {
        super(barrier, index);
    }

    @Override
    public void run() {
        try {
            this.getBarrier().await();

            System.out.println(getIndex() + "month task start");
            Thread.sleep(200);
            System.out.println(getIndex() + " month task finish");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
