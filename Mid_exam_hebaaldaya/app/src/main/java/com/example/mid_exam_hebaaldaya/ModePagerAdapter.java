package com.example.mid_exam_hebaaldaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ModePagerAdapter extends FragmentStateAdapter {

    public ModePagerAdapter(@NonNull AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new FirstFragment();
        if (position == 1) return new SecondFragment();
        return new ThirdFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
