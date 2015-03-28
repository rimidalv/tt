package com.binomv.rimidalv.tt.util;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rimidalv on 28/03/15.
 */
public class Util {
    public static void writeConfiguration(Context ctx) {
        BufferedWriter writer = null;
        try {
            FileOutputStream openFileOutput = ctx.openFileOutput("config.txt", Context.MODE_PRIVATE);
            openFileOutput.write("This is a test1.".getBytes());
            openFileOutput.write("This is a test2.".getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
