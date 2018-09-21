package dofam.in.bible.presentation.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.atomic.AtomicBoolean;

import dofam.in.bible.R;
import in.dofam.domain.Verse;

@EViewGroup(R.layout.view_verse_list_item)
public class VerseListItemView extends ConstraintLayout {

    private static final String LOG_TAG = VerseListItemView.class.getSimpleName();

    @ViewById(R.id.verse_text_view)
    TextView verseTextView;

    public VerseListItemView(Context context) {
        super(context);
    }

    public VerseListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerseListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(Verse verse) {
        Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),  "fonts/Newton-Bold_32510.ttf");
        verseTextView.setTypeface(custom_font);
        verseTextView.setText(String.valueOf(verse.getVnumber()).concat(" " + verse.getText()));
        justify();
    }

    public void justify() {

        verseTextView.post(() -> {
            final AtomicBoolean isJustify = new AtomicBoolean(false);
            final String textString = verseTextView.getText().toString();
            final TextPaint textPaint = verseTextView.getPaint();
            final SpannableStringBuilder builder = new SpannableStringBuilder();

            if (!isJustify.get()) {

                final int lineCount = verseTextView.getLineCount();
                final int textViewWidth = verseTextView.getWidth() - verseTextView.getPaddingLeft() - verseTextView.getPaddingRight();
                //Log.e(LOG_TAG, "ширина " + textViewWidth);
                for (int i = 0; i < lineCount; i++) {

                    int lineStart = verseTextView.getLayout().getLineStart(i);
                    int lineEnd = verseTextView.getLayout().getLineEnd(i);

                    Log.e(LOG_TAG, "lineStart " + lineStart + " lineEnd " + lineEnd);
                    String lineString = textString.substring(lineStart, lineEnd);


                    if (i == lineCount - 1) {
                        builder.append(new SpannableString(lineString));
                        break;
                    }

                    String trimSpaceText = lineString.trim();
                    String removeSpaceText = trimSpaceText.replaceAll(" ", "");


                    float removeSpaceWidth = textPaint.measureText(removeSpaceText);
                    //Log.e(LOG_TAG, "removeSpaceWidth " + removeSpaceWidth);
                    float spaceCount = trimSpaceText.length() - removeSpaceText.length();
                    //Log.e(LOG_TAG, spaceCount + " пробела");
                    int eachSpaceWidth = (int) ((textViewWidth - removeSpaceWidth) / spaceCount);
                    //Log.e(LOG_TAG, "размер одного пробела " + eachSpaceWidth);

                    SpannableString spannableString = new SpannableString(lineString);
                    //Log.e(LOG_TAG, trimSpaceText);
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

                verseTextView.setText(builder);
                isJustify.set(true);
            }
        });
    }
}
