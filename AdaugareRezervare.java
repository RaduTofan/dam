package ro.ase.exam03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdaugareRezervare extends AppCompatActivity {

    private List<Rezervare> lista =new ArrayList<>();
    private int pozitie=-9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_rezervare);


        Intent it=getIntent();
        if(it.getParcelableArrayListExtra("modificaLista")!=null){
            lista=it.getParcelableArrayListExtra("modificaLista");
            pozitie=it.getIntExtra("pozitie",-8);
            EditText et1=findViewById(R.id.et_id);
            et1.setText(String.valueOf(lista.get(pozitie).getIdRezervare()));
            lista.remove(pozitie);
        }
    }

    public void salveaza(View view) {
        if(pozitie>0) {
            EditText et1 = findViewById(R.id.et_id);
            int id = Integer.parseInt(et1.getText().toString());
            EditText et2 = findViewById(R.id.et_nume);
            String nume = et2.getText().toString();
            Spinner spinner = findViewById(R.id.spinner);
            String tipCamera = spinner.getSelectedItem().toString();

            DatePicker datePicker = findViewById(R.id.datepicker);
            Calendar c = Calendar.getInstance();
            c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            String data = dateFormat.format(c.getTime());


            Rezervare rezervare = new Rezervare(id, nume, tipCamera, data);
            Toast.makeText(this, "" + rezervare.toString(), Toast.LENGTH_SHORT).show();
            lista.add(pozitie,rezervare);

            Intent it = new Intent();
            it.putParcelableArrayListExtra("listaRezervari", (ArrayList<? extends Parcelable>) lista);
            setResult(RESULT_OK, it);
        }else{
            EditText et1 = findViewById(R.id.et_id);
            int id = Integer.parseInt(et1.getText().toString());
            EditText et2 = findViewById(R.id.et_nume);
            String nume = et2.getText().toString();
            Spinner spinner = findViewById(R.id.spinner);
            String tipCamera = spinner.getSelectedItem().toString();

            DatePicker datePicker = findViewById(R.id.datepicker);
            Calendar c = Calendar.getInstance();
            c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            String data = dateFormat.format(c.getTime());


            Rezervare rezervare = new Rezervare(id, nume, tipCamera, data);
            Toast.makeText(this, "" + rezervare.toString(), Toast.LENGTH_SHORT).show();
            lista.add(rezervare);

            Intent it = new Intent();
            it.putParcelableArrayListExtra("listaRezervari", (ArrayList<? extends Parcelable>) lista);
            setResult(RESULT_OK, it);
        }


    }
}
