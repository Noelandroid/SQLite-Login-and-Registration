package noel.example.com.sqliteloginandregistration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static  String DATABASE_NAME="LOGIN";
    public  static final  String TABLE_NAME="UserTable";
    public  static final  String TABLE_COLUMN_1_NAME="name";
    public  static final  String TABLE_COLUMN_2_EMAIL="email";
    public  static final  String TABLE_COLUMN_3_PASSWORD="password";



    public SQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS  "+TABLE_NAME+"  ( "+TABLE_COLUMN_1_NAME
                +" VARCHAR,"+TABLE_COLUMN_2_EMAIL+" VARCHAR,"+TABLE_COLUMN_3_PASSWORD+" VARCHAR)";

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
