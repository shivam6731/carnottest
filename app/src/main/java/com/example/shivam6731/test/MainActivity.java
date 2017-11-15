package com.example.shivam6731.test;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    TextView a1,a2,a3,a4,b1,b2,b3,b4,c1,c2,c3,c4,d1,d2,d3,d4;
    String q1,q2,w1,w2,e1,e2,r1,r2;
     long ss1,ss2,ss3,ss4,se1,se2,se3,se4;
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);

        a1=(TextView)findViewById(R.id.a1);
        a2=(TextView)findViewById(R.id.a2);
        a3=(TextView)findViewById(R.id.a3);
        a4=(TextView)findViewById(R.id.a4);
        b1=(TextView)findViewById(R.id.b1);
        b2=(TextView)findViewById(R.id.b2);
        b3=(TextView)findViewById(R.id.b3);
        b4=(TextView)findViewById(R.id.b4);
        c1=(TextView)findViewById(R.id.c1);
        c2=(TextView)findViewById(R.id.c2);
        c3=(TextView)findViewById(R.id.c3);
        c4=(TextView)findViewById(R.id.c4);
        d1=(TextView)findViewById(R.id.d1);
        d2=(TextView)findViewById(R.id.d2);
        d3=(TextView)findViewById(R.id.d3);
        d4=(TextView)findViewById(R.id.d4);

        dd1=new databasehelper1(this);
        dd2=new databasehelper2(this);
        dd3=new databasehelper3(this);
        dd4=new databasehelper4(this);
       //boolean x= pingUrl("https://jsonplaceholder.typicode.com/comments");
        new UpdateTask().execute();
       // text();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                k=1;
                new UpdateTask().execute();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                k=2;
                new UpdateTask().execute();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                k=3;
                new UpdateTask().execute();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                k=4;
                new UpdateTask().execute();
            }
        });

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
            else if(k==1)
            {
                ping("https://jsonplaceholder.typicode.com/comments",1);
            }
            else if(k==2)
            {
                ping("https://jsonplaceholder.typicode.com/photos",2);
            }
            else if(k==3)
            {
                ping("https://jsonplaceholder.typicode.com/todos",3);
            }
            else if(k==4)
            {
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

                if(c==1)
                {
                    q1=startTime+"";
                    q2=endTime+"";
                    ss1=System.currentTimeMillis();
                    getdata(uri,c);
                    se1=System.currentTimeMillis();
                }
                else  if(c==2)
                {
                    w1=startTime+"";
                    w2=endTime+"";
                    ss2=System.currentTimeMillis();
                    getdata(uri,c);
                    se2=System.currentTimeMillis();
                }
                else  if(c==3)
                {
                    e1=startTime+"";
                    e2=endTime+"";
                    ss3=System.currentTimeMillis();
                    getdata(uri,c);
                    se3=System.currentTimeMillis();
                }
                else  if(c==4)
                {
                    r1=startTime+"";
                    r2=endTime+"";
                    ss4=System.currentTimeMillis();
                    getdata(uri,c);
                    se4=System.currentTimeMillis();
                }
                System.out.println(q1+q2+w1+w2+e1+e2+r1+r2);
                //System.out.println("Ping to "+startTime+" "+endTime+"" +" was success");

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
        text();
    }
    public void ADDDATA2(String ii,String ii1,String ii2,String ii3,String ii4)
    {

        dd2.addData(ii,ii1,ii2,ii3,ii4);
        text();
    }
    public void ADDDATA3(String ii,String ii1,String ii2,String ii3)
    {

        dd3.addData(ii,ii1,ii2,ii3);
        text();
    }
    public void ADDDATA4(String ii,String ii1,String ii2,String ii3)
    {

        dd4.addData(ii,ii1,ii2,ii3);
        text();
    }
    void text()
    {

        if(k==0) {
            a1.setText(q1 + "");
            a2.setText(q2 + "");
            a3.setText(ss1 + "");
            a4.setText(se1 + "");

            b1.setText(w1 + "");
            b2.setText(w2 + "");
            b3.setText(ss2 + "");
            b4.setText(se2 + "");

            c1.setText(e1 + "");
            c2.setText(e2 + "");
            c3.setText(ss3 + "");
            c4.setText(se3 + "");


            d1.setText(r1 + "");
            d2.setText(r2 + "");
            d3.setText(ss4 + "");
            d4.setText(se4 + "");
        }
        else if(k==1)
        {
            a1.setText(q1 + "");
            a2.setText(q2 + "");
            a3.setText(ss1 + "");
            a4.setText(se1 + "");
        }
        else if(k==2)
        {
            b1.setText(w1 + "");
            b2.setText(w2 + "");
            b3.setText(ss2 + "");
            b4.setText(se2 + "");
        }
        else if(k==3)
        {
            c1.setText(e1 + "");
            c2.setText(e2 + "");
            c3.setText(ss3 + "");
            c4.setText(se3 + "");
        }
        else if(k==4)
        {
            d1.setText(r1 + "");
            d2.setText(r2 + "");
            d3.setText(ss4 + "");
            d4.setText(se4 + "");
        }

    }
}
