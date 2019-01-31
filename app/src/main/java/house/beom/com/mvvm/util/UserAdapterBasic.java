package house.beom.com.mvvm.util;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import house.beom.com.mvvm.R;
import house.beom.com.mvvm.databinding.ItemUserBasicBinding;
import house.beom.com.mvvm.dto.User;
import house.beom.com.mvvm.mvvm.basic.vm.ItemUserVM;


/**
 *
 */

public class UserAdapterBasic extends RecyclerView.Adapter<UserAdapterBasic.UserAdapterViewHolder> {

    private List<User> userList;

    public UserAdapterBasic() {this.userList = Collections.emptyList();}

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemUserBasicBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_basic ,parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));
    }

    /* Holder */
    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemUserBasicBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemUserBasicBinding itemUserBinding) {
            super(itemUserBinding.itemCardRoot);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(User user){
            if(mItemUserBinding.getItemUserViewModel() == null){
                ItemUserVM userVM = new ItemUserVM();
                userVM.setUser(user);
                mItemUserBinding.setItemUserViewModel(userVM);
            }else {
                mItemUserBinding.getItemUserViewModel().setUser(user);
            }
        }
    }

    @Override
    public int getItemCount() {
        if(userList == null){
            return 0;
        }
        return  userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public List<User> getUserList() {
        return userList;
    }
}
