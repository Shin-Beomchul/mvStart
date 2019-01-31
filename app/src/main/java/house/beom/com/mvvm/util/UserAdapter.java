package house.beom.com.mvvm.util;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import house.beom.com.mvvm.R;
import house.beom.com.mvvm.databinding.ItemUserBinding;
import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.mvvm.livedata.vm.ItemUserVM;


/**
 *
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    private List<User> userList;
    private ItemUserVM itemUserVM;

    public UserAdapter(ItemUserVM itemUserVM) {
        this.itemUserVM =itemUserVM;
        this.userList = Collections.emptyList();
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_user ,parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));

    }

    @Override
    public int getItemCount() {
        return  userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public List<User> getUserList() {
        return userList;
    }





    /* Holder */
     class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.itemCardRoot);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(User user){
            if(mItemUserBinding.getItemUserViewModel() == null){
                itemUserVM.setUser(user);
                mItemUserBinding.setItemUserViewModel(itemUserVM);
            }else {
                mItemUserBinding.getItemUserViewModel().setUser(user);
            }
        }
    }
}
