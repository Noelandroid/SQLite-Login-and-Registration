package noel.example.com.sqliteloginandregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText email,password,name;
    Button register;
    ProgressBar progressBar;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseobject;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    public  static  final String UserName="";
    public  static  final String UserEmail="";
    String F_Result = "Not_Found";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.edit_regname);

        email=findViewById(R.id.edit_regemail);

        password=findViewById(R.id.edit_regpassword);

        register=findViewById(R.id.btn_reg);

        progressBar=findViewById(R.id.regprogressBar);

        sqLiteHelper = new SQLiteHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                CheckingEmailAlreadyExistsOrNot();



                name.getText().clear();

                email.getText().clear();

                password.getText().clear();




            }
        });


    }


    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseobject = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseobject.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.TABLE_COLUMN_1_NAME + " VARCHAR, " + SQLiteHelper.TABLE_COLUMN_2_EMAIL + " VARCHAR, " + SQLiteHelper.TABLE_COLUMN_3_PASSWORD + " VARCHAR);");

    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHolder == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"');";


            sqLiteDatabaseobject.execSQL(SQLiteDataBaseQueryHolder);

            sqLiteDatabaseobject.close();

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

            intent.putExtra(UserName, NameHolder);
            startActivity(intent);

            Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(RegisterActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }


    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = name.getText().toString() ;
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void CheckingEmailAlreadyExistsOrNot(){


        sqLiteDatabaseobject = sqLiteHelper.getWritableDatabase();


        cursor = sqLiteDatabaseobject.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.TABLE_COLUMN_2_EMAIL + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                F_Result = "Email Found";


                cursor.close();
            }
        }

        CheckFinalResult();

    }

    public void CheckFinalResult(){

        if(F_Result.equalsIgnoreCase("Email Found"))
        {

            Toast.makeText(RegisterActivity.this,"Email Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }


}
