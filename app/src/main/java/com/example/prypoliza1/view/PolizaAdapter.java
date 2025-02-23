package com.example.prypoliza1.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.example.prypoliza1.R;
import com.example.prypoliza1.database.DatabaseHelper;
import com.example.prypoliza1.model.Poliza;

import java.util.List;

public class PolizaAdapter extends ArrayAdapter<Poliza> {

    private Context context;
    private List<Poliza> polizas;
    private DatabaseHelper databaseHelper;

    public PolizaAdapter(Context context, List<Poliza> polizas, DatabaseHelper databaseHelper) {
        super(context, R.layout.item_poliza, polizas);
        this.context = context;
        this.polizas = polizas;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_poliza, parent, false);
        }

        TextView tvPoliza = convertView.findViewById(R.id.tvPoliza);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        Poliza poliza = polizas.get(position);
        tvPoliza.setText(poliza.toString());

        btnEliminar.setOnClickListener(v -> {
            eliminarPoliza(poliza.getId(), position);
        });

        return convertView;
    }

    private void eliminarPoliza(long id, int position) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int deletedRows = db.delete("poliza", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        if (deletedRows > 0) {
            polizas.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Poliza eliminada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}
