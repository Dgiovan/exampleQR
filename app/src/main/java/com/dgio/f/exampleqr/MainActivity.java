package com.dgio.f.exampleqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         texto = findViewById(R.id.textView);
        Button   boton = findViewById(R.id.btnok);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result !=null){
            if (result.getContents() != null)
            {
                if (result.getContents().equals("hola")){
                    Toast.makeText(this, "certificado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "falsificado", Toast.LENGTH_SHORT).show();
                }
                texto.setText("contenido " + result.getContents());
            }else {
                texto.setText("error al escanear " );
            }
        }
    }
}
