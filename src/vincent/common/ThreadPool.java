package vincent.common;

import java.util.concurrent.*;

public class ThreadPool {
    public static ThreadPoolExecutor getCachedThreadPool() {
        return threadPoolExecutor;
    }

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,
            50,
            1,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(50));


}
