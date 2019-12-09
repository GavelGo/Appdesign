package Mobile.gavelgo.View.Fragment

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.HomeAdapter
import Mobile.gavelgo.View.Adapter.ViewPagerAdapter.HomePagerAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class HomeFragment  :Fragment(){
    lateinit var tabsTL: TabLayout
    lateinit var viewPager:ViewPager
    lateinit var homepageradapter:HomePagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        initView(view)

        return view
    }

    private fun initView(view: View?) {

        tabsTL=view!!.findViewById(R.id.tabsTL)
        viewPager=view!!.findViewById(R.id.viewPager)

        homepageradapter = HomePagerAdapter(activity,childFragmentManager)
        viewPager.setAdapter(homepageradapter)
        tabsTL.setupWithViewPager(viewPager)



    }
}