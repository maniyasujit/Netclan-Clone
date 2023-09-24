package com.example.netclanexplorer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.netclanexplorer.Adapters.ExplorePagerAdapter;
import com.example.netclanexplorer.R;
import com.google.android.material.tabs.TabLayout;

public class ExploreFragment extends Fragment {

    private TabLayout tlExplore;
    private ViewPager vpExplore;
    private FragmentManager fragmentManager;

    public ExploreFragment() {
    }

    public ExploreFragment(FragmentManager fm) {
        fragmentManager = fm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        tlExplore = view.findViewById(R.id.tl_explore);
        vpExplore = view.findViewById(R.id.vp_explore);

        if (fragmentManager != null) {
            ExplorePagerAdapter explorePagerAdapter = new ExplorePagerAdapter(fragmentManager);
            explorePagerAdapter.addFragment(new PersonalFragment(), "Personal");
            explorePagerAdapter.addFragment(new BusinessFragment(), "Business");
            explorePagerAdapter.addFragment(new MerchantFragment(), "Merchant");
            vpExplore.setAdapter(explorePagerAdapter);
            tlExplore.setupWithViewPager(vpExplore);
        }

        return view;
    }
}