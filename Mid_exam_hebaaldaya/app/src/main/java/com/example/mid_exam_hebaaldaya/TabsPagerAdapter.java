package com.example.mid_exam_hebaaldaya;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsPagerAdapter extends FragmentStateAdapter {

    private static final int TAB_COUNT = 3;

    public TabsPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TabFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
