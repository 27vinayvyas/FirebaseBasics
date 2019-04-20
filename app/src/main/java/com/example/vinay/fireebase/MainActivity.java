  package com.example.vinay.fireebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

  public class MainActivity extends AppCompatActivity {

      private RecyclerView mRecyclerView;
      private RecyclerView.Adapter mAdapter;
      private RecyclerView.LayoutManager mLayoutManager;
      ArrayList<Item>itemList;


      EditText editTextName;
    EditText editTextComments;
    Button button;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName=(EditText)findViewById(R.id.edit_name);
        editTextComments=(EditText)findViewById(R.id.edit_comments);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        databaseReference=FirebaseDatabase.getInstance().getReference("USER");

        itemList=new ArrayList<>();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ItemAdapter(itemList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

      @Override
      protected void onStart() {
          super.onStart();

          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                  itemList.clear();
                 //   int count=0;
                  for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                      Item item=itemSnapshot.getValue(Item.class);
                      itemList.add(item);
                    //  count++;
                     // Toast.makeText(MainActivity.this,count,Toast.LENGTH_LONG).show();
                    //  if(count==5){
                      //    databaseReference.child(itemList.get(0).id).removeValue();
                  //    }
                  }




              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
    }

      private void add(){
        String name=editTextName.getText().toString();
        String comment=editTextComments.getText().toString();

          Calendar calendar=Calendar.getInstance();
          String currentDate= java.text.DateFormat.getDateInstance().format(calendar.getTime());

        //Date d = new Date();
       // CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        if (TextUtils.isEmpty(name)){
            Toast.makeText(MainActivity.this,"Enter name",Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(comment)){
            Toast.makeText(MainActivity.this,"Enter comment",Toast.LENGTH_LONG).show();

        }else{
            String idd=databaseReference.push().getKey();

            Item item=new Item(idd,name,comment,currentDate);

            databaseReference.child(idd).setValue(item);

            Toast.makeText(MainActivity.this,"Added",Toast.LENGTH_LONG).show();
        }
    }

}
