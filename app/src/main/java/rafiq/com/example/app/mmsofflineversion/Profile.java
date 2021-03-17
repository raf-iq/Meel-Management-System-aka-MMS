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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import rafiq.com.example.app.mmsofflineversion.DialogHelper.ProfileUpdateOption;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.User;

public class Profile extends AppCompatActivity {
    private User profileOwner;

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 1;
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
            view=getLayoutInflater().inflate(R.layout.fragment_profile,null);
            TextView profile_detail=view.findViewById(R.id.profile);
            final StringBuffer detail=new StringBuffer();
            detail.append("Name : " + profileOwner.getName()+"\n");
            detail.append("User Name: " + profileOwner.getUser_name()+"\n");
            detail.append("Email: " + profileOwner.getUser_mail()+"\n");
            detail.append("Mobile:" + profileOwner.getUser_mobile()+"\n\n");
            profile_detail.setText(detail);

            Button editProfile=view.findViewById(R.id.edit_profile);
            editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfileUpdateOption dialog=new ProfileUpdateOption();
                    dialog.show(getSupportFragmentManager().beginTransaction(),"update");
                }
            });
            return view;
        }
    }
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /* code for toolbar */
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_profile);
        setSupportActionBar(toolbar);
        profileOwner=MainActivity.MANAGER;
        /* code for activative fragment */
        ListView listView=findViewById(R.id.profile);
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
            case R.id.monthly_bazar_detail:
                startActivity(new Intent(this,MonthlyBazarDetail.class));
                return true;
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
}
