package com.ao.fstracking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.ao.fstracking.map.MyLocationProvider;
import com.ao.fstracking.ui.charTest.CharHomeTest;
import com.ao.fstracking.ui.cloudMessages.FirMessages;
import com.ao.fstracking.ui.profile.ProfileCV;
import com.ao.fstracking.ui.signUp.SignUpPage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

	private static final int LOCATION_PERMISSION_REQUEST_CODE = 200;
	Button poshN;
	Button signUp;
	Button showAPI;

	//Button liveCurrentLocation;
	Button profile;
	SupportMapFragment mapFragment;
	TextView show_Location;
	GoogleMap googleMap;
	Marker marker;
	Location myLoaction = null;
	MyLocationProvider locationProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		mapFragment.getMapAsync(this);
		if (isLocationPermissionGranted()) {
			getUserLocation();
			Toast.makeText(MapsActivity.this, "live stream location", Toast.LENGTH_SHORT).show();

		} else {
			requestLocationPermission();
		}
		initView();

	}

	@Override
	public void onMapReady(final GoogleMap googleMap) {
		this.googleMap = googleMap;
		googleMap.setMyLocationEnabled(true);
		drawUserLocation();


		googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
			@Override
			public void onMapClick(LatLng latLng) {
				Toast.makeText(MapsActivity.this, "" + latLng.latitude + latLng.longitude, Toast.LENGTH_SHORT).show();
				/* MarkerOptions markerOptions = new MarkerOptions();

				 // Setting the position for the marker
				 markerOptions.position(latLng);

				 // Setting the title for the marker.
				 // This will be displayed on taping the marker
				 markerOptions.title(latLng.latitude + " : " + latLng.longitude);

				 // Clears the previously touched position
				 googleMap.clear();

				 // Animating to the touched position
				 googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

				 // Placing a marker on the touched position
				 googleMap.addMarker(markerOptions);
				 */
			}
		});

		googleMap.getCameraPosition();
		googleMap.getFocusedBuilding();


	}

	public void drawUserLocation() {
		if (myLoaction == null || googleMap == null) return;
		if (marker == null)

			marker = googleMap.addMarker(new MarkerOptions()
					.title("i'm here")
					.position(new LatLng(myLoaction.getLatitude(), myLoaction.getLongitude()))
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.llocatin)));

		marker.setPosition(new LatLng(myLoaction.getLatitude(), myLoaction.getLongitude()));
		googleMap.animateCamera(
				CameraUpdateFactory.newLatLngZoom(
						new LatLng(myLoaction.getLatitude(), myLoaction.getLongitude()), 15));

		LatLng D = new LatLng(myLoaction.getLatitude(), myLoaction.getLongitude());
		show_Location.setText(" " + D);

	}

	public void getUserLocation() {
		locationProvider = new MyLocationProvider(this);

		//googleMap.setMyLocationEnabled(true);
		//googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		//googleMap.getUiSettings().setZoomControlsEnabled(true);

		myLoaction = locationProvider.getCurrentLocaion(
				new LocationListener() {
					@Override
					public void onLocationChanged(Location location) {
						myLoaction = location;
						drawUserLocation();
						Toast.makeText(MapsActivity.this,
								"" +
										location.getLatitude() + " " +
										location.getLongitude(), Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onStatusChanged(String provider, int status, Bundle extras) {
						Toast.makeText(MapsActivity.this, provider + "'s status changed to " + status + "!",
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onProviderEnabled(String provider) {
						Toast.makeText(MapsActivity.this, "Provider " + provider + " enabled!",
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onProviderDisabled(String provider) {
						Toast.makeText(MapsActivity.this, "Provider " + provider + " disabled!",
								Toast.LENGTH_SHORT).show();
					}
				}
		);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String[] permissions,
										   int[] grantResults) {
		switch (requestCode) {
			case LOCATION_PERMISSION_REQUEST_CODE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
					getUserLocation();
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Toast.makeText(this, "cannot access user Location", Toast.LENGTH_SHORT).show();

				}
				return;
			}
			// other 'case' lines to check for other
			// permissions this app might request.
		}
	}

	public boolean isLocationPermissionGranted() {
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.ACCESS_FINE_LOCATION)
				== PackageManager.PERMISSION_GRANTED) {
			return true;
		}
		return false;
	}

	public void requestLocationPermission() {
		// Permission is not granted
		// Should we show an explanation?
		if (ActivityCompat.shouldShowRequestPermissionRationale(this,
				Manifest.permission.ACCESS_FINE_LOCATION)) {
			// Show an explanation to the user *asynchronously* -- don't block
			// this thread waiting for the user's response! After the user
			// sees the explanation, try again to request the permission.
			Toast.makeText(this, "app wants to access location to find nearby cafes", Toast.LENGTH_SHORT).show();
		} else {
			// No explanation needed; request the permission
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
					LOCATION_PERMISSION_REQUEST_CODE);
			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.
		}
	}


	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.poshN) {
			startActivity(new Intent(MapsActivity.this, FirMessages.class));

		} else if (view.getId() == R.id.sign_Up) {
			//Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MapsActivity.this, SignUpPage.class));

		} else if (view.getId() == R.id.show_API) {
			//Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MapsActivity.this, CharHomeTest.class));

		} /*else if (view.getId() == R.id.live_current_location) {
			Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();

		}*/ else if (view.getId() == R.id.profile) {
			//	Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MapsActivity.this, ProfileCV.class));


		}
	}

	private void initView() {
		poshN = findViewById(R.id.poshN);
		poshN.setOnClickListener(MapsActivity.this);
		signUp = findViewById(R.id.sign_Up);
		signUp.setOnClickListener(MapsActivity.this);
		showAPI = findViewById(R.id.show_API);
		showAPI.setOnClickListener(MapsActivity.this);
		//	liveCurrentLocation = findViewById(R.id.live_current_location);
		//liveCurrentLocation.setOnClickListener(MapsActivity.this);
		profile = findViewById(R.id.profile);
		show_Location = findViewById(R.id.show_Location);
		profile.setOnClickListener(MapsActivity.this);
	}
	/*
	 /* Check if device has network */
	/*private boolean isNetworkAvailable()
	{
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean isAvailable = false;
		if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
		return isAvailable;
	}

	*//* Display error *//*
	private void alertAboutERROR()
	{
		DialogAlert alertFrag = new DialogAlert();
		alertFrag.show(getFragmentManager(), "error_dial");
	}

	*/

}
