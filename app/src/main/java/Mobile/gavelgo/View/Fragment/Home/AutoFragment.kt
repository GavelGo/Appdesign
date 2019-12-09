package Mobile.gavelgo.View.Fragment.Home

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.HomeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AutoFragment:Fragment(){

    lateinit var  autoRV:RecyclerView
    lateinit var  home_adapter:HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.autofragment,container,false)

        initView(view)

        return view
    }

    private fun initView(view: View) {

        autoRV=view.findViewById(R.id.autoRV)

        home_adapter = HomeAdapter(activity,"auto")
        val linearLayoutManager = GridLayoutManager(activity, 3)
        autoRV.setHasFixedSize(true);
        autoRV.layoutManager = linearLayoutManager
        autoRV.adapter = home_adapter
    }
}