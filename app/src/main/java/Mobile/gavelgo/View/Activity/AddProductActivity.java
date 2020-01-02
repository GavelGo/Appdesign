package Mobile.gavelgo.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import Mobile.gavelgo.Controller.Apis.ProductCategoryApi;
import Mobile.gavelgo.Controller.Apis.UserRegisterApi;
import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.Model.ProductCategoryResponse;
import Mobile.gavelgo.Model.RegisterUserResponse;
import Mobile.gavelgo.R;
import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddProductActivity extends FragmentActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    EditText productnameET,descriptionET;
    Spinner categorySP,subcategorySP;
    CircleImageView profileIV;
    Button browseBT,saveproductBT,cancelBT;
    AutoCompleteTextView autocompleteTV;
    ImageView firstimageIV,secondimageIV,thirdimageIV,firstimgcrossIV,secondimgcrossIV,thirdimgcrossIV;

    RelativeLayout firstimageRL,secondimageRL,thirdimageRL;

    int SELECT_IMAGE=1;
    String selectedImagePath;

    String[] keywords = {"dealer", "private owner", "parts", "used", "brand new", "insurance quote"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        productnameET=(EditText)findViewById(R.id.productnameET);
        firstimageIV=(ImageView) findViewById(R.id.firstimageIV);
        secondimageIV=(ImageView) findViewById(R.id.secondimageIV);
        thirdimageIV=(ImageView) findViewById(R.id.thirdimageIV);
        descriptionET=(EditText)findViewById(R.id.descriptionET);
        categorySP=(Spinner) findViewById(R.id.categorySP);
        subcategorySP=(Spinner) findViewById(R.id.subcategorySP);
        profileIV=(CircleImageView) findViewById(R.id.profileIV);
        browseBT=(Button) findViewById(R.id.browseBT);
        saveproductBT=(Button) findViewById(R.id.saveproductBT);
        cancelBT=(Button) findViewById(R.id.cancelBT);
        firstimageRL=(RelativeLayout) findViewById(R.id.firstimageRL);
        secondimageRL=(RelativeLayout) findViewById(R.id.secondimageRL);
        thirdimageRL=(RelativeLayout) findViewById(R.id.thirdimageRL);
        autocompleteTV=(AutoCompleteTextView) findViewById(R.id.autocompleteTV);

        firstimgcrossIV=(ImageView) findViewById(R.id.firstimgcrossIV);
        secondimgcrossIV=(ImageView) findViewById(R.id.secondimgcrossIV);
        thirdimgcrossIV=(ImageView) findViewById(R.id.thirdimgcrossIV);

        browseBT.setOnClickListener(this);
        saveproductBT.setOnClickListener(this);
        cancelBT.setOnClickListener(this);

        firstimgcrossIV.setOnClickListener(this);
        secondimgcrossIV.setOnClickListener(this);
        thirdimgcrossIV.setOnClickListener(this);

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

            case R.id.firstimgcrossIV:

                firstimageRL.setVisibility(View.GONE);

                break;

            case R.id.secondimgcrossIV:

                secondimageRL.setVisibility(View.GONE);

                break;

                case R.id.thirdimgcrossIV:

                    thirdimageRL.setVisibility(View.GONE);

                    break ;
        }
    }



    @AfterPermissionGranted(123)
    private void opengallery() {



        String[]perms={Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(this,perms)){

            open_Gallery();

    /*        Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);*/

        }else{

            EasyPermissions.requestPermissions( this,"We need a permission from access phone gallery photos", 123,perms);
        }
    }

    private void open_Gallery() {

        List<Uri>selectedUriList=null;

        TedBottomPicker.with(AddProductActivity.this)
                .setPeekHeight(1600)
                .setSelectMaxCount(3)
                .showTitle(false)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .setSelectedUriList(selectedUriList)
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {


                        if (uriList.size()==1){


                            firstimageRL.setVisibility(View.VISIBLE);
                            secondimageRL.setVisibility(View.GONE);
                            thirdimageRL.setVisibility(View.GONE);


                            firstimageIV.setImageURI(Uri.fromFile(new File(uriList.get(0).getPath().toString())));


                        }else if (uriList.size()==2){

                            firstimageRL.setVisibility(View.VISIBLE);
                            secondimageRL.setVisibility(View.VISIBLE);
                            thirdimageRL.setVisibility(View.GONE);

                            firstimageIV.setImageURI(Uri.fromFile(new File(uriList.get(0).getPath().toString())));
                            secondimageIV.setImageURI(Uri.fromFile(new File(uriList.get(1).getPath().toString())));


                        }

                        else if (uriList.size()==3){

                            firstimageRL.setVisibility(View.VISIBLE);
                            secondimageRL.setVisibility(View.VISIBLE);
                            thirdimageRL.setVisibility(View.VISIBLE);

                            firstimageIV.setImageURI(Uri.fromFile(new File(uriList.get(0).getPath().toString())));
                            secondimageIV.setImageURI(Uri.fromFile(new File(uriList.get(1).getPath().toString())));
                            thirdimageIV.setImageURI(Uri.fromFile(new File(uriList.get(2).getPath().toString())));

                        }



                    }
                });



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



    public String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null,
                null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file
            // path
            result = contentURI.getPath();

            firstimageIV.setImageURI(contentURI);
        } else {
            cursor.moveToFirst();
            try {
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);


            } catch (Exception e) {
               // AppLog.handleException(ImageHelper.class.getName(), e);
                Toast.makeText(AddProductActivity.this,"image getting error", Toast.LENGTH_SHORT).show();

                result = "";
            }
            cursor.close();
        }
        return result;
    }
}
