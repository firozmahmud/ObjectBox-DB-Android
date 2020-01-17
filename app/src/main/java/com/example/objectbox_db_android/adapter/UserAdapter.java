package com.example.objectbox_db_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objectbox_db_android.R;
import com.example.objectbox_db_android.databinding.ItemUserHeadlineBinding;
import com.example.objectbox_db_android.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private ItemClick itemClick;


    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.itemClick = (ItemClick) context;
    }

    public interface ItemClick {
        void itemClick(User user, int position);

        void itemLongClick(User user, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemUserHeadlineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_headline, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.name.setText(users.get(position).name);
        holder.binding.profession.setText(users.get(position).profession);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemUserHeadlineBinding binding;

        public ViewHolder(ItemUserHeadlineBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.itemClick(users.get(getAdapterPosition()), getAdapterPosition());
                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    itemClick.itemLongClick(users.get(getAdapterPosition()), getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
