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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.Member;

public class MakePayment extends AppCompatActivity {
    private Member MEMBERS[];
    private ArrayList<Member> UNPAID_MEMBERS;
    class  CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return UNPAID_MEMBERS.size();
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
            view=getLayoutInflater().inflate((R.layout.fragment_make_payment),null );
            TextView nameView=view.findViewById(R.id.name_view);
            TextView date=view.findViewById(R.id.payment_date);
            date.setText(DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime()));
            final AutoCompleteTextView roomPay=view.findViewById(R.id.room_cost);
            final AutoCompleteTextView mealPay=view.findViewById(R.id.meal_cost);
            final Button paymentButton=view.findViewById(R.id.payment_now);
            nameView.setText(UNPAID_MEMBERS.get(i).getName());

            paymentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String rPay=roomPay.getText().toString().trim();
                    String mPay=mealPay.getText().toString().trim();
                    boolean err=false;
                    if(rPay.isEmpty()){
                        roomPay.setError(getString(R.string.error_field_required));
                        err=true;
                    }
                    if(mPay.isEmpty()){
                        mealPay.setError(getString(R.string.error_field_required));
                        err=true;
                    }
                    if(!err) {
                        int total = Integer.parseInt(rPay) + Integer.parseInt(mPay);
                        if (doPayment(MainActivity.MANAGER.getId(), UNPAID_MEMBERS.get(i).getId(), Integer.parseInt(rPay), Integer.parseInt(mPay), total)) {
                            toast("Payment Done");
                            paymentButton.setEnabled(false);
                            paymentButton.setText("PAID DONE");
                        } else {
                            toast("Try again");
                        }
                    }
                }
            });

            return view;
        }
    }
    public boolean doPayment(int u_id,int m_id,int rPay,int mPay,int total){
        return (new DatabaseHelper(this).payment(u_id,m_id,rPay,mPay,total));
    }
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.make_payment);
        setSupportActionBar(toolbar);

        // find all unpaid members
        UNPAID_MEMBERS=(new DatabaseHelper(this).getUnpaidMembers());
        // set custom view adapter
        ListView listView=findViewById(R.id.make_payment_list);
        if(UNPAID_MEMBERS.size()==0){
            listView.requestLayout();
            listView.setBackgroundResource(R.drawable.paid_bill);
        }
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
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
