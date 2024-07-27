package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelExecutor extends Executor {

    private final ExecutorService executorService;

    public ParallelExecutor() {
        super(Mode.PARALLEL);
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void execute(Runnable task) {
        executorService.submit(task);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }

}
