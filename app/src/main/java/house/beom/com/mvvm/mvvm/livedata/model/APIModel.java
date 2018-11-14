package house.beom.com.mvvm.mvvm.livedata.model;

import java.util.List;

import house.beom.com.mvvm.App;
import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.network.UserResponse;
import house.beom.com.mvvm.util.Constant;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class APIModel {

    private static CompositeDisposable disposer = new CompositeDisposable();

    public static void getUsers(ResponseCallBack callBack){
        disposer.add(App.getUserService().fetchUsers(Constant.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((UserResponse response) -> callBack.result(response.getPeopleList())
                        , Throwable::printStackTrace));
    }



    public void destory(){
        disposer.clear();
    }



    public interface ResponseCallBack {

          void result(List<User> users);


    }
}
