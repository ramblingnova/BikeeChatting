package com.example.user.bikeechatting.manager;

import com.example.user.bikeechatting.dto.ReceiveObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by User on 2016-03-11.
 */
public class NetworkManager {
    private static NetworkManager networkManager;
    private ServerUrl serverUrl;

    private NetworkManager() {
        OkHttpClient okHttpClient = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        okHttpClient.setCookieHandler(cookieManager);
        Client client = new OkClient(okHttpClient);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ServerUrl.baseUrl)
                .setConverter(new GsonConverter(gson))
                .setClient(client)
                .build();
        serverUrl = restAdapter.create(ServerUrl.class);
    }

    public static NetworkManager getInstance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    public interface ServerUrl {
        String baseUrl = "http://1.255.51.120:3000";

        @FormUrlEncoded
        @POST("/users/session")
        void login(@Field("email") String email, @Field("password") String password, Callback<ReceiveObject> callback);
    }

    public class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String date = json.getAsString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            try {
                return format.parse(date.replaceAll("Z$", "+0000"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void login(String email, String password, Callback<ReceiveObject> callback) {
        serverUrl.login(email, password, callback);
    }
}
