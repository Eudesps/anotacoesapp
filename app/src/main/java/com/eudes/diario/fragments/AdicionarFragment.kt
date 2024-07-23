package com.eudes.diario.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eudes.diario.R
import com.eudes.diario.database.AnotacaoDAO
import com.eudes.diario.databinding.FragmentAdicionarBinding
import com.eudes.diario.model.Anotacao

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdicionarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdicionarFragment : Fragment() {
    private var binding: FragmentAdicionarBinding? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdicionarBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.btnSalvar?.setOnClickListener {
            if (binding!!.editTextTitulo.text.toString().isNotEmpty() && binding!!.editTextDescricao.text.toString().isNotEmpty()){
                salvar()
            }else{
                Toast.makeText(requireContext(), "Um dos campos vazios", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun salvar() {
        val titulo = binding?.editTextTitulo?.text.toString()
        val descricao = binding?.editTextDescricao?.text.toString()

        val anotacao = Anotacao(-1, titulo,descricao)
        val anotacaoDAO = context?.let { AnotacaoDAO(it) }

        if (anotacaoDAO != null) {
            if (anotacaoDAO.salvar(anotacao)){
                    Toast.makeText(requireContext(), "Anotação salva", Toast.LENGTH_SHORT).show()
                binding?.editTextTitulo?.setText("")
                binding?.editTextDescricao?.setText("")
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdicionarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdicionarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}