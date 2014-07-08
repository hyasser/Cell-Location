package com.telepin.cellTower;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import android.provider.Settings;

public class LocationInfo extends CordovaPlugin {

	public static final String ACTION = "getCID";

	/**
	 * Constructor.
	 */
	public LocationInfo() {
	}

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	@Override
	public boolean execute(String action, JSONArray data,
			CallbackContext callbackContext) throws JSONException {

		if (action.equals(ACTION)) {

			this.getCID(callbackContext);
			return true;
		}
		return false;

	}

	public void getCID(CallbackContext callbackContext) throws IOException {
		TelephonyManager tm = (TelephonyManager) this.cordova.getActivity()
				.getApplicationContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();
		int cellID = location.getCid();
		Log.i("Cell ID", cellID + "");
		if (cellID != null) {
			callbackContext.success(cellID + "");
		} else {
			callbackContext.error("Cell ID is null");
		}

	}

}
