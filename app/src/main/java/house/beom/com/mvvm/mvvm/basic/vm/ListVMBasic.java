package house.beom.com.mvvm.mvvm.basic.vm;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.List;
import java.util.Observable;

import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.model.APIModel;
//https://academy.realm.io/kr/posts/eric-maxwell-mvc-mvp-and-mvvm-on-android/
public class ListVMBasic extends Observable {

    public static int RAND_USER_SUCCESS = 1;

    private ObservableInt progressBarState = new ObservableInt();
    private ObservableField<List<User>>  users = new ObservableField<>();

    //생성자
    public ListVMBasic(){  progressBarState.set(View.GONE); }


    /* API */
    public void reqUsers(View view) {
        progressBarState.set(View.VISIBLE);                                                   //MVP  : view.onProresss(View.VISIBLE) Presenter는 View를 알고 있다.
        APIModel.getUsers(users -> {                                                          //MVVM : VM의 상태(progressBarState)를 변겨 시키고 나의 상태 변경을 알린다(set).
            progressBarState.set(View.GONE);
            this.users.set(users);

            setChanged();

            notifyObservers(RAND_USER_SUCCESS);
        });
    }

    //XML(auto) , observe
    public ObservableField<List<User>> getUsers() {
        return users;
    }
    public ObservableInt getProgressBarState() {
        return progressBarState;
    }


}
