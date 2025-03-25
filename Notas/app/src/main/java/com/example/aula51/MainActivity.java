package com.example.aula51;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);


        EditText nomeAluno = findViewById(R.id.editTextNomeAluno);
        EditText matriculaAluno = findViewById(R.id.editTextMatriculaAluno);
        EditText nota1 = findViewById(R.id.editTextNota1);
        EditText nota2 = findViewById(R.id.editTextNota2);

        Button calcularNota = findViewById(R.id.buttonCalcularNota);
        Button cancelar = findViewById(R.id.buttonCancelar);

        TextView resultado = findViewById(R.id.textViewResultado);


        calcularNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeDigitado = nomeAluno.getText().toString();
                String matriculaDigitado = matriculaAluno.getText().toString();
                double nota1Digitado = Double.parseDouble(nota1.getText().toString());
                double nota2Digitado = Double.parseDouble(nota2.getText().toString());

                double notaFinal = (nota1Digitado * 0.4) + (nota2Digitado * 0.6);

                resultado.setText("A nota final do aluno " + nomeDigitado + " da matricula " + matriculaDigitado + " foi " + notaFinal);            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeAluno.setText("");
                matriculaAluno.setText("");
                nota1.setText("");
                nota2.setText("");
                resultado.setText("");
            }
        });
    }
}
