package com.example.ruslan.mapapplication;

/**
 * Created by ruslan on 21.02.15.
 */
import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // нужно проинициализировать ImageLoader перед его использованием
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }
}