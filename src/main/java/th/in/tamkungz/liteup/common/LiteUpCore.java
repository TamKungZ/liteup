package th.in.tamkungz.liteup.common;

import net.fabricmc.api.ModInitializer;
import th.in.tamkungz.liteup.common.scheduler.CommonScheduler;
import org.tinylog.Logger;

/**
 * Core initializer for both client and server environments.
 */
public class LiteUpCore implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonScheduler.initialize();
        Logger.info("CommonScheduler initialized successfully");
    }
}
