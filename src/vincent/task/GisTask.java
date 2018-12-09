package vincent.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GisTask extends BaseTask {

    public GisTask(CyclicBarrier barrier, int index) {
        super(barrier, index);
    }

    @Override
    public void run() {
        try {
            this.getBarrier().await();

            System.out.println(getIndex() + "git task start");
            Thread.sleep(500);
            System.out.println(getIndex() + " git task finish");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
