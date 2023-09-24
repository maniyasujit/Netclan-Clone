package com.example.netclanexplorer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netclanexplorer.Models.PersonalUser;
import com.example.netclanexplorer.R;

import java.util.ArrayList;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.ViewHolder> {

    private ArrayList<PersonalUser> personalUsers;
    private Context context;

    public PersonalAdapter(ArrayList<PersonalUser> personalUsers, Context context) {
        this.personalUsers = personalUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonalUser personalUser = personalUsers.get(position);
        holder.tvUsername.setText(personalUser.getName());
        holder.tvLocation.setText(personalUser.getLocation());
        holder.tvOccupation.setText(personalUser.getOccupation());
        holder.tvDistance.setText("Within " + personalUser.getDistanceRange() + " m");
        holder.tvOtherDetails.setText(personalUser.getOtherDetails());
        if (personalUser.getAbout().length() > 0) {
            holder.tvAbout.setVisibility(View.VISIBLE);
            holder.tvAbout.setText(personalUser.getAbout());
        }
        holder.pbProfile.setProgress(Integer.parseInt(personalUser.getProfileProgress()));
        holder.ivProfilePicture.setImageResource(personalUser.getProfilePicture());
    }

    @Override
    public int getItemCount() {
        return personalUsers.size();
    }

    public void filterList(ArrayList<PersonalUser> personalUsers) {
        this.personalUsers = personalUsers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfilePicture;
        public TextView tvUsername, tvLocation, tvOccupation, tvDistance, tvOtherDetails, tvAbout;
        public ProgressBar pbProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvOccupation = itemView.findViewById(R.id.tv_occupation);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            tvOtherDetails = itemView.findViewById(R.id.tv_other_details);
            tvAbout = itemView.findViewById(R.id.tv_about);
            pbProfile = itemView.findViewById(R.id.pb_profile);
        }
    }
}
