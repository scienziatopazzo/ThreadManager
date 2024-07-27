package dev.vedcodee.it;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.imp.*;
import dev.vedcodee.it.executors.model.Executor;
import dev.vedcodee.it.manager.ExecutorManager;

import java.util.*;

public class ThreadManager {

    private final ExecutorManager executorManager;

    public ThreadManager() {
        this.executorManager = new ExecutorManager();
        executorManager.add(new AsyncExecutor());
        executorManager.add(new ParallelExecutor());
        executorManager.add(new QueueExecutor());
        executorManager.add(new WorkStealingExecutor());
        executorManager.add(new IntervalExecutor());
        executorManager.add(new NormalExecutor());
    }

    public void addThread(Runnable runnable, Mode mode) {
        Executor executor = executorManager.get(mode);

        if(executor == null)
            throw new IllegalArgumentException("Unsupported mode: " + mode);

        executor.execute(runnable);
    }

    public void shutdown() {
        executorManager.shutdown();
    }

}
