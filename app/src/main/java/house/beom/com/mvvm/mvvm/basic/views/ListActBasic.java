package house.beom.com.mvvm.mvvm.basic.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Observable;
import java.util.Observer;

import house.beom.com.mvvm.R;
import house.beom.com.mvvm.databinding.ActivityListActBasicBinding;
import house.beom.com.mvvm.mvvm.basic.vm.ListVMBasic;
import house.beom.com.mvvm.util.UserAdapterBasic;

public class ListActBasic extends AppCompatActivity implements Observer {
    ActivityListActBasicBinding binding;
    ListVMBasic listVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_list_act_basic);
        listVM = new ListVMBasic();
        listVM.addObserver(this);
        binding.setListVmBasic(listVM);
    }


    //When : ListVMBasic에서 변경이 일어날 경우
    @Override
    public void update(Observable observable, Object o) {

        int action = (int)o;
        if(action == ListVMBasic.RAND_USER_SUCCESS) {
            UserAdapterBasic userAdapter = (UserAdapterBasic) binding.recyclerview.getAdapter();

            if (userAdapter == null) {
                userAdapter = new UserAdapterBasic();
                binding.recyclerview.setAdapter(userAdapter);
                binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
                userAdapter.setUserList(listVM.getUsers().get());
            } else {
                userAdapter = ((UserAdapterBasic) binding.recyclerview.getAdapter());
                int notiStartPos = userAdapter.getUserList().size();
                userAdapter.getUserList().addAll(listVM.getUsers().get());
                userAdapter.notifyItemRangeInserted(notiStartPos, userAdapter.getUserList().size());
            }
        }
    }


}
