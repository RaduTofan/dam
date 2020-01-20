package ro.ase.exam03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Rezervare> listaRez ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.despre){
            DateFormat format = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            Date data = new Date();
            String dataOra=format.format(data);
            String nume=getResources().getString(R.string.numeAutor);
            Toast.makeText(this, nume+" "+dataOra, Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.menu_adauga_rezervare){
            Intent it = new Intent(getApplicationContext(),AdaugareRezervare.class);
            startActivityForResult(it,101);


        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(resultCode==RESULT_OK){
                listaRez= new ArrayList<>();
                listaRez=data.getParcelableArrayListExtra("listaRezervari");
                if(listaRez!=null) {
                    ListView lv = findViewById(R.id.id_listview);
                    final ArrayAdapter<Rezervare> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaRez);
                    lv.setAdapter(adapter);


                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent it = new Intent(getApplicationContext(),AdaugareRezervare.class);
                            it.putParcelableArrayListExtra("modificaLista", (ArrayList<? extends Parcelable>) listaRez);
                            it.putExtra("pozitie",position);
                            startActivityForResult(it,101);

                        }
                    });


                    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(true);
                            builder.setMessage("are you sure?");
                            builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listaRez.remove(position);
                                    adapter.notifyDataSetChanged();
                                    dialog.cancel();
                                }
                            });

                            builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();

                            return true;
                        }
                    });



                }






            }
        }

    }
}
