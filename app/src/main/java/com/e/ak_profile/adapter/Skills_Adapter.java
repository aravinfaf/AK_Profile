package com.e.ak_profile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ak_profile.ProjectActivity;
import com.e.ak_profile.R;
import com.e.ak_profile.model.ProjectModel;
import com.e.ak_profile.model.SkillModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Skills_Adapter extends RecyclerView.Adapter<Skills_Adapter.ViewHolder> {

    Context context;
    ArrayList<SkillModel> data;



    public Skills_Adapter(Context context, ArrayList<SkillModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.skills_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){

            holder.img.setImageResource(data.get(position).getImage());
            holder.name_TV.setText(data.get(position).getName());

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name_TV)
        TextView name_TV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
