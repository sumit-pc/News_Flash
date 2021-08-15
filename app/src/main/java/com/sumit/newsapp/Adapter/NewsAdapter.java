package com.sumit.newsapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sumit.newsapp.R;
import com.sumit.newsapp.RoomDatabase.DatabaseClient;
import com.sumit.newsapp.model.NewsDetails;


import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<NewsDetails> orderList;
    private Context mContext;
    private ProgressDialog progressDialog;


    public NewsAdapter(Context mContext, List<NewsDetails> orderHistorySetterGetter, ProgressDialog progressDialog) {
        this.mContext = mContext;
        this.orderList = orderHistorySetterGetter;
        this.progressDialog = progressDialog;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_show, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        if(!TextUtils.isEmpty(orderList.get(position).getUrlToImage())){
            Glide.with(mContext).load(orderList.get(position).getUrlToImage()).into(holder.bookImage);
        }
        holder.bookName.setText(orderList.get(position).getTitle());
        holder.bookTitle.setText(orderList.get(position).getTitle());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(orderList.get(position).getUrl()));
                    mContext.startActivity(i);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    Toast.makeText(mContext,"Text Format is not available.", Toast.LENGTH_LONG).show();
                }
            }
        });

        if(mContext.getClass().getSimpleName().equals("MainActivity")){
            holder.bkBtnBrd.setVisibility(View.GONE);
            holder.bkBtn.setVisibility(View.VISIBLE);
        }


        holder.bkBtnBrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bkBtnBrd.setVisibility(View.GONE);
                holder.bkBtn.setVisibility(View.VISIBLE);
                // Room Database Store
                class insertRoom extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            DatabaseClient.getInstance(mContext).getAppDatabase()
                                    .taskDao()
                                    .insertUser(orderList.get(position));
                        } catch (Exception e) {
                            Toast.makeText(mContext, "Exception Thrown.",Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Toast.makeText(mContext, "News Bookmarked.", Toast.LENGTH_LONG).show();

                    }
                }
                new insertRoom().execute();
            }
        });

        holder.bkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.bkBtnBrd.setVisibility(View.VISIBLE);
                holder.bkBtn.setVisibility(View.GONE);

                // Room Database Store
                class deleteRoom extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            DatabaseClient.getInstance(mContext).getAppDatabase()
                                    .taskDao()
                                    .delete(orderList.get(position));
                        } catch (Exception e) {
                            Toast.makeText(mContext, "Exception Thrown.",Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Toast.makeText(mContext, "Bookmark Removed.", Toast.LENGTH_LONG).show();
                        if(mContext.getClass().getSimpleName().equals("MainActivity")){
                            holder.cardView.setVisibility(View.GONE);
                        }
                    }
                }
                new deleteRoom().execute();
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
        LinearLayout relativeLayout;
        ImageButton bkBtnBrd, bkBtn;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImageView);
            bookName = itemView.findViewById(R.id.bookName);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            bkBtnBrd = itemView.findViewById(R.id.bookmarkBtnBorder);
            bkBtn = itemView.findViewById(R.id.bookmarkBtn);
            cardView = itemView.findViewById(R.id.card_view);
        }

    }
}
