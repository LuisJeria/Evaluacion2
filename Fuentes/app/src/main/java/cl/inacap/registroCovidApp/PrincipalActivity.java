package cl.inacap.registroCovidApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cl.inacap.registroCovidApp.adapters.PacientesListAdapter;
import cl.inacap.registroCovidApp.dao.PacientesDAO;
import cl.inacap.registroCovidApp.dao.PacientesDAOSQLite;
import cl.inacap.registroCovidApp.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {

    private List<Paciente> pacientes;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);
    private ListView pacientesLV;
    private PacientesListAdapter pAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        this.pacientes = this.pacientesDAO.getAll();
        this.pacientesLV = findViewById(R.id.pacientes_lv);
        this.pAdapter = new PacientesListAdapter(this,R.layout.pacientes_list,this.pacientes);
        this.pacientesLV.setAdapter(pAdapter);

        this.pacientesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(PrincipalActivity.this,VerPacienteActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
    }
}