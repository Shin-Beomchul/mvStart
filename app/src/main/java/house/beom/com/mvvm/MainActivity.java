package house.beom.com.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import house.beom.com.mvvm.databinding.ActivityMainBinding;
import house.beom.com.mvvm.mvvm.basic.views.ListAct;
import house.beom.com.mvvm.util.network.UserResponse;
import house.beom.com.mvvm.util.network.Constant;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable disposer = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setMainAct(this);

    }


    public void onClickMVVMBasic(View view){
        startActivity(new Intent(this, ListAct.class));
        Toast.makeText(getApplicationContext()
                , "Only DataBinding"
                , Toast.LENGTH_SHORT).show();
    }


    public void onClickMVVMLiveData(View view){
        startActivity(new Intent(this, house.beom.com.mvvm.mvvm.livedata.views.ListAct.class));
    }


    public void onClickApiTest(View view){
        disposer.add(App.getUserService().fetchUsers(Constant.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((UserResponse response) ->
                                Toast.makeText(getApplicationContext()
                                        , response.getPeopleList().size() + ""
                                        , Toast.LENGTH_SHORT).show()
                        , Throwable::printStackTrace));
    }


    @Override
    protected void onDestroy() {
        disposer.clear();
        super.onDestroy();
    }
}
