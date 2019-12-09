package Mobile.gavelgo.View.Adapter

import Mobile.gavelgo.R

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class HomeAdapter(internal var context: Context?,internal  var tag:String) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

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

        holder.imageIV.setLayoutParams(LinearLayout.LayoutParams(finalwidth, finalwidth))

        if (tag.equals("restuarent")){
            holder.itemTV.setText("R")

            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.restuarent // Drawable
                    )
            )


        }
        else  if (tag.equals("auto")) {
            holder.itemTV.setText("A")

            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.oilchange // Drawable
                    )
            )

        }
        else  if (tag.equals("more")) {
            holder.itemTV.setText("M")


            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.cloth // Drawable
                    )
            )
        }
        else  {
            holder.itemTV.setText("E")
            holder.imageIV.setImageDrawable(
                    ContextCompat.getDrawable(
                            context!!, // Context
                            R.drawable.localfresh // Drawable
                    )
            )
        }


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageIV: ImageView
        var itemTV: TextView
        init {
            imageIV = view.findViewById(R.id.imageIV);
            itemTV = view.findViewById(R.id.itemTV);
        }
    }
}
