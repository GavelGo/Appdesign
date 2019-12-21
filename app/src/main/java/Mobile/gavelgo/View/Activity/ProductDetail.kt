package Mobile.gavelgo.View.Activity

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Adapter.ViewPagerAdapter.ProductDetailPagerAdapter
import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager


class ProductDetail:Activity(){

   lateinit var viewPager: ViewPager

  lateinit  var sliderDotspanel: LinearLayout
    var dotscount = 0
    lateinit var  dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetail)

        viewPager = findViewById(R.id.viewPager) as ViewPager
        sliderDotspanel = findViewById(R.id.SliderDots)

        val viewPagerAdapter = ProductDetailPagerAdapter(this)

        viewPager!!.adapter = viewPagerAdapter



        dotscount = viewPagerAdapter.getCount()
        dots = arrayOfNulls<ImageView>(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot))
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position:Int, positionOffset:Float, positionOffsetPixels:Int) {
            }
            override fun onPageSelected(position:Int) {
                for (i in 0 until dotscount)
                {
                    dots[i]!!.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot))
                }
                dots[position]!!.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot))
            }
            override fun onPageScrollStateChanged(state:Int) {
            }
        })
    }

}