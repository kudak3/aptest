package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.model.Exam;
import hitrac.co.zw.aptest.model.Question;
import hitrac.co.zw.aptest.model.Subject;
import hitrac.co.zw.aptest.model.Syllabus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetTest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetTest extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<Question> test;
    public  Spinner answerSpinner,answerSpinner1,answerSpinner2,answerSpinner3,answerSpinner4;

    private OnFragmentInteractionListener mListener;

    public SetTest() {

    }

    public static SetTest newInstance(String param1, String param2) {
        SetTest fragment = new SetTest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     final View rootView= inflater.inflate(R.layout.fragment_set_test, container, false);

        Spinner syllabusSpinner=(Spinner)rootView.findViewById(R.id.syllabusSpinner);
        final TextView question1=(TextView)rootView.findViewById(R.id.question);
        final TextView ans1=(TextView)rootView.findViewById(R.id.answer1);
        final TextView ans2=(TextView)rootView.findViewById(R.id.answer2);
         final TextView ans3=(TextView)rootView.findViewById(R.id.answer3);
       final TextView ans4=(TextView)rootView.findViewById(R.id.answer4);

        final TextView question2=(TextView)rootView.findViewById(R.id.question2);
        final TextView ans2_1=(TextView)rootView.findViewById(R.id.answer2_1);
        final TextView ans2_2=(TextView)rootView.findViewById(R.id.answer2_2);
        final TextView ans2_3=(TextView)rootView.findViewById(R.id.answer2_3);
        final TextView ans2_4=(TextView)rootView.findViewById(R.id.answer2_4);

        final TextView question3=(TextView)rootView.findViewById(R.id.question3);
        final TextView ans3_1=(TextView)rootView.findViewById(R.id.answer3_1);
        final TextView ans3_2=(TextView)rootView.findViewById(R.id.answer3_2);
        final TextView ans3_3=(TextView)rootView.findViewById(R.id.answer3_3);
        final TextView ans3_4=(TextView)rootView.findViewById(R.id.answer3_4);

        final TextView question4=(TextView)rootView.findViewById(R.id.question4);
        final TextView ans4_1=(TextView)rootView.findViewById(R.id.answer4_1);
        final TextView ans4_2=(TextView)rootView.findViewById(R.id.answer4_2);
        final TextView ans4_3=(TextView)rootView.findViewById(R.id.answer4_3);
        final TextView ans4_4=(TextView)rootView.findViewById(R.id.answer4_4);

        final TextView question5=(TextView)rootView.findViewById(R.id.question5);
        final TextView ans5_1=(TextView)rootView.findViewById(R.id.answer5_1);
        final TextView ans5_2=(TextView)rootView.findViewById(R.id.answer5_2);
        final TextView ans5_3=(TextView)rootView.findViewById(R.id.answer5_3);
        final TextView ans5_4=(TextView)rootView.findViewById(R.id.answer5_4);

        ArrayList<String> syllabi= new ArrayList<>();
        syllabi.add("IGCSE");
        syllabi.add("BGCSE");
        syllabi.add("DRIVING THEORY");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,syllabi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        syllabusSpinner.setAdapter(adapter);

        final ArrayList<String> subjects= new ArrayList<>();

      syllabusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0: {
                        subjects.clear();
                        subjects.add("Accounting");
                        subjects.add("Biology");
                        subjects.add("Chemistry");
                        subjects.add("Physics");


                    }
                    break;

                    case 1: {
                        subjects.clear();

                        subjects.add("Biology");
                        subjects.add("Chemistry");
                        subjects.add("Double Science");
                        subjects.add("Physics");
                    }
                    break;

                    case 2: {
                        subjects.clear();
                        subjects.add("Class B");

                    }
                    break;

                }
                Spinner subjectsSpinner=(Spinner)rootView.findViewById(R.id.subjectsSpinner);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,subjects);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectsSpinner.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        answerSpinner=(Spinner)rootView.findViewById(R.id.answerSpinner);
        answerSpinner1=(Spinner)rootView.findViewById(R.id.answerSpinner1);
       answerSpinner2=(Spinner)rootView.findViewById(R.id.answerSpinner2);
        answerSpinner3=(Spinner)rootView.findViewById(R.id.answerSpinner3);
         answerSpinner4=(Spinner)rootView.findViewById(R.id.answerSpinner4);
        ArrayList<String> ans= new ArrayList<>();
        ans.add("A");
        ans.add("B");
        ans.add("C");
        ans.add("D");
        ArrayAdapter<String> adapter3=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,ans);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        answerSpinner.setAdapter(adapter3);
        answerSpinner1.setAdapter(adapter3);
        answerSpinner2.setAdapter(adapter3);
        answerSpinner3.setAdapter(adapter3);
        answerSpinner4.setAdapter(adapter3);

        Button btn =(Button)rootView.findViewById(R.id.setTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Question q1=new Question(question1.getText().toString(),
                        ans1.getText().toString(),ans2.getText().toString(),
                        ans3.getText().toString(),ans4.getText().toString(),
                        answerSpinner.getSelectedItem().toString(),"1",1);
                Question q2= new Question(question2.getText().toString(),
                        ans2_1.getText().toString(),ans2_2.getText().toString()
                        ,ans2_3.getText().toString(),ans2_4.getText().toString()
                        ,answerSpinner1.getSelectedItem().toString(),"2",2);

                Question q3= new Question(question3.getText().toString(),
                        ans3_1.getText().toString(),ans3_2.getText().toString()
                        ,ans3_3.getText().toString(),ans3_4.getText().toString()
                        ,answerSpinner2.getSelectedItem().toString(),"3",3);

                Question q4= new Question(question4.getText().toString(),
                        ans4_1.getText().toString(),ans4_2.getText().toString()
                        ,ans4_3.getText().toString(),ans4_4.getText().toString()
                        ,answerSpinner3.getSelectedItem().toString(),"4",4);

                Question q5= new Question(question5.getText().toString(),
                        ans5_1.getText().toString(),ans5_2.getText().toString()
                        ,ans5_3.getText().toString(),ans5_4.getText().toString()
                        ,answerSpinner4.getSelectedItem().toString(),"5",5);
          test= new ArrayList<>();
                test.add(q1);
                test.add(q2);
                test.add(q3);
                test.add(q4);
                test.add(q5);
                final Subject maths= new Subject("50","name");
                final Syllabus driving= new Syllabus("2","driving");
                Exam exam= new Exam("examTest","1",test,maths,driving);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface apiInterface= retrofit.create(ApiInterface.class);

                Call<ResponseBody>call= apiInterface.setExam(exam );

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity(),"zvaita",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });




      return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
