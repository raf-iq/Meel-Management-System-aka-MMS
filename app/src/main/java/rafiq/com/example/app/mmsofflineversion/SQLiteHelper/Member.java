package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

public class Member {
    private String name,email,mobile,village,thana,district;
    private int id,room_cost;
    private String date;
    private Long timeMillis;

    public int getRoom_cost() {
        return room_cost;
    }

    public void setRoom_cost(int room_cost) {
        this.room_cost = room_cost;
    }

    public Member(int id, String name, String email, String mobile, String village, String thana, String district, int room_cost,String date,Long timeMillis) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.village = village;
        this.thana = thana;
        this.district = district;
        this.room_cost=room_cost;
        this.date=date;
        this.timeMillis=timeMillis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }
}
