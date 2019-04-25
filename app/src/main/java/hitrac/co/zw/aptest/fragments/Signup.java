package hitrac.co.zw.aptest.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.configuration.Interceptor;
import hitrac.co.zw.aptest.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;
import static hitrac.co.zw.aptest.fragments.Login.client;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Signup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Signup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnSubmit;
    private EditText etFirstName,etLastName,etPassword,etPhoneNumber,etEmail,etUserName;
    private RadioGroup rgRole;
    private RadioButton rbTeacher,rbStudent;
    private ProgressDialog progressDialog;






    private OnFragmentInteractionListener mListener;

    public Signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Signup.
     */
    // TODO: Rename and change types and number of parameters
    public static Signup newInstance(String param1, String param2) {
        Signup fragment = new Signup();
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
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        etFirstName = rootView.findViewById(R.id.firstName);
        etLastName = rootView.findViewById(R.id.lastname);
        etUserName = rootView.findViewById(R.id.userName);
        etPassword = rootView.findViewById(R.id.passwd);
        etEmail = rootView.findViewById(R.id.email);
        etPhoneNumber = rootView.findViewById(R.id.phoneNumber);

        rgRole = rootView.findViewById(R.id.role);
        rbTeacher = rootView.findViewById(R.id.teacher);
        rbStudent = rootView.findViewById(R.id.student);

        btnSubmit = rootView.findViewById(R.id.signupBtn);
        progressDialog = new ProgressDialog(getContext(),ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
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
     * See the Android Training lesson <a href=                    android:layout_width="wrap_content"

     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void signUp() {
        Log.d("SIGNUP FRAGMANET", "Signup");

        if (!validate()){
            Toast.makeText(getContext(),"Please make sure that all fields are correctly filled",Toast.LENGTH_LONG).show();

            return;
        }
        progressDialog.setMessage("Creating account please wait...");
        showDialog();


        btnSubmit.setEnabled(false);



//        add general interceptor "admin admin"
        client.addInterceptor(new Interceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface requestService = retrofit.create(ApiInterface.class);


        int roleCheckedRadioButtonId = rgRole.getCheckedRadioButtonId();
        String role;


        if (roleCheckedRadioButtonId == R.id.teacher){
            role = "Teacher";
        }else {
            role = "Student";
        }

        User user = new User(); progressDialog.setMessage("Authenticating...");
//        user.setFirstName(etFirstName.getText().toString());
//        user.setLastName(etLastName.getText().toString());
//        user.setUserName(etUserName.getText().toString());
//        user.setPassword(etPassword.getText().toString());
//        user.setEmail(etEmail.getText().toString());
//        user.setPhoneNumber(etPhoneNumber.getText().toString());
//        user.setRole(role);

        Call<ResponseBody> call = requestService.signUpUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                hideDialog();
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStackImmediate();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                hideDialog();
                Toast.makeText(getContext(),"Account creation failed please check your internet connection and try again",Toast.LENGTH_LONG).show();

            }
        });

    }

    public boolean validate(){
        boolean valid = true;
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();


        if(firstName.isEmpty() || firstName.length() < 4){
            etFirstName.setError("should be at least 4 characters long");
            valid = false;
        }else {
            etFirstName.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 4){
            etLastName.setError("should be at least 4 characters long");
            valid = false;
        }

        if (userName.isEmpty() || userName.length() < 4){
            etUserName.setError("should be at least 4 characters long");
            valid = false;
        }else {
            etUserName.setError(null);
        }

        if (password.isEmpty() || password.length() < 4){
            etPassword.setError("password should be at least 6 alphanumeric characters long");
            valid = false;
        }else {
            etPassword.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("please enter a valid email address");
            valid = false;
        }else {
            etEmail.setError(null);
        }

        if (phoneNumber.isEmpty() || !Patterns.PHONE.matcher(phoneNumber).matches()){
            etPhoneNumber.setError("please enter a valid phone number");
            valid = false;
        }else {
            etPhoneNumber.setError(null);
        }

        if (rgRole.getCheckedRadioButtonId() == -1){
            rbStudent.setError("Please check atleast one radio button");
            rbTeacher.setError("Please check atleast one radio button");

        }else{
            rbTeacher.setError(null);
            rbStudent.setError(null);

        }


        return valid;
    }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
