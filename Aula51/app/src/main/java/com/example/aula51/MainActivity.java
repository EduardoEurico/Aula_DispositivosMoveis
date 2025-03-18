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


        EditText nome = findViewById(R.id.editTextNome);
        EditText cpf = findViewById(R.id.editTextCPF);
        EditText email = findViewById(R.id.editTextEmail);
        EditText telefone = findViewById(R.id.editTextTelefone);

        Button salvar = findViewById(R.id.buttonSalvar);
        Button cancelar = findViewById(R.id.buttonCancelar);

        TextView resultado = findViewById(R.id.textViewResultado);


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeDigitado = nome.getText().toString();
                String cpfDigitado = cpf.getText().toString();
                String emailDigitado = email.getText().toString();
                String telefoneDigitado = telefone.getText().toString();

                resultado.setText("Nome: " + nomeDigitado + "\nCPF: " + cpfDigitado +
                        "\nEmail: " + emailDigitado + "\nTelefone: " + telefoneDigitado);
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome.setText("");
                cpf.setText("");
                email.setText("");
                telefone.setText("");
                resultado.setText("");
            }
        });
    }
}
