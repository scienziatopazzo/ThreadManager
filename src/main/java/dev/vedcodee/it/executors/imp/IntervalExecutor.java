package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

import java.util.HashSet;
import java.util.Set;

public class IntervalExecutor extends Executor {

    private final Set<Thread> threads;
    private boolean stopped;

    public IntervalExecutor() {
        super(Mode.INTERVAL);
        this.threads = new HashSet<>();
        this.stopped = false;
        new Thread(new ThreadsInitializer()).start();
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        Thread thread = new Thread(runnable);
        threads.add(thread);
        thread.start();
    }

    @Override
    public synchronized void shutdown() {
        stopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads.clear();
    }

    private class ThreadsInitializer implements Runnable {

        @Override
        public void run() {
            while (!stopped) {
                synchronized (IntervalExecutor.this) {
                    for (Thread thread : threads) {
                        if (thread.isAlive()) {
                            try {
                                thread.join(200);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
