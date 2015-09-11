package org.baran;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Arashmidos on 9/10/2015.
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ActiveAndroid.initialize(this);

    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
