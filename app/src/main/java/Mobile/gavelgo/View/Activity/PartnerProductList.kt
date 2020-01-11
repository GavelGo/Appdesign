package Mobile.gavelgo.View.Activity

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.PartnerProductListAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PartnerProductList:Activity(),View.OnClickListener{

    lateinit var  productRV:RecyclerView
    lateinit var  addproductBT:Button
    var array_list: ArrayList<String> = ArrayList()
    lateinit var  consumerProductListAdapter: PartnerProductListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_partnerproduct_list)

        initView()
    }

    private fun initView() {


        productRV=findViewById(R.id.productRV)
        addproductBT=findViewById(R.id.addproductBT)
        addproductBT.setOnClickListener(this)

        array_list.add("A")
        array_list.add("B")
        array_list.add("C")
        array_list.add("D")
        array_list.add("E")


        consumerProductListAdapter = PartnerProductListAdapter(this,array_list)
        val linearLayoutManager = GridLayoutManager(this, 1)
        productRV.setHasFixedSize(true)
        productRV.layoutManager = linearLayoutManager
        productRV.adapter = consumerProductListAdapter
    }

    override fun onClick(p0: View?) {

        intent = Intent(applicationContext, AddProductActivity::class.java)
        startActivity(intent)
    }


}