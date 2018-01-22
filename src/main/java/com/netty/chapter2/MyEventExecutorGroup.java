package com.netty.chapter2;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.RejectedExecutionHandler;

import java.util.concurrent.ThreadFactory;

/**
 * Created by PataPon on 2018/1/13.
 */
public class MyEventExecutorGroup extends DefaultEventExecutorGroup {
    public MyEventExecutorGroup(int nThreads) {
        super(nThreads);
    }

    public MyEventExecutorGroup(int nThreads, ThreadFactory threadFactory) {
        super(nThreads, threadFactory);
    }

    public MyEventExecutorGroup(int nThreads, ThreadFactory threadFactory, int maxPendingTasks, RejectedExecutionHandler rejectedHandler) {
        super(nThreads, threadFactory, maxPendingTasks, rejectedHandler);
    }

}
