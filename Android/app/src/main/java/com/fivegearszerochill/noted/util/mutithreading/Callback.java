package com.fivegearszerochill.noted.util.mutithreading;

public interface Callback<R> {
    void onComplete(R result);
}
