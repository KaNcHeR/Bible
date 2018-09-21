package dofam.in.bible.presentation.adapters;

import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;

import dofam.in.bible.presentation.ui.views.VerseListItemView;
import dofam.in.bible.presentation.ui.views.VerseListItemView_;
import in.dofam.domain.Verse;

@EBean
public class VerseListAdapter extends RecyclerViewAdapterBase<Verse, VerseListItemView> {

    @Override
    VerseListItemView onCreateItemView(ViewGroup parent, int viewType) {
        return VerseListItemView_.build(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolderWrapper<VerseListItemView> holder, int position) {
        holder.getView().bind(items.get(position));
    }
}
