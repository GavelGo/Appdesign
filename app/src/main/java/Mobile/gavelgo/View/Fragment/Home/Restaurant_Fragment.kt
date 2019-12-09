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

class Restaurant_Fragment:Fragment(){

    lateinit var home_adapter: HomeAdapter
    lateinit var restRV: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.restaurent_fragment,container,false)

        //currentFragment =childFragmentManager.findFragmentById(R.id.container)

        restRV=view.findViewById(R.id.restRV)

        home_adapter = HomeAdapter(activity,"restuarent")
        val linearLayoutManager = GridLayoutManager(activity, 3)
        restRV.setHasFixedSize(true);
        restRV.layoutManager = linearLayoutManager
        restRV.adapter = home_adapter


        return view
    }


}