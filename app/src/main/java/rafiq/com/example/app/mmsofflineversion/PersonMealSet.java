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
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.Member;

public class PersonMealSet extends AppCompatActivity  {

    private ListView listView;
    private ArrayList<Member> MEMBERS;
    private String currentDate;

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return MEMBERS.size();
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.fragment_person_meal,null);
            TextView name=view.findViewById(R.id.name);
            final CheckBox breakfast=view.findViewById(R.id.breakfast);
            final CheckBox lunch=view.findViewById(R.id.lunch);
            final CheckBox dinner=view.findViewById(R.id.dinner);
            final AutoCompleteTextView extraMeal=view.findViewById(R.id.extra_meal);
            final Button button=view.findViewById(R.id.add_meal_to_member);

            name.setText((i+1) + ". " + MEMBERS.get(i).getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int b=0,l=0,d=0;
                    String bf="NO";
                    String ln="NO";
                    String dn="NO";
                    if(breakfast.isChecked()){
                        b=1;
                        bf="YES";
                    }
                    if(lunch.isChecked()){
                        l=1;
                        ln="YES";
                    }
                    if(dinner.isChecked()){
                        d=1;
                        dn="YES";
                    }
                    int extra=0;
                    if(!extraMeal.getText().toString().isEmpty()){
                        extra=Integer.parseInt(extraMeal.getText().toString());
                    }
                    int total=b+l+d+extra;
                    if(update(MEMBERS.get(i),bf,ln,dn,extra,total)){
                        toast("UPDATED MEAL");
                        button.setEnabled(false);
                        button.setText("MEAL UPDATED");
                    }
                    else {
                        toast("Try Again");
                    }
                }
            });
            return view;
        }
    }
    public boolean update(Member member,String bf,String ln, String dn, int e,int total){
        DatabaseHelper db=new DatabaseHelper(this);
        return db.update_member_meal(member,bf,ln,dn,e,total);
    }
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_meal_set);
        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.person_meal_set);
        setSupportActionBar(toolbar);
        // work with list view
        /* get calender time */
        currentDate= DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime());

        MEMBERS =(new DatabaseHelper(this)).getAllMember();
        listView = findViewById(R.id.person_meal_list);
        if(MEMBERS.size()==0){
            listView.requestLayout();
            listView.setBackgroundResource(R.drawable.no_member);
        }
        else {
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

}
