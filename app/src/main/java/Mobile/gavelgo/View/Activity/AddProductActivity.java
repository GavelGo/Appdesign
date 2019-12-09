package Mobile.gavelgo.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.R;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddProductActivity extends Activity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    EditText productnameET,descriptionET;
    Spinner categorySP,subcategorySP;
    CircleImageView profileIV;
    Button browseBT,saveproductBT,cancelBT;

    int SELECT_IMAGE=1;
    String selectedImagePath;

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

        browseBT.setOnClickListener(this);
        saveproductBT.setOnClickListener(this);
        cancelBT.setOnClickListener(this);

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
