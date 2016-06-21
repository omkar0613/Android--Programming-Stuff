package com.example.user.buspasssystem;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Code39;
import com.onbarcode.barcode.android.GeneratedBarcodeInfo;
import com.onbarcode.barcode.android.IBarcode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class NewRegistration extends ActionBarActivity {
    public static final String TAG="StatusActivity";
    EditText Name,Password;
    ImageView imageView;
 //   TextView barcodecr;
    String barcodeInString;
 //   int id;
    Canvas canvas;
    private static int TAKE_PICTURE=1;
    private Uri imageUri;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);
        Button cameraButton=(Button)findViewById(R.id.btnCapturePicture);
        Name=(EditText)findViewById(R.id.id_enterName);
        Password=(EditText)findViewById(R.id.id_enterPassword);
//barcodecr=(TextView)findViewById(R.id.id_barcode);
    }
public void button_Camera(View v){
    if(v.getId()==R.id.btnCapturePicture) {
        Log.d(TAG, "Clicked camera button");
        takephoto(v);
    }
}
    private void takephoto(View v){
        Log.d(TAG, "Inside takephoto function");
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        Log.d(TAG, "Camera opened");
        File photo=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"picture.jpg");
        imageUri=Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);   // stores the image
        Log.d(TAG, "Stored the image");
        startActivityForResult(intent,TAKE_PICTURE);
        Log.d(TAG, "Last line from takephoto executed");
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        Log.d(TAG, "Inside onactResult after executing super");
        if(resultCode== Activity.RESULT_OK){
            Uri selectedImage=imageUri;
            Log.d(TAG, "In this block if results are ok");
            getContentResolver().notifyChange(selectedImage,null);
            imageView=(ImageView)findViewById(R.id.id_Image);
            ContentResolver cr=getContentResolver();
            Log.d(TAG, "After Content resolver definition");
            Bitmap bitmap;
            try{
            bitmap=MediaStore.Images.Media.getBitmap(cr,selectedImage);
                Log.d(TAG, "Inside try block");
                imageView.setImageBitmap(bitmap);
                Toast.makeText(NewRegistration.this,selectedImage.toString(),Toast.LENGTH_LONG).show();

            }
            catch(Exception e){
                Log.e(TAG,e.toString());
                Log.d(TAG, "Under Exception");
            }
        }
    }
    public void button_Save(View v) throws Exception {
        if(v.getId()==R.id.id_Save) {
            BusDatabase busDatabase=new BusDatabase(this);
            Log.d(TAG, "Save button is clicked");
            String name = Name.getText().toString();
            String password = Password.getText().toString();
     //       Log.d(TAG, String.valueOf(id));
            Log.d(TAG, name);
            Log.d(TAG, password);
            Log.d(TAG, String.valueOf(imageView));
            ///////////////////////////////////////////////////////////////
            Code39 barcode = new Code39();
            barcode.setData(String.valueOf(name));
            barcode.setExtension(false);
            barcode.setAddCheckSum(true);
            // Code 39 Wide Narrow bar Ratio
            // Valid value is from 2.0 to 3.0 inclusive.
            barcode.setN(3.0f);
            // The space between 2 characters in code 39; This a multiple of X; The default is 1.;
            // Valid value is from 1.0 (inclusive) to 5.3 (exclusive)
            barcode.setI(1.0f);
            barcode.setShowStartStopInText(true);

            // Unit of Measure, pixel, cm, or inch
            barcode.setUom(IBarcode.UOM_PIXEL);
            // barcode bar module width (X) in pixel
            barcode.setX(1f);
            // barcode bar module height (Y) in pixel
            barcode.setY(75f);

            // barcode image margins
            barcode.setLeftMargin(10f);
            barcode.setRightMargin(10f);
            barcode.setTopMargin(10f);
            barcode.setBottomMargin(10f);

            // barcode image resolution in dpi
            barcode.setResolution(72);

            // disply barcode encoding data below the barcode
            barcode.setShowText(true);
            // barcode encoding data font style
            barcode.setTextFont(new AndroidFont("Arial", Typeface.NORMAL, 12));
            // space between barcode and barcode encoding data
            barcode.setTextMargin(6);
            barcode.setTextColor(AndroidColor.black);

            // barcode bar color and background color in Android device
            barcode.setForeColor(AndroidColor.black);
            barcode.setBackColor(AndroidColor.white);

            RectF bounds = new RectF(30,30,160,160);
            barcode.drawBarcode(canvas,bounds);
      //      Log.d(TAG, "Canvas is "+String.valueOf(canvas));
            GeneratedBarcodeInfo barInfo = barcode.getBarcodeInfo();
     //       Log.d(TAG, String.valueOf(barcode.getBarcodeInfo()));
            barcodeInString=String.valueOf(barcode.getBarcodeInfo());
            barcodeInString=barcodeInString.substring(51,58);
            //barcodecr.setText(barcodeInString);
            Log.d(TAG, barcodeInString);
            ///////////////////////////////////////////////////////////////
            busDatabase.insert(name,password,String.valueOf(imageView),barcodeInString);
     //       busDatabase.insert(name,password,imageView,barcodeInString);
            Log.d(TAG, "Clicked Save Button from Add-Contacts page");
         //   id++;
            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
            Name.setText("");
            Password.setText("");
            imageView.setImageDrawable(null);
        }
    }
}







