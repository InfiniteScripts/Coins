package com.infinitescripts.coins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ResultsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Long CoinYear;


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public void saveChanges(View view) {

        TextView coinId = (TextView) findViewById(R.id.coinID);
        String key = coinId.getText().toString();

        EditText editType = (EditText) findViewById(R.id.editType);
        EditText editQR = (EditText) findViewById(R.id.editQR);
        EditText editGrade = (EditText) findViewById(R.id.editGrade);
        EditText editHolder = (EditText) findViewById(R.id.editHolder);
        EditText editYear = (EditText) findViewById(R.id.editYear);
        EditText editSalePrice = (EditText) findViewById(R.id.editSalePrice);
        EditText editVariety = (EditText) findViewById(R.id.editVariety);
        EditText editPurchaseSource = (EditText) findViewById(R.id.editPurchaseSource);
        EditText editPurchasePrice = (EditText) findViewById(R.id.editPurchasePrice);
        EditText editPurchaseDate = (EditText) findViewById(R.id.editPurchaseDate);
        EditText editPrivateComments = (EditText) findViewById(R.id.editPrivateComments);
        EditText editPublicComments = (EditText) findViewById(R.id.editPublicComments);
        EditText editMementoID = (EditText) findViewById(R.id.editMementoID);

        DatabaseReference coinRef = reference.child("Coin").child(key);
        HashMap<String, Object> queryRefUpdates = new HashMap<String, Object>();

        queryRefUpdates.put("type", editType.getText().toString());
        queryRefUpdates.put("qr", editQR.getText().toString());
        queryRefUpdates.put("grade", editGrade.getText().toString());
        queryRefUpdates.put("holder", editHolder.getText().toString());

        queryRefUpdates.put("variety", editVariety.getText().toString());
        queryRefUpdates.put("purchaseSource", editPurchaseSource.getText().toString());

        queryRefUpdates.put("purchaseDate", editPurchaseDate.getText().toString());
        queryRefUpdates.put("privateComments", editPrivateComments.getText().toString());
        queryRefUpdates.put("publicComments", editPublicComments.getText().toString());

        String year =  editYear.getText().toString();
        queryRefUpdates.put("year", year);

        String salePrice = editSalePrice.getText().toString();
        queryRefUpdates.put("salePrice", salePrice);
        String mementoID = editMementoID.getText().toString();
        queryRefUpdates.put("mementoID", mementoID);
        String purchasePrice = editPurchasePrice.getText().toString();
        queryRefUpdates.put("purchasePrice", purchasePrice);
        coinRef.updateChildren(queryRefUpdates);

        Toast.makeText(this, "Coin data Updated Successfully.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String term = bundle.getString("term");
            setTitle("Results : " + term);

            Query queryRef = reference.child("Coin").orderByChild("qr").equalTo(term);

            queryRef.addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                        String coinKey = itemSnapshot.getKey();
                        TextView coinId = (TextView) findViewById(R.id.coinID);
                        coinId.setText(coinKey);

                        Coin coin = itemSnapshot.getValue(Coin.class);

                        EditText editType = (EditText) findViewById(R.id.editType);
                        editType.setText(coin.getType());

                        EditText editQR = (EditText) findViewById(R.id.editQR);
                        editQR.setText(coin.getQR());

                        EditText editGrade = (EditText) findViewById(R.id.editGrade);
                        editGrade.setText(coin.getGrade());

                        EditText editHolder = (EditText) findViewById(R.id.editHolder);
                        editHolder.setText(coin.getHolder());

                        EditText editYear = (EditText) findViewById(R.id.editYear);
                        editYear.setText(String.valueOf(coin.getYear()));

                        EditText editSalePrice = (EditText) findViewById(R.id.editSalePrice);
                        editSalePrice.setText(String.valueOf(coin.getSalePrice()));

                        EditText editVariety = (EditText) findViewById(R.id.editVariety);
                        editVariety.setText(coin.getVariety());

                        EditText editPurchaseSource = (EditText) findViewById(R.id.editPurchaseSource);
                        editPurchaseSource.setText(coin.getPurchaseSource());

                        EditText editPurchasePrice = (EditText) findViewById(R.id.editPurchasePrice);
                        editPurchasePrice.setText(String.valueOf(coin.getPurchasePrice()));

                        EditText editPurchaseDate = (EditText) findViewById(R.id.editPurchaseDate);
                        editPurchaseDate.setText(coin.getPurchaseDate());

                        EditText editPrivateComments = (EditText) findViewById(R.id.editPrivateComments);
                        editPrivateComments.setText(coin.getPrivateComments());

                        EditText editPublicComments = (EditText) findViewById(R.id.editPublicComments);
                        editPublicComments.setText(coin.getPublicComments());

                        EditText editMementoID = (EditText) findViewById(R.id.editMementoID);
                        editMementoID.setText(String.valueOf(coin.getMementoID()));

                        EditText editSoldPrice = (EditText) findViewById(R.id.editSoldPrice);
                        editSoldPrice.setText(String.valueOf(coin.getSoldPrice()));


                    }
                }

                public void onCancelled(DatabaseError databaseError) {

                }


            });
        }

    }
}
