package Mobile.gavelgo.View.Activity.AddProduct

import Mobile.gavelgo.Controller.Apis.ProductCategoryApi
import Mobile.gavelgo.Controller.Apis.ProductCategoryApi.ProductCategory_CallBack
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.Model.ProductCategoryResponse
import Mobile.gavelgo.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

enum class enumCategorySelection{
    CATEGORY,
    SUBCATEGORY
}

class SelectCategoriesSelectionActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var TYPE: enumCategorySelection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_categories_selection)

        mRecyclerView = findViewById(R.id.select_category_selection_rv)

        if (TYPE == enumCategorySelection.CATEGORY){
            getCategoryList()
        } else if (TYPE == enumCategorySelection.SUBCATEGORY){
            getSubcategoriesList()
        }

    }

    private fun getCategoryList() {
        val productCategoryApi = ProductCategoryApi()

        if (Utills.isConnectingToInternet(this)) {
            try {
                Utills.showDialog(this)
                productCategoryApi.productcategory_const(this, object : ProductCategory_CallBack {
                    override fun onSuccess(body: ProductCategoryResponse?) {
                        Log.d("tag", "Api " + "success")
                        Utills.progressDialog_dismiss(this@SelectCategoriesSelectionActivity)
                    }

                    override fun onFailure(body: String) {
                        Utills.progressDialog_dismiss(this@SelectCategoriesSelectionActivity)
                        Log.d("tag", "Apifaliure msg=$body")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            Utills.showalerter(this, "Please check your internet connection")
        }
    }

    private fun getSubcategoriesList(){
        val productCategoryApi = ProductCategoryApi()

        if (Utills.isConnectingToInternet(this)) {
            try {
                Utills.showDialog(this)
                productCategoryApi.productSubcategory_const(this, object : ProductCategory_CallBack {
                    override fun onSuccess(body: ProductCategoryResponse?) {
                        Log.d("tag", "Api " + "success")
                        Utills.progressDialog_dismiss(this@SelectCategoriesSelectionActivity)
                    }

                    override fun onFailure(body: String) {
                        Utills.progressDialog_dismiss(this@SelectCategoriesSelectionActivity)
                        Log.d("tag", "Apifaliure msg=$body")
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            Utills.showalerter(this, "Please check your internet connection")
        }
    }

}
