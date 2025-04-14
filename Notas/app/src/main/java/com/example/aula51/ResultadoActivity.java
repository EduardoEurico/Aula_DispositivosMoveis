package com.example.aula51;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resuultado);

        textViewResultado = findViewById(R.id.textViewResultado);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String matricula = intent.getStringExtra("matricula");
        double notaFinal = intent.getDoubleExtra("notaFinal", 0);

        String mensagem = "Bem vindo aluno " + nome + " - Matrícula " + matricula + "\nSua nota final é: " + notaFinal;
        textViewResultado.setText(mensagem);
    }
}
