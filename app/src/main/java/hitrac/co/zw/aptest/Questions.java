package hitrac.co.zw.aptest;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import static hitrac.co.zw.aptest.QuestionListAdapter.a;
import static hitrac.co.zw.aptest.QuestionListAdapter.b;
import static hitrac.co.zw.aptest.QuestionListAdapter.c;
import static hitrac.co.zw.aptest.QuestionListAdapter.correct_answer;
import static hitrac.co.zw.aptest.QuestionListAdapter.d;
import static hitrac.co.zw.aptest.QuestionListAdapter.number;
import static hitrac.co.zw.aptest.QuestionListAdapter.radioGroup;
import static hitrac.co.zw.aptest.QuestionListAdapter.showAnswers;
import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

public class Questions extends AppCompatActivity {
public Exam exam;
public static ArrayList<Question> questionList;
  public ProgressDialog progressDialog;
  public Button submit_btn;
  public static ArrayList<String> correctAnswers;


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
//                questionList.addAll(0,exam.getQuestionList());


                System.out.println("========-==============0-============"+exam.getQuestionList());
               progressDialog.hide();
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Exams found",Toast.LENGTH_SHORT).show();


            }
        });


      questionList=new ArrayList<>();
      Question q1=new Question("1+5=","15","6","5","4","D","1",1);
      Question q2=new Question("1+5=","15","6","5","4","B","2",2);
      questionList.add(q1);
      questionList.add(q2);
        ListView questions =(ListView)findViewById(R.id.questions);
        correctAnswers= new ArrayList<>();







        final QuestionListAdapter adapter= new QuestionListAdapter(this,R.layout.question_layout,questionList);
        questions.setAdapter(adapter);
        for(int i=0;i<questionList.size();i++){
            correctAnswers.add(questionList.get(i).getCorrectAnswer());
        }

        submit_btn=(Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(number.size()==0){
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


                }else {
                    int score = 0;
                    ArrayList<String> mark = new ArrayList<>();

                    for (int i = 0; i < adapter.getCount(); i++) {
                        if (correctAnswers.get(i).equals(number.get(i))) {
                            mark.add("1");
                        }

                    }
                    score = mark.size();

                    showAnswers();


                    Toast.makeText(getApplicationContext(), "You scored " + score + " out of " + questionList.size(), Toast.LENGTH_SHORT).show();
                    System.out.println("---------------------" + correctAnswers + "\\" + number + "///" + "your score is " + score);
                }
            }
        });




    }
}
