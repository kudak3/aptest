package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hitrac.co.zw.aptest.Dashboard;
import hitrac.co.zw.aptest.R;


import static hitrac.co.zw.aptest.Dashboard.loginmenuItem;
import static hitrac.co.zw.aptest.Dashboard.profile;
import static hitrac.co.zw.aptest.Dashboard.results;
import static hitrac.co.zw.aptest.Dashboard.toolbarName;
import static hitrac.co.zw.aptest.fragments.Login.isLogged;
import static hitrac.co.zw.aptest.fragments.Login.userName;


public class Home extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Button syllabus1,syllabus2,syllabus3;
    public static boolean isSyllabus1=false;
    public static boolean isIsSyllabus2=false;
    public static boolean isIsSyllabus3=false;


    private OnFragmentInteractionListener mListener;

    public Home() {

    }


    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View rootView= inflater.inflate(R.layout.fragment_home, container, false);

        syllabus1=(Button)rootView.findViewById(R.id.syllabus1);
        syllabus2=(Button)rootView.findViewById(R.id.syllabus2);
        syllabus3=(Button)rootView.findViewById(R.id.syllabus3);
        loginmenuItem.setTitle("Logout");
        results.setTitle("My Results");
        profile.setTitle("My Profile");

        if(isLogged) {
            toolbarName.setTitle(userName.getText().toString());
            toolbarName.setVisible(true);
        }



        syllabus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isSyllabus1=true;
                isIsSyllabus2=false;
                isIsSyllabus3=false;
                Fragment fragment= new Subjects();
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        syllabus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIsSyllabus2=true;
                isSyllabus1=false;
                isIsSyllabus3=false;
                Fragment fragment= new Subjects();
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);

            }
        });
syllabus3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        isIsSyllabus3=true;
        isIsSyllabus2=false;
        isSyllabus1=false;
        Fragment fragment= new Subjects();
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);

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
