package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ParallelExecutor extends Executor {

    private final List<Thread> runnables;
    private final Set<Thread> startedThreads;

    public ParallelExecutor() {
        super(Mode.PARALLEL);
        this.runnables = new ArrayList<>();
        this.startedThreads = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void execute(Runnable task) {
        Thread thread = new Thread(task);
        runnables.add(thread);

        // Start threads that have not yet been started
        runnables.stream().parallel().forEach(t -> {
            if (startedThreads.add(t)) {
                t.start();
            }
        });
    }

    @Override
    public void shutdown() {
        for (Thread thread : runnables) {
            try {
                if (thread.isAlive()) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
        }
    }
}
