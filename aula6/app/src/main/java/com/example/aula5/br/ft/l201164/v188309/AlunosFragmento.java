package com.example.aula5.br.ft.l201164.v188309;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;


import java.util.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragmento extends Fragment {

    private RecyclerView alunosFragmentoView;
    private MyFirstAdapter mainAdapter;
    private  View lview;


    public AlunosFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        lview = inflater.inflate(R.layout.fragment_alunos_fragmento, container, false);


        alunosFragmentoView =  lview.findViewById(R.id.frag_Alunos);
        alunosFragmentoView.setHasFixedSize(true);
        alunosFragmentoView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainAdapter = new MyFirstAdapter((new ArrayList(Arrays.asList(Alunos.alunos))));
        alunosFragmentoView.setAdapter(mainAdapter);
        mainAdapter.setMyOnItemClickListener(new MyFirstAdapter.MyOnItemClickListener() {
                                                 @Override
                                                 public void MyOnItemClick(String nome) {
                                                     Toast toast = Toast.makeText(getContext(), "Aluno: " + nome, Toast.LENGTH_LONG);
                                                     ((MainActivity)getActivity()).meu_metodo(nome);
                                                     toast.show();
                                                 }
                                             }
        );
        // Inflate the layout for this fragment
        return lview;
    }



}
