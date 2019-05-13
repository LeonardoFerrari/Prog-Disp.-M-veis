package br.unicamp.v188309.aula10;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAstros extends AsyncTask<String,Void,String> {
    TextView textView;

    public MyAstros(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected void onPreExecute() {
        textView.append("####################### \n ");
        textView.append("Iniciando ViaCep \n ");
    }

    @Override
    protected String doInBackground(String... args) {


        HttpURLConnection httpURLConnection;
        try {
            /*
               Endereço que será acessado.
             */
            String HOST = "http://api.open-notify.org/astros.json";

        /*
          Abrindo uma conexão com o servidor
        */

            URL url = new URL(HOST);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
        /*
          Lendo a resposta do servidor
        */
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));


            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            Log.v("Erro", e.getMessage());
            return "Exception\n" + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String args) {
        // Via Cep

        try {
            Log.v("ARGS", args);
            JSONObject jsonObject = new JSONObject(args); //trata o json e permite utilizar seus métodos
            textView.append("   message:    "+  jsonObject.getString("message"));
            textView.append("   number: "+  jsonObject.getInt("number"));

            for(int count = 0; count < 6; count++){

                textView.append("chave" + jsonObject.getJSONArray("people").getJSONObject(count).getString("name")+ "\n");


            }


        } catch(JSONException e) {
            textView.append("ERRO: Não foi possível converter em JSONObject: " + args+"\n");
        }


    }
}


