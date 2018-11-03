package com.example.chetan.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.chetan.testapp.R;
import com.example.chetan.testapp.model.Main_Recycle_list;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    public List<Main_Recycle_list> mainRecycle_lists;
    private RelativeLayout linearLayout;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvPhone,tvKey,tvread;
        ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvPhone  = view.findViewById(R.id.tv_phone);
            tvKey  = view.findViewById(R.id.tv_key);
            tvread  = view.findViewById(R.id.tv_read);
            imageView = view.findViewById(R.id.image_profile);
            linearLayout = view.findViewById(R.id.clickRecyclerView);

        }
    }
    public MainAdapter(List<Main_Recycle_list> mainRecycle_lists, Context context) {
        this.mainRecycle_lists = mainRecycle_lists;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Main_Recycle_list recycleList = mainRecycle_lists.get(position);
        holder.tvName.setText(recycleList.getName());
        holder.tvPhone.setText(recycleList.getphone());

        Glide.with(context).load(recycleList.getProfile()).into(holder.imageView);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Recycle_list recycleList = mainRecycle_lists.get(position);

                String name = recycleList.getName();
                Toast.makeText(context,"User Name : "+name,Toast.LENGTH_SHORT).show();
            }

        });

    }
    @Override
    public int getItemCount() {
        return mainRecycle_lists.size();
    }

}
