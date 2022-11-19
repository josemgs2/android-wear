package com.dossis.curso4semana4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dossis.curso4semana4.R;
import com.dossis.curso4semana4.pojo.Mascota;

import java.util.ArrayList;

public class TablaMascotas {
    Context context;

    public TablaMascotas(Context context) {
        this.context = context;
    }

    private static final String TABLA_MASCOTAS="TMASCOTAS";
    private static final String TABLA_MASCOTAS_COLUMN_ID="Id";
    private static final String TABLA_MASCOTAS_COLUMN_NOMBRE="Nombre";
    private static final String TABLA_MASCOTAS_COLUMN_LIKES="Likes";
    private static final String TABLA_MASCOTAS_COLUMN_IDFOTO="IdFoto";

    public static final String SQL_CREATE_TABLA_MASCOTAS = String.format(
            "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, %s TEXT)",
            TABLA_MASCOTAS,
            TABLA_MASCOTAS_COLUMN_ID,
            TABLA_MASCOTAS_COLUMN_NOMBRE,
            TABLA_MASCOTAS_COLUMN_LIKES,
            TABLA_MASCOTAS_COLUMN_IDFOTO);

    public static final String UPDATE_LIKES_BY_ID="UPDATE "+ TABLA_MASCOTAS +
            " SET "+ TABLA_MASCOTAS_COLUMN_LIKES + " = "+ TABLA_MASCOTAS_COLUMN_LIKES + " + 1 " +
            " WHERE "+TABLA_MASCOTAS_COLUMN_ID+" = ";

    public static final String SELECT_LIKES_BY_ID="SELECT "+ TABLA_MASCOTAS_COLUMN_LIKES+ " FROM "+ TABLA_MASCOTAS +
            " WHERE "+TABLA_MASCOTAS_COLUMN_ID+" = ";

    public static final String SELECT_ORDER_ID ="SELECT * FROM " + TABLA_MASCOTAS +
                                                " ORDER BY " + TABLA_MASCOTAS_COLUMN_ID;
    public static final String SELECT_5_FAVORITOS ="SELECT * FROM "+TABLA_MASCOTAS+
                                                " ORDER BY " +TABLA_MASCOTAS_COLUMN_LIKES+" DESC LIMIT 5";

    public static final String DELETE_TABLA_MASCOTAS="DELETE FROM " + TABLA_MASCOTAS;

    public void crearTabla()
    {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        db.execSQL(TablaMascotas.SQL_CREATE_TABLA_MASCOTAS);
        db.close();

        insertarMascotasFake();

    }
    private void insertarMascotasFake() {

        ArrayList<Mascota> lista=getQueryMascotas(context,SELECT_ORDER_ID);

        if (lista.size()==0) {
            insertMascota(context, 1, "Rufo", R.drawable.perro1);
            insertMascota(context, 2, "Chicho", R.drawable.perro2);
            insertMascota(context, 3, "Luisma", R.drawable.perro3);
            insertMascota(context, 4, "Baraja", R.drawable.perro4);
            insertMascota(context, 5, "Rajoy", R.drawable.perro5);
            insertMascota(context, 6, "Mourinho", R.drawable.perro6);
            insertMascota(context, 7, "Ojopipa", R.drawable.perro7);
            insertMascota(context, 8, "Carahuevo", R.drawable.perro8);
        }
    }
    public int addLike(Context ctx, int id) {
        DatabaseHelper dbHelper = new DatabaseHelper(ctx);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.execSQL(UPDATE_LIKES_BY_ID + id);


        Cursor cursor= db.rawQuery(SELECT_LIKES_BY_ID + id,null);

        cursor.moveToNext();
        int likes=cursor.getInt(0);


        db.close();
        return likes;

    }

    public ArrayList<Mascota> getMascotasOrderedId(Context context)
    {
        return getQueryMascotas(context,SELECT_ORDER_ID);
    }

    public ArrayList<Mascota> getMascotasOrderedLikes(Context context)
    {
        return getQueryMascotas(context,SELECT_5_FAVORITOS);
    }

    private ArrayList<Mascota> getQueryMascotas(Context context, String query){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db= dbHelper.getReadableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        ArrayList<Mascota> mascotas = new ArrayList<>();
        while(cursor.moveToNext()) {
            mascotas.add(new Mascota(cursor.getInt(cursor.getColumnIndex(TABLA_MASCOTAS_COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(TABLA_MASCOTAS_COLUMN_NOMBRE)),
                    cursor.getInt(cursor.getColumnIndex(TABLA_MASCOTAS_COLUMN_LIKES)),
                    cursor.getInt(cursor.getColumnIndex(TABLA_MASCOTAS_COLUMN_IDFOTO))));
        }
        db.close();
        return mascotas;
    }
    public void insertMascota(Context ctx,int id, String nombre, int idFoto)
    {
        DatabaseHelper dbHelper = new DatabaseHelper(ctx);
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TABLA_MASCOTAS_COLUMN_ID, id);
        cv.put(TABLA_MASCOTAS_COLUMN_NOMBRE, nombre);
        cv.put(TABLA_MASCOTAS_COLUMN_LIKES, 0);
        cv.put(TABLA_MASCOTAS_COLUMN_IDFOTO, idFoto);
        db.insert(TABLA_MASCOTAS, null, cv);
        db.close();
    }

    public void deleteTablaMascotas(Context ctx)
    {
        DatabaseHelper dbHelper = new DatabaseHelper(ctx);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.execSQL(DELETE_TABLA_MASCOTAS);
        db.close();

    }

}
