package com.binomv.rimidalv.tt;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by rimidalv on 28/03/15.
 */
public class AsyncTaskTester extends ActivityUnitTestCase<MainActivity> {

    private MainActivity activity;

    public AsyncTaskTester() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testSomeAsyncTask() throws Throwable{
        final CountDownLatch latch = new CountDownLatch(1);
        activity.setListener(new MainActivity.IJobListener() {
            @Override
            public void executionDone() {
                latch.countDown();
            }
        });


        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = (Button) activity.findViewById(R.id.button);
                button.performClick();
            }
        });

        boolean await = latch.await(30, TimeUnit.SECONDS);

        assertTrue(await);
    }
}
