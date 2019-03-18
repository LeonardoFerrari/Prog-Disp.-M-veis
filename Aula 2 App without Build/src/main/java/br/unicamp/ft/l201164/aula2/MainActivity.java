package br.unicamp.ft.l201164.aula2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    private EditText txtTo;
    private EditText txtLogin;
    private EditText txtSenha;
    public EditText txtMessage;
    private EditText txtAssunto;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtTo = findViewById(R.id.txtTo);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        txtMessage = findViewById(R.id.txtMessage);
        txtAssunto = findViewById(R.id.txtAssunto);
        btnEnviar = findViewById(R.id.btnEnviar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(View view){
        String to = txtTo.getText().toString();
        String login = txtLogin.getText().toString();
        String assunto = txtAssunto.getText().toString();
        String senha = txtSenha.getText().toString();
        String message = txtMessage.getText().toString();

        try {
            Toast toast = Toast.makeText(this, "Mensagem enviada com sucesso!", Toast.LENGTH_SHORT);
            toast.show();
        }catch(NullPointerException e){
            System.out.print("Deu um erro ai meu camarada: " +e);
        }


        new GmailSend(login,senha,to,assunto,message);
    }
}


