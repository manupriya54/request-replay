package com.stripe.interview;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class StripeClient {

    private OkHttpClient httpClient;

    public StripeClient() {
        this.httpClient = new OkHttpClient();
    }

    public MyLog.MyResponse queryCustomers(MyLog.MyRequest request) {
        switch (request.method) {
            case "POST":
                return makePostQuery(request);
            case "GET":
                return makeGetQuery(request);
        }
        return null;
    }

    public MyLog.MyResponse queryCharges(MyLog.MyRequest request) {
        switch (request.method) {
            case "POST":
                return makePostQuery(request);
            case "GET":
                return makeGetQuery(request);
        }
        return null;
    }

    private MyLog.MyResponse makeGetQuery(MyLog.MyRequest request) {
        RequestBody requestBody = RequestBody.create(request.body, MediaType.parse(request.headers.get("Content-Type")));


        Request.Builder requestBuilder = new Request.Builder()
                .get()
                .url("https://api.stripe.com" + request.url);

        for (Map.Entry<String, String> header : request.headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }

        Request httpRequest = requestBuilder.build();

        Response httpResponse = null;
        try {
            httpResponse = httpClient.newCall(httpRequest).execute();
            MyLog.MyResponse response = new MyLog.MyResponse();
            response.code = httpResponse.code();
            response.body = httpResponse.body().string();
            return response;
        } catch (IOException e) {
            return null;
        }
    }

    private MyLog.MyResponse makePostQuery(MyLog.MyRequest request) {
        RequestBody requestBody = RequestBody.create(request.body, MediaType.parse(request.headers.get("Content-Type")));


        Request.Builder requestBuilder = new Request.Builder()
                .post(requestBody)
                .url("https://api.stripe.com" + request.url);

        for (Map.Entry<String, String> header : request.headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }

        Request httpRequest = requestBuilder.build();

        Response httpResponse = null;
        try {
            httpResponse = httpClient.newCall(httpRequest).execute();
            MyLog.MyResponse response = new MyLog.MyResponse();
            response.code = httpResponse.code();
            response.body = httpResponse.body().string();
            return response;
        } catch (IOException e) {
            return null;
        }
    }

}
