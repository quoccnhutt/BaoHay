package com.example.bohay;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bohay.model.DanhMuc;
import com.example.bohay.model.TinTuc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    ListView lvDanhMuc;
    RecyclerView recycler_news;

    DanhMucAdapter danhMucAdapter;
    TinTucAdapter tinTucAdapter;

    ArrayList<DanhMuc> arrayListDanhmuc;
    ArrayList<TinTuc> tinTucArrayList;

    String url = "http://192.168.43.204/doancoso3/tintuc/list_category";
//    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDanhMuc = (ListView) findViewById(R.id.lvDanhmuc);
        recycler_news = (RecyclerView) findViewById(R.id.recyler_news);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Tin nóng");
//        getWindow().getDecorView().setBackgroundColor(Color.BLACK);


        arrayListDanhmuc = new ArrayList<>();
        tinTucArrayList = new ArrayList<>();

        loadDanhmuc();
        loadTintheoDanhmuc(1);


        danhMucAdapter = new DanhMucAdapter(MainActivity.this, arrayListDanhmuc);
        lvDanhMuc.setAdapter(danhMucAdapter);


        configRecyclerView();
        tinTucAdapter = new TinTucAdapter(MainActivity.this,tinTucArrayList);
        recycler_news.setAdapter(tinTucAdapter);

        lvDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tinTucArrayList.clear();

                String title = arrayListDanhmuc.get(i).getTendanhmuc();
                setTitle(title);
                int iddanhmuc = arrayListDanhmuc.get(i).getId_danhmuc();

                loadTintheoDanhmuc(iddanhmuc);
                configRecyclerView();
                tinTucAdapter = new TinTucAdapter(MainActivity.this,tinTucArrayList);
                recycler_news.setAdapter(tinTucAdapter);

//                Toast.makeText(MainActivity.this, arrayListDanhmuc.get(i).getId_danhmuc() + "", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case R.id.itemTinDaDoc:
                Toast.makeText(MainActivity.this, "Tin đã đọc", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemTinDaLuu:
                Toast.makeText(MainActivity.this, "Tin đã lưu", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemThongTin:
                Toast.makeText(MainActivity.this, "Thông tin ứng dụng", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemBandem:
                Toast.makeText(MainActivity.this, "Chế độ ban đêm", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemReload:
//                int iddanhmuc = 0;
//                loadTintheoDanhmuc(iddanhmuc);

                Toast.makeText(MainActivity.this, "Đã load", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.option_menu, menu);
//        MenuItem item = menu.add(1, 100, 300, "Settings");
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    private void loadDanhmuc(){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i< response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                String ten = object.getString("tendanhmuc");
                                int iddanhmuc = object.getInt("id_danhmuc");
                                arrayListDanhmuc.add(new DanhMuc(iddanhmuc ,ten, R.drawable.ic_baseline_arrow_right_24));
//                                Toast.makeText(MainActivity.this, arrayListDanhmuc.get(i).getId_danhmuc() + "okok", Toast.LENGTH_LONG).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            danhMucAdapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi đọc dữ liệu", Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void loadTintheoDanhmuc(int id){

        String iddm =String.valueOf(id);

        String urlTin = "http://192.168.43.204/doancoso3/tintuc/alltintuc/" + iddm;
//        String urlTin = "http://192.168.43.204/doancoso3/tintuc/alltintuc/1";

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, urlTin, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {



                        for (int i = 0; i< response.length(); i++){
                            try {

                                JSONObject object = response.getJSONObject(i);
                                int id_tin = object.getInt("id_tin");
                                String tieude = object.getString("tieude");
                                String urlImage = object.getString("anhminhhoa");
                                String ngaydang = object.getString("ngaydang");
                                String tendanhmuc = object.getString("tendanhmuc");
                                String nguontin = object.getString("nguontin");

                                tinTucArrayList.add(new TinTuc(id_tin ,tieude, urlImage ,ngaydang, tendanhmuc, nguontin));



//                              Toast.makeText(MainActivity.this, tinTucArrayList.get(i).getAnhminhhoa() + "okok", Toast.LENGTH_LONG).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            tinTucAdapter.notifyDataSetChanged();
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi đọc dữ liệu", Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recycler_news.hasFixedSize();
        recycler_news.setLayoutManager(layoutManager);
    }
}