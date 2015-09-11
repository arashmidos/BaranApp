package org.baran.Notifications;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.baran.R;

import UIActionBarDrawer.ActionBarSetup;

public class Notification_message extends ActionBarSetup
{

    private Context context;
    private ImageView logo_title;
    private TextView title;
    private TextView labeldate;
    private TextView date;
    private TextView detail_message;
    private VideoView video;
    private Button see_message;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null)
        {
            j = (int) b.get("type");
            if (j == 0)
            {
                setContentView(R.layout.activity_notification_message);
            } else if (j == 1)
            {
                setContentView(R.layout.video_message);
            }
        }


        setupactionbar();
        drawer();
        context = this;
        Typeface font = Typeface.createFromAsset(getAssets(), "nazanin.ttf");

        // activity_notification_message /////////////////////////////
        if (j == 0)
        {

            logo_title = (ImageView) findViewById(R.id.logo_img_message);
            title = (TextView) findViewById(R.id.txt_title_message);
            labeldate = (TextView) findViewById(R.id.labeldate);
            date = (TextView) findViewById(R.id.txtdatemessage);
            detail_message = (TextView) findViewById(R.id.txtdetail_notif);
            see_message = (Button) findViewById(R.id.btnseenotification);

            title.setTypeface(font);
            labeldate.setTypeface(font);
            date.setTypeface(font);
            detail_message.setTypeface(font);
            see_message.setTypeface(font);
        }

        ///**************************************////////////////////////


        // video_message /////////////////////////////

        else if (j == 1)
        {
            logo_title = (ImageView) findViewById(R.id.logo_img_message);
            title = (TextView) findViewById(R.id.txt_title_message);
            labeldate = (TextView) findViewById(R.id.labeldate);
            date = (TextView) findViewById(R.id.txtdatemessage);
            see_message = (Button) findViewById(R.id.btnseenotification);
            video = (VideoView) findViewById(R.id.video_notif);

            title.setTypeface(font);
            labeldate.setTypeface(font);
            date.setTypeface(font);
            see_message.setTypeface(font);

        }
        ///**************************************////////////////////////


    }


}
