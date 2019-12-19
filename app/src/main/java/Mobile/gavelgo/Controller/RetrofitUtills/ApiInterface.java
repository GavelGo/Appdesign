package Mobile.gavelgo.Controller.RetrofitUtills;


import java.util.Map;

import Mobile.gavelgo.Model.AuthResponse;
import Mobile.gavelgo.Model.ProductCategoryResponse;
import Mobile.gavelgo.Model.RegisterUserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

     String BASE_URL = "http://jessgo.mr47w8dp7c.us-east-2.elasticbeanstalk.com/";

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<RegisterUserResponse> postRegisterUser(@Body Map<String, String> requestBody);


    @Headers("Content-Type: application/json")
    @POST("authenticate")
    Call<AuthResponse> postAuth(@Body Map<String, String> requestBody);

    //@Headers("Content-Type: application/json")
    @GET("category")
    Call<ProductCategoryResponse> getProductUser();

}
