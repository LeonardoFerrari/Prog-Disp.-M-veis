package br.unicamp.ft.ulisses.aula11_drawer2019;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import br.unicamp.ft.ulisses.aula09_drawer2018.R;
import br.unicamp.ft.ulisses.aula11_drawer2019.interfaces.OnBiografiaRequest;

import static java.lang.Integer.parseInt;


/**
 * A simple {@link Fragment} subclass.
 */
public class IdadeFragmentFireBase extends Fragment {

    private View lview;

    private Random random = new Random();
    private int idadeCorreta;
    private Aluno alunoCorreto;
    private int positionAluno;
    private int numTentativas;

    private ImageView imageView;
    private TextView  txtNome;
    private TextView  txtTentativas;
    private TextView  txtFeedback;

    private OnBiografiaRequest onBiografiaRequest;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public IdadeFragmentFireBase() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_idade, container, false);
        }

        imageView     = lview.findViewById(R.id.imageFoto);
        txtNome       = lview.findViewById(R.id.txtNome);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback   = lview.findViewById(R.id.txtFeedback);


        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idadeEscolhida = parseInt( ((Button)v).getText().toString() );

                int certo =0;

                if (idadeEscolhida == alunoCorreto.getIdade()){
                    certo=1;
                    txtFeedback.setText("Correto!!");
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    startGame();
                                }
                            }, 2000);
                } else {
                    txtFeedback.setText("Incorreto!!");
                    numTentativas--;
                    txtTentativas.setText("Tentativas: "+numTentativas);

                    if (numTentativas == 0){
                        txtFeedback.setText("Você Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        if (onBiografiaRequest != null){
                                            onBiografiaRequest.onRequest(positionAluno);
                                        }
                                    }
                                }, 2000);


                    }
                }


                ContentValues contentValues = new ContentValues();
                contentValues.put("idadeselecionada", idadeEscolhida);
                contentValues.put("certo", certo);
                contentValues.put("RA", parseInt(alunoCorreto.getRa()));
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                sqLiteDatabase.insert("tabela", null, contentValues);
                sqLiteDatabase.close();
                dbHelper.close();
            }
        };

        lview.findViewById(R.id.button1).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button2).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button3).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button4).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button5).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button6).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button7).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button8).setOnClickListener(guessButtonListener);
        lview.findViewById(R.id.button9).setOnClickListener(guessButtonListener);

        return lview;
    }

    private void startGame(){
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        alunoCorreto = aluno;
        idadeCorreta = aluno.getIdade();
        imageView.setImageResource(aluno.getFoto());
        txtNome.setText(aluno.getNome());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: "+numTentativas);
        txtFeedback.setText("");

    }

}
