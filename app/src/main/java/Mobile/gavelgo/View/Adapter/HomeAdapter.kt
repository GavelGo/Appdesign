package Mobile.gavelgo.View.Adapter

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Activity.MainActivity
import Mobile.gavelgo.View.Activity.ProductDetail
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class HomeAdapter(internal var context: Context?,internal  var tag:String) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(),View.OnClickListener {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.homelist_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return 30
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x
        val height: Int = size.y
        var finalwidth = width / 3
        var finalhight = width / 4

        holder.imageIV.setLayoutParams(LinearLayout.LayoutParams(finalwidth, finalhight))

        if (tag.equals("restuarent")){
          //  holder.itemTV.setText("R")

            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.restuarent // Drawable
                    )
            )


        }
        else  if (tag.equals("auto")) {
          //  holder.itemTV.setText("A")

            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.oilchange // Drawable
                    )
            )

        }
        else  if (tag.equals("more")) {
          //  holder.itemTV.setText("M")
            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.cloth // Drawable
                    )
            )
        }
        else  {
         //   holder.itemTV.setText("E")
            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.localfresh // Drawable
                    )
            )
        }

        holder.menuIV.setOnClickListener(this)
        holder.imageIV.setOnClickListener(this)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageIV: ImageView
        var itemTV: TextView
        var menuIV: ImageView
        init {
            imageIV = view.findViewById(R.id.imageIV)
            itemTV = view.findViewById(R.id.itemTV)
            menuIV = view.findViewById(R.id.menuIV)
        }
    }




    override fun onClick(v: View?) {


            when (v!!.id){

                R.id.menuIV->{

                    showPopup(v)
                }
                R.id.imageIV->{

                    //loginApi("Bearer "+token);
                    val intent = Intent(context, ProductDetail::class.java)
                    context!!.startActivity(intent)

                }


        }


    }

    fun showPopup(v: View) {
        val popup = PopupMenu(context, v)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.productmenu, popup.menu)
        popup.show()
    }
}
