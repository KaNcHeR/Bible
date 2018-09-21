package in.dofam.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import in.dofam.domain.Book;
import in.dofam.domain.executor.PostExecutionThread;
import in.dofam.domain.executor.ThreadExecutor;
import in.dofam.domain.repository.BookRepository;
import io.reactivex.Observable;

public class GetBookList extends UseCase<List<Book>, Void> {

    private final BookRepository bookRepository;

    @Inject
    GetBookList(BookRepository bookRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.bookRepository = bookRepository;
    }

    @Override
    Observable<List<Book>> buildUseCaseObservable(Void unused) {
        return this.bookRepository.books();
    }

}