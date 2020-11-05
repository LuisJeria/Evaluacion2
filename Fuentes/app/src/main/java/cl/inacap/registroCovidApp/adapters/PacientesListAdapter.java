package cl.inacap.registroCovidApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.registroCovidApp.R;
import cl.inacap.registroCovidApp.dto.Paciente;

public class PacientesListAdapter extends ArrayAdapter<Paciente> {

    private List<Paciente> pacientes;
    private Activity activity;

    public PacientesListAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.pacientes= objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View item = inflater.inflate(R.layout.pacientes_list,null,true);
        TextView rutTv = item.findViewById(R.id.rut_lv_txt);
        TextView fechaTv = item.findViewById(R.id.fecha_lv_txt);
        TextView nombreCompletoTv = item.findViewById(R.id.nombre_completo_lv_txt);
        ImageView imagenCovid = item.findViewById(R.id.img_lv_txt);

        Paciente actual = pacientes.get(position);

        rutTv.setText(actual.getRut());
        fechaTv.setText(actual.getFechaExamen());
        nombreCompletoTv.setText(actual.getNombre() + " " + actual.getApellido());
        if(actual.isSintoma()){
            imagenCovid.setImageResource(R.drawable.enfermo);
        }
        return item;
    }
}
