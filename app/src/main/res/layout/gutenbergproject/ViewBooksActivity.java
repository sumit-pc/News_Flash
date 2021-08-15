package com.sumit.gutenbergproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.sumit.gutenbergproject.Adapter.BookAdapter;
import com.sumit.gutenbergproject.Network.ApiService;
import com.sumit.gutenbergproject.model.BookDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBooksActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    ArrayList<BookDetails> bookList;
    RecyclerView recyclerView;
    BookAdapter adapter;
    Intent intent;
    Boolean isLoading = false;
    int page = 1;
    EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        intent = getIntent();
        searchText = findViewById(R.id.search_text);

        ImageView searchCancel =  findViewById(R.id.cancelSearch);
        searchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                searchText.setText("");
                bookList.clear();
                getBooksList("");
            }
        });

        recyclerView = findViewById(R.id.see_book_list);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(ViewBooksActivity.this, 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(ViewBooksActivity.this, 6));
        }


        progressDialog = new ProgressDialog(ViewBooksActivity.this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);


        if(savedInstanceState == null || !savedInstanceState.containsKey("books")) {
            bookList = new ArrayList<>();
            adapter = new BookAdapter(ViewBooksActivity.this, bookList, progressDialog);
            recyclerView.setAdapter(adapter);
            getBooksList("");
        }
        else {
            bookList = savedInstanceState.getParcelableArrayList("books");
            adapter = new BookAdapter(ViewBooksActivity.this, bookList, progressDialog);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Toolbar :: Transparent
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setTitle(intent.getStringExtra("typeCategory"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initScrollListener();

        searchText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    private Timer timer=new Timer();
                    private final long DELAY = 1000; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {

                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(!TextUtils.isEmpty(searchText.getText().toString())){
                                                    getBooksList(searchText.getText().toString());
                                                }
                                            }
                                        });

                                    }
                                },
                                DELAY
                        );
                    }
                }
        );

    }

    private void getBooksList(String Search) {
        progressDialog.show();

        if(!TextUtils.isEmpty(Search)){
            bookList.clear();
            page = 1;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("topic", intent.getStringExtra("typeCategory"));
        map.put("search", Search);
        map.put("page", String.valueOf(page));

        Call<JsonObject> call = ApiService.getApiInstance().getBooks(map);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.hide();
                if (response.code() == 200) {

                    try {
                        String responseString = response.body().toString();

                        Log.e("onResponseString ", responseString);
                        JSONObject jsonObject = new JSONObject(responseString);

                        JSONArray jsonArrayResponse = jsonObject.getJSONArray("results");
                        for (int i = 0; i < jsonArrayResponse.length(); i++) {
                            JSONObject jsonObjectResponse = jsonArrayResponse.getJSONObject(i);

                            BookDetails book = new BookDetails();
                            book.setNext(jsonObject.getString("next"));


                            JSONArray author = jsonObjectResponse.getJSONArray("authors");
                            for (int j = 0; j < author.length(); j++) {
                                JSONObject name = author.getJSONObject(j);
                                book.setName(name.getString("name"));
                            }
                            JSONObject format = jsonObjectResponse.getJSONObject("formats");
                            try {
                                book.setImage(format.getString("image/jpeg"));
                            }catch (Exception e){}
                            try {
                                book.setText(format.getString("text/plain"));
//                                book.setText(format.getString("text/plain; charset=us-ascii"));
                            }catch (Exception e){}
                            book.setTitle(jsonObjectResponse.getString("title"));
                            bookList.add(book);

                        }
                        adapter.notifyDataSetChanged();
                        isLoading = false;

                    } catch (JSONException e) {
                        Log.e("onResponseExcept ", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ViewBooksActivity.this, "Server error please try again..", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.hide();
                Log.e("onResponseListFailed ", t.getMessage());
            }
        });
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == bookList.size() - 1) {
                        //bottom of list!
                        page++;
                        if(TextUtils.isEmpty(searchText.getText().toString())){
                            getBooksList("");
                        }else{
                            getBooksList(searchText.getText().toString());
                        }

                        isLoading = true;
                    }
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("books", bookList);
        super.onSaveInstanceState(outState);
    }



}
