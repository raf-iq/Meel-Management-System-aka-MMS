package rafiq.com.example.app.mmsofflineversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;

public class MonthlyMealDetail extends AppCompatActivity {

    private ArrayList<StringBuffer> MEMBER_MEALS;
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return MEMBER_MEALS.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.fragment_monthly_meal_detail,null);
            TextView nameView=view.findViewById(R.id.member_name);
            TextView detail=view.findViewById(R.id.member_meal_detail);
            String str[]=MEMBER_MEALS.get(i).toString().split("@");
            nameView.setText(str[0]);
            detail.setText(str[1]);
            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_meal_detail);
        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.monthly_meal_detail);
        setSupportActionBar(toolbar);

        String currentDate = (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime()));
        TextView today=findViewById(R.id.present_date);
        MEMBER_MEALS=(new DatabaseHelper(this).getMemberMeal());
        ListView listView = findViewById(R.id.monthly_meal_list);
        if(MEMBER_MEALS.size()==0){
            listView.requestLayout();
            listView.setBackgroundResource(R.drawable.no_meal);
            today.requestLayout();
            today.setHeight(0);
            today.setTextSize(0);
            ViewGroup.MarginLayoutParams layoutParams= (ViewGroup.MarginLayoutParams) today.getLayoutParams();
            layoutParams.setMargins(0,0,0,0);
            today.setLayoutParams(layoutParams);
        }
        else {
            today.setText(currentDate);
            CustomAdapter customAdapter = new CustomAdapter();
            listView.setAdapter(customAdapter);
        }

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
                startActivity(new Intent(this,MonthlyMealDetail.class));
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
                break;

        }
    }
}
