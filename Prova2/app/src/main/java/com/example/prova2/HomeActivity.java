package com.example.prova2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity {
    private TextInputEditText nomeProduto, dataEntrada, preco;
    private Button btnCadastrarProduto;
    private TextView textListaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nomeProduto = findViewById(R.id.editNomeProduto);
        dataEntrada = findViewById(R.id.editDataEntrada);
        preco = findViewById(R.id.editPreco);
        btnCadastrarProduto = findViewById(R.id.btnCadastrarProduto);
        textListaProdutos = findViewById(R.id.textListaProdutos);

        carregarProdutos(); // carregar na inicialização

        btnCadastrarProduto.setOnClickListener(v -> {
            String nome = nomeProduto.getText().toString();
            String entrada = dataEntrada.getText().toString();
            String precoTxt = preco.getText().toString();

            if (nome.isEmpty() || entrada.isEmpty() || precoTxt.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int precoInt;
            try {
                precoInt = Integer.parseInt(precoTxt);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nome", nome);
            values.put("data_entrada", entrada);
            values.put("preco", precoInt);

            long result = db.insert("produtos", null, values);
            if (result != -1) {
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                nomeProduto.setText("");
                dataEntrada.setText("");
                preco.setText("");
                carregarProdutos(); // atualiza lista
            } else {
                Toast.makeText(this, "Erro ao cadastrar produto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarProdutos() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos", null);
        StringBuilder builder = new StringBuilder();
        int somaTotal = 0;

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                String entrada = cursor.getString(cursor.getColumnIndexOrThrow("data_entrada"));
                int preco = cursor.getInt(cursor.getColumnIndexOrThrow("preco"));

                builder.append("Produto: ").append(nome).append("\n")
                        .append("Entrada: ").append(entrada).append("\n")
                        .append("Preço: R$ ").append(preco).append("\n\n");

                somaTotal += preco;

            } while (cursor.moveToNext());
        } else {
            builder.append("Nenhum produto cadastrado ainda.");
        }

        builder.append("=================================\n");
        builder.append("TOTAL: R$ ").append(somaTotal).append("\n");

        cursor.close();
        textListaProdutos.setText(builder.toString());
    }

}
