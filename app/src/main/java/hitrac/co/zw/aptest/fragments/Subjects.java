package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hitrac.co.zw.aptest.Questions;
import hitrac.co.zw.aptest.R;

import static hitrac.co.zw.aptest.fragments.Home.isIsSyllabus2;
import static hitrac.co.zw.aptest.fragments.Home.isIsSyllabus3;
import static hitrac.co.zw.aptest.fragments.Home.isSyllabus1;

public class Subjects extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public ListView subjects;

    public Subjects() {
    }


    // TODO: Rename and change types and number of parameters
    public static Subjects newInstance(String param1, String param2) {
        Subjects fragment = new Subjects();
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
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_subjects, container, false);
        subjects=(ListView)rootView.findViewById(R.id.subjects);

        final ArrayList<String> subjectList = new ArrayList<>();
             if(isSyllabus1==true) {


                 subjectList.add("Accounting");
                 subjectList.add("Biology");
                 subjectList.add("Chemistry");
                 subjectList.add("Physics");
             }
        else if(isIsSyllabus2==true) {


            subjectList.add("Biology");
            subjectList.add("Chemistry");
            subjectList.add("Double Science");
            subjectList.add("Physics");
        }
        else if(isIsSyllabus3==true){
                 subjectList.add("Class B");
             }
                 ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, subjectList);
                 subjects.setAdapter(arrayAdapter);

                 subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         if (i == 0) {


                             Fragment fragment= new ExaminationNames();
                             FragmentManager fragmentManager = getFragmentManager();
                             FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                             fragmentTransaction.replace(R.id.fragment_container,fragment);
                             fragmentTransaction.commit();
                             fragmentTransaction.addToBackStack(null);
                         }
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
