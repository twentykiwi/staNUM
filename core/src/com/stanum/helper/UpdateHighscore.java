package com.stanum.helper;

import com.badlogic.gdx.Gdx;

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

/**
 * Created by varunrana on 24/12/17.
 */

public class UpdateHighscore {
    String type;
    String score_url, fetch_score;
    String sc;

    public UpdateHighscore(String type){
        this.type = type;
        score_url = "http://twentykiwi.com/score.php";
        fetch_score ="http://twentykiwi.com/score-fetch.php";
    }
    public String getScore(){
        getResult();
        return sc;
    }
    public String getResult(){
        try{
            URL url = new URL(fetch_score);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
        String data = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            data += line;
        }
        sc = data;
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        return data;
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
    }

    public String update(int highscore){
        String score = Integer.toString(highscore);
        if (type.equals("score")) {
            try {
                URL url = new URL(score_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(score, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
