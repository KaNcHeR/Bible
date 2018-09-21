package dofam.in.bible.presentation.adapters;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import dofam.in.bible.presentation.ui.views.ChapterListItemView;
import dofam.in.bible.presentation.ui.views.ChapterListItemView_;
import in.dofam.domain.Chapter;

@EBean
public class ChapterListAdapter extends RecyclerViewAdapterBase<Chapter, ChapterListItemView> {

    @Override
    ChapterListItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ChapterListItemView_.build(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolderWrapper<ChapterListItemView> holder, int position) {
        holder.getView().bind(items.get(position));
    }
}
