package com.example.chetan.testapp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chetan.testapp.adapter.MainAdapter;
import com.example.chetan.testapp.model.Main_Recycle_list;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends Activity {

    private DatabaseReference mCustomerDatabase;

    //recycleView
    private ArrayList<Main_Recycle_list> mRecycler = new ArrayList<Main_Recycle_list>();
    private RecyclerView recyclerView;
    public MainAdapter mainAdapter;
    String mProfileImageUrl;
    ProgressDialog progressDialog;
    String name = null,phone= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers");

        recyclerView = findViewById(R.id.my_recycler_view);
        mainAdapter = new MainAdapter(mRecycler,getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);
        recyclerData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

    }

    private void recyclerData(){
        mRecycler.clear();
        mCustomerDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                allListData(dataSnapshot);
                mainAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    @SuppressLint("NewApi")
    public void allListData(final DataSnapshot dataSnapshot){
        if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0) {

            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
            if (map.get("f_name") != null) {
                name = map.get("f_name").toString();
            }
            if (map.get("phone") != null) {
                phone = map.get("phone").toString();
                System.out.print("phone" + name);
            }
            if (map.get("profileImageUrl") != null){
                mProfileImageUrl = map.get("profileImageUrl").toString();
                //Glide.with(getApplication()).load(mProfileImageUrl).into(profileImage);
            }
        }

        progressDialog.dismiss();

        mRecycler.add(new Main_Recycle_list(name,phone,mProfileImageUrl));
    }


    
}