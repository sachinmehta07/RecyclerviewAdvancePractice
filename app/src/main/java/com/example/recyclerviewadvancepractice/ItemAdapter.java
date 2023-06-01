package com.example.recyclerviewadvancepractice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<String> items;
    private int selectedItemPosition = -1;

    public ItemAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItem);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(int position) {
            textView.setText(items.get(position));
            if (selectedItemPosition == position) {
                textView.setBackgroundColor(itemView.getResources().getColor(android.R.color.holo_blue_light));
            } else {
                textView.setBackgroundColor(0);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (selectedItemPosition != position) {
                if (selectedItemPosition >= 0) {
                    notifyItemChanged(selectedItemPosition);
                }
                selectedItemPosition = position;
                notifyItemChanged(selectedItemPosition);
            } else {
                selectedItemPosition = -1;
                notifyItemChanged(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            showDeleteDialog(position);
            return true;
        }
        private void showDeleteDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            items.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(itemView.getContext(), "Item deleted", Toast.LENGTH_SHORT).show();

                            if (selectedItemPosition == position) {
                                selectedItemPosition = -1;
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }


}
