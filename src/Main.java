import vincent.common.ThreadPool;
import vincent.job.ChangeStatusService;
import vincent.job.PriceService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = ThreadPool.getThreadPool();

        long start = System.currentTimeMillis();
        System.out.println("PriceService start!" + start);
        int priceServiceNum = 5;
        try {
            cachedThreadPool.execute(new ChangeStatusService());//线程定时修改status
            for (int i = 0; i < priceServiceNum; i++) {
                cachedThreadPool.execute(new PriceService(i)); //价格服务线程
            }

            Thread.sleep(50);
            cachedThreadPool.shutdown();
            cachedThreadPool.awaitTermination(10, TimeUnit.SECONDS);

            long end = System.currentTimeMillis();
            //long sec = (end - start) / 1000;
            System.out.println("main thread finish! " + end + " time= " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
