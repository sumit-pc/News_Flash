package com.sumit.newsapp.Fragement;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sumit.newsapp.Adapter.NewsAdapter;
import com.sumit.newsapp.R;
import com.sumit.newsapp.RoomDatabase.DatabaseClient;
import com.sumit.newsapp.model.NewsDetails;

import java.util.ArrayList;
import java.util.List;


public class BookmarkFragement extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    ArrayList<NewsDetails> bookList;
    private ProgressDialog progressDialog;
    TextView bookmarkMsg;

    public BookmarkFragement() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark_fragement, container, false);
        initialize(view);
        return view;
    }
    private void initialize(View view){
        bookmarkMsg = view.findViewById(R.id.bookmarkMsg);
        recyclerView = view.findViewById(R.id.see_book_list_bk);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);

        getAllNews gt = new getAllNews();
        gt.execute();





    }

    public class getAllNews extends AsyncTask<Void, Void, List<NewsDetails>> {

        @Override
        protected List<NewsDetails> doInBackground(Void... voids) {
            List<NewsDetails> taskList = DatabaseClient
                    .getInstance(getActivity())
                    .getAppDatabase()
                    .taskDao()
                    .getAllNews();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<NewsDetails> tasks) {
            super.onPostExecute(tasks);
            bookList = new ArrayList<>();
            for (NewsDetails num : tasks) {
                Log.e("LoginUser", num.getTitle());
                bookList.add(num);
            }
            if(tasks.size() == 0){
                bookmarkMsg.setVisibility(View.VISIBLE);
            }
            adapter = new NewsAdapter(getActivity(), bookList, progressDialog);
            recyclerView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();

        }
    }
}