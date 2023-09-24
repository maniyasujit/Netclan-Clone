package com.example.netclanexplorer.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.netclanexplorer.Adapters.PersonalAdapter;
import com.example.netclanexplorer.Models.PersonalUser;
import com.example.netclanexplorer.R;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {

    private EditText etSearch;
    private ImageView ivCloseSearch;
    private RecyclerView rvPersonal;
    private FloatingActionMenu fabMenu;
    private PersonalAdapter personalAdapter;
    private SwipeRefreshLayout srlPersonal;
    private ArrayList<PersonalUser> personalUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        etSearch = view.findViewById(R.id.et_search);
        ivCloseSearch = view.findViewById(R.id.iv_close_search);
        rvPersonal = view.findViewById(R.id.rv_personal);
        fabMenu = view.findViewById(R.id.fab_menu);
        srlPersonal = view.findViewById(R.id.srl_personal);

        personalUsers = new ArrayList<>();
        personalAdapter = new PersonalAdapter(personalUsers, getContext());
        rvPersonal.setHasFixedSize(true);
        rvPersonal.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPersonal.setAdapter(personalAdapter);
        getUserData();

        rvPersonal.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
            personalAdapter.filterList(personalUsers);
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

        srlPersonal.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlPersonal.setRefreshing(false);
                getUserData();
            }
        });
        return view;
    }

    private void filter(String text) {
        ArrayList<PersonalUser> filteredList = new ArrayList<>();

        for (PersonalUser item : personalUsers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        personalAdapter.filterList(filteredList);
    }

    private ArrayList<PersonalUser> getUserData() {
        ArrayList<PersonalUser> personalUsers = new ArrayList<>();
        personalUsers.add(new PersonalUser("Sujit", "21", "Male", "9898989898", "Coffee | Business | Friendship",
                "20", R.drawable.ic_profile, "Always ready to learn new things", "Surat", "Graduate",
                "Android Developer", "Developer", "300-500", new String[]{"Reading", "Cooking", "Hiking"},
                new String[]{"Inception", "The Shawshank Redemption", "Pulp Fiction"}));
        personalUsers.add(new PersonalUser("Sujit 1", "21", "Male", "9898989898", "Coffee | Business | Friendship",
                "50", R.drawable.ic_business_cards, "", "Surat", "Graduate",
                "Android Developer", "Developer", "300-500", new String[]{"Reading", "Cooking", "Hiking"},
                new String[]{"Inception", "The Shawshank Redemption", "Pulp Fiction"}));
        personalUsers.add(new PersonalUser("Sujit 2", "21", "Male", "9898989898", "Coffee | Business | Friendship",
                "40", R.drawable.ic_group, "", "Surat", "Graduate",
                "Android Developer", "Developer", "300-500", new String[]{"Reading", "Cooking", "Hiking"},
                new String[]{"Inception", "The Shawshank Redemption", "Pulp Fiction"}));
        personalUsers.add(new PersonalUser("Sujit 3", "21", "Male", "9898989898", "Coffee | Business | Friendship",
                "80", R.drawable.ic_profile, "", "Surat", "Graduate",
                "Android Developer", "Developer", "300-500", new String[]{"Reading", "Cooking", "Hiking"},
                new String[]{"Inception", "The Shawshank Redemption", "Pulp Fiction"}));
        personalUsers.add(new PersonalUser("Sujit 3", "21", "Male", "9898989898", "Coffee | Business | Friendship",
                "10", R.drawable.ic_business_cards, "Eager to contribute in the development process", "Surat", "Graduate",
                "Android Developer", "Developer", "300-500", new String[]{"Reading", "Cooking", "Hiking"},
                new String[]{"Inception", "The Shawshank Redemption", "Pulp Fiction"}));
        this.personalUsers.addAll(personalUsers);
        personalAdapter.notifyDataSetChanged();
        return personalUsers;
    }
}