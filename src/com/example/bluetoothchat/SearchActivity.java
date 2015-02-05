package com.example.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SearchActivity extends SampleActivityBase {

	public final int REQUEST_ENABLE_BT_CONST=1;
	private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
	BroadcastReceiver mReceiver ;
	BluetoothAdapter mBluetoothAdapter;
	boolean bluetoothEnabled=false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		//		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		//		if(mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()){
		//			bluetoothEnabled=true;
		//			new ConnectionAcceptThread(mBluetoothAdapter).start();
		//
		//		}


	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//FragmentActivity activity = getActivity();
		switch ( item.getItemId()) {
		case R.id.action_list_pired_devices:
			Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
			return true;

			//		case R.id.action_scan_devices:
			//			Toast.makeText(activity, "You pressed Scan",Toast.LENGTH_SHORT).show();
			//			scanBluetoothDevices();
			//			doDiscovery();
			//			return true;

		case R.id.action_turn_on:
			Toast.makeText(getApplicationContext(), "You pressed Turn On/Off",Toast.LENGTH_SHORT).show();
			checkBluetoothStatus();
			return true;

		default:
			break;
		}
		return true;
	}

	private void checkBluetoothStatus(){
		//FragmentActivity activity = getActivity();
		if (mBluetoothAdapter == null) {
			// Device does not support Bluetooth
			Toast.makeText(getApplicationContext(), "No Bluetooth found",Toast.LENGTH_SHORT).show();
			bluetoothEnabled=false;
		}
		else if (!mBluetoothAdapter.isEnabled()) {
			startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT_CONST);
			Toast.makeText(getApplicationContext(), "Please Turn on Bluetooth", Toast.LENGTH_LONG).show();

		}
		else if(mBluetoothAdapter!=null && mBluetoothAdapter.isEnabled()){
			Toast.makeText(getApplicationContext(), "Turning off Bluetooth", Toast.LENGTH_LONG).show();
			mBluetoothAdapter.disable();
		}
	}










}



//		String prodts=(PreferenceManager.getDefaultSharedPreferences(getApplicationContext())).getString("productlist", "");
//		StringTokenizer stk=new StringTokenizer(prodts, "|");
//		StringTokenizer stk2=null;
//		int no_of_prdts = stk.countTokens();
//		String[] ids = new String[no_of_prdts];
//		String[] pnames = new String[no_of_prdts];
//		String[] qtys = new String[no_of_prdts];
//		String[] prices = new String[no_of_prdts];
//		for (int i = 0; i < no_of_prdts; i++) {
//			stk2=new StringTokenizer(stk.nextToken(), ",");
//			ids[i]=((stk2.nextToken()));
//
//
//			pnames[i]=(stk2.nextToken());
//			qtys[i]=((stk2.nextToken()));
//			prices[i]=((stk2.nextToken()));




//				phone=(stk2.nextToken());
//				photo=(stk2.nextToken());
//				if(usename.equalsIgnoreCase(username_entered) && passw.equals(password_entered)){
//					loginStatus=true;
//					Editor e=(PreferenceManager.getDefaultSharedPreferences(getApplicationContext())).edit();
//					e.putString("username", username_entered);
//					e.putString("password", password_entered);
//					e.putString("name", stk2.nextToken());
//					e.putString("email", stk2.nextToken());
//					e.putLong("phone",Long.parseLong(stk2.nextToken()));
//					e.commit();
//					break;