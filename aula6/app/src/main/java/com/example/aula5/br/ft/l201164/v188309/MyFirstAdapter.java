package com.example.aula5.br.ft.l201164.v188309;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyFirstAdapter extends RecyclerView.Adapter {


    private ArrayList<Aluno> alunos;
    private MyOnItemClickListener listener;


    public MyFirstAdapter(ArrayList<Aluno> alunos){
        this.alunos = alunos;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapterlayou, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View itemView) {
                                            if (listener != null){
                                                TextView text = itemView.findViewById(R.id.alunoNomeTextView);
                                                listener.MyOnItemClick(text.getText().toString());
                                            }
                                        }
                                    }
        );
        final MyFirstViewHolder viewHolder = new MyFirstViewHolder(itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int position = viewHolder.getItemPosition();
                alunos.remove(position);
                notifyDataSetChanged();


                return true;

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyFirstViewHolder)holder).bind(alunos.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.alunos.size();
    }

    public void setMyOnItemClickListener(MyOnItemClickListener listener){
        this.listener = listener;
    }

    public interface MyOnItemClickListener {
        void MyOnItemClick(String nome);
    }

    public class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView   fotoView;
        private TextView    nomeText;
        private TextView    raText;
        private TextView    miniCvText;
        private int position;

        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoView = itemView.findViewById(R.id.alunoFotoImageView);
            nomeText = itemView.findViewById(R.id.alunoNomeTextView);
            raText = itemView.findViewById(R.id.alunoRaTextView);
            miniCvText = itemView.findViewById(R.id.alunoMiniCvTextView);
        }

        public void bind(final Aluno aluno, int position){
            fotoView.setImageResource(aluno.getFoto());
            nomeText.setText(aluno.getNome());
            raText.setText(aluno.getRa());
            miniCvText.setText(Html.fromHtml(aluno.getMiniCV()));
            this.position = position;
        }

        public int getItemPosition(){
            return position;
        }
    }



}
