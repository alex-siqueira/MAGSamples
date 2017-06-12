package com.ca.api.samples.magsample.responseType;

import com.ca.mas.foundation.MASResponseBody;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by siqal01 on 6/5/2017.
 */

public class JSONArrayResponseBody extends MASResponseBody<JSONArray> {

    @Override
    public JSONArray getContent() {
        try {
            return new JSONArray(new String(getRawContent()));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
