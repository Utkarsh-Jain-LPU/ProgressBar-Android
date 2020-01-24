package com.utkarsh.backgroundprocesses;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Noteholdet> {

    private Context context;
    private ArrayList<String> examplelist;

    Adapter(Context con, ArrayList<String> list) {
        context = con;
        examplelist = list;
    }

    @NonNull
    @Override
    public Noteholdet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.note_item,parent,false);
        return new Noteholdet(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Noteholdet holder, int position) {
        holder.data.setText(examplelist.get(position));
    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }

    class Noteholdet extends RecyclerView.ViewHolder {

        private TextView data;

        Noteholdet(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.data);
        }
    }
}
