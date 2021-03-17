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

import java.util.ArrayList;

import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.PaidMember;

public class ShowPayment extends AppCompatActivity {
    private ListView listView;
    private ArrayList<PaidMember> PAID_MEMBERS;

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return PAID_MEMBERS.size();
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
            view=getLayoutInflater().inflate(R.layout.fragment_show_payment,null);
            TextView nameView=view.findViewById(R.id.name_view);
            TextView dateView=view.findViewById(R.id.payment_date);
            TextView roomCost=view.findViewById(R.id.room_cost);
            TextView mealCost=view.findViewById(R.id.meal_cost);
            TextView totalCost=view.findViewById(R.id.total_cost);

            nameView.setText(PAID_MEMBERS.get(i).getName());
            dateView.setText(PAID_MEMBERS.get(i).getDate());
            roomCost.setText(String.valueOf(PAID_MEMBERS.get(i).getRoomPay()));
            mealCost.setText(String.valueOf(PAID_MEMBERS.get(i).getMealPay()));
            totalCost.setText(String.valueOf(PAID_MEMBERS.get(i).getTotalPaid()));

            return view;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_payment);
        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.show_payment);
        setSupportActionBar(toolbar);
        // find paid members
        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        PAID_MEMBERS=databaseHelper.getPaidMembers();
        listView=findViewById(R.id.show_payment_list);
        if(PAID_MEMBERS.size()==0){
            listView.requestLayout();
            listView.setBackgroundResource(R.drawable.due_bill);
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
}
