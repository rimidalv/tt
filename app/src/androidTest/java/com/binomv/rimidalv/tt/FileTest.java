package com.binomv.rimidalv.tt;

import android.test.mock.MockContext;

import com.binomv.rimidalv.tt.util.Util;

import junit.framework.TestCase;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rimidalv on 28/03/15.
 */
public class FileTest extends TestCase{

    private long fileWrites;

    public void testWriteConfigurationShouldAccessFileSystemTwice() {
        assertEquals("No file writes yet", fileWrites, 0);
        Util.writeConfiguration(context);
        assertEquals("Failed to write twice", 2, fileWrites);
    }


    MockContext context = new MockContext() {
        public java.io.FileOutputStream openFileOutput(String name, int mode)
                throws java.io.FileNotFoundException {
            return new StubOutputStream();
        };
    };

    public class StubOutputStream extends FileOutputStream {

        public StubOutputStream() throws FileNotFoundException {
            super(FileDescriptor.out);
        }

        // count number of calls, don't bother to really write something
        @Override
        public void write(byte[] buffer) throws IOException {
            fileWrites++;
        }

    }
}
