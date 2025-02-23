package com.example.prypoliza1.view;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prypoliza1.R;
import com.example.prypoliza1.database.DatabaseHelper;
import com.example.prypoliza1.model.Poliza;

import java.util.ArrayList;
import java.util.List;

public class ListarDatosActvity extends AppCompatActivity {

    ListView lsvListarDatos;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);

        lsvListarDatos = findViewById(R.id.lst_view);

        databaseHelper = new DatabaseHelper(this);

        mostrarDatos();
    }

    private void mostrarDatos() {
        Cursor cursor = databaseHelper.obtenerPolizas();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos almacenados", Toast.LENGTH_SHORT).show();
        }

        List<Poliza> polizas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Poliza poliza = new Poliza(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getDouble(6));
            polizas.add(poliza);
        }

        cursor.close();

        PolizaAdapter adapter = new PolizaAdapter(this, polizas, databaseHelper);
        lsvListarDatos.setAdapter(adapter);
    }

}