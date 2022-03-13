package com.chirag.newsapiclone;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class layout {
    public String getImageView() {
        return imageView;
    }

    public String getTextView() {
        return textView;
    }

    public String getUrl() {
        return url;
    }


    private String imageView;
    private String textView;
    private String url;


    layout(String image, String text,String url) {
        this.imageView = image;
        this.textView = text;
        this.url=url;
    }
}

