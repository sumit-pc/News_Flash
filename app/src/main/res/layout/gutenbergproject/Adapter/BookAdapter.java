package com.sumit.gutenbergproject.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sumit.gutenbergproject.R;
import com.sumit.gutenbergproject.model.BookDetails;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<BookDetails> orderList;
    private Context mContext;
    private ProgressDialog progressDialog;


    public  BookAdapter(Context mContext, List<BookDetails> orderHistorySetterGetter, ProgressDialog progressDialog) {
        this.mContext = mContext;
        this.orderList = orderHistorySetterGetter;
        this.progressDialog = progressDialog;
    }

    @NonNull
    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_show, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder holder, final int position) {

        if(!TextUtils.isEmpty(orderList.get(position).getImage())){
            Glide.with(mContext).load(orderList.get(position).getImage()).into(holder.bookImage);
        }
        holder.bookName.setText(orderList.get(position).getName());
        holder.bookTitle.setText(orderList.get(position).getName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(orderList.get(position).getText()));
                    mContext.startActivity(i);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    Toast.makeText(mContext,"Text Format is not available.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName;
        TextView bookTitle;
        RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImageView);
            bookName = itemView.findViewById(R.id.bookName);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }

    }
}
