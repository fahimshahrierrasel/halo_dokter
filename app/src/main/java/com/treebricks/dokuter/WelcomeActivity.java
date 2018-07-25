package com.treebricks.dokuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.treebricks.dokuter.utils.AppPreferenceManager;

public class WelcomeActivity extends AppIntro2 {
    private AppPreferenceManager appPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        appPreferenceManager = new AppPreferenceManager(this);
        if (!appPreferenceManager.isFirstTimeLaunch()) {
            loaLoginActivity();
            finish();
        }

        addSlide(AppIntro2Fragment.newInstance("Doctor Appointment", "Book an appointment with the right doctor",
                R.drawable.vector_drawable_agenda, getResources().getColor(R.color.md_red_500)));

        addSlide(AppIntro2Fragment.newInstance("Online Chat", "Too Busy to see a doctor! Chat online instead.",
                R.drawable.vector_drawable_agenda, getResources().getColor(R.color.md_teal_500)));

        addSlide(AppIntro2Fragment.newInstance("Medical History", "Keep your medical history handy and secure",
                R.drawable.vector_drawable_agenda, getResources().getColor(R.color.md_purple_500)));

        showStatusBar(false);
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    private void loaLoginActivity() {
        appPreferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loaLoginActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        loaLoginActivity();
    }
}
