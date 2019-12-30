package Mobile.gavelgo.View.Activity

import Mobile.gavelgo.Model.ChatListModel
import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.MessagesAdapter
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pub.devrel.easypermissions.EasyPermissions

class MessagesActivity : Activity(), View.OnClickListener {
    lateinit var msgRV: RecyclerView
    lateinit var messagesAdapter: MessagesAdapter
    lateinit var messageET: EditText
    lateinit var sendIV: ImageView
    lateinit var phonecallIV: ImageView
    lateinit var backRL: RelativeLayout

    var chatlist_model: ArrayList<ChatListModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        initView()
    }

    private fun initView() {

        msgRV = findViewById(R.id.msgRV)
        messageET = findViewById(R.id.messageET)
        sendIV = findViewById(R.id.sendIV)
        backRL = findViewById(R.id.backRL)
        phonecallIV = findViewById(R.id.phonecallIV)

        sendIV.setOnClickListener(this)
        backRL.setOnClickListener(this)
        phonecallIV.setOnClickListener(this)

        chatlist_model.add(ChatListModel("Hi owner! How soon the service will take? can I make an appointment with you?", "Friday 15:23", "2"))
        chatlist_model.add(ChatListModel("Hey! it will take around 30-40 minutes. Of course, you can make appointment with me", "Friday 15:40", "1"))
        chatlist_model.add(ChatListModel("When would be a good time for you to come?", "Saturday 16:26", "1"))

        messagesAdapter = MessagesAdapter(this, chatlist_model)
        val linearLayoutManager = GridLayoutManager(this, 1)
        msgRV.setHasFixedSize(true)
        msgRV.layoutManager = linearLayoutManager
        msgRV.adapter = messagesAdapter


    }

    @SuppressLint("MissingPermission")
    private fun call() {


        val perms = arrayOf(Manifest.permission.CALL_PHONE)

        if (EasyPermissions.hasPermissions(this, *perms)) {
            var posted_by = "111-333-222-4"
            val uri = "tel:" + posted_by.trim()
            val intent = Intent(Intent.ACTION_CALL)
            intent.setData(Uri.parse(uri))
            startActivity(intent)

        } else {
            EasyPermissions.requestPermissions(this, "We need a permission from access phone calls", 123, *perms)
        }



    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {

            R.id.sendIV -> {

                if (!messageET.text.toString().equals("")) {

                    chatlist_model.add(ChatListModel(messageET.text.toString(), "Friday 15:23", "1"))
                    messageET.setText("")
                    messagesAdapter.notifyDataSetChanged()
                }
            }
            R.id.backRL -> {

                onBackPressed()
            }
            R.id.phonecallIV->{

                call()
            }


        }
    }


}
