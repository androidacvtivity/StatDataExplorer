package com.bancusoft.statdataexplorer.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bancusoft.statdataexplorer.utils.Utils;
import androidx.appcompat.app.AppCompatActivity;


import com.bancusoft.statdataexplorer.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    //our splash screen views
    private ImageView mLogo;
    private TextView mainTitle;

    /**
     * Let's initialize our widgets.
     */
    private void initializeWidgets() {
        mLogo = findViewById(R.id.mLogo);
        mainTitle = findViewById(R.id.mainTitle);
        // subTitle = findViewById(R.id.subTitle);
    }

    /**
     * Let's show our Splash animation using Animation class. We fade in our widgets.
     */
    private void showSplashAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom);
        mLogo.startAnimation(animation);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mainTitle.startAnimation(fadeIn);
        // subTitle.startAnimation(fadeIn);
    }
    /**
     * Let's go to our DashBoard after 2 seconds
     */
    private void goToDashboard() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Utils.openActivity(SplashActivity.this, MainActivity.class);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    /**
     * Let's Override attachBaseContext method
     */
    @Override
    protected void attachBaseContext(Context newBase) {
      //  super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
        super.attachBaseContext(newBase);
    }

    /**
     * Let's create our onCreate method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // Find the TextView that should open the disclaimer content
//      //  TextView disclaimerTextView = findViewById(R.id.mdisclaimer);
//        disclaimerTextView.setOnClickListener(v -> {
//            Intent intent = new Intent(SplashActivity.this, EmployeesListActivity.class);
//            startActivity(intent);
//        });

        this.initializeWidgets();
        this.showSplashAnimation();
        this.goToDashboard();
    }

}
