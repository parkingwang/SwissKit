package com.parkingwng.lang;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.5
 */
final public class Try {

    private Try(){}

    public static void close(final Closeable closeable){
        ignore(new ThrowAction.ThrowAction0() {
            @Override public void invoke0() throws Throwable {
                if (closeable != null) closeable.close();
            }
        });
    }

    public static void ignore(ThrowAction action){
        try{
            action.invoke();
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static <T> T die(ThrowSupplier<T> supplier){
        try{
            return supplier.get();
        }catch (Throwable r){
            r.printStackTrace();
            System.exit(-1);
            throw new IllegalStateException("User manual shutdown the JVM on errors", r);
        }
    }

    public static void retry(int maxRetries, RetryAction action, int sleepAtNext) {
        final AtomicInteger retries = new AtomicInteger(1);
        boolean failed = true;
        while (retries.get() < maxRetries){
            try {
                action.onWork();
                failed = false;
                break;
            } catch (Throwable err) {
                final int nextRetry = retries.incrementAndGet();
                action.onThrows(err, nextRetry);
                final int sleep = nextRetry * sleepAtNext;
                if (retries.get() < maxRetries && sleep > 0) {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException sleepErr) {
                        break;
                    }
                }
            }
        }
        if (failed){
            action.onFailed();
        }
    }

    public interface RetryAction{
        void onWork() throws Throwable;
        void onThrows(Throwable err, int retry);
        void onFailed();
    }

    public static abstract class JustRetryAction implements RetryAction{
        @Override
        public void onFailed() {}
    }

    public static abstract class ExitRetryAction implements RetryAction{
        @Override
        public void onFailed() { System.exit(-1); }
    }
}
