package vincent.common;

import java.util.concurrent.*;

public class ThreadPool {
    public static ThreadPoolExecutor getThreadPool() {
        return threadPoolExecutor;
    }

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Constant.CORE_POOL_SIZE,
            Constant.MAXIMUM_POOL_SIZE,
            1,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(50));


}
