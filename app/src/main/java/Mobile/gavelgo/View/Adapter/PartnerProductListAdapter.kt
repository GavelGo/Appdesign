package Mobile.gavelgo.View.Adapter

import Mobile.gavelgo.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

class PartnerProductListAdapter(internal var context: Context,internal  var arrayList: ArrayList<String>): RecyclerView.Adapter<PartnerProductListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PartnerProductListAdapter.ViewHolder {

        val v = LayoutInflater.from(context).inflate(R.layout.partnerproductlist_item, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.deleteRL.setOnClickListener{

            holder.deletelayoutRL.setVisibility(View.VISIBLE)
        }
        holder.deletenoBT.setOnClickListener{

            holder.deletelayoutRL.setVisibility(View.GONE)


        }
        holder.deleteyesBT.setOnClickListener{
            arrayList.removeAt(position)

            notifyDataSetChanged()
            holder.deletelayoutRL.setVisibility(View.GONE)

        }

    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var deletelayoutRL:RelativeLayout
        var deleteRL:RelativeLayout
        var deleteyesBT:Button
        var deletenoBT:Button

        init {
            deletelayoutRL=view.findViewById(R.id.deletelayoutRL)
            deleteRL=view.findViewById(R.id.deleteRL)
            deleteyesBT=view.findViewById(R.id.deleteyesBT)
            deletenoBT=view.findViewById(R.id.deletenoBT)
        }

    }
}