package br.unicamp.ft.ulisses.aula11_drawer2019;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unicamp.ft.ulisses.aula09_drawer2018.R;

public class ListDatasetFragment extends Fragment {
    public static class RespostaViewHolder extends RecyclerView.ViewHolder {
        TextView answer;
        TextView choosen;

        public RespostaViewHolder(View v) {
            super(v);
            answer = (TextView) itemView.findViewById(R.id.txtAnswer);
            choosen = (TextView) itemView.findViewById(R.id.txtChosen);
        }
    }// Firebase instance variables

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Resposta, RespostaViewHolder>
            mFirebaseAdapter;

    public ListDatasetFragment() {// Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lview = inflater.inflate(R.layout.fragment_list_dataset, container, false);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        SnapshotParser<Resposta> parser = new SnapshotParser<Resposta>() {

            @Override
            public Resposta parseSnapshot(DataSnapshot dataSnapshot) {
                Resposta resposta = dataSnapshot.getValue(Resposta.class);
                return resposta;
            }
        };
        DatabaseReference messagesRef = mFirebaseDatabaseReference.child("respostas");
        FirebaseRecyclerOptions<Resposta> options = new FirebaseRecyclerOptions.Builder<Resposta>().setQuery(messagesRef, parser).build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Resposta, RespostaViewHolder>(options) {

            @Override
            public RespostaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new RespostaViewHolder(inflater.inflate(R.layout.item_message, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(final RespostaViewHolder viewHolder, int position, Resposta resposta) {
                if (resposta.getAnswer() != null) {
                    viewHolder.answer.setText(resposta.getAnswer());
                    viewHolder.choosen.setText(resposta.getChosen());
                }
            }
        };
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecycler = ((RecyclerView) lview.findViewById(R.id.respostaRecyclerView));
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mFirebaseAdapter);
        return lview;
    }

    @Override
    public void onPause() {
        mFirebaseAdapter.stopListening();
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }
}
