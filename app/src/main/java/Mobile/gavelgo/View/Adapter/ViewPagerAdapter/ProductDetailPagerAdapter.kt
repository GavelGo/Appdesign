package Mobile.gavelgo.View.Adapter.ViewPagerAdapter

import Mobile.gavelgo.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ProductDetailPagerAdapter(internal var context: Context): PagerAdapter() {

    lateinit var  layoutInflater: LayoutInflater
    private val images = arrayOf<Int>(R.drawable.localfresh, R.drawable.localfresh, R.drawable.localfresh)



    override fun isViewFromObject(view: View, `object`:Any):Boolean {
        return view === `object`
    }
    override fun instantiateItem(container: ViewGroup, position:Int):Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.productdetail_imageitem, null)
        val imageView = view.findViewById(R.id.imageIV) as ImageView
        imageView.setImageResource(images[position])
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }
    override fun destroyItem(container:ViewGroup, position:Int, `object`:Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

    override fun getCount(): Int {

        return images.size
    }
}