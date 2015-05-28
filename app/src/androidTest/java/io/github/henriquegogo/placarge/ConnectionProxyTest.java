package io.github.henriquegogo.placarge;

import android.test.InstrumentationTestCase;

public class ConnectionProxyTest extends InstrumentationTestCase {

    public void testSetConnectionProxyResponse() {
        ConnectionProxyResponse connectionProxyResponse = new ConnectionProxyResponse() {
            @Override
            public void onConnectionProxyFinish(String output) { }
        };

        ConnectionProxy connectionProxy = new ConnectionProxy(connectionProxyResponse);

        assertEquals(connectionProxyResponse, connectionProxy.connectionProxyResponse);
    }
}
