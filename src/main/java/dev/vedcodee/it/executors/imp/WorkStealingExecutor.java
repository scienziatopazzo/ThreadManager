package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingExecutor extends Executor {

    private ExecutorService executorService;

    public WorkStealingExecutor() {
        super(Mode.WORK_STEALING);
        executorService = Executors.newWorkStealingPool();
    }

    public void execute(Runnable task) {
        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }

}
