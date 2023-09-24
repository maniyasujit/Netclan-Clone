package com.example.netclanexplorer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netclanexplorer.Models.BusinessUser;
import com.example.netclanexplorer.Models.MerchantUser;
import com.example.netclanexplorer.R;

import java.util.ArrayList;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.ViewHolder> {

    private ArrayList<MerchantUser> merchantUsers;
    private Context context;

    public MerchantAdapter(ArrayList<MerchantUser> merchantUsers, Context context) {
        this.merchantUsers = merchantUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.merchant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MerchantUser merchantUser = merchantUsers.get(position);
        holder.tvUsername.setText(merchantUser.getName());
        holder.tvLocation.setText(merchantUser.getLocation() + ", within " + merchantUser.getDistanceRange() + " m");
        if (merchantUser.getAbout().length() > 0) {
            holder.tvAbout.setVisibility(View.VISIBLE);
            holder.tvAbout.setText(merchantUser.getAbout());
        }
        holder.pbProfile.setProgress(Integer.parseInt(merchantUser.getProfileProgress()));
        holder.ivProfilePicture.setImageResource(merchantUser.getProfilePicture());

        holder.ivCall.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + merchantUser.getContact().toString())));
        });
    }

    @Override
    public int getItemCount() {
        return merchantUsers.size();
    }

    public void filterList(ArrayList<MerchantUser> merchantUsers) {
        this.merchantUsers = merchantUsers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfilePicture;
        public TextView tvUsername, tvLocation, tvAbout;
        public ProgressBar pbProfile;
        public ImageView ivCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvAbout = itemView.findViewById(R.id.tv_about);
            pbProfile = itemView.findViewById(R.id.pb_profile);
            ivCall = itemView.findViewById(R.id.iv_call);
        }
    }
}
