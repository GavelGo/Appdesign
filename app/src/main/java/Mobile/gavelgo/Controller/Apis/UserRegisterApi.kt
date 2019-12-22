package Mobile.gavelgo.Controller.Apis

import Mobile.gavelgo.Controller.RetrofitUtills.APIClient
import Mobile.gavelgo.Controller.RetrofitUtills.ApiInterface
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.Model.RegisterUserResponse
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class UserRegisterApi {
    lateinit var context: Context
    lateinit var username: String
    lateinit var email: String
    lateinit var password: String

    lateinit var resgisteruser_callback: RegisterUser_CallBack


    fun home_splash(
            context: Context,
            username: String,
            email: String,
            password: String,
            resgisteruser_callback: RegisterUser_CallBack
    ) {
        this.context = context
        this.username = username
        this.email = email
        this.password = password
        this.resgisteruser_callback = resgisteruser_callback

        Log.e("tag","checkparam "+ "username= " + username + "  email= " + email + "  password= " + password)

        val requestBody: MutableMap<String, String> = HashMap()
        requestBody["username"] = username
        requestBody["email"] = email
        requestBody["password"] = password


        val apiInterface = APIClient.getClient(ApiInterface.BASE_URL)!!.create(ApiInterface::class.java)
        val call = apiInterface.postRegisterUser(requestBody)

        call.enqueue(object : Callback<RegisterUserResponse> {

            override fun onResponse(call: Call<RegisterUserResponse>, response: Response<RegisterUserResponse>) {

                Log.e("tag","RegisterUserResponsestatuscode"+response.code().toString())

                if (response.isSuccessful) {

                    Log.e("tag","RegisterUserResponse "+ GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                    if (response.code() == 201) {
                        val responseData = response.body()
                        if (responseData != null) {
                            resgisteruser_callback.onSuccess(response.body())

                        }
                    }

                } else if (response.code() == 400) {

                    Log.e("tag","res "+ "400")

                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    resgisteruser_callback.onFailure(jObjError.getString("status"))


                } else if (response.code() == 401) {

                    Log.e("tag","res "+ "401")
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    resgisteruser_callback.onFailure(jObjError.getString("status"))

                } else if (response.code() == 502) {

                    Log.e("tag","res "+ "502")
                    resgisteruser_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")

                }
                else{

                    Log.e("tag","res "+ "else")
                    resgisteruser_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")
                }

            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.d("tag", "error is " + t.message)
                resgisteruser_callback.onFailure(t.message.toString())

            }
        })
    }

    //=====creating callback method=================
    interface RegisterUser_CallBack {
        fun onSuccess(body: RegisterUserResponse?)
        fun onFailure(body: String)

    }


}

