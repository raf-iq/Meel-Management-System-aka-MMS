package rafiq.com.example.app.mmsofflineversion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import rafiq.com.example.app.mmsofflineversion.DialogHelper.ShowDialog;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.Member;

public class AddMember extends AppCompatActivity implements View.OnClickListener {

    private Button cacel,add;
    private View progressView;
    private View view;
    private AutoCompleteTextView nameView;
    private AutoCompleteTextView emailView;
    private AutoCompleteTextView mobileView;
    private AutoCompleteTextView villageView;
    private AutoCompleteTextView thanaView;
    private AutoCompleteTextView districtView;
    private AutoCompleteTextView roomCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_add_member);
        setSupportActionBar(toolbar);

        nameView=findViewById(R.id.member_name);
        emailView=findViewById(R.id.member_email);
        mobileView=findViewById(R.id.member_phone_no);
        villageView=findViewById(R.id.member_village_name);
        thanaView=findViewById(R.id.member_thana);
        districtView=findViewById(R.id.member_district);
        roomCost=findViewById(R.id.member_room_cost);

        progressView=findViewById(R.id.progress);
        view=findViewById(R.id.add_member_form);
        cacel=findViewById(R.id.cancel_add_member);
        add=findViewById(R.id.add_member);
        cacel.setOnClickListener(this);
        add.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.top_menus,menu);
        return true;
    }
    /* code for toolbar option selecting  */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(this,Login.class));
                return true;
            case R.id.addMember:
                startActivity(new Intent(this,AddMember.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this,Profile.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* code for option menu appear */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        menu.setHeaderTitle(R.string.choose_your_option);
        inflater.inflate(R.menu.option_menus,menu);
    }
    /* code for working with option menu */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.members:
                startActivity(new Intent(this,Members.class));
                return true;
            case R.id.set_meal:
                startActivity(new Intent(this,SetMeal.class));
                return true;
            case R.id.person_meal_set:
                startActivity(new Intent(this,PersonMealSet.class));
                return true;
            case R.id.monthly_meal_detail:
                startActivity(new Intent(this,MonthlyMealDetail.class));
                return true;
           /* case R.id.set_bazar:
                startActivity(new Intent(this,SetBazar.class));
                return true;*/
            case R.id.bazar_of_the_day:
                startActivity(new Intent(this,BazarOfTheDay.class));
                return true;
           /* case R.id.room_payment:
                startActivity(new Intent(this,RoomPayment.class));
                return true;*/
            case R.id.show_payment:
                startActivity(new Intent(this,ShowPayment.class));
                return true;
            case R.id.make_payment:
                startActivity(new Intent(this,MakePayment.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    /* function for working with bottom option bar*/
    public void onOptionClickedBar(View view){
        switch (view.getId()){
            case R.id.navigation_home:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.navigation_option_menu:
                //registerForContextMenu(view);
                startActivity(new Intent(this,OptionsMenu.class));
                break;
            case R.id.navigation_notifications:
                // empty now, will add notification activity
                startActivity(new Intent(this,QueryList.class));
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_member:
                View err=null;
                String name=nameView.getText().toString().trim();
                String email=emailView.getText().toString().trim();
                String mobile=mobileView.getText().toString().trim();
                String village=villageView.getText().toString().trim();
                String thana=thanaView.getText().toString().trim();
                String district=districtView.getText().toString().trim();
                String r_cost=roomCost.getText().toString().trim();
                boolean check=false;
                if(name.isEmpty()){
                    nameView.setError(getString(R.string.error_field_required));
                    check=true;
                }
                if(email.isEmpty() || !isEmailValid(email)){
                    emailView.setError(getString(R.string.error_invalid_email));
                    check=true;
                }
                if(mobile.isEmpty()){
                    mobileView.setError(getString(R.string.error_field_required));
                    check=true;
                }
                if(village.isEmpty()){
                    villageView.setError(getString(R.string.error_field_required));
                    check=true;
                }if(thana.isEmpty()){
                    thanaView.setError(getString(R.string.error_field_required));
                    check=true;
                }
                if(district.isEmpty()){
                    villageView.setError(getString(R.string.error_field_required));
                    check=true;
                }
                if(r_cost.isEmpty()){
                    roomCost.setError(getString(R.string.error_field_required));
                }

                if(!check) {
                    DatabaseHelper db = new DatabaseHelper(this);
                    String memberAddedDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime());
                    Calendar calendar=Calendar.getInstance();
                    Member member = new Member(0,name, email, mobile, village, thana, district,Integer.parseInt(r_cost),memberAddedDate,calendar.getTimeInMillis());
                    //showProgress(true);
                    if (db.addMember(member) == -1) {
                        new ShowDialog(this,"Please,Enter member real Email and Mobile number");
                        //showProgress(false);
                        //startActivity(new Intent(this,AddMember.class));
                        //Toast.makeText(this, "Something wrong.Try Again", Toast.LENGTH_SHORT).show();
                    } else {
                        new ShowDialog(this,MainActivity.class,"Member added");
                        //Toast.makeText(this, "Member added", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(this, MainActivity.class));
                    }
                }
                else {
                    nameView.setText(name);
                    emailView.setText(email);
                    mobileView.setText(mobile);
                    villageView.setText(village);
                    thanaView.setText(thana);
                    districtView.setText(district);
                }
                //Toast.makeText(this,"Added Member",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel_add_member:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
            view.setVisibility(show ? View.VISIBLE : View.GONE);
            view.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@hotmail.com");
    }
}
