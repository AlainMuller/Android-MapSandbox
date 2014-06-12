package fr.alainmuller.mapsandbox.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

    private static final LatLng RENNES = new LatLng(48.114434, -1.666916);
    private static final LatLng LAMBALLE = new LatLng(48.415883, -2.482458);
    private static final LatLng PLEUMEUR = new LatLng(48.785912, -3.523732);

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fr_main_map)).getMap();

        if (map != null) {
            Marker rennes = map.addMarker(new MarkerOptions()
                    .position(RENNES)
                    .title("Rennes")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            Marker pleumeur = map.addMarker(new MarkerOptions()
                    .position(PLEUMEUR)
                    .title("Radôme")
                    .snippet("Pleumeur Bodou")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            Marker lamballe = map.addMarker(new MarkerOptions()
                    .position(LAMBALLE)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        }

        // Set the camera to Pleumeur with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(PLEUMEUR, 15));

        // Plannified animation 2s after start
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(RENNES, 15), 5000, null);
            }
        }, 2000);

        // Second Plannified animation 8s after start
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Zoom in, animating the camera.
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(LAMBALLE, 7), 3000, null);
            }
        }, 8000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
