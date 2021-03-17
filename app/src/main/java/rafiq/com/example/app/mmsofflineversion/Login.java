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

import rafiq.com.example.app.mmsofflineversion.DialogHelper.ShowDialog;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.User;

public class Login extends AppCompatActivity {
    private AutoCompleteTextView emailView;
    private AutoCompleteTextView passwordView;
    private View progressView;
    private View loginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailView = (AutoCompleteTextView) findViewById(R.id.email);
        passwordView = (AutoCompleteTextView) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);
    }

    public void attemptLogin(){
        String email=emailView.getText().toString();
        String pass=passwordView.getText().toString();
        boolean check=false;
        View focus=null;
        if(!isEmailValid(email) || email.isEmpty()){
            emailView.setError(getString(R.string.error_invalid_email));
            focus=emailView;
            check=true;
        }
        if(!isPasswordValid(pass) || pass.isEmpty()){
            passwordView.setError(getString(R.string.error_invalid_password));
            focus=passwordView;
            check=true;
        }
        if(!check){
            /* code for varified user login*/
            DatabaseHelper db=new DatabaseHelper(this);
            showProgress(true);
            //Toast.makeText(this,"LOGING IN ", Toast.LENGTH_SHORT).show();
            pass=String.valueOf(pass.hashCode());
            User user=db.isRegisteredUser(email,pass);

            if(user==null){
                showProgress(false);
                new ShowDialog(this,"Email Or Password Not Matched");
                //Toast.makeText(this, "Email Or Password Not Matched", Toast.LENGTH_SHORT).show();
            }
            else if(user.getId() == -1){
                startActivity(new Intent(this,Signin.class));
            }
            else {
                MainActivity.MANAGER=user;
                //Toast.makeText(this,"problem here",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    // sign up button handler function
    public void signUp(View view){
        startActivity(new Intent(this,Signin.class));
    }
}
