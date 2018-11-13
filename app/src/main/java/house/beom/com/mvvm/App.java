package house.beom.com.mvvm;

import android.app.Application;

import house.beom.com.mvvm.network.ApiFactory;
import house.beom.com.mvvm.network.UsersService;

public class App extends Application {

    private static UsersService usersService;


    public static UsersService getUserService() {
        if (usersService == null) {
            usersService = ApiFactory.create();
        }

        return usersService;
    }

}
