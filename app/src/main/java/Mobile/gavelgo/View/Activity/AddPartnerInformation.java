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
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import Mobile.gavelgo.Controller.Utills;
import Mobile.gavelgo.R;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class AddPartnerInformation extends Activity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    CircleImageView profileIV;
    Button browseBT, saveBT, addproductBT;
    EditText firstnameET, lastnameET, phoneET, streetET, aptET, cityET, stateET, zipET, descriptionET;
    boolean saveprofile = false;
    int SELECT_IMAGE=1;
    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpartner_information);

        profileIV = findViewById(R.id.profileIV);
        browseBT = findViewById(R.id.browseBT);
        saveBT = findViewById(R.id.saveBT);
        addproductBT = findViewById(R.id.addproductBT);
        firstnameET = findViewById(R.id.firstnameET);
        lastnameET = findViewById(R.id.lastnameET);
        phoneET = findViewById(R.id.phoneET);
        streetET = findViewById(R.id.streetET);
        aptET = findViewById(R.id.aptET);
        cityET = findViewById(R.id.cityET);
        stateET = findViewById(R.id.stateET);
        zipET = findViewById(R.id.zipET);
        descriptionET = findViewById(R.id.descriptionET);

        browseBT.setOnClickListener(this);
        saveBT.setOnClickListener(this);
        addproductBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.saveBT:

                if (firstnameET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter first name");
                } else if (lastnameET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter last name");
                } else if (phoneET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter phone number");
                } else if (streetET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter street address");
                } else if (aptET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter apt/suite");
                } else if (cityET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter city");
                } else if (stateET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter state");
                } else if (zipET.getText().toString().equals("")) {
                    Utills.showalerter(AddPartnerInformation.this, "Please enter zipcode");
                } else {
                    Utills.showalerter(AddPartnerInformation.this, "Profile uploaded successfully");
                    saveprofile = true;
                }

                break;

            case R.id.addproductBT:

                if (saveprofile == false) {
                    Utills.showalerter(AddPartnerInformation.this, "First save Profile information ");
                } else {
                    Intent intent = new Intent(AddPartnerInformation.this, AddProductActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.browseBT:

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
                Toast.makeText(AddPartnerInformation.this, "Canceled", Toast.LENGTH_SHORT).show();
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
