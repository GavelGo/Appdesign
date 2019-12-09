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

class EntertainmentFragment:Fragment(){

    lateinit var homeRV:RecyclerView
    lateinit var home_adapter: HomeAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.entertainment_fragment,container,false)

        // currentFragment =childFragmentManager.findFragmentById(R.id.container)



        homeRV=view.findViewById(R.id.homeRV)
        home_adapter = HomeAdapter(activity,"entertainment")
        val linearLayoutManager = GridLayoutManager(activity, 3)
        homeRV.setHasFixedSize(true);
        homeRV.layoutManager = linearLayoutManager
        homeRV.adapter = home_adapter

        return view
    }
}