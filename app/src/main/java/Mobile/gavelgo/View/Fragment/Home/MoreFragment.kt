package Mobile.gavelgo.View.Fragment.Home

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.HomeAdapter
import Mobile.gavelgo.View.Fragment.HomeFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoreFragment:Fragment(){

    lateinit var  moreRV:RecyclerView
    lateinit var  home_adapter:HomeAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view=inflater.inflate(R.layout.more_fragment,container,false)

        moreRV=view.findViewById(R.id.moreRV)

        home_adapter = HomeAdapter(activity,"more")
        val linearLayoutManager = GridLayoutManager(activity, 3)
        moreRV.setHasFixedSize(true);
        moreRV.layoutManager = linearLayoutManager
        moreRV.adapter = home_adapter
        return view
    }


}