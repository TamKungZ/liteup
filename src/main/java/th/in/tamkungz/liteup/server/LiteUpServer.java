package th.in.tamkungz.liteup.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import th.in.tamkungz.liteup.common.scheduler.CommonScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import th.in.tamkungz.liteup.common.scheduler.TickType;

/**
 * Server-specific initializer and task runner.
 */
public class LiteUpServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            CommonScheduler.runPendingTasks(TickType.SERVER);
        });
    }
}