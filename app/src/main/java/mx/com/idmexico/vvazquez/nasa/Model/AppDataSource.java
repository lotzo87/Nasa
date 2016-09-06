package mx.com.idmexico.vvazquez.nasa.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import mx.com.idmexico.vvazquez.nasa.Data.MyDbHelper;

/**
 * Created by victor on 8/16/16.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    public AppDataSource(Context context) {
        MyDbHelper helper = new MyDbHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveAsFavorite(Photo apodPhoto)
    {
        //int r;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.COLUMN_SOL, apodPhoto.getSol());
        contentValues.put(MyDbHelper.COLUMN_RSC,apodPhoto.getImgSrc());
        contentValues.put(MyDbHelper.COLUMN_DESC, apodPhoto.getCamera().getFullName());
        contentValues.put(MyDbHelper.COLUMN_DATE,apodPhoto.getEarthDate());
        Log.d("TAG. SOL:",apodPhoto.getSol().toString() );
        Log.d("TAG. SRC:",apodPhoto.getImgSrc() );
        Log.d("TAG. CAMERA:",apodPhoto.getCamera().getFullName());
        Log.d("TAG. SRC:",apodPhoto.getEarthDate());

        //r = (int)
        db.insert(MyDbHelper.TABLE_NASA_NAME,null,contentValues);
        Log.d("REGS INSERTADOS:", "OK");
        //return r;
    }

    public void deleteFavorite(int id)
    {
        db.delete(MyDbHelper.TABLE_NASA_NAME,MyDbHelper.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)});
    }

    /*public List<APOD2> getApp(int appId)
    {
        List<APOD2> modelAppList = new ArrayList<>();
        Cursor cursor =db.query(MyDbHelper.TABLE_NASA_NAME, null,MyDbHelper.COLUMN_ID+ "=?",
                new String[] {String.valueOf(appId)},null,null,null,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_ID));
            String appname=cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_APP_NAME));
            String developer = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DEV_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DESCRIP));
            int resource = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_RESOURCE));
            int installed = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_INSTALLED));
            modelListItem modelApp = new modelListItem(id,resource,appname,developer,description,installed);
            modelAppList.add(modelApp);
        }
        return modelAppList;
    }*/

    public List<Photo> getAllApod()
    {
        List<Photo> modelAPODList = new ArrayList<>();
        Cursor cursor =db.query(MyDbHelper.TABLE_NASA_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_ID));
            int apodSol =cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_SOL));
            String apodSrc = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_RSC));
            String apodDesc = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DESC));
            String apodDate = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DATE));
            Photo photo = new Photo();
            photo.setId(id);
            photo.setSol(apodSol);
            photo.setImgSrc(apodSrc);
            Camera camera = new Camera();
            camera.setFullName(apodDesc);
            photo.setCamera(camera);
            photo.setEarthDate(apodDate);
            modelAPODList.add(photo);
        }
        cursor.close();
        return modelAPODList;
    }
}
