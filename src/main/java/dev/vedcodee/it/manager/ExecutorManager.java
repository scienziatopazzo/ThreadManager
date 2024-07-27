package dev.vedcodee.it.manager;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.HashSet;
import java.util.Set;


public class ExecutorManager {

    private final Set<Executor> executors;

    public ExecutorManager() {
        this.executors = new HashSet<>();
    }


    public void add(Executor executor) {
        System.out.println("Loading -> " + executor.getClass().getName());
        executors.add(executor);
        System.out.println("Finish loading of " + executor.getClass().getName());
    }

    public Executor get(Mode mode) {
        return executors.stream()
                .filter(e -> e.getMode() == mode)
                .findFirst()
                .orElse(null);
    }


    public void shutdown() {
        executors.forEach(executor -> executor.shutdown());
    }

}
