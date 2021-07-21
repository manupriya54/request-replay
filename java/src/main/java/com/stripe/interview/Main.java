package com.stripe.interview;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String... args) {
        useGSONForSomeReason("hello\n");
        System.out.println("Hello world!");

        new Main().queryCharges();
        new Main().queryCustomers();
    }

    public static void useGSONForSomeReason(String input) {
        new GsonBuilder().create().toJson(input, System.out);
    }

    public void queryCharges() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("/Users/manu/IdeaProjects/request-replay/requestlog-charges.json"));
            StripeClient client = new StripeClient();

            JsonArray logs = new GsonBuilder().create().fromJson(reader, JsonArray.class);
            for (JsonElement log : logs.getAsJsonArray()) {
                MyLog logObject = new GsonBuilder().create().fromJson(log, MyLog.class);
                MyLog.MyResponse response = client.queryCharges(logObject.request);
                if (response.code != logObject.response.code) {
                    throw new RuntimeException("Response doesn't match");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryCustomers() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("/Users/manu/IdeaProjects/request-replay/requestlog-customer-charges.json"));
            StripeClient client = new StripeClient();
            String customer = "";
            String chargeId = "";
            String refundId = "";

            JsonArray logs = new GsonBuilder().create().fromJson(reader, JsonArray.class);
            for (JsonElement log : logs.getAsJsonArray()) {
                MyLog logObject = new GsonBuilder().create().fromJson(log, MyLog.class);

                customer = loadCustomerId(logObject.request, customer);

                loadChargeId(logObject.request, chargeId);
                loadRefundId(logObject.request, refundId);

                MyLog.MyResponse response = client.queryCustomers(logObject.request);
                JsonObject respObject = new GsonBuilder().create().fromJson(response.body, JsonObject.class);
                if (!respObject.get("id").getAsString().equals(customer) && logObject.request.url.equals("/v1/customers")) {
                    customer = respObject.get("id").getAsString();
                }
                if(logObject.request.url.equals("/v1/charges")) {
                    chargeId = respObject.get("id").getAsString();
                }
                if(logObject.request.url.endsWith("/refunds")) {
                    refundId = respObject.get("id").getAsString();
                }
                if (response.code != logObject.response.code) {
                    throw new RuntimeException("Response doesn't match for request: " + logObject.request.body);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChargeId(MyLog.MyRequest request, String chargeId) {
        if(!chargeId.isEmpty()) {
            Pattern pattern = Pattern.compile("ch_([0-9a-zA-Z])*");
            Matcher matcher = pattern.matcher(request.url);
            if (matcher.find()) {
                request.url = matcher.replaceAll(chargeId);
            }
        }
    }

    private void loadRefundId(MyLog.MyRequest request, String refundId) {
        if(!refundId.isEmpty()) {
            Pattern pattern = Pattern.compile("re_([0-9a-zA-Z])*");
            Matcher matcher = pattern.matcher(request.url);
            if (matcher.find()) {
                request.url = matcher.replaceAll(refundId);
            }
        }
    }

    private String loadCustomerId(MyLog.MyRequest request, String customer) {
        Optional<String> maybeCustomer = Arrays.stream(request.getBody().split("&"))
                .filter(e -> e.split("=")[0].equals("customer"))
                .findFirst();
        if (customer.isEmpty()) {
            customer = maybeCustomer.isPresent() ? maybeCustomer.get().split("=")[1] : "";
        }

        Pattern pattern = Pattern.compile("customer=([0-9a-zA-Z_])*");
        Matcher matcher = pattern.matcher(request.body);
        if (matcher.find()) {
            request.body = matcher.replaceAll("customer=" + customer);
        }
        return customer;
    }
}
