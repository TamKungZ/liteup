package th.in.tamkungz.liteup.api;

import th.in.tamkungz.liteup.common.scheduler.CommonScheduler;
import th.in.tamkungz.liteup.common.scheduler.TickType;

/**
 * Public API for scheduling tasks and accessing caches.
 */
public class LiteUpAPI {
    public static void runClient(Runnable task) {
        CommonScheduler.submit(TickType.CLIENT, task, 0);
    }

    public static void runClientDelayed(Runnable task, int delay) {
        CommonScheduler.submit(TickType.CLIENT, task, delay);
    }

    public static void runServer(Runnable task) {
        CommonScheduler.submit(TickType.SERVER, task, 0);
    }

    public static void runServerDelayed(Runnable task, int delay) {
        CommonScheduler.submit(TickType.SERVER, task, delay);
    }
}