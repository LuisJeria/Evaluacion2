package cl.inacap.registroCovidApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.List;

import cl.inacap.registroCovidApp.R;
import cl.inacap.registroCovidApp.dto.Paciente;

public class VerListAdapter extends ArrayAdapter<Paciente> {

    private List<Paciente> pacientes;
    private Activity activity;

    public VerListAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.pacientes = objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View item = inflater.inflate(R.layout.ver_list,null,true);

        TextView fechaDetalle = item.findViewById(R.id.fecha_detalle);
        TextView sintomaDetalle = item.findViewById(R.id.sintomas_detalle);
        TextView temperaturaDetalle = item.findViewById(R.id.temperatura_detalle);
        TextView tosDetalle = item.findViewById(R.id.tos_detalle);
        TextView presionDetalle = item.findViewById(R.id.presion_detalle);

        Paciente actual = pacientes.get(position);
        DecimalFormat df = new DecimalFormat("#.0");
        String aux = df.format(actual.getTemperatura());
        aux = aux + "°C";

        fechaDetalle.setText(actual.getFechaExamen());
        if(actual.isSintoma()){
            sintomaDetalle.setText("SI");
            sintomaDetalle.setTextColor(0xFFFF5252);
        }else{
            sintomaDetalle.setText("NO");
            sintomaDetalle.setTextColor(0xFF0097A7);
        }
        if(actual.getTemperatura() > 37.5){
            temperaturaDetalle.setText(aux);
            temperaturaDetalle.setTextColor(0xFFFF5252);
        }else{
            temperaturaDetalle.setText(aux);
            temperaturaDetalle.setTextColor(0xFF0097A7);
        }
        if(actual.isTos()){
            tosDetalle.setText("SI");
            tosDetalle.setTextColor(0xFFFF5252);
        }else{
            tosDetalle.setText("NO");
            tosDetalle.setTextColor(0xFF0097A7);
        }
        if(actual.getPresionArterial() < 80 || actual.getPresionArterial() > 120){
            presionDetalle.setText(Integer.toString(actual.getPresionArterial()));
            presionDetalle.setTextColor(0xFFFF5252);
        }else{
            presionDetalle.setText(Integer.toString(actual.getPresionArterial()));
            presionDetalle.setTextColor(0xFF0097A7);
        }

        return item;
    }
}
