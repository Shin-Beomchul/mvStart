package house.beom.com.mvvm.util.network;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public interface UsersService {

    @GET
    Observable<UserResponse> fetchUsers(@Url String url);

}
