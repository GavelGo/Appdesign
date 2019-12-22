package Mobile.gavelgo.Controller.Apis

import Mobile.gavelgo.Controller.RetrofitUtills.APIClient
import Mobile.gavelgo.Controller.RetrofitUtills.ApiInterface
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.Model.ProductCategoryResponse
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductCategoryApi{

    lateinit var context: Context
    lateinit var productcategory_callback: ProductCategory_CallBack


    fun productcategory_const(
            context: Context,
            productcategory_callback: ProductCategory_CallBack
    ) {
        this.context = context
        this.productcategory_callback = productcategory_callback

        Log.e("tag","checkparam "+ "working ")

        val apiInterface = APIClient.getClient(ApiInterface.BASE_URL)!!.create(ApiInterface::class.java)
        val call = apiInterface.getProductUser()

        call.enqueue(object : Callback<ProductCategoryResponse> {

            override fun onResponse(call: Call<ProductCategoryResponse>, response: Response<ProductCategoryResponse>) {

                Log.e("tag","ProductCategory Responsestatuscode"+response.code().toString())

                if (response.isSuccessful) {

                    Log.e("tag","ProductCategoryResponse "+ GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                    if (response.code() == 200) {
                        val responseData = response.body()
                        if (responseData != null) {
                            productcategory_callback.onSuccess(response.body())

                        }
                    }

                } else if (response.code() == 400) {

                    Log.e("tag","res "+ "400")

                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    productcategory_callback.onFailure(jObjError.getString("status"))


                } else if (response.code() == 401) {

                    Log.e("tag","res "+ "401")
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    Utills.showalerter(context, jObjError.getString("status"))

                    productcategory_callback.onFailure(jObjError.getString("status"))

                } else if (response.code() == 502) {

                    Log.e("tag","res "+ "502")
                    productcategory_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")

                }
                else{

                    Log.e("tag","res "+ "else")
                    productcategory_callback.onFailure("Server error")
                    Utills.showalerter(context, "Server error")
                }

            }

            override fun onFailure(call: Call<ProductCategoryResponse>, t: Throwable) {
                Log.d("tag", "error is " + t.message)
                productcategory_callback.onFailure(t.message.toString())

            }
        })
    }

    //=====creating callback method=================
    interface ProductCategory_CallBack {
        fun onSuccess(body: ProductCategoryResponse?)
        fun onFailure(body: String)

    }




}