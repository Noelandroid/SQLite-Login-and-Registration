package noel.example.com.sqliteloginandregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    String EmailHolder;
    String NameHolder;
    TextView email,name;
    Button Logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = (TextView)findViewById(R.id.text_email);
        Logout = (Button)findViewById(R.id.btn_log);

        Intent intent = getIntent();

        EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);
        email.setText(EmailHolder);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Toast.makeText(ProfileActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });
    }
}
