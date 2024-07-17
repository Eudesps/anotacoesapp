package com.eudes.diario.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.eudes.diario.fragments.AdicionarFragment
import com.eudes.diario.model.Anotacao

class AnotacaoDAO(context: Context): IAnotacaoDAO {
    val escrita = DatabaseHelper(context).writableDatabase
    val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(anotacao: Anotacao): Boolean {
        try {
            val conteudo = ContentValues()
            conteudo.put("${DatabaseHelper.COLUNA_TITULO}", "${anotacao.titulo}")
            conteudo.put("${DatabaseHelper.COLUNA_DESCICAO}", "${anotacao.descricao}")

            escrita.insert(DatabaseHelper.NOME_TABELA_ANOTACAO,null, conteudo)

            Log.i("infodb", "Anotação salva com sucesso")
        }catch (e: Exception){
            Log.i("infodb", "Erro ao salvar: $e")
            return false
        }
        return true
    }

    override fun listar(): List<Anotacao> {
        val listaAnotacoes = mutableListOf<Anotacao>()

        val sql =
            "SELECT ${DatabaseHelper.COLUNA_ID_ANOTACAO}, ${DatabaseHelper.COLUNA_TITULO}, ${DatabaseHelper.COLUNA_DESCICAO} " +
                    "FROM ${DatabaseHelper.NOME_TABELA_ANOTACAO}"

        val cursor = leitura.rawQuery(sql, null)

        val id_anotacao = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_ANOTACAO)
        val titulo = cursor.getColumnIndex(DatabaseHelper.COLUNA_TITULO)
        val descricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCICAO)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(id_anotacao)
            val titulo = cursor.getString(titulo)
            val descricao = cursor.getString(descricao)

            listaAnotacoes.add(Anotacao(id, titulo, descricao))
        }
        return listaAnotacoes
    }
}