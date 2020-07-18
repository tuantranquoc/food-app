package com.example.giaodien_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.giaodien_v1.adapter.SectionFragmentStateAdapter;
import com.example.giaodien_v1.fragments.LoginFragment;
import com.example.giaodien_v1.fragments.SignUpFragment;

public class AuhtActivity extends AppCompatActivity {
    private SectionFragmentStateAdapter sectionFragmentStateAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        sectionFragmentStateAdapter = new SectionFragmentStateAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.activity_auth_layout);

        setUpViewPager(viewPager);
    }

    public void setUpViewPager(ViewPager viewPager){
        SectionFragmentStateAdapter adapter = new SectionFragmentStateAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(),"Login");
        adapter.addFragment(new SignUpFragment(),"SignUp");
        viewPager.setAdapter(adapter);
    }

    public void setViewPage(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }
}
