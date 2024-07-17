package com.eudes.diario.database

import com.eudes.diario.model.Anotacao

interface IAnotacaoDAO {
    fun salvar(anotacao: Anotacao): Boolean
    fun listar(): List<Anotacao>
}