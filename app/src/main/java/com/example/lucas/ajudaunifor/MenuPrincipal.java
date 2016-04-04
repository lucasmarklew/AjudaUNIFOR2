package com.example.lucas.ajudaunifor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    private Button btPostagem, btMeuHistorico, btHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btPostagem = (Button) findViewById(R.id.btPostagem);
        btMeuHistorico = (Button) findViewById(R.id.btMeuHistorico);
        btHistorico = (Button) findViewById(R.id.btHistorico);


        btHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "Historico De Postagens", Toast.LENGTH_SHORT).show();


            }
        });


        btMeuHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "Minhas Postagens", Toast.LENGTH_SHORT).show();

            }
        });

        btPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Postagem.class);
                startActivity(intent);
            }
        });

    }

}

