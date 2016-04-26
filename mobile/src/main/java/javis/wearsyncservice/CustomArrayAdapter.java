package javis.wearsyncservice;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter {
    /**
     * Custom array adapter for the sliding menu for icon+text
     */

    private Activity activity;
    List elements; // holds all the elements that occupy rows in the ListView


    public CustomArrayAdapter(Activity activity, int resource, List objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.elements = objects;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View v = convertView;
        final View v;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        int layoutResource = 0; // determined by view type
        int viewType = getItemViewType(position);

        v = inflater.inflate(R.layout.sliding_row_layout, parent, false);

        String txt = (String) elements.get(position);
        //implicit understanding that all the elements are String types.
        TextView option_name = (TextView)v.findViewById(R.id.Name);// exists in both layouts!!
        option_name.setText(txt);
        ImageView option_icon = (ImageView)v.findViewById(R.id.Icon);
        int correct_img = set_correct_img(txt);
        option_icon.setImageResource(correct_img);
        return v;
    }

    private int set_correct_img(String person_name)
    {
        switch(person_name)
        {
            case "DASHBOARD": return R.drawable.dashboardicon;
            case "NOTIFICATIONS": return R.drawable.notificationicon;
            case "SETTINGS": return R.drawable.settingsicon;
        }
        return -1;//Error case
    }

}
