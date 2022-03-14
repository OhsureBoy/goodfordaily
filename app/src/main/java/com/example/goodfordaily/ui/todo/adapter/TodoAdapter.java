package com.example.goodfordaily.ui.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodfordaily.R;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.ui.todo.model.TodoListModel;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoModel> todoList;

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        holder.onBind(todoList.get(position));
    }

    public TodoModel getTaskAt(int position) {
        return todoList.get(position);
    }

    public void setTodoList(List<TodoModel> list){
        this.todoList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoList == null ? 0 : todoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkbox;
        TextView todoData;
        Button  button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todoData = (TextView) itemView.findViewById(R.id.todo_text);
        }

        void onBind(TodoModel item) {

            todoData.setText(item.getDescription());
        }
    }
}
