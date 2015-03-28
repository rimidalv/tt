package com.binomv.rimidalv.tt;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.TextView;

/**
 * Created by rimidalv on 28/03/15.
 */
public class SecondActivityFunctionalTest extends ActivityInstrumentationTestCase2<SecondActivity> {

    private static final String NEW_TEXT = "new text";

    public SecondActivityFunctionalTest() {
        super(SecondActivity.class);
    }

    public void testSetText() throws Exception{
        SecondActivity activity = getActivity();

        final TextView textView = (TextView) activity.findViewById(R.id.resultText);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(NEW_TEXT);
            }
        });

        getInstrumentation().waitForIdleSync();
        assertEquals("Text incorrect", NEW_TEXT, textView.getText());
    }

    @UiThreadTest
    public void testSetTextWithAnnotation(){
        SecondActivity activity = getActivity();

        // search for the textView
        final TextView textView = (TextView) activity
                .findViewById(R.id.resultText);

        textView.setText(NEW_TEXT);
        assertEquals("Text incorrect", NEW_TEXT, textView.getText().toString());
    }
}
