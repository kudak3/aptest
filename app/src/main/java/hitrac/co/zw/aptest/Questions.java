package hitrac.co.zw.aptest;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.configuration.Interceptor;
import hitrac.co.zw.aptest.fragments.ExaminationNames;
import hitrac.co.zw.aptest.model.Exam;
import hitrac.co.zw.aptest.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.QuestionAdapter.ans;
import static hitrac.co.zw.aptest.QuestionAdapter.ans1;
import static hitrac.co.zw.aptest.QuestionAdapter.ans2;
import static hitrac.co.zw.aptest.QuestionAdapter.ans3;
import static hitrac.co.zw.aptest.QuestionListAdapter.a;
import static hitrac.co.zw.aptest.QuestionAdapter.number;
import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;
import static hitrac.co.zw.aptest.fragments.Login.client;
import static hitrac.co.zw.aptest.fragments.Login.etPassword;
import static hitrac.co.zw.aptest.fragments.Login.etUserName;

//import static hitrac.co.zw.aptest.QuestionListAdapter.showAnswers;

public class Questions extends AppCompatActivity {
    public Exam exam;
    public static ArrayList<Question> questionList;
    public ProgressDialog progressDialog;
    public Button submit_btn;
    public static ArrayList<String> correctAnswers , mark ;
   public RecyclerView recyclerView;
   public static RecyclerView.Adapter adapter;


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

        client.addInterceptor(new Interceptor(etUserName.getText().toString(),etPassword.getText().toString()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface= retrofit.create(ApiInterface.class);
        Call<Exam> call2=apiInterface.getExam(ExaminationNames.Name);
        call2.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                exam=response.body();
                questionList.addAll(0,exam.getQuestionList());
                for(int i=0;i<questionList.size();i++){
                    correctAnswers.add(questionList.get(i).getCorrectAnswer());
                }





                System.out.println("========-==============0-============"+exam.getQuestionList());
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Exams found",Toast.LENGTH_SHORT).show();


            }
        });


        questionList=new ArrayList<>();
        recyclerView =(RecyclerView) findViewById(R.id.recyler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        correctAnswers= new ArrayList<>();

        adapter= new QuestionAdapter(questionList,this);
        recyclerView.setAdapter(adapter);
//        final QuestionListAdapter adapter= new QuestionListAdapter(this,R.layout.question_layout,questionList);
//        questions.setAdapter(adapter);



        submit_btn=(Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (number.size() == 0) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_layout_root));


                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("You have not attempted any question. Please attempt the all the questions!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();


                } else {


                    int score = 0;

                    score = mark.size();

//                    showAnswers();
                    for(int i=0;i<5;i++) {
                        ans.setVisibility(View.VISIBLE);
                        ans1.setVisibility(View.VISIBLE);
                        ans2.setVisibility(View.VISIBLE);
                        ans3.setVisibility(View.VISIBLE);
                    }




                    Toast.makeText(getApplicationContext(), "You scored " + score + " out of " + questionList.size(), Toast.LENGTH_SHORT).show();
                    System.out.println("---------------------" + correctAnswers + "\\" + number + "///" + "your score is " + score);
                }
            }
        });




    }
}