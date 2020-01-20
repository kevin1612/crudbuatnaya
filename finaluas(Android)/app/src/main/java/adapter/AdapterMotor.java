package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.finaluas.KatalogActivity;
import com.example.finaluas.LihatKatalog;
import com.example.finaluas.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import model.MotorModel;
import server.configurl;

public class AdapterMotor extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MotorModel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public AdapterMotor(Activity activity, List<MotorModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_motor, null);

        TextView war = (TextView) convertView.findViewById(R.id.txtwarna);
        TextView namamotor = (TextView) convertView.findViewById(R.id.txtnama);
        TextView jenismotor = (TextView) convertView.findViewById(R.id.txtjenismotor);
        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);



        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, LihatKatalog.class);
                i.putExtra("_id", item.get(position).getId());
                i.putExtra("kodemotor", item.get(position).getKodemotor());
                i.putExtra("namamotor", item.get(position).getNamamotor());
                i.putExtra("jenismotor", item.get(position).getJenismotor());
                i.putExtra("warna", item.get(position).getWarna());
                i.putExtra("stok", item.get(position).getStok());
                i.putExtra("harga", item.get(position).getHarga());
                i.putExtra("detailorupdate", "detail");
                activity.startActivity(i);
                activity.finish();
            }
        });



        namamotor.setText(item.get(position).getNamamotor());
        jenismotor.setText(item.get(position).getJenismotor());
        war.setText(item.get(position).getWarna());

        return convertView;
    }




    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
