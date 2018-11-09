package id.co.kosankoding.kosanku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.kosankoding.kosanku.R;
import id.co.kosankoding.kosanku.model.Penghuni;

public class PenghuniAdapter extends ArrayAdapter<Penghuni> {

    public PenghuniAdapter(Context context, List<Penghuni> siswaList) {
        super(context, R.layout.list_item_penghuni, siswaList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_penghuni, null);
        }

        // ambil view dari layout
        TextView namaTextView = (TextView) view.findViewById(R.id.nama);
        TextView statusTextView = (TextView) view.findViewById(R.id.status);
        TextView genderTextView = (TextView) view.findViewById(R.id.gender);
        TextView hpTextView = (TextView) view.findViewById(R.id.hp);

        // ambil data pada posisi tertentu
        Penghuni penghuni = getItem(position);

        // set data ke view
        namaTextView.setText(penghuni.nama);
        statusTextView.setText(penghuni.status);
        genderTextView.setText(penghuni.gender);
        hpTextView.setText(Long.toString(penghuni.hp));

        return view;
    }

}
