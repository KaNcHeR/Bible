package dofam.in.bible.presentation.main.views;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import dofam.in.bible.BibleApplication;
import dofam.in.bible.R;
import dofam.in.bible.di.application.AppComponent;
import dofam.in.bible.di.main.DaggerMainComponent;
import dofam.in.bible.di.main.MainComponent;
import dofam.in.bible.presentation.adapters.ChapterListAdapter;
import dofam.in.bible.presentation.adapters.VerseListAdapter;
import dofam.in.bible.presentation.custom.SlidingPaneLayout;
import in.dofam.domain.Book;
import in.dofam.domain.Chapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    MainComponent mainComponent;

    @Bean
    VerseListAdapter verseListAdapter;

    @Bean
    ChapterListAdapter chapterListAdapter;

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById(R.id.chapter_recycler_view)
    RecyclerView chapterRecyclerView;

    @AfterViews
    void ready() {
        SlidingPaneLayout slidingPaneLayout = findViewById(R.id.sliding_pane_layout);
        slidingPaneLayout.setParallaxDistance(40);
        slidingPaneLayout.setShadowDrawableLeft(getResources().getDrawable(R.mipmap.shadow_sliding_pane));
        slidingPaneLayout.setShadowDrawableRight(getResources().getDrawable(R.drawable.sliding_panel_shadow_drawable));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(verseListAdapter);

        chapterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chapterRecyclerView.setAdapter(chapterListAdapter);

        Disposable disposable =
                getAppComponent().bookRepository().books()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(books -> {
                            if (books != null && !books.isEmpty()) {
                                verseListAdapter.setItems(books.get(0).getChapters().get(0).getVerses());
                                chapterListAdapter.setItems(books.get(0).getChapters());
                            }
                        });
    }

    @AfterInject
    void onInjectDependencies() {
        this.mainComponent = DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build();
    }

    protected AppComponent getAppComponent() {
        return ((BibleApplication) getApplication()).getAppComponent();
    }

    public static void justify(final TextView textView) {

        final AtomicBoolean isJustify = new AtomicBoolean(false);

        final String textString = textView.getText().toString();

        final TextPaint textPaint = textView.getPaint();

        final SpannableStringBuilder builder = new SpannableStringBuilder();

        textView.post(new Runnable() {
            @Override
            public void run() {

                if (!isJustify.get()) {

                    final int lineCount = textView.getLineCount();
                    final int textViewWidth = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight();
                    Log.e(LOG_TAG, "ширина " + textViewWidth);
                    for (int i = 0; i < lineCount; i++) {

                        int lineStart = textView.getLayout().getLineStart(i);
                        int lineEnd = textView.getLayout().getLineEnd(i);

                        String lineString = textString.substring(lineStart, lineEnd);


                        if (i == lineCount - 1) {
                            builder.append(new SpannableString(lineString));
                            break;
                        }

                        String trimSpaceText = lineString.trim();
                        String removeSpaceText = trimSpaceText.replaceAll(" ", "");


                        float removeSpaceWidth = textPaint.measureText(removeSpaceText);
                        Log.e(LOG_TAG, "removeSpaceWidth " + removeSpaceWidth);
                        float spaceCount = trimSpaceText.length() - removeSpaceText.length();
                        Log.e(LOG_TAG, spaceCount + " пробела");
                        int eachSpaceWidth = (int) ((textViewWidth - removeSpaceWidth) / spaceCount);
                        Log.e(LOG_TAG, "размер одного пробела " + eachSpaceWidth);

                        SpannableString spannableString = new SpannableString(lineString);
                        Log.e(LOG_TAG, trimSpaceText);
                        for (int j = 0; j < trimSpaceText.length(); j++) {
                            char c = trimSpaceText.charAt(j);
                            if (c == ' ') {
                                Drawable drawable = new ColorDrawable(0xff000000);
                                drawable.setBounds(0, 0, eachSpaceWidth, 0);
                                //UnderlineSpan underlineSpan = new UnderlineSpan();
                                ImageSpan span = new ImageSpan(drawable);
                                spannableString.setSpan(span, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        builder.append(spannableString);
                    }

                    textView.setText(builder);
                    isJustify.set(true);
                }
            }
        });
    }
}