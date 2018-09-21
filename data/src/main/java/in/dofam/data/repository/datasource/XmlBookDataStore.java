package in.dofam.data.repository.datasource;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import in.dofam.data.entity.BookEntity;
import in.dofam.data.entity.ChapterEntity;
import in.dofam.data.entity.VerseEntity;
import io.reactivex.Observable;

public class XmlBookDataStore implements BookDataStore {

    private static final String LOG_TAG = XmlBookDataStore.class.getSimpleName();

    private Context context;

    public XmlBookDataStore(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<BookEntity>> bookEntityList() {
        List<BookEntity> bookEntities = new ArrayList<>();
        try {
            BookEntity bookEntity = null;
            List<ChapterEntity> chapters = null;
            ChapterEntity chapterEntity = null;
            List<VerseEntity> verses = null;
            VerseEntity verseEntity = null;
            InputStream inputStream = context.getAssets().open("bible_russian.xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(inputStream, "UTF-8");

            int eventType = xpp.getEventType();
            String currentTag = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if(eventType == XmlPullParser.START_TAG) {
                    currentTag = xpp.getName();
                    switch (xpp.getName()) {
                        case "BIBLEBOOK":
                            int bnumber = Integer.parseInt(xpp.getAttributeValue(null, "bnumber"));
                            String bname = xpp.getAttributeValue(null, "bname");
                            chapters = new ArrayList<>();
                            bookEntity = new BookEntity(bnumber, bname, chapters);
                            bookEntities.add(bookEntity);
                            break;
                        case "CHAPTER":
                            int cnumber = Integer.parseInt(xpp.getAttributeValue(null, "cnumber"));
                            verses = new ArrayList<>();
                            chapterEntity = new ChapterEntity(cnumber, verses);
                            if (chapters != null) {
                                chapters.add(chapterEntity);
                            }
                            break;
                        case "VERS":
                            if (verses != null) {
                                int vnumber = Integer.parseInt(xpp.getAttributeValue(null, "vnumber"));
                                verseEntity = new VerseEntity(vnumber, xpp.getText());
                                verses.add(verseEntity);
                            }
                            break;
                    }
                } else if(eventType == XmlPullParser.END_TAG) {
                    currentTag = null;
                } else if(eventType == XmlPullParser.TEXT) {
                    if(currentTag != null && currentTag.equals("VERS") && verseEntity != null) {
                        verseEntity.setText(xpp.getText());
                    }
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("!!!!!!!!!!!!!!!!!!!!!", "bookEntities " + bookEntities.size());
        return Observable.just(bookEntities);
    }

    @Override
    public Observable<BookEntity> bookEntityDetails(int bookId) {
        return null;
    }
}
