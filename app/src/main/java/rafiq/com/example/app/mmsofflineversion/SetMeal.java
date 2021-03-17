package rafiq.com.example.app.mmsofflineversion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.Meal;


public class SetMeal extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Button updateMeal,dateDate;
    private View progressView;
    private AutoCompleteTextView breakfast,lunch,dinner;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal);
        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.set_meal_of_the_day);
        setSupportActionBar(toolbar);

        view=findViewById(R.id.meal_form);
        breakfast=findViewById(R.id.morning_meal);
        lunch=findViewById(R.id.day_meal);
        dinner=findViewById(R.id.night_meal);
        progressView=findViewById(R.id.progress);
        updateMeal=findViewById(R.id.update_meal_button);
        dateDate=findViewById(R.id.dateButton);
        updateMeal.setOnClickListener(this);
        dateDate.setOnClickListener(this);

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
        menu.setHeaderTitle("Choice your option");
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
            case R.id.monthly_bazar_detail:
                startActivity(new Intent(this,MonthlyBazarDetail.class));
                return true;
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
            case R.id.dateButton:
                /* code for activate date picker*/
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;
            case R.id.update_meal_button:
                /* code for update meal of the day */
                DatabaseHelper db=new DatabaseHelper(this);
                String date=dateDate.getText().toString();
                String breakFast=breakfast.getText().toString();
                String _lunch_=lunch.getText().toString();
                String _dinner_=dinner.getText().toString();
                Meal meal=new Meal(MainActivity.MANAGER.getId(),date,breakFast,_lunch_,_dinner_);
                showProgress(true);
                if(db.setMeal(meal)==-1){
                    showProgress(false);
                    Toast.makeText(this,"Something wrong.Try Again",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Meal Added",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }
                //Toast.makeText(this,"Updatting meal",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH,i1 );
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(c.getTime());
        Button dateBtn =  findViewById(R.id.dateButton);
        dateBtn.setText(currentDateString);
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
}
