package hitrac.co.zw.aptest.configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static  final String BASE_URL="" +
            "http://192.168.1.219:8105/api/";
    public  static Retrofit retrofit= null;

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            retrofit  = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


