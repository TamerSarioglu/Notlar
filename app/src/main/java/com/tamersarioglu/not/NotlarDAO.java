package com.tamersarioglu.not;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDAO {

    public ArrayList<Notlar> tumNotlar(Veritabani vt){
        ArrayList<Notlar> notlarArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM notlar",null);

        while (c.moveToNext()){
            Notlar n = new Notlar(c.getInt(c.getColumnIndex("not_id"))
                    ,c.getString(c.getColumnIndex("ders_adi"))
                    ,c.getInt(c.getColumnIndex("not_1")),
                    c.getInt(c.getColumnIndex("not_2")));

            notlarArrayList.add(n);
        }

        return notlarArrayList;
    }
    public void notSil(Veritabani vt,int not_id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("notlar", "not_id=?",new String[] {String.valueOf(not_id)} );
        db.close();

    }


    public void notEkle(Veritabani vt,String ders_adi,int not1,int not2){

        SQLiteDatabase dbx=vt.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("ders_adi",ders_adi );
        values.put("not_1",not1 );
        values.put("not_2",not2 );

        dbx.insertOrThrow("notlar", null, values);
        dbx.close();

    }

    public void notGuncelle(Veritabani vt,int not_id,String ders_adi,int not1,int not2){

        SQLiteDatabase dbx=vt.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("ders_adi",ders_adi );
        values.put("not_1",not1 );
        values.put("not_2",not2 );

        dbx.update("notlar", values,"not_id=?",new String[] {String.valueOf(not_id)});
        dbx.close();

    }

}
