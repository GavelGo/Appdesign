package Mobile.gavelgo.View.Activity.AddProduct

import Mobile.gavelgo.R
import Mobile.gavelgo.View.Fragment.AddProduct.FrgAPProductList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment

class AddProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        supportActionBar?.setTitle("Add Product")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //add_product_frame_ly

        //0. List Of Products -> Add Product
        //1. Select Business Category
        //2. Keywords
        //3. Pictures
        //4. Highlights
        //5. Description
        //6. Deal Rules
        //7. List Of Addresses -> Add Address
        //8. Effective Date
        //9. Agreement
        //10. Finish

        changeFragment(FrgAPProductList(), false)

    }

    private fun changeFragment(frg: Fragment, backStack: Boolean) {
        val frgManager = supportFragmentManager.beginTransaction()
        if (backStack) {
            frgManager.replace(R.id.add_product_frame_ly, frg, "AddProduct")
        } else {
            frgManager.replace(R.id.add_product_frame_ly, frg)
        }
        frgManager.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            print("Backstack Count ${supportFragmentManager.backStackEntryCount}")
            if (supportFragmentManager.backStackEntryCount > 0){
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
