# liteup

> A minimal universal Minecraft mod to boost performance and provide task scheduling APIs.

---

## ✨ Features

- **Ultra-fast task scheduler** for both server and client ticks  
  - Run tasks immediately or with delay, safely, without blocking the game loop
- **High-performance cache utilities**  
  - Built-in Caffeine cache with eviction
  - Fast primitive maps (FastUtil, Eclipse Collections) for legacy caching
- **Metrics & monitoring support**  
  - Built-in Micrometer metrics for task runs, errors, and queue size
- **Tinylog logging**  
  - Lightweight, simple logging for all environments
- **Simple, universal API**  
  - Easy to call from other mods (single line integration)
- **Designed for mod developers**  
  - Plug & play with any Fabric mod, minimal config, thread-safe

---

## 🚀 Getting Started

### 1. Add as a dependency (Gradle)

```groovy
dependencies {
    modImplementation "th.in.tamkungz:liteup:0.10"
}
```
> (Make sure to replace with the version you want)

Or add the jar to your mods folder for local use.

---

### 2. Usage Example

#### **Schedule a task to run on the next server tick**
```java
import th.in.tamkungz.liteup.api.LiteUpAPI;

LiteUpAPI.runServer(() -> {
    System.out.println("This runs on the next server tick!");
});
```

#### **Schedule a task with delay**
```java
LiteUpAPI.runClientDelayed(() -> {
    System.out.println("Runs after 20 ticks on the client!");
}, 20);
```

#### **Use global cache**
```java
import th.in.tamkungz.liteup.common.cache.CacheManager;

CacheManager.GLOBAL_CACHE.put(42, "answer");
Object answer = CacheManager.GLOBAL_CACHE.getIfPresent(42);
```

---

## 🛠 Public API

### Scheduler

| Method                                      | Description                             |
|----------------------------------------------|-----------------------------------------|
| `LiteUpAPI.runClient(Runnable task)`         | Schedule task on next client tick       |
| `LiteUpAPI.runClientDelayed(Runnable, int)`  | Schedule task on client after N ticks   |
| `LiteUpAPI.runServer(Runnable task)`         | Schedule task on next server tick       |
| `LiteUpAPI.runServerDelayed(Runnable, int)`  | Schedule task on server after N ticks   |

### Cache

| Field                             | Type                      | Description                         |
|------------------------------------|---------------------------|-------------------------------------|
| `CacheManager.GLOBAL_CACHE`        | Caffeine Cache            | Global cache, 10k entries, 5min TTL |
| `LegacyCacheManager.FAST_CACHE`    | FastUtil Int2Object map   | Primitive int→Object, no eviction   |
| `LegacyCacheManager.ECLIPSE_CACHE` | Eclipse IntObject map     | Primitive int→Object, no eviction   |

---

### Metrics

- Task run count, error count, queue size exposed as [Micrometer](https://micrometer.io/) metrics  
- Extend or export as you like (e.g., Prometheus, NewRelic)

---

### Logging

- Integrated with [Tinylog](https://tinylog.org/v2/) (simple, fast logging)
- Example:
    ```java
    import org.tinylog.Logger;
    Logger.info("LiteUp is initialized!");
    ```

---

## 📦 Integration

- Works out-of-the-box with [Fabric API](https://fabricmc.net/)
- No config needed (default settings are safe and fast)
- Support for both client and server environments

---

## 🔒 Requirements

- Minecraft 1.20.x (see `gradle.properties` for exact versions)
- Java 17+
- Fabric Loader 0.16.8 or newer

---

## 📝 Advanced Usage

- **Custom cache policies:**  
  Fork and tweak `CacheManager` as you need (different TTL, size, etc.)
- **Metrics export:**  
  Register additional Micrometer registries for integration with your monitoring stack
- **Command integration:**  
  (Coming soon) In-game commands for debugging and diagnostics

---

## 💡 Why use LiteUp?

- **No more boilerplate task scheduling**
- **Performance-first utility design**
- **Production-grade metrics and logging**
- **Ready for modpacks and large servers**

---

## 📚 Documentation

- [API Reference](#public-api)
- [Example Usage](#usage-example)
- [Metrics Integration](https://micrometer.io/docs)
- [Tinylog Config](https://tinylog.org/v2/configuration/)

---

## 🧑‍💻 License & Credits

- License: MIT
- Author: TamKungZ

---

## 🤝 Contributing

PRs are welcome!  
Issues, feature requests, and questions welcome via GitHub.

---

## 🗂️ File Structure

- `th.in.tamkungz.liteup.api.LiteUpAPI` — Public API for scheduling tasks
- `th.in.tamkungz.liteup.common.cache.*` — Cache managers
- `th.in.tamkungz.liteup.common.scheduler.*` — Core scheduler logic
- `th.in.tamkungz.liteup.common.metrics.*` — Metrics utilities

---

## 🏁 Quick FAQ

**Q: Can I use LiteUp with my own mod?**  
A: Yes, just add as a dependency and call the API!

**Q: Does it support async tasks?**  
A: All tasks are run safely in the scheduled tick; for long-running/async, schedule a stub and offload work as needed.

**Q: Can I use other cache libraries?**  
A: Yes, but Caffeine/FastUtil/Eclipse Collections are included for best performance.

**Q: How do I monitor performance?**  
A: Use Micrometer metrics, log output, or add your own exporters.

---

## 📮 Contact

- [GitHub](https://github.com/TamKungZ/liteup-fabric-1.20.1)
- Discord: TamKungZ

---
