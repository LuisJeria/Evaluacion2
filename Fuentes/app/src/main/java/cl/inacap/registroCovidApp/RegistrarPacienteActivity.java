package cl.inacap.registroCovidApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroCovidApp.dao.PacientesDAO;
import cl.inacap.registroCovidApp.dao.PacientesDAOSQLite;
import cl.inacap.registroCovidApp.dto.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(RegistrarPacienteActivity.this);

    private final static String[] areaTrabajo={"-----Seleccione Area-----","Atención a Público","Otro"};

    private Toolbar toolbar;
    private Button agregarBtn;
    private EditText ingresarRut;
    private EditText ingresarNombre;
    private EditText ingresarApellido;

    private Spinner aTrabajoSp;
    private Switch swSintoma;
    private EditText ingresarTemp;
    private Switch swTos;
    private EditText ingresarPresion;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);

        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.ingresarRut = findViewById(R.id.rut_registro);
        this.ingresarNombre = findViewById(R.id.nombre_registro);
        this.ingresarApellido = findViewById(R.id.apellido_registro);
        this.swSintoma = findViewById(R.id.sintomas_registro);
        this.ingresarTemp = findViewById(R.id.temperatura_registro);
        this.swTos = findViewById(R.id.tos_registro);
        this.ingresarPresion = findViewById(R.id.presion_registro);

        this.aTrabajoSp = findViewById(R.id.trabajo_registro);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,areaTrabajo);
        aTrabajoSp.setAdapter(spinnerAdapter);
        aTrabajoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.agregarBtn = findViewById(R.id.ingresar_btn);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> errores = new ArrayList<>();
                float temperatura = 0;
                int presion = 0;

                if(validarRut(ingresarRut.getText().toString())== false){
                    errores.add("Debe Ingresar Un RUT Válido");
                }
                if(ingresarNombre.getText().toString().isEmpty()){
                    errores.add("Debe Ingresar Nombre");
                }
                if(ingresarApellido.getText().toString().isEmpty()){
                    errores.add("Debe Ingresar Apellido");
                }
                if(aTrabajoSp.getSelectedItemPosition() == 0){
                    errores.add("Debe Seleccionar Un Area de Trabajo");
                }
                try{
                    temperatura = Float.parseFloat(ingresarTemp.getText().toString());
                    if(temperatura < 20){
                        errores.add("Debe Ingresar Una Temparatura Mayor a 20");
                    }
                }catch(NumberFormatException e){
                    errores.add("Debe Ingresar Una Temperatura válida");
                }
                try{
                    presion = Integer.parseInt(ingresarPresion.getText().toString());
                }catch(NumberFormatException e){
                    errores.add("Debe Ingresar Una Presión Válida");
                }


                if(errores.isEmpty()){
                    Paciente p = new Paciente();
                    p.setRut(ingresarRut.getText().toString());
                    p.setNombre(ingresarNombre.getText().toString());
                    p.setApellido(ingresarApellido.getText().toString());
                    //fecha aca
                    p.setAreaTrabajo(aTrabajoSp.getItemAtPosition(aTrabajoSp.getSelectedItemPosition()).toString());
                    if(swSintoma.isChecked()){
                        p.setSintoma(true);
                    }else{
                        p.setSintoma(false);
                    }
                    p.setTemperatura(temperatura);
                    if(swTos.isChecked()){
                        p.setTos(true);
                    }else{
                        p.setTos(false);
                    }
                    p.setPresionArterial(presion);
                    pacientesDAO.save(p);

                    startActivity(new Intent(RegistrarPacienteActivity.this,PrincipalActivity.class));
                }else{
                    mostrarErrores(errores);
                }
            }
        });

    }
    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrarPacienteActivity.this);
        alertBuilder.setTitle("Error de Validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create().show();
    }
    private Boolean validarRut(String revisar){
        boolean devolver = true;
        if(revisar.length()<9 || revisar.indexOf('-')==-1){
            devolver = false;
        }else{
            for(int i=0; i<revisar.length();i++){
                if(revisar.charAt(i)=='-' && i!= revisar.length()-2){
                    devolver = false;
                }
                if(revisar.charAt(i)=='k' && i!= revisar.length()-1){
                    devolver = false;
                }
            }
        }
        if(devolver == true){
            String[] formato = revisar.split("-");
            String verificador = formato[1];
            char[] cuerpo = formato[0].toCharArray();
            int acumulador = 0;
            int factor = 2;
            int resto = 0;
            int comprobar = 0;

            for(int i = formato[0].length()-1;i>=0;i--){
                acumulador = acumulador + (Character.getNumericValue(cuerpo[i])*factor);
                if(factor == 7){
                    factor = 1;
                }
                factor++;
            }
            resto = acumulador % 11;
            comprobar = 11 - resto;
            if(verificador.equals("k")){
                if(comprobar == 10){
                    devolver = true;
                }else{
                    devolver = false;
                }
            }else{
                if(comprobar == 11){
                    comprobar = 0;
                }
                if(comprobar == Integer.parseInt(verificador)){
                    devolver = true;
                }else{
                    devolver = false;
                }
            }


        }
        return(devolver);
    }
}