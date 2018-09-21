package in.dofam.domain;

import java.util.List;

public class Book {
    private int id;
    private String name;
    private List<Chapter> chapters;

    public Book(int id, String name, List<Chapter> chapters) {
        this.id = id;
        this.name = name;
        this.chapters = chapters;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }
}
