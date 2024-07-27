package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueExecutor extends Executor {

    private final ExecutorService executorService;
    private final LinkedBlockingQueue<Runnable> taskQueue;

    public QueueExecutor() {
        super(Mode.QUEUE);
        this.taskQueue = new LinkedBlockingQueue<>();
        this.executorService = Executors.newSingleThreadExecutor();
        startProcessingQueue();
    }

    private void startProcessingQueue() {
        executorService.submit(() -> {
            try {
                while (true) {
                    Runnable task = taskQueue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public void execute(Runnable task) {
        taskQueue.offer(task);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }
}