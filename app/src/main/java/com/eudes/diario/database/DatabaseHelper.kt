package com.eudes.diario.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, NOME_TABELA_ANOTACAO,null,1) {
    companion object {
        const val NOME_TABELA_ANOTACAO = "anotacoes"
        const val COLUNA_ID_ANOTACAO = "id_anotacao"
        const val COLUNA_TITULO = "titulo"
        const val COLUNA_DESCICAO = "descricao"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $NOME_TABELA_ANOTACAO(" +
                "$COLUNA_ID_ANOTACAO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$COLUNA_TITULO VARCHAR(20)," +
                "$COLUNA_DESCICAO VARCHAR(100));"
        try {
            db?.execSQL(sql)
            Log.i("info.bd", "Banco de dados criado com sucesso!")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info.bd", "Erro ao criar o banco de dados: $e")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}