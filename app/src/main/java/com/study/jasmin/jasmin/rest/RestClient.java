package com.study.jasmin.jasmin.rest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class RestClient{

    private static RestService restService;
    private static String baseUrl = "http://172.20.10.12:8080/jasmin/" ;


    public static RestService getClient() {
        if (restService == null) {

            /*OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });*/

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
//                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restService = client.create(RestService.class);
        }
        return restService ;
    }



    public interface RestService {

//        @Headers("User-Agent: JasminAndroidClient")
//        @GET("/search/users")
//        Call<GitResult> getUsersNamedTom(@Query("q") String name);

//        @POST("/user/create")
//        Call<Item> createUser(@Body String name, @Body String email);

//        @PUT("/user/{id}/update")
//        Call<Item> updateUser(@Path("id") String id, @Body Item user);

        @FormUrlEncoded
        @POST("api/regiRequest")
        Call<JsonObject> registerResult(@Field("mail") String mail,
                                        @Field("pw") String pw,
                                        @Field("name") String name,
                                        @Field("sex") String sex/*,
                                        Callback<JsonObject> callback*/);
    }

}
