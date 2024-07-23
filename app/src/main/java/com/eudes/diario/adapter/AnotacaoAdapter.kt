package com.eudes.diario.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.eudes.diario.databinding.ItemListagemBinding
import com.eudes.diario.fragments.ListarFragment
import com.eudes.diario.model.Anotacao

class AnotacaoAdapter: Adapter<AnotacaoAdapter.anotacaoViewHolder>() {
    private var listaAnotacoes : List<Anotacao> = emptyList()

    fun adicionarLista(lista: List<Anotacao>) {
        this.listaAnotacoes = lista
        notifyDataSetChanged()
    }

    inner class anotacaoViewHolder(itemView: ItemListagemBinding)
        : RecyclerView.ViewHolder(itemView.root){

            val binding : ItemListagemBinding = itemView

        //essa função
            fun bind(anotacao: Anotacao){
                binding.textTitulo.text = anotacao.titulo
                binding.textDescicao.text = anotacao.descricao
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): anotacaoViewHolder {
        val itemListagem = ItemListagemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return anotacaoViewHolder(itemListagem)
    }

    override fun getItemCount(): Int {
        return listaAnotacoes.size
    }

    override fun onBindViewHolder(holder: anotacaoViewHolder, position: Int) {
        val tarefa =listaAnotacoes[position]
        holder.bind(tarefa)
    }
}