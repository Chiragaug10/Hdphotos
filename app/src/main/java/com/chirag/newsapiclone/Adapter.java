package com.chirag.newsapiclone;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<layout> mylist;

    public Adapter(List<layout> mylist) {
        this.mylist = mylist;
    }

    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        String textView = mylist.get(position).getTextView();
        String imageView = mylist.get(position).getImageView();
        String url = mylist.get(position).getUrl();
        holder.setData(textView, imageView, url);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        TextView url;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            url=itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(this::onClick);

        }

        public void setData(String textViews, String ImageViews, String url) {
            this.textView.setText(textViews);
            Picasso.get().load(ImageViews).placeholder(R.drawable.img).into(imageView);
            this.url.setText(url);

        }

        public void onClick(View view) {
            int position = this.getAdapterPosition();
            String s=url.getText().toString();
            if (position == position) {
                String url = s;
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(itemView.getContext(), Uri.parse(url));
            }

        }
    }
}

