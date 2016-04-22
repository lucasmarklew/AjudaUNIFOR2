package com.example.lucas.ajudaunifor;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.ajudaunifor.conexao.ConexaoHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Logar extends AppCompatActivity {

    public void enableStrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private EditText etUsuario, etSenha;
    private Button btAcessar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        enableStrictMode();


        etUsuario = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btAcessar = (Button) findViewById(R.id.btLogar);


        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("logar", "entrou no evento");
                String urlPost = "http://192.168.0.46/android/logar.php";
                String urlGet = "http://192.168.0.46/android/logar.php?Matricula=" + etUsuario.getText().toString() + "&Senha=" + etSenha.getText().toString();
                ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
                parametrosPost.add(new BasicNameValuePair("Matricula",etUsuario.getText().toString()));
                parametrosPost.add(new BasicNameValuePair("Senha",etSenha.getText().toString()));
                String respostaRetornada = null;
                Log.i("logar", "vai entrar no try");

                try{

                    respostaRetornada = ConexaoHttpClient.executaHttpPost(urlPost, parametrosPost);
                    String resposta = respostaRetornada.toString();
                    Log.i("logar", "resposta = " + resposta);
                    resposta = resposta.replaceAll("\\s+","");
                    if(resposta.equals("1")){
                        Intent intent = new Intent(Logar.this, MenuPrincipal.class);
                        startActivity(intent);
                    }else{
                        mensagemExibir("Verificar! ", "Usuario ou senha invalidos");
                    }
                }
                catch (Exception erro){
                    Log.i("erro", "erro = " + erro);
                    Toast.makeText(Logar.this, "Erro.: " + erro, Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void mensagemExibir(String titulo, String texto){
        AlertDialog.Builder mensagem = new AlertDialog.Builder(Logar.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("Ok", null);
        mensagem.show();

    }
}

