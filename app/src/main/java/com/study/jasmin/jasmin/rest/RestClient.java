package com.study.jasmin.jasmin.rest;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public class RestClient{

    private static RestService restService;
    private static String baseUrl = "http://54.201.72.195:8081/test/" ; // aws
//  private static String baseUrl = "http://127.0.0.1:8080/test/" ;     // local
//  private static String baseUrl = "http://192.168.43.153:8080/test/" ;     // phone / swan
//  private static String baseUrl = "http://172.20.10.12:8080/test/" ;


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

        @FormUrlEncoded
        @POST("api/regiRequest")
        Call<JsonObject> registerResult(@Field("email") String mail,
                                        @Field("pw") String pw,
                                        @Field("name") String name,
                                        @Field("sex") String sex
                                        );

        @FormUrlEncoded
        @POST("api/login")
        Call<JsonObject> Login(@Field("email") String email,
                               @Field("password") String password
        );

        @FormUrlEncoded
        @POST("api/gotoStudy")
        Call<JsonObject> gotoStudy(@Field("studyNo") int studyNo
        );

        @FormUrlEncoded
        @POST("api/postList")
        Call<JsonObject> postList(@Field("studyNo") int studyNo
        );
//Part("description")
        @Multipart
        @POST("api/postInsert")
        Call<JsonObject> postInsert(@Part("userNo") int userNo,
                                    @Part("studyNo") int studyNo,
                                    @Part("postTitle") String postTitle,
                                    @Part("postContent") String postContent,
                                    @Part("postType") int postType,
                                    @Part MultipartBody.Part postFile
        );

        @FormUrlEncoded
        @POST("api/postUpdate")
        Call<JsonObject> postUpdate (@Field("postNo") int postNo,
                                     @Field("postTitle") String postTitle,
                                     @Field("postContent") String postContent,
                                     @Field("postType") int postType,
                                     @Field("postFile") String postFile
        );

        @FormUrlEncoded
        @POST("api/postDelete")
        Call<JsonObject> postDelete(@Field("postNo") int postNo
        );

        @FormUrlEncoded
        @POST("api/commentList")
        Call<JsonObject> commentList(@Field("postNo") int postNo
        );

        @FormUrlEncoded
        @POST("api/commentInsert")
        Call<JsonObject> commentInsert(@Field("userNo") int userNo,
                                       @Field("postNo") int postNo,
                                       @Field("commentContent") String commentContent

        );

        @FormUrlEncoded
        @POST("api/commentUpdate")
        Call<JsonObject> commentUpdate(@Field("commentNo") int commentNo,
                                       @Field("commentContent") String commentContent
        );

        @FormUrlEncoded
        @POST("api/commentDelete")
        Call<JsonObject> commentDelete(@Field("commentNo") int commentNo
        );

        @FormUrlEncoded
        @POST("api/homeworkList")
        Call<JsonObject> assignmentList(@Field("studyNo") int studyNo);

        @FormUrlEncoded
        @POST("api/homeworkInsert")
        Call<JsonObject> insertAssignment(@Field("studyNo")             int studyNo,
                                           @Field("homeworkTitle")      String homeworkTitle,
                                           @Field("homeworkContent")    String homeworkContent,
                                           @Field("homeworkStart")      String homeworkStart,
                                           @Field("homeworkEnd")        String homeworkEnd,
                                           @Field("homeworkMoney")      int homeworkMoney
        );


        @FormUrlEncoded
        @POST("api/homeworkDelete")
        Call<JsonObject> deleteAssignment(@Field("homeworkNo") int homeworkNo);

        @FormUrlEncoded
        @POST("api/homeworkUpdate")
        Call<JsonObject> updateAssignment(@Field("homeworkNo") int homeworkNo,
                                          @Field("homeworkTitle") String homeworkTitle,
                                          @Field("homeworkContent") String homeworkContent,
                                          @Field("homeworkEnd") String homeworkEnd,
                                          @Field("homeworkMoney") int homeworkMoney
        );

        @FormUrlEncoded
        @POST("api/attendanceList")
        Call<JsonObject> attendanceList(@Field("studyNo") int studyNo
        );

        @FormUrlEncoded
        @POST("api/attendanceInsert")
        Call<JsonObject> insertAttendance(@Field("attendanceList") String attendanceList
        );

        @FormUrlEncoded
        @POST("api/attendanceUpdate")
        Call<JsonObject> updateAttendance(@Field("attendanceList") String attendanceList
        );


        @FormUrlEncoded
        @POST("api/attendanceDelete")
        Call<JsonObject> deleteAttendance(@Field("attendanceNoList") String attendanceNoList
        );


        @FormUrlEncoded
        @POST("api/getMoneybookList")
        Call<JsonObject> moneybookList(@Field("studyNo") int studyNo
        );

        @FormUrlEncoded
        @POST("api/showPenaltyMoneyList")
        Call<JsonObject> penaltyList(@Field("studyNo") int studyNo
        );

        @FormUrlEncoded
        @POST("api/alarmList")
        Call<JsonObject> goAlarmList(@Field("studyNo") int studyNo
        );

        @FormUrlEncoded
        @POST("api/alarmInsert")
        Call<JsonObject> addAlarm(@Field("studyNo") int studyNo,
                                  @Field("alarmDate") int alarmDate,
                                  @Field("alarmTime") int alarmTime,
                                  @Field("alarmRepeat") int alarmRepeat,
                                  @Field("alarmContent") String alarmContent
        );

        @FormUrlEncoded
        @POST("api/alarmUpdate")
        Call<JsonObject> updateAlarm(@Field("alarmNo") int alarmNo,
                                     @Field("alarmDate") int alarmDate,
                                     @Field("alarmTime") int alarmTime,
                                     @Field("alarmRepeat") int alarmRepeat,
                                     @Field("alarmContent") String alarmContent
        );

        @FormUrlEncoded
        @POST("api/alarmDelete")
        Call<JsonObject> deleteAlarm(@Field("alarmNo") int alarmNo
        );

        @FormUrlEncoded
        @POST("api/managerShare")
        Call<JsonObject> gradeShare(@Field("studyNo") int studyNo,
                                    @Field("userNo") int userNo
        );

        @FormUrlEncoded
        @POST("api/managerShareDelete")
        Call<JsonObject> gradeDelete(@Field("studyNo") int studyNo,
                                         @Field("userNo") int userNo
        );

        @FormUrlEncoded
        @POST("api/managerDelegate")
        Call<JsonObject> gradeDelegate(@Field("studyNo") int studyNo,
                                       @Field("userNo") int userNo
        );


        @FormUrlEncoded
        @POST("api/removeUser")
        Call<JsonObject> removeMember(@Field("studyNo") int studyNo,
                                       @Field("userNo") int userNo
        );

        @FormUrlEncoded
        @POST("api/studyFeeSet")
        Call<JsonObject> updateStudyFee(@Field("studyNo")            int studyNo,
                                        @Field("studyUseDeposit")   int studyUseDeposit,
                                        @Field("studyBasicDeposit")int studyBasicDeposit,
                                        @Field("studyMaxLate")      int studyMaxLate,
                                        @Field("studyLateUnit")     int studyLateUnit,
                                        @Field("studyLateFee")      int studyLateFee,
                                        @Field("studyAbsenceFee")   int studyAbsenceFee
        );

        @FormUrlEncoded
        @POST("api/studyEnd")
        Call<JsonObject> endStudy(@Field("studyNo")            int studyNo
        );


        @FormUrlEncoded
        @POST("api/withdrawStudy")
        Call<JsonObject> withdrawStudy(@Field("studyNo")            int studyNo,
                                        @Field("userNo")             int userNo
        );
    }

}
