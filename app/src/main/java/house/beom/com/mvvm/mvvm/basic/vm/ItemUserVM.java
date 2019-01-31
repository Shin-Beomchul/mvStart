package house.beom.com.mvvm.mvvm.basic.vm;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import house.beom.com.mvvm.dto.User;

public class ItemUserVM {

    private User user;


    public ItemUserVM(){

    }

    public String getProfileThumb() {
        return user.picture.medium;
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getCell() { return user.cell; }
    public String getEmail() { return user.email; }
    public String getFullName() {
        user.fullName = user.name.title + "." + user.name.first + " " + user.name.last;
        return user.fullName;
    }

    public void onItemClick(View v){

    }

    public void setUser(User user) {
        this.user = user;

    }



}
