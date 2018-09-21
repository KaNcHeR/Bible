package in.dofam.data.entity;

import java.util.List;

public class ChapterEntity {
    private int cnumber;
    private List<VerseEntity> verses;

    public ChapterEntity(int cnumber, List<VerseEntity> verses) {
        this.cnumber = cnumber;
        this.verses = verses;
    }

    public int getCnumber() {
        return cnumber;
    }

    public void setCnumber(int cnumber) {
        this.cnumber = cnumber;
    }

    public List<VerseEntity> getVerses() {
        return verses;
    }

    public void setVerses(List<VerseEntity> verses) {
        this.verses = verses;
    }
}
