package com.sumit.newsapp.Fragement;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumit.newsapp.R;
import com.sumit.newsapp.ViewNewsActivity;


public class NewsTypeFragment extends Fragment {

    public NewsTypeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_type, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view){
        CardView fiction = view.findViewById(R.id.fictionType);
        CardView drama = view.findViewById(R.id.dramaType);
        CardView humor = view.findViewById(R.id.humorType);
        CardView politics = view.findViewById(R.id.politicsType);
        CardView philosophy = view.findViewById(R.id.philosophyType);
        CardView history = view.findViewById(R.id.historyType);
        CardView adventure = view.findViewById(R.id.adventureType);

        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Fiction");
                startActivity(intent);
            }
        });

        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Drama");
                startActivity(intent);
            }
        });

        humor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Humor");
                startActivity(intent);
            }
        });

        politics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Politics");
                startActivity(intent);
            }
        });

        philosophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Philosophy");
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "History");
                startActivity(intent);
            }
        });

        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewNewsActivity.class);
                intent.putExtra("typeCategory", "Adventure");
                startActivity(intent);
            }
        });
    }
}