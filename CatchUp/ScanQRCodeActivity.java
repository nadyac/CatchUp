package com.example.catchup;

import com.example.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class ScanQRCodeActivity extends ActionBarActivity {

	//-----------------------------------------------------------------
	// Scanning Code
	//-----------------------------------------------------------------
    private Button scan;
    private String qr_content = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
         
        scan= (Button)findViewById(R.id.btnScan);
         
        scan.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
        if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
    }
    //---------------------------------------------------------
    // Code that does the barcode scanning magic. 
    //---------------------------------------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
           if (requestCode == 0) {
              if (resultCode == RESULT_OK) {
                  
                 String contents = intent.getStringExtra("SCAN_RESULT");
                 String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
              
                 // Handle successful scan
                 qr_content = contents;
                 System.out.println("The QR content contains " + qr_content);
                 
                                           
              } else if (resultCode == RESULT_CANCELED) {
                 // Handle cancel
                 Log.i("App","Scan unsuccessful");
              }
         }
      }
     
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
    
    //******END OF QR SCANNING CODE********\\
    //---------------------------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scan_qrcode, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_scan_qrcode,
					container, false);
			return rootView;
		}
	}

}
