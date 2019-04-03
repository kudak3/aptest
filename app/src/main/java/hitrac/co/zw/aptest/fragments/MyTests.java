package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.model.Exam;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

public class MyTests extends Fragment {


    public ListView examNames;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyTests() {

    }



    public static MyTests newInstance(String param1, String param2) {
        MyTests fragment = new MyTests();
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

        View rootView= inflater.inflate(R.layout.fragment_my_tests, container, false);
        examNames=(ListView)rootView.findViewById(R.id.examNames);

       final ArrayList<String> exams= new ArrayList<>();
       final ArrayAdapter arrayAdapter= new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,exams);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface= retrofit.create(ApiInterface.class);
        Call<Exam> call2=apiInterface.getExam("mid-month exam");
       call2.enqueue(new Callback<Exam>() {
           @Override
           public void onResponse(Call<Exam> call, Response<Exam> response) {
               Exam exam=response.body();
               exams.add(exam.getExamName());
               examNames.setAdapter(arrayAdapter);
               examNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Toast.makeText(getActivity(), ((TextView) view).getText(),
                               Toast.LENGTH_SHORT).show();
                   }
               });
               System.out.println("========-==============0-============"+exam.getQuestionList());

           }

           @Override
           public void onFailure(Call<Exam> call, Throwable t) {
               Toast.makeText(getActivity(),"No Exams found",Toast.LENGTH_SHORT).show();


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
