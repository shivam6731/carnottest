package com.example.shivam6731.test;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    databasehelper1 dd1 = null;
    databasehelper2 dd2=null;
    databasehelper3 dd3=null;
    databasehelper4 dd4=null;
    int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dd1=new databasehelper1(this);
        dd2=new databasehelper2(this);
        dd3=new databasehelper3(this);
        dd4=new databasehelper4(this);
       //boolean x= pingUrl("https://jsonplaceholder.typicode.com/comments");
        new UpdateTask().execute();

    }
    private class UpdateTask extends AsyncTask<String, String,String> {
        protected String doInBackground(String... urls) {
            if(k==0)
            {
                ping("https://jsonplaceholder.typicode.com/comments",1);
                ping("https://jsonplaceholder.typicode.com/photos",2);
               ping("https://jsonplaceholder.typicode.com/todos",3);
                ping("https://jsonplaceholder.typicode.com/posts",4);
            }


            return null;
        }

    }
    void ping(String uri,int c)
    {
        try {
            final URL url = new URL(uri);
            final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
            final long startTime = System.currentTimeMillis();
            urlConn.connect();
            final long endTime = System.currentTimeMillis();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Time (ms) : " + (endTime - startTime));
                System.out.println("Ping to "+startTime+" "+endTime+"" +" was success");
                getdata(uri,c);
            }
        } catch (final MalformedURLException e1) {
            e1.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    void getdata(String uri, final int c)
    {
        final StringRequest stringRequest=new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray heroArray = new JSONArray(response);


                           for (int i = 0; i < 2; i++) {
                                JSONObject heroObject = (JSONObject)heroArray.get(i);
                               if(c==1){
                               String ii=heroObject.optString("PostId");
                               String ii1=heroObject.optString("id");
                               String ii2=heroObject.optString("email");
                               String ii3=heroObject.optString("name");
                               String ii4=heroObject.optString("body");
                               // Toast.makeText(MainActivity.this,ii,Toast.LENGTH_LONG).show();
                               ADDDATA(ii,ii1,ii2,ii3,ii4);}
                               else if(c==2)
                               {
                                   String ii=heroObject.optString("albumId");
                                   String ii1=heroObject.optString("id");
                                   String ii2=heroObject.optString("title");
                                   String ii3=heroObject.optString("url");
                                   String ii4=heroObject.optString("thumbnailUrl");
                                   // Toast.makeText(MainActivity.this,ii,Toast.LENGTH_LONG).show();
                                   ADDDATA2(ii,ii1,ii2,ii3,ii4);
                               }
                               else if(c==3)
                               {
                                   String ii=heroObject.optString("userId");
                                   String ii1=heroObject.optString("id");
                                   String ii2=heroObject.optString("title");
                                   String ii3=heroObject.optString("completed");

                                   // Toast.makeText(MainActivity.this,ii,Toast.LENGTH_LONG).show();
                                   ADDDATA3(ii,ii1,ii2,ii3);
                               }
                               else if(c==4)
                               {

                                   String ii=heroObject.optString("userId");
                                   String ii1=heroObject.optString("id");
                                   String ii2=heroObject.optString("title");
                                   String ii3=heroObject.optString("body");

                                   // Toast.makeText(MainActivity.this,ii,Toast.LENGTH_LONG).show();
                                   ADDDATA4(ii,ii1,ii2,ii3);
                               }



                            }



                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,"bb",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    public void ADDDATA(String ii,String ii1,String ii2,String ii3,String ii4)
    {

        dd1.addData(ii,ii1,ii2,ii3,ii4);
    }
    public void ADDDATA2(String ii,String ii1,String ii2,String ii3,String ii4)
    {

        dd2.addData(ii,ii1,ii2,ii3,ii4);
    }
    public void ADDDATA3(String ii,String ii1,String ii2,String ii3)
    {

        dd3.addData(ii,ii1,ii2,ii3);
    }
    public void ADDDATA4(String ii,String ii1,String ii2,String ii3)
    {

        dd4.addData(ii,ii1,ii2,ii3);
    }
}
