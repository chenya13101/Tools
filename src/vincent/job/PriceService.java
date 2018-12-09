package vincent.job;

import vincent.common.Constant;
import vincent.common.StatusManager;
import vincent.common.ThreadPool;
import vincent.task.GisTask;
import vincent.task.MonthTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PriceService implements Runnable {

    private int index;

    public PriceService(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        //TODO 第二方案: 在两个线程中分别放入countDownLatch,两个服务一旦完成就会通知priceService线程
        //TODO 也许简单的wait notify就可以了
        ExecutorService cachedThreadPool = ThreadPool.getCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);//确保两个线程一起开始
        CountDownLatch gisLatch = new CountDownLatch(1);
        CountDownLatch monthLatch = new CountDownLatch(1);

        cachedThreadPool.submit(new GisTask(cyclicBarrier, index, gisLatch));
        //sleep();//判断gis与 month是否同时启动
        cachedThreadPool.submit(new MonthTask(cyclicBarrier, index, monthLatch));

        waitResult(gisLatch, monthLatch);
    }

    private void waitResult(CountDownLatch gisLatch, CountDownLatch monthLatch) {
        //TODO 最好得到返回值
        try {
            boolean gisLatchReachZero = gisLatch.await(Constant.MAX_WAIT_MILLSECONDS, TimeUnit.MILLISECONDS);
            if (!gisLatchReachZero) {
                System.out.println("gis service time out!!!");
                return;
            }

            if (StatusManager.getStatus() == 0) {
                System.out.println("priceService" + index + " joining monthThread,index == " + this.index + "; status = 0; monthLatch =" + monthLatch.getCount());
                System.out.println(System.currentTimeMillis());
                monthLatch.await(Constant.MAX_WAIT_MILLSECONDS, TimeUnit.MILLISECONDS);
                System.out.println(System.currentTimeMillis());
            } else {
                System.out.println("don't wait monthService end!!  status =1");
            }

            System.out.println(this.index + "PriceService thread finish!" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    private void sleep() {
//        try {
//            Thread.sleep(25);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
}
