package Mobile.gavelgo.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import Mobile.gavelgo.Controller.Apis.ProductCategoryApi;
import Mobile.gavelgo.Controller.Apis.UserRegisterApi;
import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.Model.ProductCategoryResponse;
import Mobile.gavelgo.Model.RegisterUserResponse;
import Mobile.gavelgo.R;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddProductActivity extends Activity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    EditText productnameET,descriptionET;
    Spinner categorySP,subcategorySP;
    CircleImageView profileIV;
    Button browseBT,saveproductBT,cancelBT;
    AutoCompleteTextView autocompleteTV;

    int SELECT_IMAGE=1;
    String selectedImagePath;

    String[] keywords = {"dealer", "private owner", "parts", "used", "brand new", "insurance quote"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        productnameET=(EditText)findViewById(R.id.productnameET);
        descriptionET=(EditText)findViewById(R.id.descriptionET);
        categorySP=(Spinner) findViewById(R.id.categorySP);
        subcategorySP=(Spinner) findViewById(R.id.subcategorySP);
        profileIV=(CircleImageView) findViewById(R.id.profileIV);
        browseBT=(Button) findViewById(R.id.browseBT);
        saveproductBT=(Button) findViewById(R.id.saveproductBT);
        cancelBT=(Button) findViewById(R.id.cancelBT);
        autocompleteTV=(AutoCompleteTextView) findViewById(R.id.autocompleteTV);

        browseBT.setOnClickListener(this);
        saveproductBT.setOnClickListener(this);
        cancelBT.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.select_autocomplete_item, keywords);

        //Getting the instance of AutoCompleteTextView
        //AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setThreshold(1);//will start working from first character
        autocompleteTV.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
       // actv.setTextColor(Color.RED);

        productCategoryListApi();

    }

    private void productCategoryListApi() {

        ProductCategoryApi productCategoryApi = new ProductCategoryApi();

        if (Utills.isConnectingToInternet(AddProductActivity.this)) {
            try {
                Utills.showDialog(AddProductActivity.this);
                productCategoryApi.productcategory_const(AddProductActivity.this,  new ProductCategoryApi.ProductCategory_CallBack(){


                    @Override
                    public void onSuccess(@Nullable ProductCategoryResponse body) {

                        Log.d("tag","Api "+ "success");
                        Utills.progressDialog_dismiss(AddProductActivity.this);

                    }

                    @Override

                    public void onFailure(@NotNull String body) {

                        Utills.progressDialog_dismiss(AddProductActivity.this);
                        Log.d("tag","Api"+ "faliure msg="+body);

                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utills.showalerter(this, "Please check your internet connection");
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.saveproductBT:

                if (productnameET.getText().toString().equals("")){

                    Utills.showalerter(AddProductActivity.this,"Please enter product name");
                }

                else if (categorySP.getSelectedItem().equals("Select Business Category")){

                    Utills.showalerter(AddProductActivity.this,"Please select businnes category");

                }  else if (subcategorySP.getSelectedItem().equals("Select Subcategory")){

                    Utills.showalerter(AddProductActivity.this,"Please select sub category");
                }
                else{
                    Utills.showalerter(AddProductActivity.this,"Product save successfully");
                }

                break;

            case R.id.cancelBT:

                finish();

                break;

            case  R.id.browseBT:

                opengallery();

                break;
        }
    }



    @AfterPermissionGranted(123)
    private void opengallery() {
        String[]perms={Manifest.permission.READ_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(this,perms)){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);

        }else{

            EasyPermissions.requestPermissions(this,"We need a permission from access phone gallery photos",
                    123,perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    selectedImagePath = getPath(selectedImageUri);
                    System.out.println("Image Path : " + selectedImagePath);
                    profileIV.setImageURI(selectedImageUri);
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(AddProductActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
