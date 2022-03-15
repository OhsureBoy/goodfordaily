package com.example.goodfordaily.ui.diary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodfordaily.R;
import com.example.goodfordaily.model.DiaryModel;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<DiaryModel> diaryList;

    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary, parent, false);
        return new DiaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.ViewHolder holder, int position) {
        holder.onBind(diaryList.get(position));
    }

    public DiaryModel getTaskAt(int position) {
        return diaryList.get(position);
    }

    public void setDiaryList(List<DiaryModel> list){
        this.diaryList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return diaryList == null ? 0 : diaryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView diaryData;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaryData = (TextView) itemView.findViewById(R.id.diary_text);
        }

        void onBind(DiaryModel item) {

            diaryData.setText(item.getDescription());
        }
    }
}
