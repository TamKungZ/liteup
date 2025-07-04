package th.in.tamkungz.liteup.common.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

/**
 * Provides high-performance caching with eviction policies via Caffeine.
 */
public final class CacheManager {
    /**
     * A cache that holds up to 10,000 entries and expires entries 5 minutes after write.
     */
    public static final Cache<Integer, Object> GLOBAL_CACHE = Caffeine.newBuilder()
        .maximumSize(10_000)
        .expireAfterWrite(5, TimeUnit.MINUTES)
        .build();

    private CacheManager() {}
}