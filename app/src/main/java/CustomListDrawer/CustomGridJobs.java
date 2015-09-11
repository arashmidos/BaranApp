package CustomListDrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.baran.MainActivity;
import org.baran.R;
import org.baran.model.Jobs;

import java.util.List;

import loadimage.ImageLoader;


/**
 * Created by Vafa on 6/8/15.
 */
public class CustomGridJobs extends BaseAdapter
{


    ImageLoader loader;
    private Context mContext;
    private List<Jobs> listi;


    public CustomGridJobs(Context c, List<Jobs> listi)
    {
        mContext = c;
        this.listi = listi;
        loader = new ImageLoader(c);

    }

    @Override
    public int getCount()
    {

        return listi.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    private class ViewHolder
    {
        Jobs c;
        TextView txtViewTitle;
        ImageView category_image;
        ImageView like;
        int n = 0;
        int actionBarHeight;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

        if (convertView == null)
        {

            convertView = inflater.inflate(R.layout.category_grid_list, null);
            holder = new ViewHolder();

            holder.category_image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.like = (ImageView) convertView.findViewById(R.id.imglikecategory);
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.textView34);
            Typeface font = Typeface.createFromAsset(mContext.getAssets(), "nazanin.ttf");
            holder.txtViewTitle.setTypeface(font);


            convertView.setTag(holder);

        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

//        ArrayList<String> m = new ArrayList<String>();
        final Jobs m = listi.get(position);
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            holder.actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
        }


        if (position % 2 == 0)
        {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.category_image.getLayoutParams();

            lp.setMargins(0, 15, 20, 0);

            holder.category_image.setLayoutParams(lp);
            holder.category_image.getLayoutParams().height = (int) ((screenHeight - holder.actionBarHeight) * 0.4);
            holder.category_image.getLayoutParams().width = (int) (screenWidth * 0.49);
            holder.category_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else
        {

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.category_image.getLayoutParams();

            lp.setMargins(20, 15, 0, 0);
            holder.category_image.setLayoutParams(lp);
            holder.category_image.getLayoutParams().height = (int) ((screenHeight - holder.actionBarHeight) * 0.4);
            holder.category_image.getLayoutParams().width = (int) (screenWidth * 0.49);
            holder.category_image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }

        loader.DisplayImage("sddasd", holder.category_image, 200);

        holder.txtViewTitle.setText(m.getJobsName());
        holder.c = m;
        holder.like.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {

                holder.n++;

                if (holder.n == 1 || holder.n == 3)
                {
                    new CountDownTimer(1500, 1000)
                    {

                        @Override
                        public void onTick(long arg0)
                        {

                        }

                        @Override
                        public void onFinish()
                        {

                            if (holder.n == 2)
                            {

                                //double click anjam shod
                                holder.like.setImageResource(R.drawable.like);

                                m.addToFavourite();
                                Log.w("d", "doublecliked");


                            } else if (holder.n == 4)
                            {
                                holder.like.setImageResource(R.drawable.unlike);
                                holder.n = 0;
                                Log.w("d", "doubleclikeddddddd");
                            }

                        }
                    }.start();
                }

            }
        });


        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ViewHolder holder2 = (ViewHolder) v.getTag();
                Jobs temp = holder2.c;
                ((MainActivity) mContext).showJobsDetail(temp);

            }
        });
        return convertView;
    }
}
