package bv.dev.dynamicsidemenu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bv.dev.dynamicsidemenu.R
import bv.dev.dynamicsidemenu.models.MainViewModel

class TextFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tvMain : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.text_fragment, container, false)
        tvMain = root.findViewById(R.id.tvMain)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                tvMain.text = it[viewModel.index].param
            }
        })
    }

}
