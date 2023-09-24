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
import com.example.netclanexplorer.Models.PersonalUser;
import com.example.netclanexplorer.R;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    private ArrayList<BusinessUser> businessUsers;
    private Context context;
    public BusinessAdapter(ArrayList<BusinessUser> businessUsers, Context context) {
        this.businessUsers = businessUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.business_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusinessUser businessUser = businessUsers.get(position);
        holder.tvUsername.setText(businessUser.getName());
        holder.tvLocation.setText(businessUser.getLocation() + ", within " + businessUser.getDistanceRange() + " KM");
        holder.tvOtherDetails.setText(businessUser.getOccupation() + " | " + businessUser.getExperience() + " years of experience");
        if (businessUser.getAbout().length() > 0) {
            holder.tvAbout.setVisibility(View.VISIBLE);
            holder.tvAbout.setText(businessUser.getAbout());
        }
        holder.pbProfile.setProgress(Integer.parseInt(businessUser.getProfileProgress()));
        holder.ivProfilePicture.setImageResource(businessUser.getProfilePicture());

        holder.ivCall.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:"+businessUser.getContact().toString())));
        });
    }

    @Override
    public int getItemCount() {
        return businessUsers.size();
    }

    public void filterList(ArrayList<BusinessUser> businessUsers) {
        this.businessUsers = businessUsers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfilePicture;
        public TextView tvUsername, tvLocation, tvOtherDetails, tvAbout;
        public ProgressBar pbProfile;
        public ImageView ivCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvOtherDetails = itemView.findViewById(R.id.tv_other_details);
            tvAbout = itemView.findViewById(R.id.tv_about);
            pbProfile = itemView.findViewById(R.id.pb_profile);
            ivCall = itemView.findViewById(R.id.iv_call);
        }
    }
}
