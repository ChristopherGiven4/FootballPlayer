package com.si61.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    private String id, nama, nomor, klub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNama");
        nomor = intent.getStringExtra("vNomor");
        klub = intent.getStringExtra("vKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempnama = etNama.getText().toString();
                String tempnomor = etNomor.getText().toString();
                String tempklub = etKlub.getText().toString();

                if(tempnama.trim().equals("")){
                    etNama.setError("Nama harus diisi");
                }
                else if(tempnomor.trim().equals("")){
                    etNomor.setError("Nomor harus diisi");
                }

                else if (tempklub.trim().equals("")){
                    etKlub.setError("klub harus diisi");
                }

                else{
                    MyDatabaseHelper mydb = new MyDatabaseHelper(UbahActivity.this);
                    long eksekusi = mydb.ubahPlayer(id, tempnama, tempnomor, tempklub);

                    if (eksekusi == -1){
                        Toast.makeText(UbahActivity.this ,"Gagal menambah Data Player",Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Sukses Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}