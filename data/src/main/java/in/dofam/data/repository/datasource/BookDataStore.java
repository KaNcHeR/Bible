package in.dofam.data.repository.datasource;

import java.util.List;

import in.dofam.data.entity.BookEntity;
import io.reactivex.Observable;

public interface BookDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link BookEntity}.
     */
    Observable<List<BookEntity>> bookEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link BookEntity} by its id.
     *
     * @param bookId The id to retrieve book data.
     */
    Observable<BookEntity> bookEntityDetails(final int bookId);
}
