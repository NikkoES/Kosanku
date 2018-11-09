package id.co.kosankoding.kosanku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.List;

import id.co.kosankoding.kosanku.adapter.PenghuniAdapter;
import id.co.kosankoding.kosanku.model.Penghuni;

public class ListActivity extends AppCompatActivity {

    ListView listView;

    PenghuniAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ambil view dari layout
        listView = (ListView) findViewById(R.id.listView);

        // ambil data dari database
        List<Penghuni> penghuniList = new Select().from(Penghuni.class).execute();
        // definisikan array nama siswa sebanyak jumlah siswa
        String[] namaPenghuniArray = new String[penghuniList.size()];

        // copy semua nama siswa ke dalam namaSiswaArray
        for (int i = 0; i < penghuniList.size(); i++) {
            Penghuni penghuni = penghuniList.get(i);
            namaPenghuniArray[i] = penghuni.nama;
        }

        // mendefinisikan adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, namaPenghuniArray);

        // pakai custom adapter
        adapter2 = new PenghuniAdapter(this, penghuniList);

        //pasangkan adapter ke listView
        listView.setAdapter(adapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Penghuni penghuni = adapter2.getItem(position);
                Toast.makeText(ListActivity.this, "" + penghuni.nama, Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
//                intent.putExtra("id", siswa.getId());
//                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            // pindah dari scren list(ListActivity) ke screen form (MainActivity)
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
