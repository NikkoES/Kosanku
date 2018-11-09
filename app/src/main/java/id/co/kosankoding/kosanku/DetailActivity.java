package id.co.kosankoding.kosanku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.query.Select;

import id.co.kosankoding.kosanku.model.Penghuni;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // mengganti judul activity
        setTitle("Halaman Detail");

        // menampilkan tombol back di kiri atas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ambil view dari layout
        TextView textViewNama = (TextView) findViewById(R.id.nama);
        TextView textViewNomorHp = (TextView) findViewById(R.id.hp);
        TextView textViewGender = (TextView) findViewById(R.id.gender);
        TextView textViewStatus = (TextView) findViewById(R.id.status);
        TextView textViewFasilitas = (TextView) findViewById(R.id.fasilitas);
        TextView textViewAlamat = (TextView) findViewById(R.id.alamat);

        // ambil data yang dikirim dari MainActivity
        long id = getIntent().getLongExtra("id", 0);

        Penghuni penghuni = new Select().from(Penghuni.class).where("id=?", id).executeSingle();

        String nama = penghuni.nama; //getIntent().getStringExtra("nama");
        String hp = penghuni.hp;//getIntent().getIntExtra("hp", 0);
        String gender = penghuni.gender;//getIntent().getStringExtra("gender");
        String status = penghuni.status;//getIntent().getStringExtra("jenjang");
        String fasilitas = penghuni.fasilitas;//getIntent().getStringExtra("hobi");
        String alamat = penghuni.alamat;//getIntent().getStringExtra("alamat");

        // Set data ke view
        textViewNama.setText(nama);
        textViewNomorHp.setText(hp);
        textViewGender.setText(gender);
        textViewStatus.setText(status);
        textViewFasilitas.setText(fasilitas);
        textViewAlamat.setText(alamat);
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
