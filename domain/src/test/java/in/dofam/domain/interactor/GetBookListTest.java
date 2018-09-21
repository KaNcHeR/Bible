package in.dofam.domain.interactor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import in.dofam.domain.executor.PostExecutionThread;
import in.dofam.domain.executor.ThreadExecutor;
import in.dofam.domain.repository.BookRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetBookListTest {

    private GetBookList getBookList;

    @Mock private BookRepository mockBookRepository;
    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;

    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        getBookList = new GetBookList(mockBookRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetBookListUseCaseObservableHappyCase() {
        getBookList.buildUseCaseObservable(null);
        verify(mockBookRepository).books();
        verifyNoMoreInteractions(mockBookRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}