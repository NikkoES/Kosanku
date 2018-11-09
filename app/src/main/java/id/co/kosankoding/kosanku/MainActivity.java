package id.co.kosankoding.kosanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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
            }
        });

    }
}
