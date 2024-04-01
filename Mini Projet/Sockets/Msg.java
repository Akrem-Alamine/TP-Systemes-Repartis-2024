import java.io.Serializable;

public class Msg implements Serializable {
    private String user;
    private String txt;

    Msg(String user, String txt){
        this.user=user;
        this.txt=txt;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUser() {
        return user;
    }

    public String getTxt() {
        return txt;
    }
}
