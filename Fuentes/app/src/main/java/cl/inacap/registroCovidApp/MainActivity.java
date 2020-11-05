package cl.inacap.registroCovidApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText userTxt;
    private EditText passTxt;
    private Button ingresarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userTxt = findViewById(R.id.user_log);
        this.passTxt = findViewById(R.id.pass_log);
        this.ingresarBtn = findViewById(R.id.btn_log);


        this.ingresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> errores = new ArrayList<>();
                String rut = userTxt.getText().toString();
                String pass = passTxt.getText().toString();
                String prueba = "";

                if(!pass.isEmpty() && rut.length() > 6){
                    prueba = rut.substring(rut.length()-6,rut.length()-2);
                }else{
                    prueba = "prueba";
                }

                if(validarRut(rut)== false){
                    if(rut.isEmpty()){
                        userTxt.setBackgroundResource(R.drawable.borde_alerta);
                        errores.add("Debe Ingresar Nombre de Usuario");
                    }else {
                        errores.add("Nombre de Usuario Inválido");
                    }
                }
                if(pass.isEmpty() || !pass.equals(prueba)){
                    errores.add("La Contraseña no es Válida");
                }
                if(errores.isEmpty()){
                    startActivity(new Intent(MainActivity.this,PrincipalActivity.class));
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
        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
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