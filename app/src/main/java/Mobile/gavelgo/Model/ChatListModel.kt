package Mobile.gavelgo.Model

class ChatListModel(internal var message: String, internal  var time: String, internal  var userid: String) {


    fun getUserId(): String? {
        return userid
    }

    fun setUserId(userid: String) {
        this.userid = userid
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String) {
        this.time = time
    }

}