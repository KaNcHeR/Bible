package in.dofam.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookDataStoreFactory {
    private final Context context;

    @Inject
    BookDataStoreFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * Create {@link BookDataStore} from a user id.
     */
    public BookDataStore create(int userId) {
        BookDataStore bookDataStore;

//        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
//            userDataStore = new DiskUserDataStore(this.userCache);
//        } else {
//            userDataStore = createCloudDataStore();
//        }

        return createXmlDataStore();
    }

    /**
     * Create {@link BookDataStore} to retrieve data from the Xml.
     */
    public BookDataStore createXmlDataStore() {

        return new XmlBookDataStore(context);
    }
}
