package com.binomv.rimidalv.tt;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by rimidalv on 28/03/15.
 */
public class MainActivityFunctionalTest extends ActivityInstrumentationTestCase2<FullscreenActivity> {

    private FullscreenActivity activity;

    public MainActivityFunctionalTest() {
        super(FullscreenActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testStartSecondActivity() throws Exception{
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(SecondActivity.class.getName(), null, false);

        Button view = (Button) activity.findViewById(R.id.dummy_button);

        assertNotNull("view is null", view);

        TouchUtils.clickView(this, view);

        SecondActivity secondActivity = (SecondActivity) activityMonitor.waitForActivityWithTimeout(2000);

        assertNotNull("secondActivity is null", secondActivity);

        TextView textView = (TextView) secondActivity.findViewById(R.id.resultText);

        ViewAsserts.assertOnScreen(secondActivity.getWindow().getDecorView(), textView);

        assertEquals("Text incorrect", "Started", textView.getText());

        sendKeys(KeyEvent.KEYCODE_BACK);

        TouchUtils.clickView(this, view);
    }
}
