package com.example.appn1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    public static void inserir(Context context, Cadastro cadastro){
        ContentValues values = new ContentValues();
        values.put( "nome" , cadastro.getNome() );
        values.put( "editora" , cadastro.getEditora() );
        values.put ("descricao", cadastro.getDescricao() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("cadastros", null , values);
    }

    public static void editar(Context context, Cadastro cadastro){
        ContentValues values = new ContentValues();
        values.put( "nome" , cadastro.getNome() );
        values.put( "editora" , cadastro.getEditora() );
        values.put( "descricao", cadastro.getDescricao() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("cadastros", values, " id = " + cadastro.getId(), null);
    }

    public static void excluir(Context context, int idCadastro){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("cadastros", " id = " + idCadastro, null);
    }

    public static List<Cadastro> getCadastros(Context context){
        List<Cadastro> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros ORDER BY nome ", null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Cadastro cadastro = new Cadastro();
                cadastro.setId(  cursor.getInt( 0 ) );
                cadastro.setNome( cursor.getString( 1 ));
                cadastro.setEditora( cursor.getString( 2 ));
                cadastro.setDescricao( cursor.getString(3));
                lista.add( cadastro );

            }while ( cursor.moveToNext() );
        }
        return lista;
    }


    public static Cadastro getCadastroById(Context context, int idCadastro){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros WHERE id =  " + idCadastro, null);

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Cadastro cadastro = new Cadastro();
            cadastro.setId(  cursor.getInt( 0 ) );
            cadastro.setNome( cursor.getString( 1 ));
            cadastro.setEditora( cursor.getString( 2 ));
            cadastro.setDescricao( cursor.getString( 3));

            return cadastro;

        }else {
            return null;
        }
    }
}
