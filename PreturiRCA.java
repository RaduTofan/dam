package com.example.project_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class PreturiRCA extends AppCompatActivity {

    private List<CompanieAsigurari> companii;
    private float[] comisioane = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preturi_rc);

        GetJSON getJSON = new GetJSON() {
            @Override
            protected void onPostExecute(float[] floats) {
                for (int i = 0; i < floats.length; i++) {
                    comisioane[i] = floats[i];
                }
            }
        };
        getJSON.execute("https://api.myjson.com/bins/176pbo");

        double comision = 1;
        Intent intent = getIntent();
        Vehicul v = intent.getParcelableExtra("Date Vehicul");
        int daune = intent.getIntExtra("Daune", 0);
        final String tipPers = intent.getStringExtra("TipPers");


        if (v.getTip_combustibil().contains("electric")) {
            comision += 0.05;
        }
        if (v.getTip_combustibil().contains("benzina")) {
            comision += 0.15;
        } else if (v.getTip_combustibil().contains("motorina")) {
            comision += 0.12;
        }
        if (v.getTip_combustibil().contains("GPL")) {
            comision += 0.1;
        }
        if (daune >= 1 && daune < 3) {
            comision += 0.1;
        } else if (daune >= 3) {
            comision += 0.2;
        }

        TextView tv = findViewById(R.id.preturi_info);
        tv.setText("Dvs. sunteti " + tipPers +
                    " aveti tip combustibil: " + v.getTip_combustibil() +
                    ", daune provocate: " + daune);




        companii = new ArrayList<>();
        if (v.getAn_fabricatie() > 1990) {
            if (tipPers.contains("fizica")) {
                companii.add(new CompanieAsigurari("Generali", (float) (300 * (comision+0.1))));
            }else if (tipPers.contains("juridica")) {
                companii.add(new CompanieAsigurari("Generali", (float) (300 * (comision+0.2))));
            }
        }
        if (tipPers.contains("fizica")) {
            companii.add(new CompanieAsigurari("City Insurance", (float) (439 * (comision+0.09))));
        }else if (tipPers.contains("juridica")) {
            companii.add(new CompanieAsigurari("City Insurance", (float) (439 * (comision+0.19))));
        }
        if (tipPers.contains("fizica")) {
        companii.add(new CompanieAsigurari("UNIQUA", (float) (576 * (comision+0.08))));
        }else if (tipPers.contains("juridica")) {
            companii.add(new CompanieAsigurari("UNIQUA", (float) (576 * (comision+0.18))));
        }
        if (v.getAn_fabricatie() > 2005) {
            if (tipPers.contains("fizica")) {
            companii.add(new CompanieAsigurari("Allianz", (float) (983 * (comision+0.11))));
            }else if (tipPers.contains("juridica")) {
                companii.add(new CompanieAsigurari("Allianz", (float) (983 * (comision+0.22))));

            }
        }
        if (tipPers.contains("fizica")) {
        companii.add(new CompanieAsigurari("Omniasig", (float) (342 * (comision+0.06))));
        }else if (tipPers.contains("juridica")) {
            companii.add(new CompanieAsigurari("Omniasig", (float) (342 * (comision+0.15))));
        }


        ListView lv = findViewById(R.id.id_lv_RCA);

        RCAadapter adapter = new RCAadapter(this, R.layout.items_layout, companii);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), LivrareRCA.class);
                it.putExtra("Livrare", companii.get(i));
                it.putExtra("TipulPers", tipPers);
                startActivity(it);
                finish();

            }
        });



        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                ProgressBar progressBar=findViewById(R.id.id_bara_progres);
                progressBar.setVisibility(View.GONE);
                Button btnComisioane=findViewById(R.id.afiseazaComisioane);
                btnComisioane.setVisibility(View.VISIBLE);
                Toast.makeText(PreturiRCA.this, "JSON primit cu succes. Puteti vedea comisioanele", Toast.LENGTH_SHORT).show();
            }
        }.start();


    }




    public void afiseazaComisioane(View view) {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //exista conexiune
            AlertDialog.Builder builder = new AlertDialog.Builder(PreturiRCA.this);

            builder.setCancelable(true);
            builder.setMessage("Generali" + System.getProperty("line.separator") + "Pret: " + (comisioane[0])
                    + System.getProperty("line.separator") + "An de fabricatie minim: " + new DecimalFormat("####").format(comisioane[1])
                    + System.getProperty("line.separator") + "Comision persoana fizica: " + comisioane[2]
                    + System.getProperty("line.separator") + "Comision persoana juridica: " + comisioane[3]
                    + System.getProperty("line.separator")
                    + System.getProperty("line.separator") + "CityInsurance" + System.getProperty("line.separator") + "Pret: " + (comisioane[4])
                    + System.getProperty("line.separator") + "An de fabricatie minim: " + new DecimalFormat("####").format(comisioane[5])
                    + System.getProperty("line.separator") + "Comision persoana fizica: " + comisioane[6]
                    + System.getProperty("line.separator") + "Comision persoana juridica: " + comisioane[7]
                    + System.getProperty("line.separator")
                    + System.getProperty("line.separator") + "UNIQUA" + System.getProperty("line.separator") + "Pret: " + (comisioane[8])
                    + System.getProperty("line.separator") + "An de fabricatie minim: " + new DecimalFormat("####").format(comisioane[9])
                    + System.getProperty("line.separator") + "Comision persoana fizica: " + comisioane[10]
                    + System.getProperty("line.separator") + "Comision persoana juridica: " + comisioane[11]
                    + System.getProperty("line.separator")
                    + System.getProperty("line.separator") + "Allianz" + System.getProperty("line.separator") + "Pret: " + (comisioane[12])
                    + System.getProperty("line.separator") + "An de fabricatie minim: " +new DecimalFormat("####").format( comisioane[13])
                    + System.getProperty("line.separator") + "Comision persoana fizica: " + comisioane[14]
                    + System.getProperty("line.separator") + "Comision persoana juridica: " + comisioane[15]
                    + System.getProperty("line.separator")
                    + System.getProperty("line.separator") + "Omniasig" + System.getProperty("line.separator") + "Pret: " + (comisioane[16])
                    + System.getProperty("line.separator") + "An de fabricatie minim: " + new DecimalFormat("####").format(comisioane[17])
                    + System.getProperty("line.separator") + "Comision persoana fizica: " + comisioane[18]
                    + System.getProperty("line.separator") + "Comision persoana juridica: " + comisioane[19]);

            builder.setNegativeButton("Inchide", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
        else {
            Toast.makeText(this, "Nu aveti conexiune la internet!", Toast.LENGTH_SHORT).show();
        }


    }
}
