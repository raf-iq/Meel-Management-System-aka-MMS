package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

public class Meal {
    private int id;
    private String date;
    private String breakfast;
    private String lunch;
    private String dinner;

    public Meal(int id,String date, String breakfast, String lunch, String dinner) {
        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public int getId() {
        return id;
    }

    public void setId(String manager) {
        this.id = id;
    }
}
