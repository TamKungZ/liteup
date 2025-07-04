package th.in.tamkungz.liteup.client;

import net.fabricmc.api.ClientModInitializer;
import th.in.tamkungz.liteup.common.scheduler.CommonScheduler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import th.in.tamkungz.liteup.common.scheduler.TickType;

/**
 * Client-specific initializer and task runner.
 */
public class LiteUpClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(tick -> {
            CommonScheduler.runPendingTasks(TickType.CLIENT);
        });
    }
}
