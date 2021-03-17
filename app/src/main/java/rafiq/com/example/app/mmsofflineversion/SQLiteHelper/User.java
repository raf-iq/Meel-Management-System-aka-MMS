package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

public class User {
    private int id;
    private String user_name;
    private String user_password;
    private String user_mail;
    private String user_mobile;
    private String name;

    public User(int id,String name, String user_name, String user_password, String user_mail, String user_mobile) {
        this.id=id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_mail = user_mail;
        this.user_mobile = user_mobile;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
