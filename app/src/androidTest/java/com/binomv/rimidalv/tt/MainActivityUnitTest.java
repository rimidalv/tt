package com.binomv.rimidalv.tt;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

/**
 * Created by rimidalv on 28/03/15.
 */
public class MainActivityUnitTest extends ActivityUnitTestCase<FullscreenActivity> {

    private FullscreenActivity activity;

    public MainActivityUnitTest() {
        super(FullscreenActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), SecondActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testLayout(){
        int buttonId = R.id.dummy_button;
        assertNotNull(activity.findViewById(buttonId));
        Button view = (Button) activity.findViewById(buttonId);
        assertEquals("Incorrect table of the button", "Start", view.getText());
    }

    public void testIntentTriggerViaOnClick(){
        int buttonId = R.id.dummy_button;
        Button view = (Button) activity.findViewById(buttonId);
        assertNotNull("Button not allowed to be null", view);

        view.performClick();

        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);
        String data = triggeredIntent.getExtras().getString("URL");

        assertEquals("Incorrect data passed via intents", "http://www.vogella.com", data);
    }
}
