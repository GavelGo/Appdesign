package Mobile.gavelgo.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class RegisterUserResponse(){

    @SerializedName("user")
    @Expose
    private var user: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getUser(): String? {
        return user
    }

    fun setUser(user: String?) {
        this.user = user
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }


}