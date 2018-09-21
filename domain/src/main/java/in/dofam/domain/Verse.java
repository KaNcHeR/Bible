package in.dofam.domain;

public class Verse {
    private int vnumber;
    private String text;

    public Verse(int vnumber, String text) {
        this.vnumber = vnumber;
        this.text = text;
    }

    public int getVnumber() {
        return vnumber;
    }

    public void setVnumber(int vnumber) {
        this.vnumber = vnumber;
    }

    public String getText() {
        return text;
    }
}
