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

import com.example.netclanexplorer.Adapters.BusinessAdapter;
import com.example.netclanexplorer.Adapters.PersonalAdapter;
import com.example.netclanexplorer.Models.BusinessUser;
import com.example.netclanexplorer.Models.PersonalUser;
import com.example.netclanexplorer.R;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class BusinessFragment extends Fragment {

    private EditText etSearch;
    private ImageView ivCloseSearch;
    private RecyclerView rvBusiness;
    private FloatingActionMenu fabMenu;
    private BusinessAdapter businessAdapter;
    private SwipeRefreshLayout srlBusiness;
    private ArrayList<BusinessUser> businessUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_business, container, false);

        etSearch = view.findViewById(R.id.et_search);
        ivCloseSearch = view.findViewById(R.id.iv_close_search);
        rvBusiness = view.findViewById(R.id.rv_business);
        fabMenu = view.findViewById(R.id.fab_menu);
        srlBusiness = view.findViewById(R.id.srl_business);

        businessUsers = new ArrayList<>();
        businessAdapter = new BusinessAdapter(businessUsers, getContext());
        rvBusiness.setHasFixedSize(true);
        rvBusiness.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBusiness.setAdapter(businessAdapter);
        getUserData();

        rvBusiness.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
            businessAdapter.filterList(businessUsers);
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

        srlBusiness.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlBusiness.setRefreshing(false);
                getUserData();
            }
        });
        return view;
    }

    private void filter(String text) {
        ArrayList<BusinessUser> filteredList = new ArrayList<>();

        for (BusinessUser item : businessUsers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        businessAdapter.filterList(filteredList);
    }

    private ArrayList<BusinessUser> getUserData() {
        ArrayList<BusinessUser> businessUsers = new ArrayList<>();
        businessUsers.add(new BusinessUser("Sujit", "21", "Male", "9898989898", "",
                "Surat" , "20", "Android Developer", "40", R.drawable.ic_user, "0"));
        businessUsers.add(new BusinessUser("Sujit 1", "22", "Male", "9898989898", "Always eager to help others",
                "Bharuch" , "15", "Android Developer", "20", R.drawable.ic_profile, "4"));
        businessUsers.add(new BusinessUser("Sujit 2", "21", "Male", "9898989898", "",
                "Botad" , "20", "Android Developer", "60", R.drawable.ic_user, "6"));
        businessUsers.add(new BusinessUser("Sujit 3", "35", "Male", "9898989898", "Always ready to work",
                "Surat" , "4", "Android Developer", "10", R.drawable.ic_user, "3"));
        businessUsers.add(new BusinessUser("Sujit 4", "20", "Male", "9898989898", "",
                "Ahmedabad" , "8", "Android Developer", "20", R.drawable.ic_profile, "2"));

        this.businessUsers.addAll(businessUsers);
        businessAdapter.notifyDataSetChanged();
        return businessUsers;
    }
}