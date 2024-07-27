package dev.vedcodee.it.executors.imp;

import dev.vedcodee.it.enums.Mode;
import dev.vedcodee.it.executors.model.Executor;

public class NormalExecutor extends Executor {

    public NormalExecutor() {
        super(Mode.NORMAL);
    }

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void shutdown() {

    }
}
