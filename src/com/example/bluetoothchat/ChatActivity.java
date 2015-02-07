package com.example.bluetoothchat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class ChatActivity extends SampleActivityBase  {

	BluetoothChatFragment bf=new BluetoothChatFragment();
	private BluetoothAdapter mBluetoothAdapter = null;
	public static final String TAG = "ChatActivity";

	private BluetoothChatService mChatService = null;


	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE_SECURE=1;
	//	private static final int REQUEST_CONNECT_DEVICE_INSECURE=2 ;
	//	private static final int REQUEST_ENABLE_BT=3;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

		setContentView(R.layout.activity_frag);

		if (savedInstanceState == null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			BluetoothChatFragment fragment = new BluetoothChatFragment();
			transaction.replace(R.id.sample_content_fragment, fragment);
			transaction.commit();
		}



	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE_SECURE:
			Toast.makeText(getApplicationContext(), "Chat Activity", Toast.LENGTH_SHORT).show();
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {

				connectDevice(data, true);
			}
			break;
			//		case REQUEST_CONNECT_DEVICE_INSECURE:
			//			// When DeviceListActivity returns with a device to connect
			//			if (resultCode == Activity.RESULT_OK) {
			//				connectDevice(data, false);
			//			}
			//			break;
			//		case REQUEST_ENABLE_BT:
			//			// When the request to enable Bluetooth returns
			//			if (resultCode == Activity.RESULT_OK) {
			//				// Bluetooth is now enabled, so set up a chat session
			//				setupChat();
			//			} else {
			//				// User did not enable Bluetooth or an error occurred
			//				Log.d(TAG, "BT not enabled");
			//				Toast.makeText(getApplicationContext(), R.string.bt_not_enabled_leaving,
			//						Toast.LENGTH_SHORT).show();
			//				//getApplicationContext().finish();
			//			}
		}
	}

	/**
	 * Establish connection with other divice
	 *
	 * @param data   An {@link Intent} with {@link DeviceListActivity#EXTRA_DEVICE_ADDRESS} extra.
	 * @param secure Socket Security type - Secure (true) , Insecure (false)
	 */
	private void connectDevice(Intent data, boolean secure) {
		// Get the device MAC address
		String address = data.getExtras()
				.getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
		// Get the BluetoothDevice object
		BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
		// Attempt to connect to the device
		mChatService.connect(device, secure);
	}

	
}