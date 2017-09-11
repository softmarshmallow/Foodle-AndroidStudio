package com.softmarshmallow.foodle.Views.Test;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class TestActivity extends AppCompatActivity implements VerticalStepperForm
{


        @BindView(R.id.vertical_stepper_form)
        VerticalStepperFormLayout verticalStepperForm;


        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);
                ButterKnife.bind(this);

                InitStepper();

        }

        void InitStepper(){
                String[] stepsTitles = {"푸드트럭 이름", "푸드트럭 짧은 설명", "푸드트럭 풀 설명"};

                int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

                VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm,
                        stepsTitles, this, this)
                        //.stepsSubtitles(stepsSubtitles)

                        //.materialDesignInDisabledSteps(true) // false by default
                        //.showVerticalLineWhenStepsAreCollapsed(true) // false by default
//                        .primaryColor(colorPrimary)
//                        .primaryDarkColor(colorPrimaryDark)
                        .displayBottomNavigation(true)
                        .init();

        }

        @Override
        public View createStepContentView(int stepNumber) {
                return new View(this);
        }

        @Override
        public void onStepOpening(int stepNumber) {
                checkSomething();
        }

        @Override
        public void sendData() {

        }



        boolean checkSomething(){
                verticalStepperForm.setActiveStepAsCompleted();

                return true;
        }
}


