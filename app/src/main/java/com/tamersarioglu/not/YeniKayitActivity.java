package com.tamersarioglu.not;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;


public class YeniKayitActivity extends AppCompatActivity {

    private Toolbar toolbarYeniKayit;
    private EditText editTextDersAdi, editTextNot1, editTextNot2;
    private Button buttonKaydet;

    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_kayit);

        toolbarYeniKayit = findViewById(R.id.toolbarYeniKayit);
        editTextDersAdi = findViewById(R.id.editTextDersAdi);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        buttonKaydet = findViewById(R.id.buttonKaydet);

        toolbarYeniKayit.setTitle("Not Kayıt");
        setSupportActionBar(toolbarYeniKayit);

        vt = new Veritabani(this);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ders_adi = editTextDersAdi.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();


                if (TextUtils.isEmpty(ders_adi)) {
                    Snackbar.make(view, "Ders adÄ± giriniz", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(not1)) {
                    Snackbar.make(view, "Not1 giriniz", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(not2)) {
                    Snackbar.make(view, "Not2 giriniz", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new NotlarDAO().notEkle(vt, ders_adi, Integer.parseInt(not1), Integer.parseInt(not2));

                startActivity(new Intent(YeniKayitActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
