package demo.welcomeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeHelper;

import java.util.ArrayList;
import java.util.List;

import demo.welcomeactivity.activity.SampleWelcomeActivity;

public class MainActivity extends AppCompatActivity {

    private WelcomeHelper sampleWelcomeScreen;
    private List<ScreenItem> welcomeScreens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The welcome screen for this app (only one that automatically shows)
        sampleWelcomeScreen = new WelcomeHelper(this, SampleWelcomeActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);

        //List of welcome screens
        welcomeScreens.add(new ScreenItem(R.string.title_sample, R.string.description_sample, sampleWelcomeScreen, null));
//        welcomeScreens.add(new ScreenItem(R.string.title_button_bar, R.string.description_button_bar, ButtonBarWelcomeActivity.class, REQUEST_WELCOME_SCREEN_RESULT));
//        welcomeScreens.add(new ScreenItem(R.string.title_included_pages, R.string.description_included_pages, IncludedPagesWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_single_button, R.string.description_single_button, ButtonBarSingleWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_indicator_only, R.string.description_indicator_only, IndicatorOnlyWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_custom_page, R.string.description_custom_page, CustomPageWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_light, R.string.description_light, LightWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_no_skip, R.string.description_no_skip, NoSkipWelcomeActivity.class, REQUEST_WELCOME_SCREEN_RESULT));
//        welcomeScreens.add(new ScreenItem(R.string.title_back_exit, R.string.description_back_exit, BackExitWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_previous_button, R.string.description_previous_button, PreviousButtonWelcomeActivity.class));
//        welcomeScreens.add(new ScreenItem(R.string.title_default, R.string.description_default, DefaultWelcomeActivity.class));

        findViewById(R.id.showWelcomeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ScreenItem item = welcomeScreens.get(welcomeScreens.size() - 1);
                if (item.requestCode != null) {
                    item.helper.forceShow(item.requestCode);
                } else {
                    item.helper.forceShow();
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // This is needed to prevent welcome screens from being
        // automatically shown multiple times

        // This is the only one needed because it is the only one that
        // is shown automatically. The others are only force shown.
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }

    /**
     * Data model for list of welcome screens
     * Title to show in list, welcome screen to show, request code
     */
    private class ScreenItem {

        String title;
        String description;
        WelcomeHelper helper;
        Integer requestCode;

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass) {
            this(titleRes, descriptionRes, activityClass, null);
        }

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass, Integer requestCode) {
            this(titleRes, descriptionRes, new WelcomeHelper(MainActivity.this, activityClass), requestCode);
        }

        ScreenItem(int titleRes, int descriptionRes, WelcomeHelper helper, Integer requestCode) {
            this.title = getString(titleRes);
            this.description = getString(descriptionRes);
            this.helper = helper;
            this.requestCode = requestCode;
        }

    }

}