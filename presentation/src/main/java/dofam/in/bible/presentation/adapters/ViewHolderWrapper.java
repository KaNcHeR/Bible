package dofam.in.bible.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolderWrapper<V extends View> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    V view;
    private ClickListener clickListener;

    public ViewHolderWrapper(V itemView, ClickListener clickListener) {
        super(itemView);
        view = itemView;
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public V getView() {
        return view;
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION && clickListener != null) {
            clickListener.onItemClicked(position, v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION && clickListener != null) {
            clickListener.ontItemLongClicked(getAdapterPosition(), v);
            return true;
        }
        return false;
    }

    public interface ClickListener {
        void onItemClicked(int position, View v);
        boolean ontItemLongClicked(int position, View v);
    }
}
