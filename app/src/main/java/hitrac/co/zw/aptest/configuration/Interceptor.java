package hitrac.co.zw.aptest.configuration;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor  implements okhttp3.Interceptor {

    private String credentials;

    public Interceptor() {
        this.credentials = Credentials.basic("admin", "admin");;
    }

    public Interceptor(String userName, String password) {
        this.credentials = Credentials.basic(userName, password);;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}


