package Mobile.gavelgo.Controller.RetrofitUtills

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  APIClient{

    internal var retrofit: Retrofit? = null

    fun getClient(baseURL: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } else {
            if (retrofit!!.baseUrl().equals(baseURL)) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
        return retrofit
    }
}