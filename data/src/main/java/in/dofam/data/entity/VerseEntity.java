package in.dofam.data.entity;

public class VerseEntity {
    private int vnumber;
    private String text;

    public VerseEntity(int vnumber) {
        this.vnumber = vnumber;
    }

    public VerseEntity(int vnumber, String text) {
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

    public void setText(String text) {
        this.text = text;
    }
}
