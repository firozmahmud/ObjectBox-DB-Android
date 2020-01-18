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
    private Context context;
    private boolean isAnyItemSelected;


    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        this.itemClick = (ItemClick) context;
    }

    public interface ItemClick {
        void itemClick(User user);

        void itemLongClick(boolean isAnyItemSelected, User user);
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

        if (users.get(position).isSelected) {
            holder.binding.getRoot().setBackgroundColor(context.getResources().getColor(R.color.green_lite));
            holder.binding.checkedIcon.setVisibility(View.VISIBLE);
        } else {
            holder.binding.getRoot().setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.binding.checkedIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemUserHeadlineBinding binding;

        public ViewHolder(final ItemUserHeadlineBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.itemClick(users.get(getAdapterPosition()));
                }
            });

            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectOrUnSelect(getAdapterPosition(), binding);
                    return false;
                }
            });
        }
    }

    public void clearAll() {

        for (int i = 0; i < users.size(); i++) {
            users.get(i).isSelected = false;
        }

        notifyDataSetChanged();
    }

    public void selectOrUnSelect(int position, ItemUserHeadlineBinding binding) {

        users.get(position).isSelected = !users.get(position).isSelected;
        notifyDataSetChanged();


        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isSelected) {
                isAnyItemSelected = true;
                break;
            }
            isAnyItemSelected = false;
        }

        itemClick.itemLongClick(isAnyItemSelected, users.get(position));

    }
}
