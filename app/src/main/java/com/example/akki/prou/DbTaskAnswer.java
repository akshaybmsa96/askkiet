package com.example.akki.prou;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Akki on 18-04-2017.
 */

public class DbTaskAnswer extends AsyncTask<String,Void,String> {

        Context ctx;

        DbTaskAnswer(Context ctx)
        {
        this.ctx=ctx;
        }

@Override
protected String doInBackground(String... params) {

        String ip_url="http://shivtravel.ueuo.com/prou/reply.php";
     //   String ip_url="http://10.0.3.2/prou/reply.php";


        String qid=params[0];
        String text=params[1];
        String name=params[2];
        String uid=params[3];

        try {


        URL url = new URL(ip_url);
        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream OS=httpURLConnection.getOutputStream();
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
        String data= URLEncoder.encode("qid","UTF-8")+"="+URLEncoder.encode(qid,"UTF-8")+"&"+
        URLEncoder.encode("text","UTF-8")+"="+URLEncoder.encode(text,"UTF-8")+"&"+
        URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(uid,"UTF-8")+"&"+
        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
        br.write(data);
        br.flush();
        br.close();
        OS.close();
        InputStream IS=httpURLConnection.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
        String res="";
        String line="";
        while ((line=bufferedReader.readLine())!=null)
        {
        res+=line;
        }
        bufferedReader.close();
        IS.close();
        httpURLConnection.disconnect();
        return  res;

        }

        catch (Exception e) {
        e.printStackTrace();
        }


        return "Error";

        }

@Override
protected void onPreExecute() {
        super.onPreExecute();
        }

@Override
protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        }
