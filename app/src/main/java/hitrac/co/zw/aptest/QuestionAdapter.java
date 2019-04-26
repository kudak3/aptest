package hitrac.co.zw.aptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hitrac.co.zw.aptest.model.Question;

import static hitrac.co.zw.aptest.Questions.correctAnswers;
import static hitrac.co.zw.aptest.Questions.mark;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questions;
    private  Context context;
    private ICheckChangeListener iCheckChangeListener;
    public static ArrayList<String> number;
public static TextView ans, ans1,ans2,ans3;

    public QuestionAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }
     @Override
    public int getItemViewType(  int position) {


        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        final  Question question=questions.get(position);
        holder.qstn.setText(question.getQuestion());
        holder.A.setText(question.getPossibleAnswer1());
        holder.B.setText(question.getPossibleAnswer2());
        holder.C.setText(question.getPossibleAnswer3());
        holder.D.setText(question.getPossibleAnswer4());

        holder.setICheckChangeListener(new ICheckChangeListener() {
            @Override
            public void onOptionSelected(int position, int itemSelected) {



            }
        });


        number=new ArrayList<>();

        for(int i=0;i<5;i++){
            number.add("2");
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  RadioButton A,B,C,D;
        public  RadioGroup radioGroup;
        public  TextView qstn,correct_ans;
        private ICheckChangeListener iCheckChangeListener;



        public ViewHolder(View itemView) {
            super(itemView);
            correct_ans=(TextView)itemView.findViewById(R.id.correct_answer);
            qstn=(TextView)itemView.findViewById(R.id.textView1);
            radioGroup=(RadioGroup)itemView.findViewById(R.id.rdg);
            A=(RadioButton) itemView.findViewById(R.id.textView2);
            B=(RadioButton) itemView.findViewById(R.id.textView3);
            C=(RadioButton)itemView.findViewById(R.id.textView4);
            D=(RadioButton)itemView.findViewById(R.id.textView5);
            mark = new ArrayList<>();


            A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    iCheckChangeListener.onOptionSelected(getAdapterPosition(),1);
                    if(b){
                    System.out.println("///////////////"+questions.get(getAdapterPosition()).isSelected1());
                    A.setTag(getAdapterPosition());
                    int a= (int)(A.getTag());
                        System.out.println("333333333333333333 "+a);

                        number.set(a,"A");
                       correct_ans.setText("the correct answer is "+correctAnswers.get(a));
                       correct_ans.setVisibility(View.INVISIBLE);
                        ans1=correct_ans;
                        ans=correct_ans;
                        ans3=correct_ans;
                        ans2=correct_ans;



                        for (int i = getAdapterPosition(); i <=getItemViewType(); i++) {
                            if (correctAnswers.get(i).equals(number.get(a))) {
                                mark.add("1");
                                break;

                            }


                        }
                        number.set(a,"2");
                    }else{
                        number.remove(number.size()-number.size());
                    }
                    }

            });
            B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b){
                    B.setTag(getAdapterPosition());
                    int aa= (int)(B.getTag());
                    System.out.println("BBBBBBBBBBBBBBBB "+aa);

                    number.set(aa,"B");


                       correct_ans.setText("the correct answer is "+correctAnswers.get(aa));
                        correct_ans.setVisibility(View.INVISIBLE);
                        ans1=correct_ans;
                           ans=correct_ans;
                        ans3=correct_ans;
                        ans2=correct_ans;

                        for (int i = getAdapterPosition(); i <=getItemViewType(); i++) {

                        if (correctAnswers.get(i).equals(number.get(aa))) {
                            mark.add("1");
                            break;
                        }

                    }
                        number.set(aa,"2");
                    }else{
                        number.remove(number.size()-number.size());
                    }

                }
            });
            C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(b){
                    C.setTag(getAdapterPosition());
                    int c= (int)(C.getTag());
                    System.out.println("CCCCCCCCCCCCCC "+c);

                    number.set(c,"C");

                      correct_ans.setText("the correct answer is "+correctAnswers.get(c));

                      correct_ans.setVisibility(View.INVISIBLE);
                     ans1=correct_ans;
                           ans=correct_ans;
                        ans3=correct_ans;
                        ans2=correct_ans;

                    for (int i = getAdapterPosition(); i <=getItemViewType(); i++) {

                        if (correctAnswers.get(i).equals(number.get(c))) {
                            mark.add("1");
                            break;
                        }

                    }
                      number.set(c,"2");
                  }else {
                      number.remove(number.size()-number.size());
                  }

                }
            });

            D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(b) {
                      D.setTag(getAdapterPosition());
                      int d = (int) (D.getTag());
                      System.out.println("CCCCCCCCCCCCCC " + d);

                      number.set(d, "D");
                      correct_ans.setText("the correct answer is "+correctAnswers.get(d));
                      correct_ans.setVisibility(View.INVISIBLE);
                      ans1=correct_ans;
                      ans=correct_ans;
                      ans3=correct_ans;
                      ans2=correct_ans;

                      for (int i = getAdapterPosition(); i <= getItemViewType(); i++) {

                          if (correctAnswers.get(i).equals(number.get(d))) {
                              mark.add("1");
                              break;
                          }

                      }
                      number.set(d,"2");

                  }else{
                      number.remove(number.size()-number.size());
                  }

                }
            });

        }

        void setICheckChangeListener(ICheckChangeListener iCheckChangeListener) {
            this.iCheckChangeListener = iCheckChangeListener;
        }


    }

}
