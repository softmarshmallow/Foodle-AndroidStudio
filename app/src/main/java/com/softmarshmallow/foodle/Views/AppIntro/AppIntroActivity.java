package com.softmarshmallow.foodle.Views.AppIntro;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.softmarshmallow.foodle.R;

public class AppIntroActivity extends AppIntro
{
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setTheme(R.style.AppTheme_FullScreen);

                // Note here that we DO NOT use setContentView();

                // Add your slide fragments here.
                // AppIntro will automatically generate the dots indicator and buttons.
/*                addSlide(firstFragment);
                addSlide(secondFragment);
                addSlide(thirdFragment);
                addSlide(fourthFragment);*/

                // Instead of fragments, you can also use our default slide
                // Just set a title, description, background and image. AppIntro will do the rest.
                addSlide(AppIntroFragment.newInstance("어서오세요!", "불러라, 갈지여다.\n푸들", R.drawable.app_icon_red_v3, Color.RED));
                addSlide(AppIntroFragment.newInstance("전국 푸드트럭", "요즘 잘나가는 푸드트럭들", R.drawable.app_icon_red_v3, Color.LTGRAY));
                addSlide(AppIntroFragment.newInstance("푸드트럭 행사", "색다른 여행을 떠나보세요.", R.drawable.app_icon_red_v3, Color.DKGRAY));
                addSlide(AppIntroFragment.newInstance("케이터링", "어디 불러보시지요, 직접 찾아갑니다.", R.drawable.app_icon_red_v3, Color.GREEN));
                addSlide(AppIntroFragment.newInstance("내주변, 모든것", "푸들.", R.drawable.app_icon_red_v3, Color.GRAY));

                // OPTIONAL METHODS
                // Override bar/separator color.
                setBarColor(Color.LTGRAY);
                setSeparatorColor(Color.WHITE);

                // Hide Skip/Done button.
                showSkipButton(true);
                setProgressButtonEnabled(true);

                // Turn vibration on and set intensity.
                // NOTE: you will probably need to ask VIBRATE permission in Manifest.
                setVibrate(true);
                setVibrateIntensity(30);
        }

        @Override
        public void onSkipPressed(Fragment currentFragment) {
                super.onSkipPressed(currentFragment);
                // Do something when users tap on Skip button.
        }

        @Override
        public void onDonePressed(Fragment currentFragment) {
                super.onDonePressed(currentFragment);
                // Do something when users tap on Done button.
        }

        @Override
        public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
                super.onSlideChanged(oldFragment, newFragment);
                // Do something when the slide changes.
        }
}
