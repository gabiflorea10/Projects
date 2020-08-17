package com.example.rehearsalapp;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<NotesItem> notesList;
    private OnClickInterface onClickInterface;

    public interface OnClickInterface{
        void onDeleteClick(int position);
    }

    public void setOnClickInterface(OnClickInterface onClickInterface) {
        this.onClickInterface = onClickInterface;
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        public TextView title;
        public TextView text;
        private OnClickInterface onClickInterface;


        public NotesViewHolder(View itemView, final OnClickInterface onClickInterface){
            super(itemView);
            title = itemView.findViewById(R.id.notesItemTitle);
            text = itemView.findViewById(R.id.notesItemText);
            this.onClickInterface = onClickInterface;

            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem delete = menu.add(Menu.NONE, 1, 1, "Delete");
            delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    onClickInterface.onDeleteClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public NotesAdapter(ArrayList<NotesItem> notesList) {
        this.notesList = notesList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        NotesViewHolder nvh = new NotesViewHolder(v, onClickInterface);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
        NotesItem currentItem = notesList.get(position);

        holder.title.setText(currentItem.getTitle());
        holder.text.setText(currentItem.getText());

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

}
