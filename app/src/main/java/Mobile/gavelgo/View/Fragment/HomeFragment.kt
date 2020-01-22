package Mobile.gavelgo.View.Fragment

import Mobile.gavelgo.Controller.GPSTracker
import Mobile.gavelgo.Controller.Utills
import Mobile.gavelgo.R
import Mobile.gavelgo.View.Activity.UserLogin
import Mobile.gavelgo.View.Adapter.ViewPagerAdapter.HomePagerAdapter
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.io.IOException
import java.util.*


class HomeFragment :Fragment(),View.OnClickListener {
    lateinit var tabsTL: TabLayout
    lateinit var viewPager:ViewPager
    lateinit var homepageradapter:HomePagerAdapter
    lateinit var locationManager: LocationManager
    lateinit var locationRL: RelativeLayout
    lateinit var geocoder: Geocoder
    lateinit var gps: GPSTracker
    lateinit var addressTV :TextView
    lateinit var addresses: List<Address>
    val LOCATIONPERMISSION = 123


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        initView(view)

        return view
    }

    private fun initView(view: View?) {

        addressTV=view!!.findViewById(R.id.addressTV)
        tabsTL=view!!.findViewById(R.id.tabsTL)
        viewPager=view!!.findViewById(R.id.viewPager)
        locationRL=view!!.findViewById(R.id.locationRL)


        homepageradapter = HomePagerAdapter(activity,childFragmentManager)
        viewPager.setAdapter(homepageradapter)
        tabsTL.setupWithViewPager(viewPager)

        locationRL.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.locationRL->{
                getloc()

            }
        }
    }


    private fun getloc() {
        gps = GPSTracker(activity)
        if (gps.canGetLocation()) {
            val latitude = gps.getLatitude()
            val longitude = gps.getLongitude()
            Log.d("tag", "latitude" + latitude.toString())
            Log.d("tag", "longitude" + longitude.toString())

           var  lat = latitude.toString()
          var  lon = longitude.toString()

            getcurrentlocation(lat,lon)


        } else {

            showSettingsAlert()

        }
    }



    fun getcurrentlocation(lat: String, lon: String) {


        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATIONPERMISSION)

        } else {
            geocoder = Geocoder(activity!!, Locale.getDefault())
            Log.d("tag", "geo=" + "working")


            Log.d("tag", "lat=" + lat+" lng="+lon)

            if(lat.toDouble()==0.0){
                getloc()
            }
            else{
                reversegeocode(lat.toDouble(), lon.toDouble())

            }

        }

    }

    fun reversegeocode(latitude: Double, longitude: Double) {
        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            val address = addresses.get(0)
                    .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            val city = addresses.get(0).locality
            val state = addresses.get(0).adminArea
            val country = addresses.get(0).countryName
            val postalCode = addresses.get(0).postalCode
            val knownName = addresses.get(0).featureName

            Log.d("tag", "location=" + city)
            addressTV.setText(city)
            //Toast.makeText(activity, "Loc1=" + city, Toast.LENGTH_SHORT).show()


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(activity)

        // Setting Dialog Title
        alertDialog.setTitle("GPS settings")

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton(
                "Cancel"
        ) { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        Log.e("tag","test "+ "-1")
        when (requestCode) {
            LOCATIONPERMISSION-> {
                Log.e("tag","test "+ "0")
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Utills.showDialog(activity)
                    Handler().postDelayed({
                      Utills.progressDialog_dismiss(activity)
                        getloc()
                    }, 3000)


                    Log.e("tag","test "+ "1")

                } else {
                    Log.e("tag","test " +"2")
                }
                return
            }



        }
    }

}

