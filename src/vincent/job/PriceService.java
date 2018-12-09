package vincent.job;

import vincent.common.StatusManager;
import vincent.common.ThreadPool;
import vincent.task.GisTask;
import vincent.task.MonthTask;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

public class PriceService implements Runnable {

    private int index;

    public PriceService(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        ExecutorService cachedThreadPool = ThreadPool.getCachedThreadPool();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        //TODO 第一方案：这里使用线程不合理，换成jdk8 lambda来做，不要自己去new Thread
        //TODO 第二方案: 在两个线程中分别放入countDownLatch,两个服务一旦完成就会通知priceService线程
        Thread gisThread = new Thread(new GisTask(cyclicBarrier, index));
        cachedThreadPool.execute(gisThread);

        sleep();//判断gis与 month是否同时启动

        Thread monthThread = new Thread(new MonthTask(cyclicBarrier, index));
        cachedThreadPool.execute(monthThread);
        // monthThread.start();

        try {
            gisThread.join();
            int status = StatusManager.getStatus();
            if (status == 0) {
                System.out.println("priceService" + index + " join monthThread,index == " + this.index + "; status =" + status);
                monthThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
