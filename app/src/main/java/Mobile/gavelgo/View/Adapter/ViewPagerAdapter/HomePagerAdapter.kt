package Mobile.gavelgo.View.Adapter.ViewPagerAdapter
import Mobile.gavelgo.View.Fragment.Home.AutoFragment
import Mobile.gavelgo.View.Fragment.Home.EntertainmentFragment
import Mobile.gavelgo.View.Fragment.Home.MoreFragment
import Mobile.gavelgo.View.Fragment.Home.Restaurant_Fragment
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomePagerAdapter(internal var context:Context?,internal  var fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position:Int): Fragment? {
        var fragment: Fragment? = null
        if (position == 0)
        {
            fragment = EntertainmentFragment()
        }
        else if (position == 1)
        {
            fragment = Restaurant_Fragment()
        }
        else if (position == 2)
        {
            fragment = AutoFragment()
        }
        else if (position == 3)
        {
            fragment = MoreFragment()
        }


        return fragment
    }

    override fun getCount(): Int {

        return 4

    }

    override fun getPageTitle(position:Int): String? {
        var title: String? = null
        if (position == 0)
        {
            title = "Entertainment"
        }
        else if (position == 1)
        {
            title = "Restaurant"
        }
        else if (position == 2)
        {
            title = "Auto"
        }
        else if (position == 3)
        {
            title = "More"
        }
        return title
    }
}