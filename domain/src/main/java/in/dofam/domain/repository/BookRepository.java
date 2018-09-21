package in.dofam.domain.repository;

import java.util.List;

import in.dofam.domain.Book;
import io.reactivex.Observable;

public interface BookRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Book}.
     */
    Observable<List<Book>> books();

    /**
     * Get an {@link Observable} which will emit a {@link Book}.
     *
     * @param bookId The book id used to retrieve book data.
     */
    Observable<Book> book(final int bookId);
}