package hitrac.co.zw.aptest.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hitrac.co.zw.aptest.R;
import hitrac.co.zw.aptest.configuration.ApiInterface;
import hitrac.co.zw.aptest.configuration.Interceptor;
import hitrac.co.zw.aptest.model.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static hitrac.co.zw.aptest.configuration.ApiClient.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   public Button loginBtn;
   public static EditText etUserName,etPassword;
   public static boolean isLogged=false;
   public static String role;
   public static  String userId;
   private ProgressDialog progressDialog;
   private TextView tvSignup;

    public static OkHttpClient.Builder client = new OkHttpClient.Builder();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_login, container, false);


        loginBtn = rootView.findViewById(R.id.loginBtn);
        tvSignup = rootView.findViewById(R.id.signupBtn);


        etUserName=(EditText)rootView.findViewById(R.id.userName);
        etPassword=(EditText)rootView.findViewById(R.id.password);
        progressDialog = new ProgressDialog(getContext(),ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setEnabled(false);
                           login();
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment fragment= new Signup();
                FragmentManager fragmentManager= getFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);

            }
        });



        return  rootView;
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
    private void login() {
        if (!validate()){
            Toast.makeText(getContext(),"Please make sure that all fields are correctly filled",Toast.LENGTH_LONG).show();
            loginBtn.setEnabled(true);

            return;
        }
        loginBtn.setEnabled(false);

        progressDialog.setMessage("Authenticating ...");
        showDialog();


        client.addInterceptor(new Interceptor(etUserName.getText().toString(), etPassword.getText().toString()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface requestService = retrofit.create(ApiInterface.class);
        Call<User> call = requestService.login(etUserName.getText().toString(),etPassword.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!"userExists".equals(response.headers().get("Responded"))) {

                    hideDialog();

                    loginBtn.setEnabled(true);

                    Toast.makeText(getActivity(), "Incorrect credentials! Please try again",
                            Toast.LENGTH_LONG).show();
                } else {
                    User user = response.body();
                    userId = user.getId();

                    role=response.headers().get("Role");

                    if("Student".equals(response.headers().get("Role"))){
                    Fragment fragment= new Home();
                    FragmentManager fragmentManager= getFragmentManager();
                    FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,fragment);
                    fragmentTransaction.commit();


                    Toast.makeText(getActivity(), "logged in successfully!",
                            Toast.LENGTH_LONG).show();
                        loginBtn.setEnabled(true);
                    hideDialog();
                   }else if("Teacher".equals(role)){
                        Fragment fragment= new TeacherHome();
                        FragmentManager fragmentManager= getFragmentManager();
                        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,fragment);
                        fragmentTransaction.commit();


                        Toast.makeText(getActivity(), "logged in successfully!",
                                Toast.LENGTH_LONG).show();
                        loginBtn.setEnabled(true);
                        hideDialog();
                    }
                }

            }
            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.d("onFailure", throwable.toString());
                System.out.println("========================");

                Toast.makeText(getActivity(), "Login failed. Please check your internet connecion.", Toast.LENGTH_SHORT).show();
                hideDialog();


            }
        });
    }
    public boolean validate(){
        boolean valid = true;

        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();



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



