package br.com.rafaelamorim.exemplousonotific;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonPedirPermissao = findViewById(R.id.btnPedirPermissao);
        Button buttonNotificacaoSimples = findViewById(R.id.btnNotificacaoSimples);
        Button buttonRegistrarNoFCM = findViewById(R.id.btnRegistrarNoFCM);
        Notificacao not = new Notificacao();

        buttonPedirPermissao.setOnClickListener(v -> {
            not.pedePermissao(this);
        });

        buttonNotificacaoSimples.setOnClickListener(v -> {
            not.mostraNotificacaoLocal(this);
        });

        buttonRegistrarNoFCM.setOnClickListener(v -> {
            Toast.makeText(this, "Essa é a sua missão 🙂", Toast.LENGTH_LONG).show();
        });
    }
}