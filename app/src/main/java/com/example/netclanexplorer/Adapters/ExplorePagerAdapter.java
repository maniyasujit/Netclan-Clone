package com.example.netclanexplorer.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ExplorePagerAdapter extends PagerAdapter {

    ArrayList<Fragment> fragmentListExplore = new ArrayList<>();
    ArrayList<String> fragmentTitleListExplore = new ArrayList<>();
    FragmentManager fragmentManager;

    public ExplorePagerAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentListExplore.add(fragment);
        fragmentTitleListExplore.add(title);
    }

    @Override
    public int getCount() {
        return fragmentListExplore.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((Fragment) object).getView();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = fragmentListExplore.get(position);
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction().add(fragment, fragment.getClass().getSimpleName()).commit();
            fragmentManager.executePendingTransactions();
        }
        if (fragment.getView() != null) {
            container.addView(fragment.getView());
        }
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = fragmentListExplore.get(position);
        container.removeView(fragment.getView());
        if (!fragmentManager.isDestroyed()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleListExplore.get(position);
    }
}
