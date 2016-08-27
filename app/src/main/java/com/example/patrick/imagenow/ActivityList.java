package com.example.patrick.imagenow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by PATRICK on 7/4/2016.
 */
public class ActivityList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] names;
    private Activity context;

    public ActivityList(Activity context, String[] ids, String[] names) {
        super(context, R.layout.activity_list_view, ids);
        this.context = context;
        this.ids = ids;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_list_view, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.locationTextView);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.activityTextView);

        textViewId.setText(ids[position]);
        textViewName.setText(names[position]);

        return listViewItem;
    }
}
