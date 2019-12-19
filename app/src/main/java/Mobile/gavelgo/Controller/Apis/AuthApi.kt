package Mobile.gavelgo.Controller.Apis

import Mobile.gavelgo.Controller.RetrofitUtills.APIClient
import Mobile.gavelgo.Controller.RetrofitUtills.ApiInterface
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.Model.AuthResponse
import Mobile.gavelgo.Model.ProductCategoryResponse
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthApi{

    lateinit var context: Context
    lateinit var auth_callback: Auth_CallBack
    lateinit var username: String
    lateinit var password: String


    fun authapi_const(
            context: Context,
            username:String,
            password:String,
            auth_callback: Auth_CallBack) {
        this.context = context
        this.username = username
        this.password = password
        this.auth_callback = auth_callback

        Log.e("tag","checkparam "+ "working ")

        val requestBody: MutableMap<String, String> = HashMap()
        requestBody["username"] = username
        requestBody["password"] = password

        val apiInterface = APIClient.getClient(ApiInterface.BASE_URL)!!.create(ApiInterface::class.java)
        val call = apiInterface.postAuth(requestBody)

        call.enqueue(object : Callback<AuthResponse> {

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {

                Log.e("tag","Auth Responsestatuscode"+response.code().toString())

                if (response.isSuccessful) {

                    Log.e("tag","Auth Api Response "+ GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                    if (response.code() == 200) {
                        val responseData = response.body()
                        if (responseData != null) {
                            auth_callback.onSuccess(response.body())

                        }
                    }

                } else if (response.code() == 400) {

                    Log.e("tag","res "+ "400")

                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    auth_callback.onFailure(jObjError.getString("status"))


                } else if (response.code() == 401) {

                    Log.e("tag","res "+ "401")
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    auth_callback.onFailure(jObjError.getString("status"))

                } else if (response.code() == 502) {

                    Log.e("tag","res "+ "502")
                    auth_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")

                }
                else{

                    Log.e("tag","res "+ "else")
                    auth_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")
                }

            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("tag", "error is " + t.message)
                auth_callback.onFailure(t.message.toString())

            }
        })
    }



    //=====creating callback method=================
    interface Auth_CallBack {
        fun onSuccess(body: AuthResponse?)
        fun onFailure(body: String)

    }




}