package noel.example.com.sqliteloginandregistration;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    EditText email,password,name;
    Button login,register;
    ProgressBar progressBar;
    SQLiteDatabase sqLiteDatabaseobject;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    Boolean EditTextEmptyHolder;
    String TempPassword="NOT_FOUND";
    public  static  final String UserEmail="";
    String  EmailHolder,PasswordHolder,NameHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        name=findViewById(R.id.edit_name);
        login=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_register);
        progressBar=findViewById(R.id.loginprogressBar);


        Intent intent = getIntent();

        NameHolder = intent.getStringExtra(RegisterActivity.UserName);

        name.setText(NameHolder);



        sqLiteHelper= new SQLiteHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkedittextstatus();
                signin();

            }
        });
    }

    private void checkedittextstatus() {


          EmailHolder = email.getText().toString();
          PasswordHolder = password.getText().toString();

        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }



    }


    private void signin() {

        if(EditTextEmptyHolder) {

            sqLiteDatabaseobject = sqLiteHelper.getWritableDatabase();

            cursor = sqLiteDatabaseobject.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.TABLE_COLUMN_2_EMAIL + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.TABLE_COLUMN_3_PASSWORD));

                    cursor.close();
                }
            }

            CheckFinalResult();

        }
        else {

            Toast.makeText(LoginActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }


    }

    private void CheckFinalResult() {

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);

           intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);


        }
        else {

            Toast.makeText(LoginActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;
    }
}
