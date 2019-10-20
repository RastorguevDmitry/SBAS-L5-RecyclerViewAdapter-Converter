package com.rdi.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConvertAdapter extends RecyclerView.Adapter<ConvertAdapter.ConvertItemHolder> {

    List<Conversion> arrayConvert;
    IMainItemClickListener mMainItemClickListener;

    public ConvertAdapter(List<Conversion> asList, MainActivity mainActivity) {
        arrayConvert = asList;
        mMainItemClickListener = mainActivity;
    }

    public void setArrayConvert(List<Conversion> converts) {
        arrayConvert = converts == null ? null : new ArrayList<>(converts);
    }

    @NonNull
    @Override
    public ConvertItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_convert, parent, false);
        return new ConvertItemHolder(view, mMainItemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ConvertItemHolder holder, int position) {
        holder.bindView(arrayConvert.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayConvert.size();
    }

    static class ConvertItemHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        IMainItemClickListener mMainItemClickListener;

        public ConvertItemHolder(@NonNull View itemView, IMainItemClickListener mainItemClickListenrt) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            mMainItemClickListener = mainItemClickListenrt;
        }

        void bindView(final Conversion conversion) {
            textView.setText(conversion.getLable());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMainItemClickListener.onMainItemClick(conversion);
                }
            });
        }
    }
}
