package com.example.prypoliza1.view;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prypoliza1.R;
import com.example.prypoliza1.controller.PolizaController;
import com.example.prypoliza1.database.DatabaseHelper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtAccidentes, txtValorVehi;

    TextView lblCostoPoliza;

    Button btnCalcular, btnLimpiar, btnSalir, btnListarDatos;

    Spinner cboModelo, cboEdad;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Agregar esta línea

        txtNombre = findViewById(R.id.txt_nombre);
        txtAccidentes = findViewById(R.id.txt_accidentes);
        txtValorVehi = findViewById(R.id.txt_valorVehiculo);

        lblCostoPoliza = findViewById(R.id.lb_resultado);

        btnCalcular = findViewById(R.id.btn_calcular);
        btnLimpiar = findViewById(R.id.btn_limpar);
        btnSalir = findViewById(R.id.btn_salir);
        btnListarDatos = findViewById(R.id.btn_listar);

        cboEdad = findViewById(R.id.spn_edad);
        cboModelo = findViewById(R.id.spn_modelo);

        cargarDatos();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularPoliza();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblCostoPoliza.setText("");
                txtNombre.setText("");
                txtAccidentes.setText("");
                txtValorVehi.setText("");
                cboEdad.setSelected(false);
                cboModelo.setSelected(false);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnListarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarDatosActvity.class);
                startActivity(intent);
            }
        });


    }

    private void cargarSpinners(List<String> data, Spinner spinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                data);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(dataAdapter);
    }

    // Metodo para carga de datos
    private void cargarDatos() {
        //spiner carga de datos de modelos
        List<String> modelos = Arrays.asList("Modelo A", "Modelo B", "Modelo C");
        List<String> edades = Arrays.asList("18-23", "24-55", "Mayor de 35");
        cargarSpinners(modelos, cboModelo);
        cargarSpinners(edades, cboEdad);
    }

    // Metodo calcular poliza
    @SuppressLint("SetTextI18n")
    private void calcularPoliza() {
        //validacion cajas de texto
        if(txtNombre.getText().toString().isEmpty() || txtValorVehi.getText().toString().isEmpty()
                || txtAccidentes.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese Todos los datos", Toast.LENGTH_LONG).show();
        }

        //Validacion de los spinners
        if(cboModelo.getSelectedItem() == null || cboEdad.getSelectedItem() == null) {
            Toast.makeText(this, "Seleccione un modelo y una edad", Toast.LENGTH_LONG).show();
        }

        //Declarar variables

        String nombre = txtNombre.getText().toString();
        double valorAuto = Double.parseDouble(txtValorVehi.getText().toString());
        int accidenes = Integer.parseInt(txtAccidentes.getText().toString());

        String modelo = cboModelo.getSelectedItem().toString();
        String edad = cboEdad.getSelectedItem().toString();

        String costoTotal = PolizaController.calcularPoliza(nombre, valorAuto, modelo, accidenes, edad);

        lblCostoPoliza.setText("El costo de la poliza es: " + costoTotal);

        //Guardar en base de datos
        databaseHelper = new DatabaseHelper(this);
        boolean insertado = databaseHelper.insertarPoliza(nombre, valorAuto, modelo, accidenes, edad, Double.parseDouble(costoTotal));
        if(insertado) {
            Toast.makeText(this, "Poliza insertada correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error al insertar poliza", Toast.LENGTH_LONG).show();
        }

    }


}