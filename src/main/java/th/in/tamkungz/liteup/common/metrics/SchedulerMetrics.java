package th.in.tamkungz.liteup.common.metrics;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;

import th.in.tamkungz.liteup.common.scheduler.TickType;
import org.jctools.queues.SpscArrayQueue;

import java.util.Map;

/**
 * Exposes scheduler metrics using Micrometer.
 */
public final class SchedulerMetrics {
    public static final Counter tasksRun = Metrics.counter("liteup.scheduler.tasksRun");
    public static final Counter tasksError = Metrics.counter("liteup.scheduler.tasksError");

    /**
     * Register a gauge for each queue type.
     */
    public static void registerQueues(Map<TickType, ? extends SpscArrayQueue<?>> queues) {
        for (TickType type : TickType.values()) {
            Gauge.builder("liteup.scheduler.queueSize", queues, q -> {
                SpscArrayQueue<?> queue = q.get(type);
                return queue != null ? queue.size() : 0;
            })
            .tag("tickType", type.name().toLowerCase())
            .register(Metrics.globalRegistry);
        }
    }

    private SchedulerMetrics() {}
}