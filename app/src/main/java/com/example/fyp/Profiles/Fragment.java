package com.example.fyp.Profiles;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fyp.Profiles.HouseOwner;
import com.example.fyp.Profiles.ProfileDisplay;

public class Fragment extends FragmentStateAdapter {
    public Fragment(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        if (position ==1){
            return new HouseOwner();
        }

          return new ProfileDisplay();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
