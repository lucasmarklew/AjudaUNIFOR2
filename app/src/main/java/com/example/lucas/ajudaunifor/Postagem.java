package com.example.lucas.ajudaunifor;

import android.app.AlertDialog;
import android.content.Intent;
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

public class Postagem extends AppCompatActivity {


    private EditText etTitulo, etPost;
    private Button btSavarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postagem);


        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etPost = (EditText) findViewById(R.id.etPost);
        btSavarP = (Button) findViewById(R.id.btSalvarPost);



        btSavarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etTitulo.getText().toString().equals("") || etPost.getText().toString().equals("")) {
                    mensagemExibir("Erro.:", "Campo titulo ou postagem nao podem estar vazios");

                }else{
                Log.i("Salvar", "entrou no evento");
                String urlPost = "http://192.168.0.46/android/gravarPostagem.php";
                String urlGet = "http://192.168.0.46/android/gravarPostagem.php?titulo=" + etTitulo.getText().toString() + "&texto=" + etPost.getText().toString();
                ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
                parametrosPost.add(new BasicNameValuePair("titulo", etTitulo.getText().toString()));
                parametrosPost.add(new BasicNameValuePair("texto", etPost.getText().toString()));

                String respostaRetornada = null;
                Log.i("Salvar", "vai entrar no try");

                try {

                    respostaRetornada = ConexaoHttpClient.executaHttpPost(urlPost, parametrosPost);
                    String resposta = respostaRetornada.toString();
                    Log.i("Salvar", "resposta = " + resposta);
                    resposta = resposta.replaceAll("\\s+", "");
                    if (resposta.equals("1")) {
                        Intent intent = new Intent(Postagem.this, MenuPrincipal.class);
                        startActivity(intent);
                    } else {
                        mensagemExibir("Tente novamente! ", "Dados não foram salvos");
                    }
                } catch (Exception erro) {
                    Log.i("erro", "erro = " + erro);
                    Toast.makeText(Postagem.this, "Erro.: " + erro, Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    public void mensagemExibir(String titulo, String texto) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(Postagem.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("Ok", null);
        mensagem.show();
    }
}
