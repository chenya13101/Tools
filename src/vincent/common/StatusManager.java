package vincent.common;

public class StatusManager {

    private static volatile int status;

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        System.out.println("set status =" + status);
        StatusManager.status = status;
    }


}
