package cl.inacap.registroCovidApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cl.inacap.registroCovidApp.adapters.VerListAdapter;
import cl.inacap.registroCovidApp.dao.PacientesDAO;
import cl.inacap.registroCovidApp.dao.PacientesDAOSQLite;
import cl.inacap.registroCovidApp.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private List<Paciente> pacientes;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);

    private Paciente paciente;
    private Toolbar toolbar;
    private TextView rutTV;
    private TextView nombreCompletoTV;
    private TextView trabajoTV;
    private ListView verLV;
    private VerListAdapter vAdapter;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);



        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.rutTV = findViewById(R.id.rut_detalle);
        this.nombreCompletoTV = findViewById(R.id.nombre_completo_detalle);
        this.trabajoTV = findViewById(R.id.trabajo_detalle);
        this.verLV = findViewById(R.id.detalle_lv);


        if(getIntent().getExtras() != null){
            this.paciente =(Paciente) getIntent().getSerializableExtra("paciente");
            this.pacientes = this.pacientesDAO.getByRut(this.paciente.getRut());
            this.vAdapter = new VerListAdapter(this,R.layout.ver_list,this.pacientes);
            this.verLV.setAdapter(vAdapter);
            this.rutTV.setText("RUT: " + this.paciente.getRut());
            this.nombreCompletoTV.setText("NOMBRE: "+ this.paciente.getNombre() + " " + this.paciente.getApellido());
            this.trabajoTV.setText("TRABAJO: " + this.paciente.getAreaTrabajo());

        }
    }
}