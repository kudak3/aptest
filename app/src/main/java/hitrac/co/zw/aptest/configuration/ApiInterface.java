package hitrac.co.zw.aptest.configuration;



import java.util.List;

import hitrac.co.zw.aptest.model.Exam;
import hitrac.co.zw.aptest.model.Syllabus;
import hitrac.co.zw.aptest.model.User;
import hitrac.co.zw.aptest.model.UserDetails;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("user")
    Call<User> login(@Query("userName") String username, @Query("password") String password);

    @GET("user/{id}")
    Call<UserDetails>getUserById(@Path("id") String id);

    @FormUrlEncoded
    @POST("setQuestion")
    Call<ResponseBody> createQuestion(
            @Field("question") String question,
            @Field("ans1") String ans1,
            @Field("ans2") String ans2,
            @Field("ans3") String ans3,
            @Field("ans4") String ans4,
            @Field("id") String id,
            @Field("correctAns") String correctAns
    );


    @POST("exam")
    Call<ResponseBody> setExam(
            @Body Exam exam

            );
 @GET("exam/{examName}")
    Call<Exam>getExam(@Path("examName") String EXAM);

 @GET("exams")
    Call<List<Exam>>getExams();

    @GET("syllabus")
    Call<List<Syllabus>> getAllSyllabus();

    @POST("user")
    Call<ResponseBody> signUpUser(@Body User user);
}
