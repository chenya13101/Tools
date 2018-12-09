package vincent.job;

import vincent.common.StatusManager;

public class ChangeStatusService extends Thread {

    @Override
    public void run() {
        int i = 0;
        while (i++ < 30) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis % 2 == 0) {
                StatusManager.setStatus(0);
            } else {
                StatusManager.setStatus(1);
            }

            try {
                Thread.sleep(101);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
