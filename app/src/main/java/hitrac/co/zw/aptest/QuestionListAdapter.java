package hitrac.co.zw.aptest;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hitrac.co.zw.aptest.model.Question;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private Context mContext;
    int mResource;
    public static ArrayList<String> number , selectedAnswers ;
    public static  RadioButton a,b,c,d;
    public static  RadioGroup radioGroup;
    public static TextView correct_answer;
    public static int i;


    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

        selectedAnswers = new ArrayList<>();
        for(i=0;i<5;i++){
            selectedAnswers.add("4");
        }
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String question=getItem(position).getQuestion();
        String  answer1=getItem(position).getPossibleAnswer1();
        String  answer2=getItem(position).getPossibleAnswer2();
        String  answer3=getItem(position).getPossibleAnswer3();
        String  answer4=getItem(position).getPossibleAnswer4();
        String  correctAnswer=getItem(position).getCorrectAnswer();
        String id =getItem(position).getId();
        int questionNumber=getItem(position).getQuestionNumber();


        number=new ArrayList<>();

        Question q=new Question(question,answer1,answer2,answer3,answer4,correctAnswer,id,questionNumber);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView= inflater.inflate(mResource,parent,false);

        TextView qstn=(TextView)convertView.findViewById(R.id.textView1);
      radioGroup=(RadioGroup)convertView.findViewById(R.id.rdg);
         a=(RadioButton) convertView.findViewById(R.id.textView2);
          b=(RadioButton) convertView.findViewById(R.id.textView3);
          c=(RadioButton)convertView.findViewById(R.id.textView4);
        d=(RadioButton)convertView.findViewById(R.id.textView5);
        correct_answer=(TextView)convertView.findViewById(R.id.correct_answer);

        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                a.setTag(position);
                if(b){
                    number.add("A");

                    selectedAnswers.set(position ,"0");
                    System.out.println("-=-=-=-=-=-position"+position);
                    System.out.println("[]]]]]]]]]]]]]]]"+selectedAnswers.get(position));




                }
                else {
                    number.remove(number.size()-number.size());
                }
            }
        });


        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bi) {
                b.setTag(position);
                if(bi){
                    number.add("B");
                    selectedAnswers.set(position ,"1");
                    System.out.println("-=-=-=-=-=-position"+position);
                    System.out.println("B[]]]]]]]]]]]]]]]"+selectedAnswers.get(position));


                }
                else {
                    number.remove(number.size()-number.size());
                }
            }
        });

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c.setTag(position);
                if(b){
                    number.add("C");
                    selectedAnswers.set(position ,"2");
                    System.out.println("-=-=-=-=-=-position"+position);
                    System.out.println("C[]]]]]]]]]]]]]]]"+selectedAnswers.get(position));

                }
                else {
                    number.remove(number.size()-number.size());
                }
            }
        });

        d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                d.setTag(position);
                if(b){
                    number.add("D");
                    selectedAnswers.set(position ,"3");
                    System.out.println("-=-=-=-=-=-position"+position);
                    System.out.println("D[]]]]]]]]]]]]]]]"+selectedAnswers.get(position));
                }
                else {
                    number.remove(number.size()-number.size());
                }
            }
        });

        qstn.setText(question);
        a.setText(answer1);
        b.setText(answer2);
        c.setText(answer3);
        d.setText(answer4);
        My_method(position);

        return convertView;

    }


    private void My_method(int i) {

        try{

        for (int j=0;j<selectedAnswers.size();j++)
        {
            if (i == j) {

                int a = Integer.parseInt( selectedAnswers.get( i ).trim() );
                System.out.println("==================="+a);
                ((RadioButton)radioGroup.getChildAt( a )).setChecked( true );

            }
        }}catch (Exception e){
            Log.d("*******", e.toString());
        }
    }
    public static void showAnswers(){
        for(i=0;i<Questions.questionList.size();i++){
            correct_answer.setText(Questions.correctAnswers.get(i));
        }
    }





}
