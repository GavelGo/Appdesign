package Mobile.gavelgo.Controller.Apis

import Mobile.gavelgo.Controller.RetrofitUtills.APIClient
import Mobile.gavelgo.Controller.RetrofitUtills.ApiInterface
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.Model.AuthResponse
import Mobile.gavelgo.Model.LoginResponse
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginApi{

    lateinit var context: Context
    lateinit var login_callback: Login_CallBack
    lateinit var username: String
    lateinit var password: String
    lateinit var token: String

    fun login_const(
            context: Context,
            token:String,
            username:String,
            password:String,
            login_callback: Login_CallBack) {
        this.context = context
        this.token = token
        this.username = username
        this.password = password
        this.login_callback = login_callback

        Log.e("tag","checkparam "+ "working ")

        val requestBody: MutableMap<String, String> = HashMap()
        requestBody["username"] = username
        requestBody["password"] = password

        val apiInterface = APIClient.getClient(ApiInterface.Login_URL)!!.create(ApiInterface::class.java)
        val call = apiInterface.postLogin(token,requestBody)

        call.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                Log.e("tag","Login Responsestatuscode"+response.code().toString())

                if (response.isSuccessful) {

                    Log.e("tag","Login Api Response "+ GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                    if (response.code() == 200) {
                        val responseData = response.body()
                        if (responseData != null) {
                            login_callback.onSuccess(response.body())

                        }
                    }

                } else if (response.code() == 400) {

                    Log.e("tag","res "+ "400")

                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    login_callback.onFailure(jObjError.getString("status"))


                } else if (response.code() == 401) {

                    Log.e("tag","res "+ "401")
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    login_callback.onFailure(jObjError.getString("status"))

                } else if (response.code() == 502) {

                    Log.e("tag","res "+ "502")
                    login_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")

                }
                else{

                    Log.e("tag","res "+ "else")
                    login_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("tag", "error is " + t.message)
                login_callback.onFailure(t.message.toString())

            }
        })
    }



    //=====creating callback method=================
    interface Login_CallBack {
        fun onSuccess(body: LoginResponse?)
        fun onFailure(body: String)

    }



}