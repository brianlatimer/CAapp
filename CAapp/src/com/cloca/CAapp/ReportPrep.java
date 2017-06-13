package com.cloca.CAapp;

import com.cloca.CAapp.R;
import com.cloca.CAapp.AlbumStorageDirFactory;
import com.cloca.CAapp.BaseAlbumDirFactory;
//import com.example.android.photobyintent.FroyoAlbumDirFactory;


import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
//import java.util.Date;
import java.util.List;
//import java.util.Locale;

import android.app.Activity;
//import android.content.ContentResolver;
//import android.app.AlertDialog;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
//import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class ReportPrep extends Activity {
	
	private static final int ACTION_TAKE_PHOTO_B = 1;
	private static final int ACTION_TAKE_PHOTO_S = 2;
	private static final int ACTION_TAKE_VIDEO = 3;
	private static final int SELECT_PHOTO = 100;

	//private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	//private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private ImageView mImageView;
	private Bitmap mImageBitmap;
	private TextView mTextView;
	private TextView mCatView;
	private TextView mInfoView;
	
	//private static final String VIDEO_STORAGE_KEY = "viewvideo";
	//private static final String VIDEOVIEW_VISIBILITY_STORAGE_KEY = "videoviewvisibility";
	private VideoView mVideoView;
	private Uri mVideoUri;

	private String mCurrentPhotoPath;

	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	
	private static final String TAG = "";
	
	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;	
	private LocationManager locationManager;
	private TextView locationText;
	private static String GPS_INFO;
	private Uri ATTACHMENT_URI;
	//private String FILE_NAME;
    
	
	/** Called when the activity is first created.**/	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportprep);

		Intent showReportPrep = getIntent();
		// Report Category
		mCatView = (TextView) findViewById(R.id.report_category);
		// Report Info (from strings.xml file). One of our strings has a hyperlink in it, so we use setMovementMethod as well.
		mInfoView = (TextView) findViewById(R.id.category_info);
		mInfoView.setMovementMethod(LinkMovementMethod.getInstance());
		// Video view (not currently used)
		mVideoView = (VideoView) findViewById(R.id.videoView1);
		// TextView for user's to enter information
		mTextView = (TextView) findViewById(R.id.description_new);
		mTextView.setHint(R.string.description_hint);
		
		// GPS co-ordinates. Just display the info, do not let the user edit it.
		locationText = (TextView) findViewById(R.id.gps_loc);
		locationText.setEnabled(false);
		// bitmap and video uri also not currently used
		mImageBitmap = null;
		mVideoUri = null;
		
		// Get GPS co-ordinates
		locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); 
		if (location != null) {
		    Log.d(TAG, location.toString());
		    String text = String.format("Lat:\t %.2f Long:\t %.2f Bearing:\t %.2f", location.getLatitude(), 
		    		location.getLongitude(),  location.getBearing());
		    GPS_INFO = text;
	        locationText.setText(text);
		}
		
		//mCatView.setTextSize(12);
		mCatView.setText(showReportPrep.getCharSequenceExtra("report_category"));
		mInfoView.setText(showReportPrep.getCharSequenceExtra("category_info"));		

		Button picBtn = (Button) findViewById(R.id.btnTakePhoto);
		setBtnListenerOrDisable(picBtn,	mTakePicOnClickListener, MediaStore.ACTION_IMAGE_CAPTURE);

		Button slctBtn = (Button) findViewById(R.id.btnSelectPhoto);
		slctBtn.setOnClickListener(mSlctPicOnClickListener);		

		Button vidBtn = (Button) findViewById(R.id.btnTakeVid);
		setBtnListenerOrDisable(vidBtn,	mTakeVidOnClickListener, MediaStore.ACTION_VIDEO_CAPTURE);
		
		Button emlBtn = (Button) findViewById(R.id.send_report_button);
		emlBtn.setOnClickListener(mEmlBtnOnClickListener);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
		} else {
			mAlbumStorageDirFactory = new BaseAlbumDirFactory();
		}
	}

	/* Photo album for this application */
	private String getAlbumName() {
		return getString(R.string.album_name);
	}

		
	private File getAlbumDir() {
		
		File storageDir = null;
 		
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());			

			if (storageDir != null) {
				if (! storageDir.mkdirs()) {
					if (! storageDir.exists()){
						Log.d("CLOCA", "failed to create directory");
						return null;
					}
				}
			}
			
		} else {
			Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
		}
		
		return storageDir;
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
		File albumF = getAlbumDir();
		File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
		return imageF;
	}

	private File setUpPhotoFile() throws IOException {
		
		File f = createImageFile();
		mCurrentPhotoPath = f.getAbsolutePath();
		
		return f;
	}

	private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
		int targetW = mImageView.getWidth();
		int targetH = mImageView.getHeight();

		/* Get the size of the image */
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		/* Figure out which way needs to be reduced less */
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		/* Set bitmap options to scale the image decode target */
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		/* Associate the Bitmap to the ImageView */
		mImageView.setImageBitmap(bitmap);
		mVideoUri = null;
		mImageView.setVisibility(View.VISIBLE);
		mVideoView.setVisibility(View.INVISIBLE);
	}

	private void galleryAddPic() {
		    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
			File f = new File(mCurrentPhotoPath);
		    Uri contentUri = Uri.fromFile(f);
		    mediaScanIntent.setData(contentUri);
		    this.sendBroadcast(mediaScanIntent);
	}
	
	private void dispatchSelectPictureIntent() {
		
		Intent SelectPictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
		SelectPictureIntent.setType("image/*");
		startActivityForResult(SelectPictureIntent, SELECT_PHOTO);		
	}
	
	private void dispatchTakePictureIntent(int actionCode) {

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch(actionCode) {
		case ACTION_TAKE_PHOTO_B:
			File f = null;
			
			try {
				f = setUpPhotoFile();
				mCurrentPhotoPath = f.getAbsolutePath();
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				// set our variable so 'send email' can access it
				ATTACHMENT_URI = Uri.fromFile(f);
			} catch (IOException e) {
				e.printStackTrace();
				f = null;
				mCurrentPhotoPath = null;
			}
			break;

		default:
			break;			
		} // switch

		startActivityForResult(takePictureIntent, actionCode);
	}

	private void dispatchTakeVideoIntent() {
		Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		startActivityForResult(takeVideoIntent, ACTION_TAKE_VIDEO);
	}
	
	private void dispatchSendEmailIntent() {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");		
		
		// setup new email message
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mobilereporting@cloca.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, mCatView.getText());
		i.putExtra(Intent.EXTRA_TEXT, GPS_INFO + "     " + "\n\n" + mTextView.getText());
		
		// Attach the photo if there is one
		if (ATTACHMENT_URI != null) {
			
			i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			i.setType("image/jpeg");
			i.putExtra(Intent.EXTRA_STREAM, ATTACHMENT_URI);
		
		}

		// check some conditions then send email or fail if no clients installed					
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ReportPrep.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}		
		
		// Reset the form and vars for next use
		ATTACHMENT_URI = null;		
		
		// Take user back to the initial report screen
		super.finish();
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
		mImageView.setImageBitmap(mImageBitmap);
		mVideoUri = null;
		mImageView.setVisibility(View.VISIBLE);
		mVideoView.setVisibility(View.INVISIBLE);
	}
	
	private void handleSelectPhoto(Intent data) {
        Uri selectedImage = data.getData();
        if (selectedImage != null)
        {
           String path = selectedImage.toString();
           if (path.toLowerCase().startsWith("file://"))
           {
              // Selected file/directory path is below
              path = (new File(URI.create(path))).getAbsolutePath();
           } else {
        	   path = selectedImage.toString();
        	   ATTACHMENT_URI = selectedImage;
           }
        }        
	}

	private void handleBigCameraPhoto() {

		if (mCurrentPhotoPath != null) {
			//setPic();
			galleryAddPic();
			mCurrentPhotoPath = null;
		}

	}

	private void handleCameraVideo(Intent intent) {
		mVideoUri = intent.getData();
		mVideoView.setVideoURI(mVideoUri);
		mImageBitmap = null;
		mVideoView.setVisibility(View.VISIBLE);
		mImageView.setVisibility(View.INVISIBLE);
	}

	Button.OnClickListener mTakePicOnClickListener = 
		new Button.OnClickListener() {
		//@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
		}
	};

	Button.OnClickListener mSlctPicOnClickListener = 
		new Button.OnClickListener() {
		public void onClick(View v) {
			dispatchSelectPictureIntent();
			
		}
	};

	Button.OnClickListener mTakeVidOnClickListener = 
		new Button.OnClickListener() {
		public void onClick(View v) {
			dispatchTakeVideoIntent();
		}
	};
	
	Button.OnClickListener mEmlBtnOnClickListener = 
			new Button.OnClickListener() {
			public void onClick(View v) {
				dispatchSendEmailIntent();
			}
		};

	Button.OnClickListener mSlctBtnOnClickListener = 
			new Button.OnClickListener() {
			public void onClick(View v) {
			dispatchSelectPictureIntent();
			}
		};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	        	handleSelectPhoto(data);
	        }
		case ACTION_TAKE_PHOTO_B: {
			if (resultCode == RESULT_OK) {
				handleBigCameraPhoto();
			}
			break;
		} // ACTION_TAKE_PHOTO_B

		case ACTION_TAKE_PHOTO_S: {
			if (resultCode == RESULT_OK) {
				handleSmallCameraPhoto(data);
			}
			break;
		} // ACTION_TAKE_PHOTO_S

		case ACTION_TAKE_VIDEO: {
			if (resultCode == RESULT_OK) {
				handleCameraVideo(data);
			}
			break;
		} // ACTION_TAKE_VIDEO
		} // switch
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  setContentView(R.layout.reportprep);
	}

	// Some life-cycle call-backs so that the image can survive orientation change
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		//outState.putParcelable(VIDEO_STORAGE_KEY, mVideoUri);
		//outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		//outState.putBoolean(VIDEOVIEW_VISIBILITY_STORAGE_KEY, (mVideoUri != null) );
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		//mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
		//mVideoUri = savedInstanceState.getParcelable(VIDEO_STORAGE_KEY);
		//mImageView.setImageBitmap(mImageBitmap);
/*		mImageView.setVisibility(
				savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? 
						ImageView.VISIBLE : ImageView.INVISIBLE
		);*/
		//mVideoView.setVideoURI(mVideoUri);
/*		mVideoView.setVisibility(
				savedInstanceState.getBoolean(VIDEOVIEW_VISIBILITY_STORAGE_KEY) ? 
						ImageView.VISIBLE : ImageView.INVISIBLE
		);*/
	}

	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
	 *
	 * @param context The application's environment.
	 * @param action The Intent action to check for availability.
	 *
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list =
			packageManager.queryIntentActivities(intent,
					PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void setBtnListenerOrDisable( 
			Button btn, 
			Button.OnClickListener onClickListener,
			String intentName
	) {
		if (isIntentAvailable(this, intentName)) {
			btn.setOnClickListener(onClickListener);        	
		} else {
			btn.setText( 
				getText(R.string.cannot).toString() + " " + btn.getText());
			btn.setClickable(false);
		}
	}

}
