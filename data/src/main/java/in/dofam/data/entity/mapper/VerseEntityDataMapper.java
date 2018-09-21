package in.dofam.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.dofam.data.entity.VerseEntity;
import in.dofam.domain.Verse;

@Singleton
public class VerseEntityDataMapper {
    @Inject
    VerseEntityDataMapper() {}

    /**
     * Transform a {@link VerseEntity} into an {@link Verse}.
     *
     * @param verseEntity Object to be transformed.
     * @return {@link Verse} if valid {@link VerseEntity} otherwise null.
     */
    public Verse transform(VerseEntity verseEntity) {
        Verse verse = null;
        if (verseEntity != null) {
            verse = new Verse(verseEntity.getVnumber(), verseEntity.getText());
        }
        return verse;
    }

    /**
     * Transform a List of {@link VerseEntity} into a Collection of {@link Verse}.
     *
     * @param verseEntityCollection Object Collection to be transformed.
     * @return {@link Verse} if valid {@link VerseEntity} otherwise null.
     */
    public List<Verse> transform(Collection<VerseEntity> verseEntityCollection) {
        final List<Verse> verseList = new ArrayList<>(20);
        for (VerseEntity verseEntity : verseEntityCollection) {
            final Verse verse = transform(verseEntity);
            if (verse != null) {
                verseList.add(verse);
            }
        }
        return verseList;
    }
}
