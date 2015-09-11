package UIActionBarDrawer;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.baran.Notifications.Notification_message;
import org.baran.R;

import CustomListDrawer.CustomListNav;


public class ActionBarSetup extends ActionBarActivity
{


    private String[] web = {
//            "ثبت نام",
            "ورود"
            , "تنظیمات"
            , "درباره ما"
    };
    Integer[] imageId = {

            R.drawable.help,
            R.drawable.news,
            R.drawable.about

    };
    /* private String [] parttwo = {"رهگیری سفارشات" ,"ورود", "سبد خرید"};
     Integer[] imageIdtwo = {
             R.drawable.help,
             R.drawable.news,
             R.drawable.about

     };*/
    private DrawerLayout mDrawerLayout;
    private ImageButton navbutton;
    private ListView mDrawerList;
    private Boolean test = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar);


        setupactionbar();
        // drawer();


    }


    public void setupactionbar()
    {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);


    }


    public void drawer()
    {


        Typeface font = Typeface.createFromAsset(getAssets(), "nazanin.ttf");
        TextView headeruser = (TextView) findViewById(R.id.txtheaderuser);
        headeruser.setTypeface(font);
        headeruser.setText("نام کاربری");
        navbutton = (ImageButton) findViewById(R.id.imageButton);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // ListView two = (ListView)findViewById(R.id.lefttwo);


        CustomListNav adapters = new CustomListNav(ActionBarSetup.this, web, imageId);
        mDrawerList.setAdapter(adapters);
       /* CustomListNav adaptertwo = new CustomListNav(ActionBarSetup.this, parttwo, imageIdtwo);
            two.setAdapter(adaptertwo);*/


        navbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (test)
                {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                    test = false;
                } else
                {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    test = true;
                }
            }
        });
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int post, long arg3)
            {

                switch (post)
                {
                    case 0:
                       registerUSer();

                        break;

                case 1:
                    int m = 1;

                    Intent ii = new Intent(ActionBarSetup.this, Notification_message.class);
                    ii.putExtra("type", m);
                    startActivity(ii);                        break;
/*
                    case 2:
                        startActivity(new Intent(ActionBarSetup.this, AboutUs.class));
                        break;
*/
                    default:
                        break;


                }

            }

        });


       /* two.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int post, long arg3) {

               *//* switch (post) {
                    case 0:
                        startActivity(new Intent(ActionBarSetup.this, Register.class));

                        break;




                    case 1:
                        startActivity(new Intent(ActionBarSetup.this, News.class));
                        break;

                    case 2:
                        startActivity(new Intent(ActionBarSetup.this, AboutUs.class));
                        break;

                    default:
                        break;


                }*//*

            }

        });*/

    }

    private void registerUSer()
    {

    }


}
