package house.beom.com.mvvm.mvvm.livedata.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import java.util.List;

import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.mvvm.livedata.model.APIModel;

public class ListVM extends ViewModel {

    //use LiveData
    private MutableLiveData<Integer> progressBarState = new MutableLiveData<>();
    private MutableLiveData<List<User>>  users = new MutableLiveData<>();
    private MutableLiveData<String>  firstuserName = new MutableLiveData<>();

    //생성자
    ListVM(){  progressBarState.postValue(View.GONE); }


    /* API  */
    public void reqUsers(View view) {
        progressBarState.postValue(View.VISIBLE);                                                   //MVP : view.onProresss(View.VISIBLE) P는 View를 알고 있다.

      APIModel.getUsers(users -> {
          progressBarState.postValue(View.GONE);
          this.users.postValue(users);
          this.firstuserName.postValue(users.get(0).login.username);
      });
    }

    //XML(auto) , observe
    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public MutableLiveData<String> getFirstuserName() {
        return firstuserName;
    }

    public MutableLiveData<Integer> getProgressBarState() {
        return progressBarState;
    }
}
