package hitrac.co.zw.aptest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.fragments.ExaminationNames;
import hitrac.co.zw.aptest.model.Exam;
import hitrac.co.zw.aptest.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

public class Questions extends AppCompatActivity {
public Exam exam;
public  ArrayList<Question> questionList;
  public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        progressDialog = new ProgressDialog(Questions.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface= retrofit.create(ApiInterface.class);
        Call<Exam> call2=apiInterface.getExam(ExaminationNames.Name);
        call2.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
               exam=response.body();
                questionList.addAll(0,exam.getQuestionList());


                System.out.println("========-==============0-============"+exam.getQuestionList());
               progressDialog.hide();
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Exams found",Toast.LENGTH_SHORT).show();


            }
        });


      questionList=new ArrayList<>();

        ListView questions =(ListView)findViewById(R.id.questions);





        QuestionListAdapter adapter= new QuestionListAdapter(this,R.layout.question_layout,questionList);
        questions.setAdapter(adapter);
    }
}
