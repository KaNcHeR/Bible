package in.dofam.data.entity;

import java.util.List;

public class BookEntity {
    private int id;
    private String name;
    private List<ChapterEntity> chapters;

    public BookEntity(int id, String name, List<ChapterEntity> chapters) {
        this.id = id;
        this.name = name;
        this.chapters = chapters;
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

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterEntity> chapters) {
        this.chapters = chapters;
    }
}
