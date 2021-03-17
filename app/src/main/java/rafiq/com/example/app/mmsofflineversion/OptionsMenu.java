package rafiq.com.example.app.mmsofflineversion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class OptionsMenu extends AppCompatActivity {
    private  String options[];

    class CustomAdaper extends BaseAdapter{

        @Override
        public int getCount() {
            return options.length;
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
            view=getLayoutInflater().inflate(R.layout.fragment_option_menu,null);
            TextView click=view.findViewById(R.id.option_click);
            click.setText(options[i]);
            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activate(i);
                }
            });
            return view;
        }
    }
    public void activate(int i){
        switch (i){
            case 0:
                startActivity(new Intent(this,Members.class));
                break;
            case 1:
                activateDialog(getResources().getStringArray(R.array.bazar_option),"BAZAR");
                break;
            case 2:
                activateDialog(getResources().getStringArray(R.array.meal_option),"MEAL");
                break;
            case 3:
                activateDialog(getResources().getStringArray(R.array.payment_option),"PAYMENT");
                break;
            default:
                // do in future
        }
    }
    public void activateDialog(String resource[], final String type){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_your_option);
        builder.setItems(resource, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(type.equals("BAZAR")){
                    startBazarActivity(i);
                }
                else if(type.equals("MEAL")){
                    startMealActivity(i);
                }
                else if(type.equals("PAYMENT")){
                    startPaymentActivity(i);
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void startBazarActivity(int i){
        switch (i){
            case 0:
                startActivity(new Intent(this,BazarOfTheDay.class));
                break;
            case 1:
                startActivity(new Intent(this,MonthlyBazarDetail.class));
                break;
            default:
                // do in future
        }
    }
    public void startMealActivity(int i){
        switch (i){
            case 0:
                startActivity(new Intent(this,SetMeal.class));
                break;
            case 1:
                startActivity(new Intent(this,PersonMealSet.class));
                break;
            case 2:
                startActivity(new Intent(this,MonthlyMealDetail.class));
                break;
            default:
                // do in future
        }
    }
    public void startPaymentActivity(int i){
        switch (i){
            case 0:
                startActivity(new Intent(this,MakePayment.class));
                break;
            case 1:
                startActivity(new Intent(this,ShowPayment.class));
                break;
            default:
                // do in future
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_option_menu);
        setSupportActionBar(toolbar);

        options=getResources().getStringArray(R.array.options);
        ListView listView=findViewById(R.id.options);
        CustomAdaper adapter=new CustomAdaper();
        listView.setAdapter(adapter);

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
