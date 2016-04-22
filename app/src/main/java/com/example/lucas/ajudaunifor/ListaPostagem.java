package com.example.lucas.ajudaunifor;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.ajudaunifor.conexao.ConexaoHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ListaPostagem extends AppCompatActivity{

    private ListView listViewPostagem;
    String [] listapostagens;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postagem);

        listViewPostagem = (ListView) findViewById(R.id.listViewPost);

        Log.i("logar", "entrou no evento");
        String url = "http://192.168.0.46/android/listarPostagem.php";
        String respostaRetornada = null;
        Log.i("posts", "vai entrar no try");

        try{

            respostaRetornada = ConexaoHttpClient.executaHttpGet(url);
            String resposta = respostaRetornada.toString();
            Log.i("postagens", "" + resposta);

            char separador = '#';
            int contaTitulo = 0;
            for(int i=0; i<resposta.length(); i++) {
                if (separador == resposta.charAt(i)) {
                    contaTitulo++;
                }
            }

            listapostagens = new String[contaTitulo];

            char caracterLido=resposta.charAt(0);
            String titulo="";
            for(int i=0; caracterLido != '^'; i++){
                caracterLido = resposta.charAt(i);
                Log.i("Chars posts", "" + caracterLido);
                if(caracterLido != '#'){
                    titulo += (char) caracterLido;
                }else{
                    Log.i("titulo", "" + titulo);
                    listapostagens[pos] = "" + titulo;
                    Log.i("titulo pos ["+pos+"]",""+ listapostagens[pos]);
                    pos++;
                    titulo="";
                }
            }
            Log.i("FIM", "FIM");
        }
        catch (Exception erro) {
            Log.i("erro", "erro = " + erro);
            }

        ArrayAdapter<String> ltPostagens = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listapostagens);
        listViewPostagem.setAdapter(ltPostagens);


        listViewPostagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Intent intent = new Intent(ListaPostagem.this, VisualizarPost.class);
                //Parcelable parcelable = (Parcelable) listViewPostagem.getItemAtPosition();

            }
        });



        }

    public void mensagemExibir(String titulo, String texto){
        AlertDialog.Builder mensagem = new AlertDialog.Builder(ListaPostagem.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("Ok", null);
        mensagem.show();

    }

}

