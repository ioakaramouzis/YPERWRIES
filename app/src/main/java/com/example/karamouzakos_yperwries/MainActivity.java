package com.example.karamouzakos_yperwries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;






public class MainActivity extends AppCompatActivity {




//      ΜΕΤΑΒΛΗΤΕΣ
    DatabaseHelpers mDatabaseHelper;
    public  static  final String TAG = "MainActivity";
    private Button btnAdd,btnViewData;
    private EditText editText;
    Calendar mCurrentDate;
    int day,month,year;
    private  EditText editText1;
    TimePickerDialog timerpickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






          // ΜΕΤΑΒΛΗΤΕΣ ΑΠΟ I.D
        editText = (EditText) findViewById(R.id.editText);
        editText1 = (EditText) findViewById(R.id.editText2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        mDatabaseHelper = new DatabaseHelpers(this);
           //      ΜΕΤΑΒΛΗΤΕΣ ΑΠΟ I.D


     //          ΠΡΩΤΟ ΚΟΜΜΑΤΙ ΓΙΑ ΔHΛΩΣΗ
        mCurrentDate = Calendar.getInstance();         //βάζω στο mCurrentDate να πάρει την ημερομηνία,ωρα κτλπ
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH); // ημερα
        month = mCurrentDate.get(Calendar.MONTH);  // μηνας
        year = mCurrentDate.get(Calendar.YEAR);    // χρονος
        final int HOUR = mCurrentDate.get(Calendar.HOUR);  //ωρα
        final int MINUTE = mCurrentDate.get(Calendar.MINUTE);  // λεπτα
        SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // format για να πως θα φένεται η ώρα
        final String time = format.format(mCurrentDate.getTime()); //βαζω στην μεταβλητη time την τρεχουσα ωρα
        editText1.setText(time);   // editText1 ειναι το editext της ωρας την περναω εκει για να εμφανιζει την τρεχουσα ωρα
        month = month +1; // ο μηνας ειναι ο μηνας + 1
        editText.setText(day +"/"+ month+"/" +year);  // βαζω στο edittext ημερομηνια την ημερομηνια


        //          ΠΡΩΤΟ ΚΟΜΜΑΤΙ ΓΙΑ ΔHΛΩΣΗ



       // μεθοδος για οταν θα πατας πανω στην ωρα να βγαζει dialog
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerpickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(minute<=9)
                        {
                          editText1.setText(hourOfDay+ ":0" + minute);
                        }
                        else{
                            editText1.setText(hourOfDay+ ":" + minute);
                        }

                    }
                },HOUR,MINUTE,true);
                timerpickerDialog.show();
            }
        });
        // μεθοδος για οταν θα πατας πανω στην ωρα να βγαζει dialog




          //μεθοδος για dialog στην ημερομηνια
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dataPickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +1 ;
                        editText.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,day);
                dataPickerDialog.show();
            }
        });
            //μεθοδος για dialog στην ημερομηνια

          //μεθοδος για το κουμπη προσιηκη υπερωριας
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString() + "  " + editText1.getText().toString();
                if (editText.length() != 0 || editText1.length() !=0) {
                    Adddata(newEntry);

                } else {
                    toastMessage("Πρέπει να βάλεις υπερωρία");
                }
            }
        });


        //μεθοδος για το κουμπη προσιηκη υπερωριας



btnViewData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent intent= new Intent(MainActivity.this, ListDataActivity.class);
        startActivity(intent);


    }
});



    }

    public  void Adddata(String newEntry){


        boolean insertData=mDatabaseHelper.addData(newEntry);
        if(insertData){
            toastMessage("Μπήκε η υπερωρία");
        }
        else {
            toastMessage("Δεν μπήκε η υπερωρία");
        }
    }


    public  void  toastMessage(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }



}
