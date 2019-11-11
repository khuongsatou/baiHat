package com.example.a0306171362_baihat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import java.util.List;

public class DSBaiHatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_LOADING = 0;
    private static final int TYPE_BAI_HAT =1 ;
    public static final String KEY_BAI_HAT = "bai_hat";
    private Context context;
    private List<BaiHat> baiHats;

    public DSBaiHatAdapter(Context context, List<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_LOADING == viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_loading,parent,false);
            return new LoadingHolder(view);
        }else if(TYPE_BAI_HAT == viewType){
            View view = LayoutInflater.from(context).inflate(R.layout.custom_item_bai_hat,parent,false);
            return new BaiHatHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaiHat baiHat = baiHats.get(position);
        if (holder instanceof BaiHatHolder){
            BaiHatHolder baiHatHolder = (BaiHatHolder) holder;

            baiHatHolder.tvTenBaiHat.setText(baiHat.getTieuDe());
            baiHatHolder.tvTenTacGia.setText(baiHat.getTacGia());
        }
    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    @Override
    public int getItemViewType(int position) {
        return baiHats.get(position) == null ? TYPE_LOADING : TYPE_BAI_HAT;
    }

    class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class BaiHatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivMusic;
        private TextView tvTenBaiHat,tvTenTacGia;
        private CardView cartView;
        public BaiHatHolder(@NonNull View itemView) {
            super(itemView);

            ivMusic = itemView.findViewById(R.id.imageView);
            tvTenBaiHat = itemView.findViewById(R.id.tvTenBaiHat);
            tvTenTacGia = itemView.findViewById(R.id.tvTacGia);
            cartView = itemView.findViewById(R.id.cvItem);
            cartView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,SecondActivity.class);
            intent.putExtra(KEY_BAI_HAT,baiHats.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }
}
