package com.example.project_1;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJSON extends AsyncTask<String,Void,float[]> {
    @Override
    protected float[] doInBackground(String... strings) {
                float[] result=new float[20];
                int i=0;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream is = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line=null;
            StringBuilder builder = new StringBuilder();

            while((line=reader.readLine())!=null){
                builder.append(line);

            }

            String textTotal=builder.toString();
            JSONObject obiect = new JSONObject(textTotal);
            JSONObject generali=obiect.getJSONObject("Generali");
            result[i]=(float)generali.getDouble("pret"); i++;
            result[i]=(float)generali.getDouble("an_fabricatie_minim"); i++;
            result[i]=(float)generali.getDouble("comision_pers_fizice"); i++;
            result[i]=(float)generali.getDouble("comision_pers_juridica"); i++;
            JSONObject CityInsurance=obiect.getJSONObject("CityInsurance");
            result[i]=(float)CityInsurance.getDouble("pret"); i++;
            result[i]=(float)CityInsurance.getDouble("an_fabricatie_minim"); i++;
            result[i]=(float)CityInsurance.getDouble("comision_pers_fizice"); i++;
            result[i]=(float)CityInsurance.getDouble("comision_pers_juridica"); i++;
            JSONObject UNIQUA=obiect.getJSONObject("UNIQUA");
            result[i]=(float)UNIQUA.getDouble("pret"); i++;
            result[i]=(float)UNIQUA.getDouble("an_fabricatie_minim"); i++;
            result[i]=(float)UNIQUA.getDouble("comision_pers_fizice"); i++;
            result[i]=(float)UNIQUA.getDouble("comision_pers_juridica"); i++;
            JSONObject Allianz=obiect.getJSONObject("Allianz");
            result[i]=(float)Allianz.getDouble("pret"); i++;
            result[i]=(float)Allianz.getDouble("an_fabricatie_minim"); i++;
            result[i]=(float)Allianz.getDouble("comision_pers_fizice"); i++;
            result[i]=(float)Allianz.getDouble("comision_pers_juridica"); i++;
            JSONObject Omniasig=obiect.getJSONObject("Omniasig");
            result[i]=(float)Omniasig.getDouble("pret"); i++;
            result[i]=(float)Omniasig.getDouble("an_fabricatie_minim"); i++;
            result[i]=(float)Omniasig.getDouble("comision_pers_fizice"); i++;
            result[i]=(float)Omniasig.getDouble("comision_pers_juridica");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }


}
