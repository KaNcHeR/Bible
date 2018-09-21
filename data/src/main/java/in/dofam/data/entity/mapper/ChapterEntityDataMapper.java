package in.dofam.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.dofam.data.entity.ChapterEntity;
import in.dofam.data.entity.VerseEntity;
import in.dofam.domain.Chapter;
import in.dofam.domain.Verse;

@Singleton
public class ChapterEntityDataMapper {
    @Inject
    ChapterEntityDataMapper() {}

    /**
     * Transform a {@link VerseEntity} into an {@link Verse}.
     *
     * @param chapterEntity Object to be transformed.
     * @return {@link Verse} if valid {@link VerseEntity} otherwise null.
     */
    public Chapter transform(ChapterEntity chapterEntity) {
        Chapter chapter = null;
        VerseEntityDataMapper verseEntityDataMapper = new VerseEntityDataMapper();
        if (chapterEntity != null) {
            chapter = new Chapter(chapterEntity.getCnumber(), verseEntityDataMapper.transform(chapterEntity.getVerses()));
        }
        return chapter;
    }

    /**
     * Transform a List of {@link ChapterEntity} into a Collection of {@link Chapter}.
     *
     * @param chapterEntityCollection Object Collection to be transformed.
     * @return {@link Chapter} if valid {@link ChapterEntity} otherwise null.
     */
    public List<Chapter> transform(Collection<ChapterEntity> chapterEntityCollection) {
        final List<Chapter> chapterList = new ArrayList<>(20);
        for (ChapterEntity chapterEntity : chapterEntityCollection) {
            final Chapter chapter = transform(chapterEntity);
            if (chapter != null) {
                chapterList.add(chapter);
            }
        }
        return chapterList;
    }
}
