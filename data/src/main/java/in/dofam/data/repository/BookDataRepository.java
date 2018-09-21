package in.dofam.data.repository;

import java.util.List;

import javax.inject.Inject;

import in.dofam.data.entity.mapper.BookEntityDataMapper;
import in.dofam.data.repository.datasource.BookDataStore;
import in.dofam.data.repository.datasource.BookDataStoreFactory;
import in.dofam.domain.Book;
import in.dofam.domain.repository.BookRepository;
import io.reactivex.Observable;

public class BookDataRepository implements BookRepository {

    private final BookDataStoreFactory bookDataStoreFactory;
    private final BookEntityDataMapper bookEntityDataMapper;

    /**
     * Constructs a {@link BookRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param bookEntityDataMapper {@link BookEntityDataMapper}.
     */
    @Inject
    BookDataRepository(BookDataStoreFactory dataStoreFactory,
                       BookEntityDataMapper bookEntityDataMapper) {
        this.bookDataStoreFactory = dataStoreFactory;
        this.bookEntityDataMapper = bookEntityDataMapper;
    }

    @Override
    public Observable<List<Book>> books() {
        final BookDataStore bookDataStore = this.bookDataStoreFactory.createXmlDataStore();
        return bookDataStore.bookEntityList().map(this.bookEntityDataMapper::transform);
    }

    @Override
    public Observable<Book> book(int bookId) {
        final BookDataStore bookDataStore = this.bookDataStoreFactory.create(bookId);
        return bookDataStore.bookEntityDetails(bookId).map(this.bookEntityDataMapper::transform);
    }
}
