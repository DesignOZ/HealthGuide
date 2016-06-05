package com.mobile.healthguide.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.healthguide.db.DbActivity;
import com.mobile.healthguide.R;
import com.mobile.healthguide.util.Kalman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ExerActivity extends Activity {
	private SensorManager manager;
	private Sensor accSensor;

	private float mX, mY, mZ;
	private float mX_Start, mY_Start, mZ_Start;

	private Kalman mKalmanAccX;
	private Kalman mKalmanAccY;
	private Kalman mKalmanAccZ;

	private int nTemp = 0;
	private int nCount = 0;
//	public static int callCount = 0; //함수 호출 카운트 변수
	private boolean bFirst = false;
	private boolean bWork = false;
	private boolean bReset = false;

	private boolean bX, bY, bZ = false;

	Activity mContext;
	EditText edtMax;
	TextView tvTitle, tvNow, tvState;
	Button btnBack, btnSavedb;
	String sSelect;
	Boolean bExer = false;

	MediaPlayer fini;
	MediaPlayer noti;
	MediaPlayer mp;
	NotificationManager nm;
	Vibrator vibe;



	BluetoothAdapter mBluetoothAdapter;//
	static final int REQUEST_ENABLE_BT = 10;//
	int mPairedDeviceCount = 0;//
	Set<BluetoothDevice> mDevices;//
	BluetoothDevice mRemoteDevice;//
 	BluetoothSocket mSocket = null;//
	OutputStream mOutputStream = null;//
	InputStream mInputStream = null;//
	Thread mWorkerThread = null;//
	byte[] readBuffer;
	int readBufferPosition;
	String mStrDelimiter = "\n";
	char mCharDelimiter = '\n';

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exer);
		btnSavedb = (Button) findViewById(R.id.btnSavedb);
		btnSavedb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExerActivity.this, DbActivity.class);
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		mContext = this;



		checkBluetooth();

		Intent intent = getIntent();
		sSelect = intent.getStringExtra("select");
		bExer = false;

		mKalmanAccX = new Kalman(0.0f);
		mKalmanAccY = new Kalman(0.0f);
		mKalmanAccZ = new Kalman(0.0f);

		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		accSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		mp = MediaPlayer.create(this, R.raw.voice);
		mp.setLooping(true);
		noti = MediaPlayer.create(this, R.raw.bell);
		noti.setLooping(false);
		fini = MediaPlayer.create(this, R.raw.finish);
		fini.setLooping(false);

		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		edtMax = (EditText) findViewById(R.id.edtMax);
		if (sSelect.equals("팔굽혀펴기")) {
			edtMax.setText("20");
		} else if (sSelect.equals("데드리프트")) {
			edtMax.setText("15");
		} else if (sSelect.equals("턱걸이")) {
			edtMax.setText("10");
		} else if (sSelect.equals("딥스")) {
			edtMax.setText("15");
		} else if (sSelect.equals("윗몸일으키기")) {
			edtMax.setText("50");
		} else if (sSelect.equals("V_크런치")) {
			edtMax.setText("30");
		} else if (sSelect.equals("터칭토우")) {
			edtMax.setText("100");
		} else if (sSelect.equals("스쿼트")) {
			edtMax.setText("20");
		} else if (sSelect.equals("러싱런지")) {
			edtMax.setText("30");
		} else if (sSelect.equals("만보기")) {
			edtMax.setText("1000");
		} else if (sSelect.equals("사이드런지")) {
			edtMax.setText("20");
		}

		tvTitle = (TextView) findViewById(R.id.tvTitle_Exer);
		tvTitle.setText(sSelect);

		tvState = (TextView) findViewById(R.id.tvState);
		tvState.setText("중지");

		btnBack = (Button) findViewById(R.id.btnBack_Exer);
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		tvNow = (TextView) findViewById(R.id.tvNow);
		tvNow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (bExer == true) {
					vibe.vibrate(100);
					mp.pause();
					manager.unregisterListener(sel);
					tvState.setText("중지");
					btnBack.setEnabled(true);
				} else if (bExer == false) {
					tvState.setText("준비");
					vibe.vibrate(100);

					mX_Start = mY_Start = 0;
					bFirst = true;
					nCount = 0;
					nTemp = 5;
					tvNow.setText(String.format("%d개", nCount));

					mp.start();
					btnBack.setEnabled(false);
					Thread delayThread = new Thread() {
						@Override
						public void run() {
							try {
								synchronized (this) {
									wait(2300);
								}
							} catch (InterruptedException ex) {
							}
							mp.pause();

							mContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									tvState.setText("시작");
									//sendData("maxCount" + edtMax.getText().toString());
									sendData(("maxCount" + edtMax.getText().toString()));
									manager.registerListener(sel, accSensor, SensorManager.SENSOR_DELAY_GAME);
								}
							});
						}
					};
					delayThread.start();
				}
				bExer = !bExer;
			}
		});


	}

	public SensorEventListener sel = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];

			float filteredX = 0.0f;
			float filteredY = 0.0f;
			float filteredZ = 0.0f;

			boolean bState = false;

			filteredX = (float) mKalmanAccY.update(x);
			filteredY = (float) mKalmanAccX.update(y);
			filteredZ = (float) mKalmanAccX.update(z);

			mX = filteredX;
			mY = filteredY;
			mZ = filteredZ;

			if (bFirst == true) {
				if (nTemp == 0) {
					mX_Start = mX;
					mY_Start = mY;
					mZ_Start = mZ;
					bX = bY = bZ = bFirst = false;
					bReset = true;
				} else {
					nTemp--;
				}
			} else {
				bState = false;

				if (sSelect.equals("턱걸이")) {
					bState = (mY <= (mY_Start + 3) && mY >= (mY_Start - 30));
				} else if (sSelect.equals("팔굽혀펴기")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("데드리프트")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("딥스")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("V_크런치")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("터칭토우")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("스쿼트")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("러싱런지")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("사이드런지")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("만보기")) {
					bState = (mX <= (mX_Start + 5) && mX >= (mX_Start - 30));
				} else if (sSelect.equals("윗몸일으키기")) {
					bX = bY = bZ = false;
					if (mX <= (mX_Start + 3) && mX >= (mX_Start - 30)) bX = true;
					if (mY <= (mY_Start + 3) && mY >= (mY_Start - 30)) bY = true;
					if (mZ <= (mZ_Start + 3) && mZ >= (mZ_Start - 30)) bZ = true;

					bState = ((bX == true && bY == true) && bZ == true);
				}

				if (bState == true) {
					if (bReset == false) {
						Log.i("test", "=====RESET===========================");
						bReset = true;
						nCount++;
						vibe.vibrate(100);
						tvNow.setText(String.format("%d개", nCount));

						////////////////////////////////////
						//sendData("count" + Integer.toString(nCount)); //갯수 전송
						sendData("count" + Integer.toString(nCount));
						if (Integer.parseInt(edtMax.getText().toString()) <= nCount)
							finish_notice(true);
						else if (Integer.parseInt(edtMax.getText().toString()) < (nCount + 3))
							finish_notice(false);
					}
				} else {
					if (bReset == true) bReset = false;
				}
				Log.i("test", String.format("%.1f", mX) + "/" + String.format("%.1f", mY) + "/" + String.format("%.1f", mZ) + "  -  " + String.format("%.1f", mX_Start) + "/" + String.format("%.1f", mY_Start) + "/" + String.format("%.1f", mZ_Start));
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};


	public void finish_notice(boolean bState) {
		if (bState == true) {
			fini.seekTo(0);
			fini.start();
			mp.pause();

			bExer = false;
			manager.unregisterListener(sel);
			tvState.setText("완료");
			btnBack.setEnabled(true);
		} else {
			noti.seekTo(0);
			noti.start();
		}
	}

	@Override
	protected void onResume() {
		if (bWork == true)
			manager.registerListener(sel, accSensor, SensorManager.SENSOR_DELAY_GAME);


		super.onResume();
	}

	@Override
	protected void onPause() {
		manager.unregisterListener(sel);
		super.onPause();
	}


	/////////////////////////


	void sendData(String msg) {
		try {
			mOutputStream.write(msg.getBytes());
			Log.d("SH", "" + msg.getBytes());
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
			finish();
		}
	}

	BluetoothDevice getDeviceFromBondedList(String name) {
		BluetoothDevice selectedDevice = null;
		for (BluetoothDevice device : mDevices) {
			if (name.equals(device.getName())) {
				selectedDevice = device;
				break;
			}
		}
		return selectedDevice;
	}

	void checkBluetooth() {
		//callCount++; // checkBluetooth 함수 호출 시 callCount 변수 1을 증가 시킨다.
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Toast.makeText(getApplicationContext(),
					"기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
			finish();
		} else {
			if (!mBluetoothAdapter.isEnabled()) {
				Toast.makeText(getApplicationContext(),
						"현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show();
				Intent enableBtIntent =
						new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
			else
				selectDevice();
		}
	}

//------------------------------------------------------------------------------------ Back Button 버튼 이벤트 처리
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if( keyCode == KeyEvent.KEYCODE_BACK )
		{
			AlertDialog.Builder d = new AlertDialog.Builder(this);                                //다이얼 로그 팝업
			d.setTitle("알림");
			d.setMessage("뒤로 이동 시 블루투스 연결이 해제 됩니다. \n 이동하시겠습니까?");
			d.setIcon(R.drawable.ic_launcher);

			d.setPositiveButton("예",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
					destroySocket();   //블루투스 연곃 해제 및 초기화
				}
			});

			d.setNegativeButton("아니요",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			AlertDialog dialog = d.show();
			TextView msgView = (TextView)dialog.findViewById(android.R.id.message);
			msgView.setTextSize(14);

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
//-------------------------------------------------------------------------------------------------------------------


	//------------------------------------------------- 블루투스 연결 해제 및 초기화 부분 소스 추가 함.
	public void destroySocket()
	{
		try {
			if(mInputStream != null)
			{
				mInputStream.close();
				mInputStream = null;
			}
			if(mOutputStream != null)
			{
				mOutputStream.close();
				mOutputStream  = null;
			}
			if(mSocket != null)
			{
				mSocket.close();
				mSocket = null;
			}
			if(mInputStream == null && mOutputStream == null && mSocket == null)
				Toast.makeText(getApplicationContext(),"블루투스 연결이 해제 되었습니다.",Toast.LENGTH_SHORT).show();
		} catch (IOException e1) {
			mInputStream = null;
			mOutputStream  = null;
			mSocket = null;
			e1.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------------------------------------------------

	void selectDevice(){
		mDevices = mBluetoothAdapter.getBondedDevices();
		mPairedDeviceCount = mDevices.size();
		if(mPairedDeviceCount == 0){
			Toast.makeText(getApplicationContext(),
					"페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
			finish();
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("블루투스 장치 선택");
		List<String> listItems = new ArrayList<String>();
		for (BluetoothDevice device : mDevices) {
			listItems.add(device.getName());
		}
		listItems.add("취소");
		final CharSequence[] items =
				listItems.toArray(new CharSequence[listItems.size()]);
		builder.setItems(items, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int item){
				if(item == mPairedDeviceCount){
					Toast.makeText(getApplicationContext(),
							"연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
					finish();
				}
				else{
					connectToSelectedDevice(items[item].toString());
				}
			}
		});
		builder.setCancelable(false);
		AlertDialog alert = builder.create();
		alert.show();
	}

	void connectToSelectedDevice(String selectedDeviceName){
		mRemoteDevice = getDeviceFromBondedList(selectedDeviceName);
		UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
		try{
			mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
			mSocket.connect();
			mOutputStream = mSocket.getOutputStream();
			mInputStream = mSocket.getInputStream();

			//beginListenForData();
		}catch(Exception e){
			Toast.makeText(getApplicationContext(),
					"블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
			finish();
		}
	}

}
