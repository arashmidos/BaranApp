package CustomListDrawer;


import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.baran.R;

public class CustomListNav extends ArrayAdapter<String>{


    private final Activity context;

    //معرفی نام و تصاویر از اکتیوتی
    private final String[] web;
    private final Integer[] imageId;


    public CustomListNav(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.list_file, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_file, null, true);
        Typeface font = Typeface.createFromAsset(context.getAssets(),"nazanin.ttf");
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView2);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        txtTitle.setTypeface(font);
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }

}