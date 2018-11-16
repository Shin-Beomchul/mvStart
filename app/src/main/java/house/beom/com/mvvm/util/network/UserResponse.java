package house.beom.com.mvvm.util.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import house.beom.com.mvvm.dto.User;

public class UserResponse {

    @SerializedName("results") private List<User> userList;

    public List<User> getPeopleList () { return userList;}

    public void setPeopleList(List<User> mUserList) { this.userList = mUserList ;}

}