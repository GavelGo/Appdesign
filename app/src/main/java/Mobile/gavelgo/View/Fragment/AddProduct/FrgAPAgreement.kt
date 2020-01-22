package Mobile.gavelgo.View.Fragment.AddProduct

import Mobile.gavelgo.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FrgAPAgreement : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_ap_agreement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<FloatingActionButton>(R.id.add_product_next_fbtn);
        button.setOnClickListener {
            changeFragment(FrgAPSccuess(), true)
        }

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