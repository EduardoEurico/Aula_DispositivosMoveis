package com.example.revisao;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView pergunta2;
    private TextView pergunta1;
    private Button botaoEnviar;
    private RadioButton radioButtonSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inicializaTela();

    }


   private void inicializaTela(){
        pergunta1 = findViewById(R.id.pergunta1);
        pergunta2 = findViewById(R.id.pergunta2);
        botaoEnviar = findViewById(R.id.botaoEnviar);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarResposta();
            }

        });
   }



    private void enviarResposta() {
        String resposta1 = verificaRespostas(pergunta1);
        String resposta2 = verificaRespostas(pergunta2);

        Intent telaResultado = new Intent(this, TelaResultado.class);

        telaResultado.putExtra("resposta1", resposta1);
        telaResultado.putExtra("resposta2", resposta2);

        startActivity(telaResultado);

    }

    private String verificaRespostas(RadioGrup pergunta) {
        int idOpcaoSelecionada = pergunta.getCheckedRadioButtonId();
        Button radioButtonSelecionado = findViewById(idOpcaoSelecionada);
        String resposta = radioButtonSelecionado.getText().toString();

        return resposta;
    }
}