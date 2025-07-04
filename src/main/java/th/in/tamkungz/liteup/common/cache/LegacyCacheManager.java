package th.in.tamkungz.liteup.common.cache;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;

/**
 * Provides legacy primitive collection caches for direct usage when evictions are not needed.
 */
public final class LegacyCacheManager {
    /**
     * FastUtil map for primitive-int to object caching.
     */
    public static final Int2ObjectOpenHashMap<Object> FAST_CACHE = new Int2ObjectOpenHashMap<>();

    /**
     * Eclipse Collections map for primitive-int to object caching.
     */
    public static final IntObjectHashMap<Object> ECLIPSE_CACHE = new IntObjectHashMap<>();

    private LegacyCacheManager() {}
}