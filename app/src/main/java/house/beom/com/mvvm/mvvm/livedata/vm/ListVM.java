package house.beom.com.mvvm.mvvm.livedata.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.model.APIModel;

public class ListVM extends AndroidViewModel {

    // use LiveData
    private MutableLiveData<Integer> progressBarState = new MutableLiveData<>();
    private MutableLiveData<List<User>>  users = new MutableLiveData<>();

    public ListVM(@NonNull Application application) {
        super(application);
        {  progressBarState.postValue(View.GONE); }
    }

    // 생성자



    /* API */
    public void reqUsers(View view) {
        progressBarState.postValue(View.VISIBLE);                                                   //MVP : view.onProresss(View.VISIBLE) P는 View를 알고 있다.
        APIModel.getUsers(users -> {
            progressBarState.postValue(View.GONE);
            this.users.postValue(users);
        });
    }

    //XML(auto) , observe(Act,Fmt)
    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public MutableLiveData<Integer> getProgressBarState() {
        return progressBarState;
    }
}
