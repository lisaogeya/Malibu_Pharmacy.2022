package com.example.malibupharmacy;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundHelper extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundHelper(Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        //String result="Test";
        if (type.equals("checkinsurance")){
            String loginUrl = "http://127.0.0.1/poriandroid/insurancedetails.php";
            try {
                String insurance = params[1];
                String policyNo = params[2];
                URL url = new URL(loginUrl);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                OutputStream outputStream = http.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("insurance", "UTF-8")+"="+URLEncoder.encode(insurance, "UTF-8")+ "&"
                        + URLEncoder.encode("clientpolicyno", "UTF-8")+"="+URLEncoder.encode(policyNo, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                http.setDoInput(true);
                InputStream inputStream = http.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
                String line="";
                String result="";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                http.disconnect();
                return result;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Hali");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("No Policy Found")){
            Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
        }
        else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
