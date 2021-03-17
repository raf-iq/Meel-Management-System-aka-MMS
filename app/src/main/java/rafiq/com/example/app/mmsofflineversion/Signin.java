package rafiq.com.example.app.mmsofflineversion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import rafiq.com.example.app.mmsofflineversion.DialogHelper.ShowDialog;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.User;

public class Signin extends AppCompatActivity implements View.OnClickListener{
    private Button signup;
    private TextView login;
    private DatabaseHelper sqLiteDatabase;
    private View mProgressView;
    private View mSignupFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        if(MainActivity.MANAGER!=null){
            startActivity(new Intent(this,MainActivity.class));
        }
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        mProgressView = findViewById(R.id.signin_progress);
        mSignupFormView = findViewById(R.id.signup_form);

        sqLiteDatabase=new DatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                /* code for working with sign up page database entry*/
                AutoCompleteTextView name=findViewById(R.id.user_name);
                AutoCompleteTextView user_name=findViewById(R.id.user_id);
                AutoCompleteTextView password=findViewById(R.id.user_password);
                AutoCompleteTextView rePassword=findViewById(R.id.user_re_password);
                AutoCompleteTextView mail=findViewById(R.id.user_email);
                AutoCompleteTextView mobile=findViewById(R.id.user_phone);
                View err=null;
                boolean check=false;
                if(name.getText().toString().equals("")) {
                    name.setError(getString(R.string.error_field_required));
                    err=name;
                    check=true;
                }
                if(user_name.getText().toString().equals("")) {
                    user_name.setError(getString(R.string.error_field_required));
                    err=user_name;
                    check=true;
                }
                if(password.getText().toString().equals("")) {
                    password.setError(getString(R.string.error_field_required));
                    err=password;
                    check=true;
                }
                if(rePassword.getText().toString().equals("")) {
                    rePassword.setError(getString(R.string.error_field_required));
                    err=rePassword;
                    check=true;
                }
                if(mail.getText().toString().equals("") || !isEmailValid(mail.getText().toString())){
                    mail.setError(getString(R.string.error_invalid_email));
                    err=mail;
                    check=true;
                }
                if(mobile.getText().toString().equals("")) {
                    mobile.setError(getString(R.string.error_field_required));
                    err=mobile;
                    check=true;
                }
                if(!password.getText().toString().equals(rePassword.getText().toString())){
                    rePassword.setError(getString(R.string.password_mismatched));
                    err=rePassword;
                    check=true;
                }

                if (check){
                    name.setText(name.getText().toString());
                    user_name.setText(user_name.getText().toString());
                    password.setText(password.getText().toString());
                    rePassword.setText(rePassword.getText().toString());
                    mail.setText(mail.getText().toString());
                    mobile.setText(mobile.getText().toString());
                }
                else {
                    String Name=name.getText().toString().trim();
                    String User_name=user_name.getText().toString().trim();
                    String Pass=String.valueOf(password.getText().toString().trim().hashCode());
                    String Mail=mail.getText().toString().trim();
                    String Mobile=mobile.getText().toString().trim();

                    User user=new User(0,Name,User_name,Pass,Mail,Mobile);
                    //showProgress(true);
                    User user1 = sqLiteDatabase.addUser(user);
                    if(user1 == null){
                        //showProgress(false);
                        new ShowDialog(this,"user name or emaial or mobile number are taken.Try something else. Thanks");
                        //Toast.makeText(this,"Please Try again",Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(this,Signin.class));
                    }
                    else {
                        MainActivity.MANAGER=user1;
                        Toast.makeText(this,"Signing In",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                    }
                }

               /* Toast.makeText(this,"Sign up",Toast.LENGTH_SHORT).show();*/
                break;
            case R.id.login:
                startActivity(new Intent(this,Login.class));
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignupFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignupFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com");
    }
}
