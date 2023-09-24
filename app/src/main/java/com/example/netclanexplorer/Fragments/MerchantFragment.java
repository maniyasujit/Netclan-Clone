package com.example.netclanexplorer.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.netclanexplorer.Adapters.MerchantAdapter;
import com.example.netclanexplorer.Models.MerchantUser;
import com.example.netclanexplorer.R;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MerchantFragment extends Fragment {

    private EditText etSearch;
    private ImageView ivCloseSearch;
    private RecyclerView rvMerchant;
    private FloatingActionMenu fabMenu;
    private MerchantAdapter merchantAdapter;
    private SwipeRefreshLayout srlMerchant;
    private ArrayList<MerchantUser> merchantUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_merchant, container, false);


        etSearch = view.findViewById(R.id.et_search);
        ivCloseSearch = view.findViewById(R.id.iv_close_search);
        rvMerchant = view.findViewById(R.id.rv_merchant);
        fabMenu = view.findViewById(R.id.fab_menu);
        srlMerchant = view.findViewById(R.id.srl_merchant);

        merchantUsers = new ArrayList<>();
        merchantAdapter = new MerchantAdapter(merchantUsers, getContext());
        rvMerchant.setHasFixedSize(true);
        rvMerchant.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMerchant.setAdapter(merchantAdapter);
        getUserData();

        rvMerchant.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 10 && !fabMenu.isMenuButtonHidden()) {
                    fabMenu.hideMenuButton(true);
                }
                if (dy < -10 && fabMenu.isMenuButtonHidden()) {
                    fabMenu.showMenuButton(true);
                }
            }
        });

        ivCloseSearch.setOnClickListener(v -> {
            etSearch.setText("");
            merchantAdapter.filterList(merchantUsers);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    ivCloseSearch.setVisibility(View.INVISIBLE);
                } else {
                    ivCloseSearch.setVisibility(View.VISIBLE);
                }
            }
        });

        srlMerchant.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlMerchant.setRefreshing(false);
                getUserData();
            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<MerchantUser> filteredList = new ArrayList<>();

        for (MerchantUser item : merchantUsers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        merchantAdapter.filterList(filteredList);
    }

    private ArrayList<MerchantUser> getUserData() {
        ArrayList<MerchantUser> merchantUsers = new ArrayList<>();
        merchantUsers.add(new MerchantUser("Sujit", "9895898569", "Banker", "Surat", "200",
                "20", R.drawable.ic_user));
        merchantUsers.add(new MerchantUser("Sujit 1", "9895898569", "Doctor", "Ahmedabad", "200",
                "20", R.drawable.ic_profile));
        merchantUsers.add(new MerchantUser("Sujit 2", "9895898569", "", "Baroda", "200",
                "20", R.drawable.ic_group));
        merchantUsers.add(new MerchantUser("Sujit 3", "9895898569", "Pathologist", "Mumbai", "200",
                "20", R.drawable.ic_user));
        merchantUsers.add(new MerchantUser("Sujit 4", "9895898569", "Tour operator", "", "200",
                "20", R.drawable.ic_profile));
        this.merchantUsers.addAll(merchantUsers);
        merchantAdapter.notifyDataSetChanged();
        return merchantUsers;
    }
}