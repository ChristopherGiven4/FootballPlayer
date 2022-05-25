package com.si61.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);

        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String nomor = etNomor.getText().toString();
                String klub = etKlub.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama harus diisi");
                }
                else if(nomor.trim().equals("")){
                    etNomor.setError("Nomor harus diisi");
                }

                else if (klub.trim().equals("")){
                    etKlub.setError("klub harus diisi");
                }

                else{
                    MyDatabaseHelper mydb = new MyDatabaseHelper(TambahActivity.this);
                    long eksekusi = mydb.tambahPlayer(nama, Integer.parseInt(nomor), klub);

                    if (eksekusi == -1){
                        Toast.makeText(TambahActivity.this ,"Gagal menambah Data Player",Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(TambahActivity.this, "Sukses Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}