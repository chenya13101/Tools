import vincent.common.ThreadPool;
import vincent.job.ChangeStatusService;
import vincent.job.PriceService;

import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) {
        System.out.println("PriceService start!");
        ExecutorService cachedThreadPool = ThreadPool.getCachedThreadPool();

        try {
            ChangeStatusService changeStatusService = new ChangeStatusService();//线程定时修改status
            cachedThreadPool.execute(changeStatusService);//changeStatusService.start();

            for (int i = 0; i < 5; i++) {
                //价格服务线程
                Thread priceService = new Thread(new PriceService(i));
                cachedThreadPool.execute(priceService);
            }

            changeStatusService.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("PriceService end!");
    }
}
