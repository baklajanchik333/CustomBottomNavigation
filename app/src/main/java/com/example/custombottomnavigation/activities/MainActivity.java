package com.example.custombottomnavigation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.custombottomnavigation.R;
import com.example.custombottomnavigation.fragments.AboutFragment;
import com.example.custombottomnavigation.fragments.HomeFragment;
import com.example.custombottomnavigation.fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_info));

        bottomNavigation.setOnShowListener(item -> {
            Fragment fragment = null;

            switch (item.getId()) {
                case 1:
                    fragment = new NotificationFragment();
                    break;
                case 2:
                    fragment = new HomeFragment();
                    break;
                case 3:
                    fragment = new AboutFragment();
                    break;
            }

            loadFragment(fragment);
        });

        bottomNavigation.setCount(1, "10");
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(item -> Toast.makeText(MainActivity.this, R.string.toastClicked + item.getId(), Toast.LENGTH_SHORT).show());

        bottomNavigation.setOnReselectListener(item -> Toast.makeText(MainActivity.this, R.string.toastReselected + item.getId(), Toast.LENGTH_SHORT).show());
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }
}