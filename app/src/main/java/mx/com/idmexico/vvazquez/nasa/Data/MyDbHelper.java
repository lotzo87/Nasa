package mx.com.idmexico.vvazquez.nasa.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by victor on 8/15/16.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "DbApod";
    private final static int DATABASE_VERSION = 1;

    private String appName;
    private String devName;
    private String description;
    private int isInstalled;

    public static final String TABLE_NASA_NAME ="TBL_APOD_FAV";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_SOL = "SOL";
    public static final String COLUMN_RSC = "IMG_RSC";
    public static final String COLUMN_DESC = "DESCRIP";
    public static final String COLUMN_DATE = "DATE";



    private static final String CREATE_TABLE_NASA = String.format("create table %s " +
                    "(%s integer primary key autoincrement," +
                    "%s integer not null," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text not null)",
            TABLE_NASA_NAME, COLUMN_ID, COLUMN_SOL, COLUMN_RSC, COLUMN_DESC,COLUMN_DATE);


    private static final String DELETE_TABLE_NASA =
            String.format("DROP TABLE %s ",TABLE_NASA_NAME);


    public MyDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NASA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE_NASA);
        onCreate(db);
    }
}
