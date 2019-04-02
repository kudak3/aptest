package hitrac.co.zw.aptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import hitrac.co.zw.aptest.model.Question;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        ListView questions =(ListView)findViewById(R.id.questions);

        Question question1= new Question("what is the sum of 1 and 1","0","1","2","3","2");
        Question question2= new Question("what is the difference between 1 and 1","0","1","2","3","0");

        ArrayList<Question> questionList= new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);

        QuestionListAdapter adapter= new QuestionListAdapter(this,R.layout.question_layout,questionList);
        questions.setAdapter(adapter);
    }
}
