package hitrac.co.zw.aptest.configuration;

import hitrac.co.zw.aptest.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("user")
    Call<User> login(@Query("userName") String username, @Query("password") String password);

}
