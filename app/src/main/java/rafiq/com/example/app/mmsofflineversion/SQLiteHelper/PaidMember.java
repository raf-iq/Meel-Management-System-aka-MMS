package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

public class PaidMember {
    private String name;
    private String date;
    private int roomPay;
    private int mealPay;
    private int bazarPay;
    private int totalPaid;
    private int id;

    public PaidMember(int id,String name, String date, int roomPay, int mealPay, int bazarPay, int totalPaid) {
        this.id=id;
        this.name = name;
        this.date = date;
        this.roomPay = roomPay;
        this.mealPay = mealPay;
        this.bazarPay = bazarPay;
        this.totalPaid=totalPaid;
    }

    public int getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(int totalPaid) {
        this.totalPaid = totalPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRoomPay() {
        return roomPay;
    }

    public void setRoomPay(int roomPay) {
        this.roomPay = roomPay;
    }

    public int getMealPay() {
        return mealPay;
    }

    public void setMealPay(int mealPay) {
        this.mealPay = mealPay;
    }

    public int getBazarPay() {
        return bazarPay;
    }

    public void setBazarPay(int bazarPay) {
        this.bazarPay = bazarPay;
    }
}
