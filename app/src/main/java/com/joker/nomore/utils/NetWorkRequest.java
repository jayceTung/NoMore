package com.joker.nomore.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

/**
 * Created by Super on 2016/11/15.
 */

public class NetWorkRequest<T> extends JsonRequest<JSONObject> {
    private static final String TAG = "NetWorkRequest";


    public NetWorkRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return null;
    }
}
