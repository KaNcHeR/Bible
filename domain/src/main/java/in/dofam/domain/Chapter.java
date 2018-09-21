package in.dofam.domain;

import java.util.List;

public class Chapter {
    private int cnumber;
    private List<Verse> verses;

    public Chapter(int cnumber, List<Verse> verses) {
        this.cnumber = cnumber;
        this.verses = verses;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public int getCnumber() {
        return cnumber;
    }
}
