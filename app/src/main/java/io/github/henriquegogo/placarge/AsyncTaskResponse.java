package io.github.henriquegogo.placarge;

import org.json.JSONException;

public interface AsyncTaskResponse {
    void onAsyncTaskFinish(String output) throws JSONException;
}
