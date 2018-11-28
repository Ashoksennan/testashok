package com.examp.three.activity.faq;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examp.three.R;

import java.util.List;

public class FAQ_Adapter extends RecyclerView.Adapter<FAQ_Adapter.FAQ_ViewHolder> {
    Context context;
    List<Faq_Model> itemsList;

    public FAQ_Adapter(Context context, List<Faq_Model> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public FAQ_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_itemview,parent,false);
        return new FAQ_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQ_ViewHolder holder, int position) {
        Faq_Model item = itemsList.get(position);
        holder.ques_tv.setText(item.getFaqQuestion());
        holder.ans_tv.setText(item.getFaqAnswer());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class FAQ_ViewHolder extends RecyclerView.ViewHolder{
        TextView ques_tv,ans_tv;
        public FAQ_ViewHolder(View itemView){
            super(itemView);
            ques_tv = (TextView)itemView.findViewById(R.id.ques_tv);
            ans_tv = (TextView)itemView.findViewById(R.id.ans_tv);
        }
    }
}
