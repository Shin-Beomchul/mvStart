package house.beom.com.mvvm.mvvm.livedata.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import house.beom.com.mvvm.R;
import house.beom.com.mvvm.databinding.ActivityMvlistBinding;
import house.beom.com.mvvm.mvvm.livedata.vm.ItemUserVM;
import house.beom.com.mvvm.mvvm.livedata.vm.ListVM;
import house.beom.com.mvvm.util.UserAdapter;
import house.beom.com.mvvm.util.ViewModelFactory;

/*
   확실히 하고 갈것.
    - 혼란의 시작
       Android에서 View와 Controller를 명확히 나눌 수 없다.
       MVVM의 구현 방식은 표준이 없다.
       샘플의 구현 방법과 적용 라이브러리가 모두 다르다.
       Controller(Activity)옵져빙 -> LiveData
       XML 자동업데이트  -> DataBinding / LiveData.setLifecycleOwner


    - MVP를 한줄로 요약 한다면 ?
        Model과 View계층을 완벽히(이론적으로) 분리 시켜 각 도메인의 역할이 명확해 졌으며, 코딩 규칙이 생겼다.

    - MVVM을 한줄료 요약한다면 ?
       ViewModel은 View를 알 수 없다.
       View는 ViewModel의 상태 변화를 감시 할 수 있다.



 */
public class ListAct extends AppCompatActivity {
    ActivityMvlistBinding mvListBinding;
    ListVM listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvListBinding =  DataBindingUtil.setContentView(this,R.layout.activity_mvlist);
        mvListBinding.setLifecycleOwner(this);                                                       // ViewModel에서  Live Data필드 postValue를 해주는 순간 XML UI가 업데이트
                                                                                                     // observe(liveData)는 항상함. 혼동 유의. XML UI 업데이트

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        listViewModel = ViewModelProviders.of(this,factory).get(ListVM.class);              // 라이프 사이클 관리하기 위해  | 생성자가 있는 View모델을 초기화 할 경우 https://medium.com/@jungil.han/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8-viewmodel-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-2e4d136d28d2
        mvListBinding.setListViewModel(listViewModel);





        //View가 VM을 옵져빙 When : API 호출
        listViewModel.getUsers().observe(this,users -> {
            UserAdapter userAdapter;
                if(mvListBinding.recyclerview.getAdapter() == null){
                    ItemUserVM itemUserVM = ViewModelProviders.of(this,factory).get(ItemUserVM.class);
                    userAdapter = new UserAdapter(itemUserVM);
                    mvListBinding.recyclerview.setAdapter(userAdapter);
                    mvListBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
                    userAdapter.setUserList(users);
                }else{
                    userAdapter = ((UserAdapter) mvListBinding.recyclerview.getAdapter());
                    int notiStartPos =  userAdapter.getUserList().size();
                    userAdapter.getUserList().addAll(users);
                    userAdapter.notifyItemRangeInserted(notiStartPos, userAdapter.getUserList().size());
                }


            Toast.makeText(getApplicationContext(),"[Controller] :: Users 데이터 변경감지 ",Toast.LENGTH_SHORT).show();
        });


    }

}
