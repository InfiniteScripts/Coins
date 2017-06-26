package com.infinitescripts.coins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddCoinActivity extends AppCompatActivity {

    private Button buttonScan;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coin);
        setTitle("Add a new Coin");
        buttonScan = (Button) findViewById(R.id.buttonScan);
        //attaching onclick listener
        //intializing scan object
        qrScan = new IntentIntegrator(this);

    }
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public void addCoin(View view) {

        EditText editType = (EditText) findViewById(R.id.editType);
        String Type = editType.getText().toString();
        if(TextUtils.isEmpty(Type)) {
            editType.setError("Type cannot be empty");
            return;
        }

        EditText editQR = (EditText) findViewById(R.id.editQR);
        String QR = editQR.getText().toString();
        if(TextUtils.isEmpty(QR)) {
            editQR.setError("QR cannot be empty");
            return;
        }

        EditText editGrade = (EditText) findViewById(R.id.editGrade);
        String Grade = editGrade.getText().toString();
        EditText editHolder = (EditText) findViewById(R.id.editHolder);
        String Holder = editHolder.getText().toString();
        EditText editYear = (EditText) findViewById(R.id.editYear);
        String year =  editYear.getText().toString();
        EditText editSalePrice = (EditText) findViewById(R.id.editSalePrice);
        String salePrice = editSalePrice.getText().toString();
        EditText editVariety = (EditText) findViewById(R.id.editVariety);
        String Variety = editVariety.getText().toString();
        EditText editPurchaseSource = (EditText) findViewById(R.id.editPurchaseSource);
        String PurchaseSource = editPurchaseSource.getText().toString();
        EditText editPurchasePrice = (EditText) findViewById(R.id.editPurchasePrice);
        String purchasePrice = editPurchasePrice.getText().toString();
        EditText editPurchaseDate = (EditText) findViewById(R.id.editPurchaseDate);
        String Purchasedate = editPurchaseDate.getText().toString();
        EditText editPrivateComments = (EditText) findViewById(R.id.editPrivateComments);
        String PrivateComments = editPrivateComments.getText().toString();
        EditText editPublicComments = (EditText) findViewById(R.id.editPublicComments);
        String PublicComments = editPublicComments.getText().toString();
        EditText editMementoID = (EditText) findViewById(R.id.editMementoID);
        String mementoID = editMementoID.getText().toString();


        HashMap<String, Coin> queryRefUpdates = new HashMap<String, Coin>();

        Coin addCoin = new Coin();
        String Uid = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Uid = user.getUid();


        addCoin.setUid(Uid);
        addCoin.setYear(year);
        addCoin.setSalePrice(salePrice);
        addCoin.setMementoID(mementoID);
        addCoin.setPurchasePrice(purchasePrice);
        addCoin.setType(Type);
        addCoin.setQR(QR);
        addCoin.setGrade(Grade);
        addCoin.setHolder(Holder);
        addCoin.setPrivateComments(PrivateComments);
        addCoin.setPublicComments(PublicComments);
        addCoin.setVariety(Variety);
        addCoin.setPurchaseSource(PurchaseSource);
        addCoin.setPurchaseDate(Purchasedate);

        DatabaseReference coinRef = reference.child("Coin");
        DatabaseReference newCoinref = coinRef.push();
        newCoinref.setValue(addCoin);

        //coinRef.push(coinRef);

        Toast.makeText(this, "Coin Added Successfully.", Toast.LENGTH_LONG).show();
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    EditText editText = (EditText) findViewById(R.id.editText);
                    editText.setText(result.getContents());

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    EditText editText = (EditText) findViewById(R.id.editQR);
                    editText.setText(result.getContents());
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClickScan(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
}