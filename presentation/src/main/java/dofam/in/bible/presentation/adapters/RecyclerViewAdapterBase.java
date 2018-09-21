package dofam.in.bible.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewHolderWrapper<V>> {

    protected List<T> items = new ArrayList<>();
    private ViewHolderWrapper.ClickListener clickListener;

    abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public ViewHolderWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderWrapper<>(onCreateItemView(parent, viewType), clickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void init(List<T> items, ViewHolderWrapper.ClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    public void initClickListener(ViewHolderWrapper.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setItems(List<T> items) {
        if(this.items != items) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    public void addAll(List<T> items) {
        int positionStart = this.items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    public void addAll(int location, List<T> items) {
        this.items.addAll(location, items);
        notifyItemRangeInserted(location, items.size());
    }

    public void replaceAll(List<T> items) {
        removeAll();
        addAll(items);
    }

    public int add(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
        return getItemCount() - 1;
    }

    public int add(int location, T item) {
        items.add(location, item);
        notifyItemInserted(location);
        return getItemCount() - 1;
    }

    public void removeItem(int position) {
        if (items.size() > position) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        int itemCount = items.size();
        this.items.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public int getItemPosition(T item) {
        return items.indexOf(item);
    }

    public boolean existItem(T item) {
        return items.contains(item);
    }

    public void setClickListener(ViewHolderWrapper.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ViewHolderWrapper.ClickListener getClickListener() {
        return clickListener;
    }
}