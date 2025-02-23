package com.example.prypoliza1.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prypoliza1.R;
import com.example.prypoliza1.controller.PolizaController;
import com.example.prypoliza1.database.DatabaseHelper;
import com.example.prypoliza1.model.Poliza;

import java.util.Arrays;
import java.util.List;

public class ActualizarPolizaActivity extends AppCompatActivity {

    EditText txtNombre, txtAccidentes, txtValorVehi;
    Spinner cboModelo, cboEdad;
    Button btnActualizar;
    DatabaseHelper databaseHelper;
    Long idPoliza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_poliza);

        txtNombre = findViewById(R.id.txt_nombre);
        txtAccidentes = findViewById(R.id.txt_accidentes);
        txtValorVehi = findViewById(R.id.txt_valorVehiculo);
        cboModelo = findViewById(R.id.spn_modelo);
        cboEdad = findViewById(R.id.spn_edad);
        btnActualizar = findViewById(R.id.btn_actualizar);

        // Cargar datos en los spinners
        List<String> modelos = Arrays.asList("Modelo A", "Modelo B", "Modelo C");
        List<String> edades = Arrays.asList("18-23", "24-55", "Mayor de 35");

        ArrayAdapter<String> adapterModelo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modelos);
        adapterModelo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        cboModelo.setAdapter(adapterModelo);

        ArrayAdapter<String> adapterEdad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, edades);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        cboEdad.setAdapter(adapterEdad);

        // Obtener datos enviados por el Intent
        Intent intent = getIntent();
        if (intent != null) {
            idPoliza = intent.getLongExtra("id", 0);
            txtNombre.setText(intent.getStringExtra("nombre"));
            txtValorVehi.setText(String.valueOf(intent.getDoubleExtra("valorAuto", 0)));
            txtAccidentes.setText(String.valueOf(intent.getIntExtra("accidentes", 0)));

            String modelo = intent.getStringExtra("modelo");
            String edad = intent.getStringExtra("edad");

            int indexModelo = modelos.indexOf(modelo);
            if (indexModelo >= 0) {
                cboModelo.setSelection(indexModelo);
            }
            int indexEdad = edades.indexOf(edad);
            if (indexEdad >= 0) {
                cboEdad.setSelection(indexEdad);
            }
        }

        databaseHelper = new DatabaseHelper(this);

        btnActualizar.setOnClickListener(v -> actualizarPoliza());
    }

    private void actualizarPoliza() {
        // Obtener datos actualizados
        String nombre = txtNombre.getText().toString();
        double valorAuto = Double.parseDouble(txtValorVehi.getText().toString());
        int accidentes = Integer.parseInt(txtAccidentes.getText().toString());
        String modelo = cboModelo.getSelectedItem().toString();
        String edad = cboEdad.getSelectedItem().toString();

        String costoTotal = PolizaController.calcularPoliza(nombre, valorAuto, modelo, accidentes, edad);

        Poliza poliza = new Poliza(idPoliza, nombre, valorAuto, accidentes, modelo, edad,
                Double.parseDouble(costoTotal));

        boolean isUpdated = databaseHelper.updatePoliza(poliza);

        if (isUpdated) {
            Toast.makeText(this, "Poliza actualizada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }
}
