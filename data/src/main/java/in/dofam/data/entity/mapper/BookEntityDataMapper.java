package in.dofam.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.dofam.data.entity.BookEntity;
import in.dofam.domain.Book;

@Singleton
public class BookEntityDataMapper {
    @Inject
    BookEntityDataMapper() {}

    /**
     * Transform a {@link BookEntity} into an {@link Book}.
     *
     * @param bookEntity Object to be transformed.
     * @return {@link Book} if valid {@link BookEntity} otherwise null.
     */
    public Book transform(BookEntity bookEntity) {
        Book book = null;
        ChapterEntityDataMapper chapterEntityDataMapper = new ChapterEntityDataMapper();
        if (bookEntity != null) {
            book = new Book(bookEntity.getId(), bookEntity.getName(), chapterEntityDataMapper.transform(bookEntity.getChapters()));
        }
        return book;
    }

    /**
     * Transform a List of {@link BookEntity} into a Collection of {@link Book}.
     *
     * @param bookEntityCollection Object Collection to be transformed.
     * @return {@link Book} if valid {@link BookEntity} otherwise null.
     */
    public List<Book> transform(Collection<BookEntity> bookEntityCollection) {
        final List<Book> bookList = new ArrayList<>(20);
        for (BookEntity userEntity : bookEntityCollection) {
            final Book book = transform(userEntity);
            if (book != null) {
                bookList.add(book);
            }
        }
        return bookList;
    }
}
