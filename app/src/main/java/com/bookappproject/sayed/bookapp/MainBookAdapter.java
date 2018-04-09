package com.bookappproject.sayed.bookapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainBookAdapter extends RecyclerView.Adapter<MainBookAdapter.MainBookHolder>{
    Context context;
    ArrayList<MainBookObject>bookObjects;

    public MainBookAdapter(Context context, ArrayList<MainBookObject> bookObjects){
        this.context = context;
        this.bookObjects = bookObjects;
    }

    @NonNull
    @Override
    public MainBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_book_rv, parent, false);

        return new MainBookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainBookHolder holder, int position) {
        holder.imageView.setImageResource(bookObjects.get(position).getImage());
        holder.textView.setText(bookObjects.get(position).getBookTitle());
    }

    @Override
    public int getItemCount() {
        return bookObjects.size();
    }

    class MainBookHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        MainBookHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_book_IV_Adapter);
            textView = itemView.findViewById(R.id.main_book_TV_adapter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(context, ""+bookObjects.get(position).getBookId(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
