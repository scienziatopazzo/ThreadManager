package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncExecutor extends Executor {

    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workerThreads;
    private volatile boolean isShutdown;

    public AsyncExecutor() {
        super(Mode.ASYNC);
        taskQueue = new LinkedBlockingQueue<>();
        isShutdown = false;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        workerThreads = new Thread[availableProcessors];

        for (int i = 0; i < availableProcessors; i++) {
            workerThreads[i] = new Worker(taskQueue);
            workerThreads[i].start();
        }
    }

    @Override
    public void execute(Runnable task) {
        if (!isShutdown) {
            taskQueue.offer(task);
        }
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        for (Thread worker : workerThreads) {
            worker.interrupt();
        }
    }

    private static class Worker extends Thread {
        private final BlockingQueue<Runnable> taskQueue;

        public Worker(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}