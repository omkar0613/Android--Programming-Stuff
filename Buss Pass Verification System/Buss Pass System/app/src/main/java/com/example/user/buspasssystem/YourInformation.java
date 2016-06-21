package com.example.user.buspasssystem;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.AndroidFont;
import com.onbarcode.barcode.android.Code39;
import com.onbarcode.barcode.android.GeneratedBarcodeInfo;
import com.onbarcode.barcode.android.IBarcode;


public class YourInformation extends ActionBarActivity {
    public static final String TAG="StatusActivity";
    Canvas canvas;
    String nameToBePrinted,custNumberToBePrinted;
    TextView name,customerNumber;

    public void setNameToBePrinted(String nameToBePrinted){
this.nameToBePrinted=nameToBePrinted;
    }
    public void setCustNumberToBePrinted(String custNumberToBePrinted){
this.custNumberToBePrinted=custNumberToBePrinted;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"I am in YourInfo onCreate");
        setContentView(R.layout.activity_your_information);
        name=(TextView)findViewById(R.id.id_namereceivedfromDB);
        customerNumber=(TextView)findViewById(R.id.id_custNumber);
        Log.d(TAG, name.getText().toString());
        Log.d(TAG,customerNumber.getText().toString());

        name.setText(BusDatabase.pname);
        customerNumber.setText(BusDatabase.pBarcode);
    }
    public void button_Schedule(View v){
        Log.d(TAG,"schedule Pressed");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.viainfo.net/busservice/schedules.aspx"));
        startActivity(intent);
    }
}
