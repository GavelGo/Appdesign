package Mobile.gavelgo.View.Fragment.AddProduct

import Mobile.gavelgo.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.robertlevonyan.views.chip.Chip

class FrgAPEnterKeywords : Fragment(){

    private lateinit var mTagsView: ConstraintLayout
    private lateinit var mTagChip: Chip

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_ap_keywords, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTagsView = view.findViewById(R.id.ap_keywords_tag_list_view)

        mTagChip = view.findViewById(R.id.ap_keywords_tag_view)

        val button = view.findViewById<FloatingActionButton>(R.id.add_product_next_fbtn);
        button.setOnClickListener {
            changeFragment(FrgAPAddPictures(), true)
        }

        val keywords = view.findViewById<EditText>(R.id.ap_keywords_keyword_et)

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