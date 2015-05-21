package io.github.henriquegogo.placarge;

import android.test.InstrumentationTestCase;

public class ConnectionProxyTest extends InstrumentationTestCase {

    public void testSetAsyncTaskResponse() {
        AsyncTaskResponse asyncTaskResponse = new AsyncTaskResponse() {
            @Override
            public void onAsyncTaskFinish(String output) { }
        };

        ConnectionProxy connectionProxy = new ConnectionProxy(asyncTaskResponse);

        assertEquals(asyncTaskResponse, connectionProxy.asyncTaskResponse);
    }
}
