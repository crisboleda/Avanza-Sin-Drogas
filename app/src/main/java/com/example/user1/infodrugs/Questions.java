package com.example.user1.infodrugs;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Questions extends AppCompatActivity {

    private Button btnSkip, btnNext;
    private ViewPager sViewPager;
    private LinearLayout dotsLayout;
    private TextView dots[];
    private RadioButton radioSi,radioNo;
    private EditText ed_respuesta;

    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_questions);
        getSupportActionBar().hide();

        initView();

        // create Adapter object
        sliderAdapter = new SliderAdapter(this);
        // set adapter in ViewPager
        sViewPager.setAdapter(sliderAdapter);
        // set PageChangeListener
        sViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // adding bottom dots -> addBottomDots(0);
//      addDotIndicator(0);
        addBottomDots(0);
    }


    // btnSkipClick
    public  void btnSkipClick(View v) {
        launchHomeScreen();
    }

    //btnNextClick
    public  void btnNextClick(View v) {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(1);
//        if (current < layouts.length) {
        if (current < sliderAdapter.image_slide.length) {
            // move to next screen
            sViewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    private void launchHomeScreen() {
        startActivity(new Intent(this, ResultActivity.class));
        finish();
    }

    private int getItem(int i) {
        return sViewPager.getCurrentItem() + i;
    }

    private void initView() {
        sViewPager = (ViewPager) findViewById(R.id.sViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
        radioNo = (RadioButton) findViewById(R.id.radio_no);
        radioSi = (RadioButton) findViewById(R.id.radio_si);
        ed_respuesta =(EditText) findViewById(R.id.editText_questions);
    }


    // viewPagerPage ChangeListener according to Dots-Points
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == sliderAdapter.image_slide.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
            /*
            if (position == 8){
                radioNo.setVisibility(View.INVISIBLE);
                radioSi.setVisibility(View.INVISIBLE);
                ed_respuesta.setVisibility(View.VISIBLE);
            } else {
                radioNo.setVisibility(View.VISIBLE);
                radioSi.setVisibility(View.VISIBLE);
                ed_respuesta.setVisibility(View.INVISIBLE);
            }*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };



    // add dot indicator
    public void addDotIndicator(){
        dots = new TextView[10];
        for (int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8266;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.cardview_dark_background));

            dotsLayout.addView(dots[i]);
        }
    }

    // set of Dots points
    private void addBottomDots(int currentPage) {
//        dots = new TextView[layouts.length];
        dots = new TextView[10];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.cardview_dark_background));  // dot_inactive
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorPrimaryDark)); // dot_active
    }

}