package dofam.in.bible.presentation.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import dofam.in.bible.R;
import in.dofam.domain.Chapter;

@EViewGroup(R.layout.view_chapter_list_item)
public class ChapterListItemView extends ConstraintLayout {

    @ViewById(R.id.chapter_text_view)
    TextView chapterTextView;

    public ChapterListItemView(Context context) {
        super(context);
    }

    public ChapterListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChapterListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DefaultLocale")
    public void bind(Chapter chapter) {
        chapterTextView.setText(String.format("Глава %d", chapter.getCnumber()));
    }
}
