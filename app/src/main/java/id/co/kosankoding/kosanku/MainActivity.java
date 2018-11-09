package id.co.kosankoding.kosanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;

import id.co.kosankoding.kosanku.model.Penghuni;

public class MainActivity extends AppCompatActivity {

    EditText editTextNama;
    EditText editTextNomorHp;
    RadioGroup radioGroupGender;
    Spinner spinnerStatus;
    CheckBox checkBoxTelevisi;
    CheckBox checkBoxAC;
    CheckBox checkBoxKulkas;
    EditText editTextAlamat;
    Button buttonSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mengganti judul activity
        setTitle("Halaman Form Penghuni");

        // menampilkan tombol back di kiri atas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //casting view - mengambil view dari layout xml
        editTextNama = findViewById(R.id.nama);
        editTextNomorHp = findViewById(R.id.hp);
        radioGroupGender = findViewById(R.id.gender);
        spinnerStatus = findViewById(R.id.status);
        checkBoxTelevisi = findViewById(R.id.tv);
        checkBoxAC = findViewById(R.id.ac);
        checkBoxKulkas = findViewById(R.id.kulkas);
        editTextAlamat = findViewById(R.id.alamat);
        buttonSimpan = findViewById(R.id.button);

        //action ketika button diklik
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ambil data nama
                String nama = editTextNama.getText().toString();

                //ambil data nomor hp
                String hp = editTextNomorHp.getText().toString();

                //ambil dara gender
                int genderId = radioGroupGender.getCheckedRadioButtonId();
                String gender = null;
                if (genderId == R.id.pria) {
                    gender = "Pria";
                } else if (genderId == R.id.wanita) {
                    gender = "Wanita";
                }

                //ambil data status
                String status = spinnerStatus.getSelectedItem().toString();

                //ambil data fasilitas
                String fasilitas = "";
                if (checkBoxTelevisi.isChecked()) {
                    fasilitas = fasilitas + "TV, ";
                }
                if (checkBoxAC.isChecked()) {
                    fasilitas = fasilitas + "AC, ";
                }
                if (checkBoxKulkas.isChecked()) {
                    fasilitas = fasilitas + "Kulkas, ";
                }

                //ambil data alamat
                String alamat = editTextAlamat.getText().toString();

                // tampilkan alert
                Toast.makeText(MainActivity.this,
                        "Nama: " + nama + "\n" +
                                "HP: " + hp + "\n" +
                                "Gender: " + gender + "\n" +
                                "Status: " + status + "\n" +
                                "Fasilitas: " + fasilitas + "\n" +
                                "Alamat: " + alamat,
                        Toast.LENGTH_LONG).show();

                // save ke database
                Penghuni penghuni;
                if(getIntent().hasExtra("id")){
                    long id = getIntent().getLongExtra("id", 0);
                     penghuni = new Select().from(Penghuni.class).where("id=?", id).executeSingle();
                }
                else{
                    penghuni = new Penghuni();
                }
                penghuni.nama = nama;
                penghuni.hp = hp;
                penghuni.gender = gender;
                penghuni.status = status;
                penghuni.fasilitas = fasilitas;
                penghuni.alamat = alamat;
                penghuni.save();

                // destroy main activity dan kembali ke list
                finish();
            }
        });

        // mengecek apakah ada action edit
        if(getIntent().hasExtra("id")){
            // mengambil data dari tabel berdasarkan id
            long id = getIntent().getLongExtra("id", 0);

            Penghuni penghuni = new Select().from(Penghuni.class).where("id=?", id).executeSingle();

            // meset data nama
            editTextNama.setText(penghuni.nama);

            // meset data nomor ho
            editTextNomorHp.setText(penghuni.hp);

            // meset data gender
            if(penghuni.gender.equalsIgnoreCase("Pria")){
                radioGroupGender.check(R.id.pria);
            }
            else{
                radioGroupGender.check(R.id.wanita);
            }

            // meset data status
            for (int i=0;i<spinnerStatus.getCount();i++){
                if (spinnerStatus.getItemAtPosition(i).toString().equalsIgnoreCase(penghuni.status)){
                    spinnerStatus.setSelection(i);
                    break;
                }
            }

            // meset data fasilitas
            if(penghuni.fasilitas.contains("TV")){
                checkBoxTelevisi.setChecked(true);
            }
            if(penghuni.fasilitas.contains("AC")){
                checkBoxAC.setChecked(true);
            }
            if(penghuni.fasilitas.contains("Kulkas")){
                checkBoxKulkas.setChecked(true);
            }

            // meset data alamat
            editTextAlamat.setText(penghuni.alamat);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // check apakah home button
        if (item.getItemId() == android.R.id.home) {
            // destroy detail activty
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
