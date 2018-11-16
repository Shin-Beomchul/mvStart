package house.beom.com.mvvm.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.VisibleForTesting;

import house.beom.com.mvvm.mvvm.livedata.vm.ItemUserVM;
import house.beom.com.mvvm.mvvm.livedata.vm.ListVM;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;



    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
//                    INSTANCE = new ViewModelFactory(application, Injection.provideTasksRepository(application.getApplicationContext()));
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application) {
        mApplication = application;

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

         if (modelClass.isAssignableFrom(ItemUserVM.class)) {
            return (T) new ItemUserVM(mApplication);
        } else if (modelClass.isAssignableFrom(ListVM.class)) {
            //noinspection unchecked
            return (T) new ListVM(mApplication);
//             return (T) new ListVM(param1,param2); if you want param
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }






}