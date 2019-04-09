package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import hitrac.co.zw.aptest.Questions;
import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.model.Exam;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;


public class ExaminationNames extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListView examinationNames;
    public static ArrayList<String> names;
    public static String Name;

    private OnFragmentInteractionListener mListener;

    public ExaminationNames() {
    }


    public static ExaminationNames newInstance(String param1, String param2) {
        ExaminationNames fragment = new ExaminationNames();
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

        View rootView= inflater.inflate(R.layout.fragment_examination_names, container, false);
         examinationNames=(ListView)rootView.findViewById(R.id.examinationNames);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface= retrofit.create(ApiInterface.class);

        Call<List<Exam>> call=apiInterface.getExams();
       call.enqueue(new Callback<List<Exam>>() {
           @Override
           public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
               System.out.println("///////////////////////////*"+response.body());
               List<Exam> ex= response.body();

                   names=new ArrayList<>();

                       names.add(ex.get(0).getExamName());
                       names.add(ex.get(1).getExamName());


                       final ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, names);

                       examinationNames.setAdapter(arrayAdapter);
                       System.out.println("////////////////////****************/"+names);

               examinationNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Name=((TextView) view).getText().toString();
                       Intent intent = new Intent(getActivity(), Questions.class);
                       startActivity(intent);
                   }
               });

           }

           @Override
           public void onFailure(Call<List<Exam>> call, Throwable t) {

           }
       });

        return rootView;
    }


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
        void onFragmentInteraction(Uri uri);
    }
}
