package com.example.prova2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText email, senha;
    private Button btnEntrar, btnIrCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editEmailLogin);
        senha = findViewById(R.id.editSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnIrCadastro = findViewById(R.id.btnIrCadastro);

        btnEntrar.setOnClickListener(v -> {
            String emailTxt = email.getText().toString().trim();
            String senhaTxt = senha.getText().toString().trim();

            if (emailTxt.isEmpty() || senhaTxt.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(
                    "SELECT * FROM usuarios WHERE email = ? AND senha = ?",
                    new String[]{emailTxt, senhaTxt}
            );

            if (cursor != null && cursor.moveToFirst()) {
                Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                cursor.close();
                db.close();
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email ou senha inválidos ou usuário não cadastrado", Toast.LENGTH_SHORT).show();
                if (cursor != null) cursor.close();
                db.close();
            }
        });

        btnIrCadastro.setOnClickListener(v -> {
            startActivity(new Intent(this, CadastroActivity.class));
        });
    }
}
