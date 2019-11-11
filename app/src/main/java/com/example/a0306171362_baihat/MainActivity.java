package com.example.a0306171362_baihat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView rcvDSBaiHat;
    private DSBaiHatAdapter adapter;
    private List<BaiHat> baiHats = new ArrayList<>();
    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";
    public static final int PAGE_SIZE = 15;
    public int totalPage = 0;
    public boolean checkLoading = false;
    public boolean checkLastPage = false;
    public int currentPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiation();
        createAdapter();
        loadData(null);
        checkScroll();

    }

    private void checkScroll() {
        rcvDSBaiHat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcvDSBaiHat.getLayoutManager();
                int itemCount = linearLayoutManager.getItemCount();
                int childCount = linearLayoutManager.getChildCount();
                int findNextChild = linearLayoutManager.findFirstVisibleItemPosition();
                if (!checkLoading && !checkLastPage){
                    if ((childCount+findNextChild) >=itemCount && findNextChild > 0 &&  itemCount >= PAGE_SIZE){
                        checkLoading = true;

                        currentPage ++;

                        baiHats.add(null);
                        adapter.notifyItemInserted(baiHats.size()-1);

                        Bundle data = new Bundle();
                        data.putInt(KEY_PAGE,currentPage);
                        data.putInt(KEY_LIMIT,PAGE_SIZE);
                        loadData(data);
                    }
                }
            }
        });

    }

    private void loadData(Bundle data) {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null){
            networkInfo = manager.getActiveNetworkInfo();
        }
        if (networkInfo != null & networkInfo.isConnected()){
            startLoader(data);
        }else{
            Toast.makeText(this,"Bạn Cần Kết nối Internet",Toast.LENGTH_LONG).show();
        }


    }

    private void startLoader(Bundle data) {
        if (getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().restartLoader(0,data,this);
        }
        getSupportLoaderManager().initLoader(0,data,this);
    }

    private void createAdapter() {
        adapter = new DSBaiHatAdapter(this,baiHats);
        rcvDSBaiHat.setLayoutManager(new LinearLayoutManager(this));
        rcvDSBaiHat.setAdapter(adapter);
    }

    private void radiation() {
        rcvDSBaiHat = findViewById(R.id.rcvDSBaiHat);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        int page =1;
        int limit = 20;
        if (args!=null){
            page = args.getInt(KEY_PAGE);
            limit = args.getInt(KEY_LIMIT);
        }
        return new BaiHatLoader(this,page,limit);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("AAAAA",data);
        try {

            JSONObject objBaiHat = new JSONObject(data);
            JSONObject objdata = objBaiHat.getJSONObject("data");
            int total = objdata.getInt("total");
            totalPage = total / PAGE_SIZE;
            if (totalPage % PAGE_SIZE !=0){
                totalPage++;
            }

            if (baiHats.size() > 0){
                baiHats.remove(baiHats.size() -1 );
                adapter.notifyItemRemoved(baiHats.size());
            }


            JSONArray arrDSBaiHat = objdata.getJSONArray("ds_bai_hat");
            for (int i = 0; i <arrDSBaiHat.length() ; i++) {
                JSONObject objItem = arrDSBaiHat.getJSONObject(i);
                int id = objItem.getInt("id");
                String tieu_de = objItem.getString("tieu_de");
                String tac_gia = objItem.getString("tac_gia");
                String loi_bai_hat = objItem.getString("loi_bai_hat");
                BaiHat baiHat = new BaiHat();
                baiHat.setId(id);
                baiHat.setTieuDe(tieu_de);
                baiHat.setTacGia(tac_gia);
                baiHat.setLoiBaiHat(loi_bai_hat);
                baiHats.add(baiHat);

            }
            checkLoading = false;
            checkLastPage = (currentPage == totalPage);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
