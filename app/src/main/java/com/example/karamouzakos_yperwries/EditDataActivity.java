package com.example.karamouzakos_yperwries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {


    public  static  final String TAG = "EditDataActivity";
    private Button btnSave,btnDelete;
    private EditText editable_item;

    DatabaseHelpers mDatabaseHelper;

    private  String selectedName;
    private  int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnSave=(Button)findViewById(R.id.btnSave);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        editable_item=(EditText)findViewById(R.id.editable_item);
        mDatabaseHelper= new DatabaseHelpers(this);

        Intent recieveIntent= getIntent();

        selectedID=recieveIntent.getIntExtra("id",-1);

        selectedName=recieveIntent.getStringExtra("name");

        editable_item.setText(selectedName);

btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        String item = editable_item.getText().toString();
if (!item.equals("")){
    mDatabaseHelper.updateName(item,selectedID,selectedName);
}
else{
    toastMessage("Βάλε Όνομα");
}
    }
});

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



mDatabaseHelper.deleteName(selectedID,selectedName);

editable_item.setText("");
toastMessage("Removed from database");
    }
});



    }

    public  void  toastMessage(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
