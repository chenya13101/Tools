import vincent.common.ThreadPool;
import vincent.job.ChangeStatusService;
import vincent.job.PriceService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("PriceService start!");
        ExecutorService cachedThreadPool = ThreadPool.getCachedThreadPool();

        try {
            ChangeStatusService changeStatusService = new ChangeStatusService();//线程定时修改status
            cachedThreadPool.execute(changeStatusService);

            for (int i = 0; i < 1; i++) {
                //价格服务线程
                cachedThreadPool.execute(new PriceService(i));
            }

            changeStatusService.join();


            Thread.sleep(200);
            cachedThreadPool.shutdown();
            cachedThreadPool.awaitTermination(25, TimeUnit.SECONDS);

            System.out.println("main thread finish!" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
