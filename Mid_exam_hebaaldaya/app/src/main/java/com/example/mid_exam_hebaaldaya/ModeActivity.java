package com.example.mid_exam_hebaaldaya;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NavUtils;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        // زر Up (Ancestral Navigation) -> الأب هو MainActivity (بالـ Manifest)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.mode_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back); // أيقونة الرجوع تبعتك
        }

        // TabLayout + ViewPager2 بثلاث تابات
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabsPagerAdapter(this));

        final String[] titles = {
                getString(R.string.tab_first),
                getString(R.string.tab_second),
                getString(R.string.tab_third)
        };
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();

        // زر Set Mode -> PopupMenu
        Button btnSetMode = findViewById(R.id.btn_set_mode);
        btnSetMode.setOnClickListener(this::showModePopup);

        // سلوك زر الرجوع المخصص
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0); // من Second/Third -> First
                } else {
                    finish(); // من First -> يرجع على MainActivity
                }
            }
        });
    }

    private void showModePopup(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_mode, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.mode_light) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            } else if (id == R.id.mode_night) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                return true;
            } else if (id == R.id.mode_system) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                return true;
            }
            return false;
        });
        popup.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this); // يرجع مباشرة على MainActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
