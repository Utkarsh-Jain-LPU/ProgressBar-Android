package com.utkarsh.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> list;
    private ProgressBar progressBar;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        progressBar = findViewById(R.id.progressbar);
        RecyclerView recyclerView = findViewById(R.id.listview);

        new MyTask().execute();

        adapter = new Adapter(MainActivity.this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    class MyTask extends AsyncTask<Void,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setProgress(0);
            progressBar.setMax(10);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {

            for(int i=1;i<=10;i++) {

                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("Item "+i);
            }
            return "Completed...";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressBar.setProgress(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }
}
