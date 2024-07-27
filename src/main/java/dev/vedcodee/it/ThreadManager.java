package dev.vedcodee.it;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.imp.*;
import dev.vedcodee.it.executors.model.Executor;

import java.util.*;

public class ThreadManager {

    private final Set<Executor> executors;

    public ThreadManager() {
        this.executors = new HashSet<>();
        executors.add(new AsyncExecutor());
        executors.add(new ParallelExecutor());
        executors.add(new QueueExecutor());
        executors.add(new WorkStealingExecutor());
    }

    public void addThread(Runnable runnable, Mode mode) {
        Executor executor = executors.stream()
                .filter(e -> e.getMode() == mode)
                .findFirst()
                .orElse(null);

        if(executor == null)
            throw new IllegalArgumentException("Unsupported mode: " + mode);

        executor.execute(runnable);
    }

    public void shutdown() {
        executors.forEach(executor -> executor.shutdown());
    }

}
