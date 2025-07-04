package th.in.tamkungz.liteup.common.scheduler;

import org.jctools.queues.SpscArrayQueue;
import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicInteger;
import th.in.tamkungz.liteup.common.scheduler.TickType;
import th.in.tamkungz.liteup.common.metrics.SchedulerMetrics;
import org.tinylog.Logger;

/**
 * Central scheduler supporting both client and server ticks.
 */
public class CommonScheduler {
    private static final EnumMap<TickType, SpscArrayQueue<ScheduledTask>> queues = new EnumMap<>(TickType.class);
    private static final AtomicInteger tickCounter = new AtomicInteger(0);

    static {
        for (TickType type : TickType.values()) {
            queues.put(type, new SpscArrayQueue<>(512));
        }
        // Register queue metrics
        SchedulerMetrics.registerQueues(queues);
    }

    public static void initialize() {
        // Already prepared queues
    }

    public static void submit(TickType type, Runnable task, int delayTicks) {
        int scheduleTick = tickCounter.get() + delayTicks + (type == TickType.CLIENT ? 0 : 0);
        queues.get(type).offer(new ScheduledTask(task, scheduleTick));
    }

    public static void runPendingTasks(TickType type) {
        int currentTick = tickCounter.incrementAndGet();
        SpscArrayQueue<ScheduledTask> queue = queues.get(type);
        ScheduledTask scheduled;
        while ((scheduled = queue.peek()) != null && scheduled.scheduledTick <= currentTick) {
            queue.poll();
            try {
                scheduled.task.run();
                th.in.tamkungz.liteup.common.metrics.SchedulerMetrics.tasksRun.increment();
            } catch (Throwable t) {
                th.in.tamkungz.liteup.common.metrics.SchedulerMetrics.tasksError.increment();
                t.printStackTrace();
                Logger.error(t, "Task run failed: {}", t.getMessage());
            }
        }
    }

    static class ScheduledTask {
        final Runnable task;
        final int scheduledTick;

        ScheduledTask(Runnable task, int scheduledTick) {
            this.task = task;
            this.scheduledTick = scheduledTick;
        }
    }
}