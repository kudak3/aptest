package hitrac.co.zw.aptest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiClient;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.configuration.Interceptor;
import hitrac.co.zw.aptest.model.User;
import hitrac.co.zw.aptest.model.UserDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public TextView profile_name,profile_username,
            profile_last_name, profile_role,profile_email,
            profile_phone,profile_address;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View rootView=inflater.inflate(R.layout.fragment_profile, container, false);
        profile_name=(TextView)rootView.findViewById(R.id.profile_name);
        profile_last_name=(TextView)rootView.findViewById(R.id.profile_last_name);
        profile_username=(TextView)rootView.findViewById(R.id.profile_username);
        profile_role=(TextView)rootView.findViewById(R.id.profile_role);
        profile_email=(TextView)rootView.findViewById(R.id.profile_email);
        profile_phone=(TextView)rootView.findViewById(R.id.profile_phone);
        profile_address=(TextView)rootView.findViewById(R.id.profile_address);
        Login.client.addInterceptor(new Interceptor(Login.userName.getText().toString(),Login.password.getText().toString()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(Login.client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface= retrofit.create(ApiInterface.class);
       Call<UserDetails> call=apiInterface.getUserById(Login.userId);
       call.enqueue(new Callback<UserDetails>() {
           @Override
           public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
               UserDetails userDetails= response.body();
               profile_name.setText(userDetails.getFirstName());
               profile_last_name.setText(userDetails.getLastName());
               profile_username.setText(userDetails.getUserName());
//               profile_role.setText(userDetails.get);
               profile_email.setText(userDetails.getEmail());
               profile_phone.setText(userDetails.getPhoneNum());
               profile_address.setText(userDetails.getAddress());

           }

           @Override
           public void onFailure(Call<UserDetails> call, Throwable t) {
               System.out.println("******************"+t);
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
