package com.tamersarioglu.not;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class NotGuncelleActivity extends AppCompatActivity {

    private Toolbar toolbarNotGuncelle;
    private EditText editText_NotGuncelle_DersAdi, editText_NotGuncelle_Not1, editText_NotGuncelle_Not2;
    private ConstraintLayout constraintLayout;

    private Notlar not;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_guncelle);

        editText_NotGuncelle_DersAdi = findViewById(R.id.editText_NotGuncelle_DersAdi);
        editText_NotGuncelle_Not1 = findViewById(R.id.editText_NotGuncelle_Not1);
        editText_NotGuncelle_Not2 = findViewById(R.id.editText_NotGuncelle_Not2);
        constraintLayout = findViewById(R.id.constraintLayout);

        toolbarNotGuncelle = findViewById(R.id.toolbarNotGuncelle);
        toolbarNotGuncelle.setTitle("NOT DETAY");
        setSupportActionBar(toolbarNotGuncelle);

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        vt = new Veritabani(this);

        editText_NotGuncelle_DersAdi.setText(not.getDers_adi());
        editText_NotGuncelle_Not1.setText(String.valueOf(not.getNot1()));
        editText_NotGuncelle_Not2.setText(String.valueOf(not.getNot2()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_Sil:

                Snackbar.make(toolbarNotGuncelle, "Sil Tıklandı", Snackbar.LENGTH_SHORT).setAction("SİL", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new NotlarDAO().notSil(vt, not.getNot_id());

                        startActivity(new Intent(NotGuncelleActivity.this, MainActivity.class));
                        finish();
                    }
                })
                        .show();
                return true;
            case R.id.action_düzenle:

                String ders_adi = editText_NotGuncelle_DersAdi.getText().toString().trim();
                String not1 = editText_NotGuncelle_Not1.getText().toString().trim();
                String not2 = editText_NotGuncelle_Not2.getText().toString().trim();

                if (TextUtils.isEmpty(ders_adi)) {
                    Snackbar.make(toolbarNotGuncelle, "Ders adı giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(not1)) {
                    Snackbar.make(toolbarNotGuncelle, "Not1 giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(not2)) {
                    Snackbar.make(toolbarNotGuncelle, "Not2 giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                new NotlarDAO().notGuncelle(vt, not.getNot_id(), ders_adi, Integer.parseInt(not1), Integer.parseInt(not2));

                startActivity(new Intent(NotGuncelleActivity.this, MainActivity.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}
