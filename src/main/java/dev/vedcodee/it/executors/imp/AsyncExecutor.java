package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExecutor extends Executor {

    private ExecutorService executorService;

    public AsyncExecutor() {
        super(Mode.ASYNC);
        executorService = Executors.newCachedThreadPool();
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
