package com.example.prova2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CadastroActivity extends AppCompatActivity {
    private TextInputEditText nome, email, senha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.editNome);
        email = findViewById(R.id.editEmail);
        senha = findViewById(R.id.editSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String nomeTxt = nome.getText().toString();
            String emailTxt = email.getText().toString();
            String senhaTxt = senha.getText().toString();

            ContentValues valores = new ContentValues();
            valores.put("nome", nomeTxt);
            valores.put("email", emailTxt);
            valores.put("senha", senhaTxt);

            try {
                long result = db.insertOrThrow("usuarios", null, valores);
                if (result != -1) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    finish(); // Voltar pro login
                }
            } catch (Exception e) {
                Toast.makeText(this, "Erro: Email já cadastrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
