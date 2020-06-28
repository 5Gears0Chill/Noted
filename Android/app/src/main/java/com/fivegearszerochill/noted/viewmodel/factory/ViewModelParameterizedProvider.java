package com.fivegearszerochill.noted.viewmodel.factory;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Nikola on 7/19/2017.
 */

public class ViewModelParameterizedProvider {

    private AtomicBoolean set = new AtomicBoolean(false);

    private ViewModelStore viewModelStore = null;


    static ViewModelParameterizedProvider getProvider() {
        return new ViewModelParameterizedProvider();
    }

    @MainThread
    public static ViewModelProvider ofSupportFragment(Fragment fragment, Object... params) {
        return getProvider().of(fragment).with(params);
    }

    @MainThread
    public static ViewModelProvider ofActivity(FragmentActivity fragmentActivity, Object... params) {
        return getProvider().of(fragmentActivity).with(params);
    }

    @MainThread
    public static ViewModelProvider ofFragment(androidx.fragment.app.Fragment fragment, Object... params) {
        return getProvider().of(fragment).with(params);
    }

    private ViewModelParameterizedProvider of(Fragment fragment) {
        checkForPreviousTargetsAndSet();
        viewModelStore =  fragment.getViewModelStore();
        return this;
    }

    private ViewModelParameterizedProvider of(android.app.Fragment fragment) {
        FragmentActivity fragAct = (FragmentActivity) fragment.getActivity();
        return of(fragAct);
    }

    private ViewModelParameterizedProvider of(FragmentActivity activity) {
        checkForPreviousTargetsAndSet();
        viewModelStore = activity.getViewModelStore();
        return this;
    }


    private ViewModelProvider with(Object... constructorParams) {
        return new ViewModelProvider(viewModelStore, parametrizedFactory(constructorParams));
    }


    private void checkForPreviousTargetsAndSet() {
        if (set.get()) {
            throw new IllegalArgumentException("ViewModelStore already has been set. Create new instance.");
        }
        set.set(true);
    }

    private ViewModelProvider.Factory parametrizedFactory(Object... constructorParams) {
        return new ParametrizedFactory(constructorParams);
    }


    private static final class ParametrizedFactory implements ViewModelProvider.Factory {
        private final Object[] mConstructorParams;

        ParametrizedFactory(Object... constructorParams) {
            mConstructorParams = constructorParams;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            try {
                if (mConstructorParams == null || mConstructorParams.length == 0) {
                    return modelClass.newInstance();
                } else {
                    Class<?>[] classes = new Class<?>[mConstructorParams.length];
                    for (int i = 0; i < mConstructorParams.length; i++) {
                        classes[i] = mConstructorParams[i].getClass();
                    }
                    return modelClass.getConstructor(classes).newInstance(mConstructorParams);
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
