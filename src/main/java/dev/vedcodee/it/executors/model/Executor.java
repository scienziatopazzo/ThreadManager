package dev.vedcodee.it.executors.model;

import dev.vedcodee.it.enums.Mode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.RecursiveTask;

@RequiredArgsConstructor
public abstract class Executor {

    @Getter
    private final Mode mode;

    public abstract void execute(Runnable runnable);
    public abstract void shutdown();

}
