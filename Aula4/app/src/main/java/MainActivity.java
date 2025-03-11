import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import com.example.aula4.R;

public class MainActivity extends Activity {
    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        EditText Login = findViewById(R.id.editTextText);
        EditText Senha = findViewById(R.id.editTextText2);
        Button btnLogin = findViewById(R.id.button);
        }
    }
@Override
public void onClick(View v){
    login = Login.getText().toString().equals("admin") && Senha.getText().toString().equals("admin");

}


