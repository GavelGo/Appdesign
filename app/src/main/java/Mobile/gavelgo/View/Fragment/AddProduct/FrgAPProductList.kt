package Mobile.gavelgo.View.Fragment.AddProduct

import Mobile.gavelgo.R
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FrgAPProductList : Fragment(){

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_product_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.add_product_list_rv)

        val button = view.findViewById<FloatingActionButton>(R.id.add_product_next_fbtn);
        button.setOnClickListener {
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_add_item){
            changeFragment(FrgAPSelectCategory(), true)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeFragment(frg: Fragment, backStack: Boolean) {
        val frgManager = activity?.supportFragmentManager?.beginTransaction()
        if (backStack) {
            frgManager?.replace(R.id.add_product_frame_ly, frg, "AddProduct")
        } else {
            frgManager?.replace(R.id.add_product_frame_ly, frg)
        }
        frgManager?.commit()
    }

}