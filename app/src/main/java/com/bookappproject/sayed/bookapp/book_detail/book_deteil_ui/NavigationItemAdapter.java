package com.bookappproject.sayed.bookapp.book_detail.book_deteil_ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bookappproject.sayed.bookapp.R;
import com.bookappproject.sayed.bookapp.book_detail.Chapter;

import java.util.ArrayList;

public class NavigationItemAdapter extends ArrayAdapter<Chapter>{
    private Context context;
    private ArrayList<Chapter>chapters;

    public NavigationItemAdapter(@NonNull Context context, ArrayList<Chapter>chapters) {
        super(context, R.layout.custom_layout_for_nav_list, chapters);
        this.chapters = chapters;
        this.context = context;
    }

    public class ViewHolder{
        TextView chapterNameTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if (convertView==null){
            viewHolder = new ViewHolder();
            assert inflater != null;
            convertView = inflater.inflate(R.layout.custom_layout_for_nav_list, parent, false);
            viewHolder.chapterNameTV = convertView.findViewById(R.id.navItemTextId);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.chapterNameTV.setText(chapters.get(position).getChapterTitle());

        return convertView;
    }
}

