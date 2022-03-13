package com.chirag.newsapiclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<layout> mylist;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue;
        editText = findViewById(R.id.Search);
        button = findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist = new ArrayList<>();
                String search = editText.getText().toString();
                String url = "";
                    url="https://pixabay.com/api/?key=22462062-3779c0fd00b3923a02c6dd67d&q="+search+"&image_type=photo&pretty=true";
                    editText.setText("");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String image = jsonObject.getString("largeImageURL");
                                String text = jsonObject.getString("likes");
                                String url=jsonObject.getString("pageURL");
                                mylist.add(new layout(image, text,url));

                            }

                            initrecyclerview();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);

            }

            private void initrecyclerview() {
                recyclerView = findViewById(R.id.recyclerview);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new Adapter(mylist);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        });

    }

}


