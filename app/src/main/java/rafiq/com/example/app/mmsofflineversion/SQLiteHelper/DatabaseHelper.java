package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import rafiq.com.example.app.mmsofflineversion.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static Map<String,Integer> MONTH;

    private static final String DATABASE_NAME="MMS_Db.db";
    private static final int VERSION=1;
    private static final String ID="id";
    private static final String DATE="date";
    private static final String DATE_INTEGER="date_millis";
    // user table and attribute
    private static final String USER="user_table";
    private static final String USER_ID="user_id";
    private static final String NAME="name";
    private static final String USER_NAME="user_name";
    private static final String USER_PASSWORD="user_password";
    private static final String USER_MAIL="user_mail";
    private static final String USER_MOBILE="user_mobile";

    // user table format string
    private static final String CREATE_USER_TABLE= "CREATE TABLE " + USER +
                    "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    NAME + " TEXT NOT NULL," + USER_NAME + " TEXT NOT NULL UNIQUE," +
                    USER_PASSWORD + " TEXT NOT NULL," + USER_MAIL +" TEXT NOT NULL UNIQUE, " +
                    USER_MOBILE + " TEXT NOT NULL UNIQUE );";
    // meal set table attribute
    private static final String MEAL_TABLE="meal_table";
    private static final String MEAL_ID="meal_id";
    private static final String MEAL_DATE="date";
    private static final String MORNING_MEAL_NAME="breakfast";
    private static final String DAY_MEAL_NAME="lunch";
    private static final String NIGHT_MEAL_NAME="dinner";
    // meal table construction string
    private  static final String CREATE_MEAL_TABLE="CREATE TABLE "+MEAL_TABLE+
            "("+ MEAL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            USER_ID +" INTEGER NOT NULL,"+
            MEAL_DATE + " TEXT NOT NULL,"+MORNING_MEAL_NAME + " TEXT,"+
            DAY_MEAL_NAME + " TEXT,"+NIGHT_MEAL_NAME+" TEXT ," + DATE_INTEGER + " INTEGER,"+
            " FOREIGN KEY (" + USER_ID + ") REFERENCES " + USER + "(" + USER_ID + ") );";

    // Memeber table attribute
    private static final String MEMBER_TABLE="member";
    private static final String MEMBER_ID="member_id";
    private static final String MEMBER_NAME="member_name";
    private static final String MEMBER_EMAIL="member_email";
    private static final String MEMBER_MOBILE="member_mobile";
    private static final String MEMBER_VILLAGE="member_village";
    private static final String MEMBER_THANA="member_thana";
    private static final String MEMBER_DISTRICT="member_district";
    private static final String MEMBER_ROOM_COST="room_cost";
    // Member table construction string
    private static final String CREATE_MEMBER_TABLE="CREATE TABLE " + MEMBER_TABLE +
            "( " + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            MEMBER_NAME + " TEXT NOT NULL," + MEMBER_EMAIL + " TEXT NOT NULL UNIQUE,"
            + MEMBER_MOBILE + " TEXT NOT NULL UNIQUE," + MEMBER_VILLAGE + " TEXT,"+
            MEMBER_THANA + " TEXT,"+ MEMBER_DISTRICT + " TEXT," + MEMBER_ROOM_COST + " INTEGER," +
            DATE + " TEXT, " + DATE_INTEGER + " INTEGER " + ");";

    // Member meal set table attribute
    private static final String MEMBER_MEAL_TABLE="member_meal";
    private static final String MEMBER_BREAKFAST="breakfast";
    private static final String MEMBER_LUNCH="lunch";
    private static final String MEMBER_DINNER="dinner";
    private static final String MEMBER_EXTRA_MEAL="extra";
    private static final String MEMBER_TOTAL_MEAL="total_meal";
    // Member meal set table construction string
    private static final String CREATE_MEMBER_MEAL_TABLE="CREATE TABLE " + MEMBER_MEAL_TABLE +
            "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            MEAL_DATE + " TEXT ," + MEMBER_ID + " INTEGER ,"+ MEMBER_NAME +
            " TEXT,"+ MEMBER_BREAKFAST + " TEXT," + MEMBER_LUNCH + " TEXT," +
            MEMBER_DINNER + " TEXT," + MEMBER_EXTRA_MEAL + " INTEGER," +
            MEMBER_TOTAL_MEAL + " INTEGER ," + DATE_INTEGER + " INTEGER ," +
            "FOREIGN KEY (" + MEMBER_ID + ") REFERENCES " + MEMBER_TABLE + "("+ MEMBER_ID + ") );";

    // Bazaa table attribute
    private static final String BAZAR_TABLE="bazar_table";
    private static final String BAZAR_DATE="date";
    private static final String BAZAR_MAKER="bazar_maker";
    private static final String ITEM_1="item_1";
    private static final String ITEM_2="item_2";
    private static final String ITEM_3="item_3";
    private static final String ITEM_4="item_4";
    private static final String ITEM_5="item_5";
    private static final String ITEM_6="item_6";
    private static final String ITEM_7="item_7";
    private static final String ITEM_8="item_8";
    private static final String ITEM_9="item_9";
    private static final String ITEM_10="item_10";
    private static final String PRICE_1="price_1";
    private static final String PRICE_2="price_2";
    private static final String PRICE_3="price_3";
    private static final String PRICE_4="price_4";
    private static final String PRICE_5="price_5";
    private static final String PRICE_6="price_6";
    private static final String PRICE_7="price_7";
    private static final String PRICE_8="price_8";
    private static final String PRICE_9="price_9";
    private static final String PRICE_10="price_10";
    private static final String TOTAL_BAZAR_COST="bazar_cost";
    // bazar table construction string
    private static final String CREATE_BAZAR_LIST="CREATE TABLE " + BAZAR_TABLE + "( "+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+ BAZAR_DATE + " TEXT,"+
            BAZAR_MAKER + " TEXT,"+
            ITEM_1 + " TEXT," + PRICE_1 + " INTEGER ,"+
            ITEM_2 + " TEXT," + PRICE_2 + " INTEGER ,"+
            ITEM_3 + " TEXT," + PRICE_3 + " INTEGER ,"+
            ITEM_4 + " TEXT," + PRICE_4 + " INTEGER ,"+
            ITEM_5 + " TEXT," + PRICE_5 + " INTEGER ,"+
            ITEM_6 + " TEXT," + PRICE_6 + " INTEGER ,"+
            ITEM_7 + " TEXT," + PRICE_7 + " INTEGER ,"+
            ITEM_8 + " TEXT," + PRICE_8 + " INTEGER ,"+
            ITEM_9 + " TEXT," + PRICE_9 + " INTEGER ,"+
            ITEM_10 + " TEXT," + PRICE_10 + " INTEGER,"+
            TOTAL_BAZAR_COST + " INTEGER ," + DATE_INTEGER + " INTEGER );";
    // payemnt table construction attribute
    private static final String PAYMENT_TABLE="payment";
    private static final String ROOM_PAYMENT="room_paymet";
    private static final String MEAL_PAYMENT="meal_paymet";
    private static final String TOTAL_PAYMENT="total_payment";
    // payment table construction string
    private static final String CREATE_PAYMENT_TABLE="CREATE TABLE " + PAYMENT_TABLE +
            "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            USER_ID + " INTEGER,"+
            MEMBER_ID + " INTEGER," + DATE + " TEXT," + ROOM_PAYMENT + " INTEGER,"+
            MEAL_PAYMENT + " INTEGER," + TOTAL_PAYMENT + " INTEGER,"+
            " FOREIGN KEY (" + MEMBER_ID + ") REFERENCES " + MEMBER_TABLE + "("+ MEMBER_ID + ")," +
            " FOREIGN KEY (" + USER_ID + ") REFERENCES " + USER + "("+USER_ID+") );";

    // User balance table construction attribute
    private static final String BALANCE_TABLE="balance_table";
    private static final String BALANCE="balance";
    // creating balance table string
    private static final String CREATE_BALANCE_TABLE="CREATE TABLE "+ BALANCE_TABLE +
            "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            USER_ID + " INTEGER," +BALANCE + " INTEGER,"+
            "FOREIGN KEY (" + USER_ID + " ) REFERENCES " + USER + "(" + USER_ID + ") );";
    /* member due table construction */
    private static final String MEMBER_PAID_TABLE="member_paid_table";
    private static final String CREATE_MEMBER_PAID_TABLE="CREATE TABLE " + MEMBER_PAID_TABLE +
           "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + MEMBER_ID + " INTEGER," +
            BALANCE + " INTEGER, FOREIGN KEY (" + MEMBER_ID + ") REFERENCES " + MEMBER_TABLE + "("+ MEMBER_ID + ") );";
    //private  SQLiteDatabase db;
    public static void populateMonth(){
        MONTH=new HashMap<>();
        MONTH.put("JANUARY",1);
        MONTH.put("FEBRUARY",2);
        MONTH.put("MARCH",3);
        MONTH.put("APRIL",4);
        MONTH.put("MAY",5);
        MONTH.put("JUNE",6);
        MONTH.put("JULY",7);
        MONTH.put("AUGUST",8);
        MONTH.put("SEPTEMBER",9);
        MONTH.put("OCTOBER",10);
        MONTH.put("NOVEMBER",11);
        MONTH.put("DECEMBER",12);
    }
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        populateMonth();
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_MEAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_BAZAR_LIST);
        sqLiteDatabase.execSQL(CREATE_PAYMENT_TABLE);
        sqLiteDatabase.execSQL(CREATE_BALANCE_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_PAID_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MEMBER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MEMBER_MEAL_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MEAL_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BAZAR_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PAYMENT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BALANCE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MEMBER_PAID_TABLE);

        onCreate(sqLiteDatabase);
    }
    /* ..... START ....Insertion method in Database ...........*/
    // add user ...
    public User addUser(User user){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,user.getName());
        values.put(USER_NAME,user.getUser_name());
        values.put(USER_PASSWORD,user.getUser_password());
        values.put(USER_MAIL,user.getUser_mail());
        values.put(USER_MOBILE,user.getUser_mobile());
        Long id=sqLiteDatabase.insert(USER,null,values);
        if(id == -1){
            return null;
        }
        else {
            SQLiteDatabase db=this.getReadableDatabase();
            String sql="SELECT * FROM " + USER + " WHERE " + USER_MAIL + " = ?";
            Cursor res=db.rawQuery(sql,new String[]{user.getUser_mail()});
            if(res.getCount()>0) {
                res.moveToNext();
                User user1= new User(Integer.parseInt(res.getString(0)), res.getString(1),
                        res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5));
                res.close();
                return user1;
            }
            else {
                return  null;
            }
        }
    }

    // entry set meal in database
    public Long setMeal(Meal meal){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        Calendar calendar=Calendar.getInstance();
        values.put(DATE_INTEGER,calendar.getTimeInMillis());
        values.put(MEAL_DATE,meal.getDate());
        values.put(MORNING_MEAL_NAME,meal.getBreakfast());
        values.put(DAY_MEAL_NAME,meal.getLunch());
        values.put(NIGHT_MEAL_NAME,meal.getDinner());
        values.put(USER_ID,meal.getId());
        Long id= sqLiteDatabase.insert(MEAL_TABLE,null,values);
        return id;
    }
    // method for adding member of the mess
    public Long addMember(Member member){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        Calendar calendar=Calendar.getInstance();
        values.put(DATE_INTEGER,member.getTimeMillis());
        values.put(MEMBER_NAME,member.getName());
        values.put(MEMBER_EMAIL,member.getEmail());
        values.put(MEMBER_MOBILE,member.getMobile());
        values.put(MEMBER_VILLAGE,member.getVillage());
        values.put(MEMBER_THANA,member.getThana());
        values.put(MEMBER_DISTRICT,member.getDistrict());
        values.put(MEMBER_ROOM_COST,member.getRoom_cost());
        values.put(DATE,member.getDate());
        Long id=sqLiteDatabase.insert(MEMBER_TABLE,null,values);
        return id;
    }
    public boolean deleteMember(Member member){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String whereClause=MEMBER_ID + " = ? ";
        String whereArgs[]=new String[]{String.valueOf(member.getId())};
        return (sqLiteDatabase.delete(MEMBER_TABLE,whereClause,whereArgs)!=0);
    }

    public boolean addBazarList(Button date, AutoCompleteTextView bazarMan,AutoCompleteTextView item[],AutoCompleteTextView price[],int totalCost){
        while (!updateBazarList(date,bazarMan,item,price,totalCost));
        while (!balanceUpdate(MainActivity.MANAGER.getId(),(-1)*totalCost));
        return true;
    }
    // payment insert
    public boolean payment(int u_id,int m_id,int rPay,int mPay,int total){
        while (!paymentDone(u_id,m_id,rPay,mPay,total));
        while (!balanceUpdate(u_id,total));
        //insertDue(u_id,total,"payment");
        return true;
    }
    /* ..... END ....Insertion method in Database ...........*/

    /* ..... START .... Retrieved method in Database ...........*/

    public ArrayList<Member> getAllMember(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM " + MEMBER_TABLE + " ORDER BY " + MEMBER_NAME + " ASC;",null);

        ArrayList<Member> members=new ArrayList<>();
        while(res.moveToNext()){
            members.add(new Member(Integer.parseInt(res.getString(0)),res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),res.getString(5),
                    res.getString(6),Integer.parseInt(res.getString(7)),res.getString(8),
                    Long.parseLong(res.getString(9))));
        }
        res.close();
        return members;
    }
    public ArrayList<StringBuffer> getMemberMeal(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<StringBuffer> list=new ArrayList<>();
        String currentDate[]= (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime())).split(" ");
        String totalMeal="SELECT DISTINCT COUNT("+MEAL_ID+") FROM " + MEAL_TABLE + " WHERE " + DATE +
                " LIKE '%"+ currentDate[1] + "%' AND " + DATE + " LIKE '%" + currentDate[3] + "%' ;";
        Cursor res=sqLiteDatabase.rawQuery(totalMeal,null);
        res.moveToNext();
        int total_meal_of_the_month=Integer.parseInt(res.getString(0));
        res.close();
        if(total_meal_of_the_month==0){
            return list;
        }
        total_meal_of_the_month*=3;
        String bazarCost="SELECT TOTAL(" + TOTAL_BAZAR_COST + ") FROM " + BAZAR_TABLE + " WHERE " + BAZAR_DATE +
                " LIKE '%" + currentDate[1] + "%' AND " + BAZAR_DATE + " LIKE '%" + currentDate[3]+ "%' ;";
        Cursor bc=sqLiteDatabase.rawQuery(bazarCost,null);
        bc.moveToNext();
        int bazar_cost=Integer.parseInt(bc.getString(0));
        bc.close();
        String query="SELECT * FROM " + MEMBER_TABLE + " ORDER BY " + MEMBER_NAME + " ASC ;";
        Cursor members=sqLiteDatabase.rawQuery(query,null);
        while (members.moveToNext()){
            StringBuffer stringBuffer=new StringBuffer();
            stringBuffer.append("Name: " + members.getString(1) + "@");
            String memberMeal="SELECT " + MEMBER_TOTAL_MEAL + " FROM " + MEMBER_MEAL_TABLE +
                    " WHERE " + MEMBER_ID + " = " + members.getString(0)+ " AND " + DATE + " LIKE '%" +
                    currentDate[1] + "%' AND " + DATE + " LIKE '%"+
                    currentDate[3] + "%' ;";
            Cursor member_meal=sqLiteDatabase.rawQuery(memberMeal,null);
            member_meal.moveToNext();
            int member_total_meal_of_the_month=0;
            if(member_meal.getCount()>0) {
                member_total_meal_of_the_month=Integer.parseInt(member_meal.getString(0));
            }
            String formatedString=String.format("%-27s","Total Meals");
            String formatedInteger=String.format("%10s",String.valueOf(total_meal_of_the_month));
            stringBuffer.append(formatedString + "=" +formatedInteger + "\n");
            formatedString=String.format("%-26s","Taken Meals");
            formatedInteger=String.format("%10s",String.valueOf(member_total_meal_of_the_month));
            stringBuffer.append(formatedString +"="+ formatedInteger + "\n");
            formatedString=String.format("%-26s","Per meal cost");
            formatedInteger=String.format("%10s",String.valueOf(bazar_cost/total_meal_of_the_month));
            stringBuffer.append(formatedString +"="+ formatedInteger + "\n");
            formatedString=String.format("%-24s","Total meals cost");
            formatedInteger=String.format("%10s",String.valueOf((bazar_cost*member_total_meal_of_the_month)/total_meal_of_the_month));
            stringBuffer.append("-------------------------------------------------\n");
            stringBuffer.append(formatedString + "=" +formatedInteger + "\n\n");
            list.add(stringBuffer);
            member_meal.close();
        }
        members.close();
        return list;
    }
    public ArrayList<BazarList> getBazarList(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ArrayList<BazarList> list =new ArrayList<>();
        String currentDate[]= (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime())).split(" ");
        String sql="SELECT * FROM " + BAZAR_TABLE + " WHERE " + BAZAR_DATE + " LIKE '%" + currentDate[1] +
                 "%' AND " + BAZAR_DATE + " LIKE '%" + currentDate[3] + "%' ORDER BY " + BAZAR_DATE + " ASC ;";
        Cursor res=sqLiteDatabase.rawQuery(sql,null);
        if(res.getCount()==0){
            return list;
        }
        while(res.moveToNext()){
            StringBuffer str=new StringBuffer();
            int columnsCounter=1;
            str.append(res.getString(columnsCounter++) + "\n");
            str.append("Bazar By: " + res.getString(columnsCounter++));
            int colums=res.getColumnCount()-1;
            int totalCost=0;
            Map<String,String> detail=new HashMap<>();
            for(int i=columnsCounter; i<colums; i+=2){
                if(!res.getString(i).isEmpty()) {
                    detail.put(res.getString(i),"= " + res.getString(i+1)+ " Tk");
                    totalCost+=Integer.parseInt(res.getString(i+1));
                }
            }
            //detail.put("Total","= " + String.valueOf(totalCost) + " Tk");
            String dotted= new String("---------------------------------------------------");
            list.add(new BazarList(str,detail,dotted,totalCost));
        }
        res.close();
        return list;
    }

    /* ..... END .... Retrieved method in Database ...........*/

    /* ...... START... checking method ...... Database ...*/
    // method for checking valite user
    public User isRegisteredUser(String email,String pass){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String dbName=this.getDatabaseName();
        if(dbName.isEmpty()){
            return new User(-1,"","","","","");
        }
        String query="SELECT * FROM " + USER + " WHERE " + USER_MAIL + " = ? AND " + USER_PASSWORD + " = ?";
        String args[]={email,pass};
        Cursor res=sqLiteDatabase.rawQuery(query,args);
        if(res.getCount()>0) {
            res.moveToNext();
            User user= new User(Integer.parseInt(res.getString(0)),
                    res.getString(1), res.getString(2), res.getString(3)
                    , res.getString(4), res.getString(5));
            res.close();
            return user;
        }
        else {
            return null;
        }
    }
    public boolean update_member_meal(Member member,String bf,String ln, String dn, int extra,int total){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime());
        Cursor res=sqLiteDatabase.rawQuery("SELECT "+MEMBER_ID+" FROM " + MEMBER_MEAL_TABLE +
                " WHERE " + MEAL_DATE + " =? AND "+ MEMBER_ID + " =?;",
                new String[]{currentDate, (String.valueOf(member.getId()))});
        if(res.getCount()>0){
           ContentValues values=new ContentValues();
           values.put(MEMBER_BREAKFAST,bf);
           values.put(MEMBER_LUNCH,ln);
           values.put(MEMBER_DINNER,dn);
           values.put(MEMBER_EXTRA_MEAL,extra);
           values.put(MEMBER_TOTAL_MEAL,total);
           String whereClause=MEMBER_ID + " = ?";
           String whereArgs[]={String.valueOf(member.getId())};
           int v=sqLiteDatabase.update(MEMBER_MEAL_TABLE,values,whereClause,whereArgs);
           return v!=0;
        }
        else {
            ContentValues values=new ContentValues();
            Calendar calendar=Calendar.getInstance();
            values.put(DATE_INTEGER,calendar.getTimeInMillis());
            values.put(MEAL_DATE,currentDate);
            values.put(MEMBER_ID,member.getId());
            values.put(MEMBER_NAME,member.getName());
            values.put(MEMBER_BREAKFAST,bf);
            values.put(MEMBER_LUNCH,ln);
            values.put(MEMBER_DINNER,dn);
            values.put(MEMBER_EXTRA_MEAL,extra);
            values.put(MEMBER_TOTAL_MEAL,total);
            Long id=sqLiteDatabase.insert(MEMBER_MEAL_TABLE,null,values);
            return id!=-1;
        }
    }
    public ArrayList<PaidMember> getPaidMembers(){
        ArrayList<PaidMember>members=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String currentDate[] = (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime())).split(" ");
        String query="SELECT * FROM " + PAYMENT_TABLE + " LEFT JOIN " + MEMBER_TABLE + " ON " +
                MEMBER_TABLE + "." + MEMBER_ID + " = " +PAYMENT_TABLE+ "." + MEMBER_ID +
                " WHERE " + PAYMENT_TABLE+ "."+ DATE + " LIKE '%"+currentDate[1] + "%' AND " +
                PAYMENT_TABLE + "." + DATE + " LIKE '%"+
                currentDate[3]+"%' ;";
        Cursor res=sqLiteDatabase.rawQuery(query,null);
        if(res.getCount()==0){
            return members;
        }
        while(res.moveToNext()){
            members.add(new PaidMember(Integer.parseInt(res.getString(2)),res.getString(8),
                    res.getString(3), Integer.parseInt(res.getString(4)),
                    Integer.parseInt(res.getString(5)),0,
                    Integer.parseInt(res.getString(6))));
        }
        res.close();
        return members;
    }

    public ArrayList<Member> getUnpaidMembers(){
        ArrayList<Member>members=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String currentDate[]= (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime())).split(" ");
        String sqlPayment="SELECT DISTINCT COUNT(" + MEMBER_ID + ") FROM " + PAYMENT_TABLE +
                " WHERE " + DATE + " LIKE '%" + currentDate[1] + "%' AND " + DATE + " LIKE '%" +
                currentDate[3] + "%' ;";
        Cursor p=sqLiteDatabase.rawQuery(sqlPayment,null);
        p.moveToNext();
        if(Integer.parseInt(p.getString(0))==0){
            p.close();
            members=getAllMember();
            return members;
        }
        else {
            String query="SELECT * FROM " + PAYMENT_TABLE + " LEFT JOIN " + MEMBER_TABLE + " ON " +
                    MEMBER_TABLE + "." + MEMBER_ID + " = " +PAYMENT_TABLE+ "." + MEMBER_ID +
                    " WHERE " + PAYMENT_TABLE+ "."+ DATE + " LIKE '%"+currentDate[1] + "%' AND " +
                    PAYMENT_TABLE + "." + DATE + " LIKE '%"+
                    currentDate[3]+"%' ;";
            Cursor res = sqLiteDatabase.rawQuery(query, null);
            Cursor m=sqLiteDatabase.rawQuery("SELECT * FROM " + MEMBER_TABLE,null);
            if(res.getCount()==m.getCount()){
                m.close();
                return members;
            }
            else {
                while (res.moveToNext()) {
                    members.add(new Member(Integer.parseInt(res.getString(7)), res.getString(8),
                            res.getString(9), res.getString(10), res.getString(11),
                            res.getString(12), res.getString(13), Integer.parseInt(res.getString(14)),
                            res.getString(15),Long.parseLong(res.getString(16))));
                }
                res.close();
                return members;
            }
        }
    }
    /* ...... END ..... checking method in database .......*/

    /* all helper method here ... start ... */

    public boolean paymentDone(int u_id,int m_id,int rPay,int mPay,int total){
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime());
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_ID,u_id);
        values.put(MEMBER_ID,m_id);
        values.put(ROOM_PAYMENT,rPay);
        values.put(MEAL_PAYMENT,mPay);
        values.put(DATE,currentDate);
        values.put(TOTAL_PAYMENT,total);
        Long ck=sqLiteDatabase.insert(PAYMENT_TABLE,null,values);
        return ck!=-1;
    }
    public boolean balanceUpdate(int u_id,int total){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_ID,u_id);
        values.put(BALANCE,total);
        Long ck=sqLiteDatabase.insert(BALANCE_TABLE,null,values);
        return ck!=-1;
    }

    public boolean updateBazarList(Button date, AutoCompleteTextView bazarMan,AutoCompleteTextView item[],AutoCompleteTextView price[],int totalCost) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String dateString = date.getText().toString();
        String bazar_man = bazarMan.getText().toString().trim();
        String items[] = new String[12];
        String prices[] = new String[12];
        for (int i = 1; i <= 10; i++) {
            items[i] = item[i].getText().toString().trim();
            prices[i] = price[i].getText().toString().trim();
        }
        ContentValues values = new ContentValues();
        Calendar calendar=Calendar.getInstance();
        values.put(DATE_INTEGER,calendar.getTimeInMillis());
        values.put(BAZAR_DATE, dateString);
        values.put(BAZAR_MAKER, bazar_man);
        values.put(ITEM_1, items[1]);
        values.put(ITEM_2, items[2]);
        values.put(ITEM_3, items[3]);
        values.put(ITEM_4, items[4]);
        values.put(ITEM_5, items[5]);
        values.put(ITEM_6, items[6]);
        values.put(ITEM_7, items[7]);
        values.put(ITEM_8, items[8]);
        values.put(ITEM_9, items[9]);
        values.put(ITEM_10, items[10]);

        values.put(PRICE_1, prices[1]);
        values.put(PRICE_2, prices[2]);
        values.put(PRICE_3, prices[3]);
        values.put(PRICE_4, prices[4]);
        values.put(PRICE_5, prices[5]);
        values.put(PRICE_6, prices[6]);
        values.put(PRICE_7, prices[7]);
        values.put(PRICE_8, prices[8]);
        values.put(PRICE_9, prices[9]);
        values.put(PRICE_10, prices[10]);
        values.put(TOTAL_BAZAR_COST, totalCost);
        Long ck = sqLiteDatabase.insert(BAZAR_TABLE, null, values);
        return ck != -1;
    }

    public boolean changePassword(String cPass,String nPass){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String sql="SELECT * FROM " + USER + " WHERE " + USER_ID + " = ? AND " + USER_PASSWORD + " = ? ;";
        String args[]=new String[]{String.valueOf(MainActivity.MANAGER.getId()),cPass};
        Cursor res=sqLiteDatabase.rawQuery(sql,args);
        if(res.getCount()>0){
            ContentValues values=new ContentValues();
            values.put(USER_PASSWORD,nPass);
            String whereClause= USER_ID + " = ? ";
            String whereArgs[]=new String[]{String.valueOf(MainActivity.MANAGER.getId())};
            res.close();
            int id=sqLiteDatabase.update(USER,values,whereClause,whereArgs);
            if(id>0){
                res.close();
                MainActivity.MANAGER.setUser_password(nPass);
                return true;
            }
            else {
                changePassword(cPass,nPass);
            }
        }
        else {
            res.close();
            return false;
        }
        return true;
    }

    public boolean changeMobile(String mobile){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_MOBILE,mobile);
        String whereClause=USER_ID + " = ?";
        String whereArgs[]=new String[]{String.valueOf(MainActivity.MANAGER.getId())};
        int res=sqLiteDatabase.update(USER,values,whereClause,whereArgs);
        if(res>0){
            MainActivity.MANAGER.setUser_mobile(mobile);
            return true;
        }
        else {
            return false;
        }
    }

    // method to get all due member
    public ArrayList<StringBuffer> getDueMembers(){
        ArrayList<StringBuffer> dueMembers=new ArrayList<>();
        ArrayList<Member>members=getAllMember();
        for(int i=0; i<members.size(); ++i){
            int id=members.get(i).getId();
            int paid=getPaid(id);
            int due=getDue(members.get(i));
            if(due-paid>0){
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(id+"&");
                stringBuffer.append(members.get(i).getName()+"&");
                stringBuffer.append(due-paid);
                dueMembers.add(stringBuffer);
            }
        }
        return dueMembers;
    }
    // method of finding balance of user hand
    public int getBalance(int id){
        int balance=0;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql="SELECT TOTAL("+BALANCE+")"+ " FROM " + BALANCE_TABLE + " WHERE " + USER_ID + " = " + id ;
        Cursor res=sqLiteDatabase.rawQuery(sql,null);
        if(res.getCount()>0){
            res.moveToNext();
            balance=Integer.parseInt(res.getString(0));
            res.close();
        }
        return balance;
    }
    // method to payment due
    public boolean payDuePayment(int id,int taka){
        balanceUpdate(MainActivity.MANAGER.getId(),taka);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(MEMBER_ID,id);
        values.put(BALANCE,taka);
        return (sqLiteDatabase.insert(MEMBER_PAID_TABLE,null,values) != -1);
    }
    public int getPaid(int id){
        int balance=0;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql="SELECT TOTAL("+BALANCE+")"+ " FROM " + MEMBER_PAID_TABLE + " WHERE " + MEMBER_ID + " = " + id ;
        Cursor res=sqLiteDatabase.rawQuery(sql,null);
        if(res.getCount()>0){
            res.moveToNext();
            balance=Integer.parseInt(res.getString(0));
            res.close();
        }
        return balance;
    }
    public int getDue(Member member){
        int due=0;
        int rent_cost=getNoOfMonth(member.getDate())*member.getRoom_cost();
        int totalMeal=getTotalMeal(member)*3;
        if(totalMeal==0){
            return rent_cost;
        }
        int bazarCost=getBazarCost(member);
        int mealTaken=getMealTaken(member);
        int cost=(bazarCost*mealTaken)/totalMeal;
        due=cost+rent_cost;
        return due;
    }
    public int getNoOfMonth(String date){
        String currentDate[] = (DateFormat.getDateInstance(DateFormat.FULL, Locale.US).format(Calendar.getInstance().getTime())).split(" ");
        int rent=0;
        String dateSplit[]=date.split(" ");
       // int cm=MONTH.get(currentDate[1]);
        int cm=findMonth(currentDate[1]);
        int cy=Integer.parseInt(currentDate[3]);
        //int am=MONTH.get(dateSplit[1]);
        int am=findMonth(dateSplit[1]);
        int ay=Integer.parseInt(dateSplit[3]);
        int b=cm-am+1;
        rent+=12*(cy-ay)+b;
        return rent;
    }
    public int getBazarCost(Member member){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        int bazarCost=0;
        String query="SELECT TOTAL(" + TOTAL_BAZAR_COST + ") FROM " + BAZAR_TABLE + " WHERE " + DATE_INTEGER +
                " >= " + member.getTimeMillis() ;
        Cursor res=sqLiteDatabase.rawQuery(query,null);
        if(res.getCount()>0){
            res.moveToNext();
            bazarCost=Integer.parseInt(res.getString(0));
            res.close();
        }
        return bazarCost;
    }
    public int getTotalMeal(Member member){
        int meal=0;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String query="SELECT * FROM " + MEAL_TABLE + " WHERE " + DATE_INTEGER + " >= " + member.getTimeMillis();
        Cursor res=sqLiteDatabase.rawQuery(query,null);
        if(res.getCount()>0){
            meal=res.getCount();
            res.close();
        }
        return meal;
    }
    public int getMealTaken(Member member){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        int total=0;
        String query="SELECT * FROM " + MEMBER_MEAL_TABLE + " WHERE " + MEMBER_ID + " = " + member.getId();
        Cursor res=sqLiteDatabase.rawQuery(query,null);
        if(res.getCount()>0){
            total=res.getCount();
            res.close();
        }
        return total;
    }
    public int findMonth(String month){
        switch (month){
            case "JANUARY":
                return 1;
            case "FEBRUARY":
                return 2;
            case "MARCH":
                return 3;
            case "APRIL":
                return 4;
            case "MAY":
                return 5;
            case "JUNE":
                return 6;
            case "JULY":
                return 7;
            case "AUGUST":
                return 8;
            case "SEPTEMBER":
                return 9;
            case "OCTOBER":
                return 10;
            case "NOVEMBER":
                return 11;
            case "DECMBER":
                return 12;
        }
        return 0;
    }
}
