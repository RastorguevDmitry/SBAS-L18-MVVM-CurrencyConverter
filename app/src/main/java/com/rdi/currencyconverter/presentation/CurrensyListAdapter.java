package com.rdi.currencyconverter.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rdi.currencyconverter.R;
import com.rdi.currencyconverter.domain.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrensyListAdapter extends RecyclerView.Adapter<CurrensyListAdapter.ConvertItemHolder> {

    List<Currency> arrayCyrency;
    IMainItemClickListener mMainItemClickListener;

    public CurrensyListAdapter(List<Currency> asList) {
        arrayCyrency = asList;

    }

    public void setArrayCyrency(List<Currency> converts) {
        arrayCyrency = converts == null ? null : new ArrayList<>(converts);
    }

    @NonNull
    @Override
    public ConvertItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_convert, parent, false);
        return new ConvertItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ConvertItemHolder holder, int position) {
        holder.bindView(arrayCyrency.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayCyrency.size();
    }

    static class ConvertItemHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        IMainItemClickListener mMainItemClickListener;

        public ConvertItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

        }

        void bindView(final Currency conversion) {
            textView.setText(conversion.getName());
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mMainItemClickListener.onMainItemClick(conversion);
//                }
//            });
        }
    }
}