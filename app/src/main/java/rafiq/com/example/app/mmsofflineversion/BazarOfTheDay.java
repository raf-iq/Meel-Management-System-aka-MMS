package rafiq.com.example.app.mmsofflineversion;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import rafiq.com.example.app.mmsofflineversion.DialogHelper.ShowDialog;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;

public class BazarOfTheDay extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Button updateBazar,dateDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazar_of_the_day);
        /* code for toolbar activity */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.bazar_of_the_day);
        setSupportActionBar(toolbar);

        updateBazar=findViewById(R.id.update_bazar);
        updateBazar.setOnClickListener(this);
        dateDate=findViewById(R.id.dateBazar);
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
                registerForContextMenu(view);
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
            case R.id.dateBazar:
                /* code for activate date picker*/
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;
            case R.id.update_bazar:
                /* code for updating bazar*/
                Button date=findViewById(R.id.dateBazar);
                AutoCompleteTextView bazarMan=findViewById(R.id.bazar_man);
                AutoCompleteTextView item[]=new AutoCompleteTextView[12];
                AutoCompleteTextView price[]=new AutoCompleteTextView[12];
                int i=1;
                item[i]=findViewById(R.id.item_1);
                price[i]=findViewById(R.id.item_1_price);
                boolean err=false;
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_2);
                price[i]=findViewById(R.id.item_2_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_3);
                price[i]=findViewById(R.id.item_3_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_4);
                price[i]=findViewById(R.id.item_4_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_5);
                price[i]=findViewById(R.id.item_5_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_6);
                price[i]=findViewById(R.id.item_6_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_7);
                price[i]=findViewById(R.id.item_7_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_8);
                price[i]=findViewById(R.id.item_8_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_9);
                price[i]=findViewById(R.id.item_9_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                item[i]=findViewById(R.id.item_10);
                price[i]=findViewById(R.id.item_10_price);
                if(!item[i].getText().toString().isEmpty() && price[i].getText().toString().isEmpty()){
                    price[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                else if(item[i].getText().toString().isEmpty() && !price[i].getText().toString().isEmpty()) {
                    item[i].setError(getString(R.string.error_field_required));
                    err=true;
                }
                i++;
                if(!err){
                    int totalCost=0;
                    for( i=1; i<=10; ++i){
                        if(!price[i].getText().toString().trim().isEmpty()){
                            totalCost+=(Integer.parseInt(price[i].getText().toString().trim()));
                        }
                    }

                    boolean check=false;
                    if(date.getText().toString().isEmpty()){
                        date.setError(getString(R.string.error_field_required));
                        check=true;
                    }
                    if(bazarMan.getText().toString().isEmpty()){
                        bazarMan.setError(getString(R.string.error_field_required));
                        check=true;
                    }
                    if(!check){
                        boolean ck= new DatabaseHelper(this).addBazarList(date,bazarMan,item,price,totalCost);
                        if(ck){
                            new ShowDialog(this,"Bazar List Added");
                           // toast("Bazar List Added");
                            startActivity(new Intent(this,MainActivity.class));
                        }
                        else {
                            toast("Something Wrong,Try Again");
                        }
                    }
                }
                //Toast.makeText(this,"Updating Bazar List",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH,i1 );
        c.set(Calendar.DAY_OF_MONTH, i2);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(c.getTime());

        Button dateBtn =  findViewById(R.id.dateBazar);
        dateBtn.setText(currentDateString);
    }
}
