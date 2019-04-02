package hitrac.co.zw.aptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import hitrac.co.zw.aptest.model.Question;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private Context mContext;
    int mResource;

    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String question=getItem(position).getQuestion();
        String  answer1=getItem(position).getAnswer1();
        String  answer2=getItem(position).getAnswer2();
        String  answer3=getItem(position).getAnswer3();
        String  answer4=getItem(position).getAnswer4();
        String  correctAnswer=getItem(position).getCorrectAnswer();

        Question q=new Question(question,answer1,answer2,answer3,answer4,correctAnswer);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView= inflater.inflate(mResource,parent,false);

        TextView qstn=(TextView)convertView.findViewById(R.id.textView1);
        RadioButton a=(RadioButton) convertView.findViewById(R.id.textView2);
        RadioButton b=(RadioButton) convertView.findViewById(R.id.textView3);
        RadioButton c=(RadioButton)convertView.findViewById(R.id.textView4);
        RadioButton d=(RadioButton)convertView.findViewById(R.id.textView5);

        qstn.setText(question);
        a.setText(answer1);
        b.setText(answer2);
        c.setText(answer3);
        d.setText(answer4);

        return convertView;

    }

}
