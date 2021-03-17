package rafiq.com.example.app.mmsofflineversion.SQLiteHelper;

import java.util.Map;

public class BazarList {
    private StringBuffer description;
    private Map<String,String> detail;
    private String dotted;
    private int total;

    public BazarList(StringBuffer description, Map<String,String> detail, String dotted,int total) {
        this.description = description;
        this.detail=detail;
        this.dotted = dotted;
        this.total=total;
    }

    public void setDotted(String dotted) {
        this.dotted = dotted;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public StringBuffer getDescription() {
        return description;
    }

    public void setDescription(StringBuffer description) {
        this.description = description;
    }

    public Map<String, String> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, String> detail) {
        this.detail = detail;
    }

    public String getDotted() {
        return dotted;
    }

    public void setTotal(String dotted) {
        this.dotted = dotted;
    }
}
