package com.tamersarioglu.not;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Notlar> notlarListe;

    public NotlarAdapter(Context mContext, List<Notlar> notlarListe) {
        this.mContext = mContext;
        this.notlarListe = notlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarimi, parent, false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {

        final Notlar not = notlarListe.get(position);

        holder.textViewDers.setText(not.getDers_adi());
        holder.textViewNot1.setText(String.valueOf(not.getNot1()));
        holder.textViewNot2.setText(String.valueOf(not.getNot2()));

        holder.card_Note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NotGuncelleActivity.class);
                intent.putExtra("nesne",not);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notlarListe.size();
    }

    public static class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textViewDers;
        private TextView textViewNot1;
        private TextView textViewNot2;
        private CardView card_Note;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewDers = itemView.findViewById(R.id.textView_Ders);
            textViewNot1 = itemView.findViewById(R.id.textView_Not1);
            textViewNot2 = itemView.findViewById(R.id.textView_Not2);
            card_Note = itemView.findViewById(R.id.card_Note);

        }
    }

}
