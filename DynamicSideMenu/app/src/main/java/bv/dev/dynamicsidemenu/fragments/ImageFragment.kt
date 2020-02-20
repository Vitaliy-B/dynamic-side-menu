package bv.dev.dynamicsidemenu.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bv.dev.dynamicsidemenu.R
import bv.dev.dynamicsidemenu.models.MainViewModel
import com.bumptech.glide.Glide

class ImageFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var ivMain : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_image, container, false)
        ivMain = root.findViewById(R.id.ivMain)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this)
                    .load(it[viewModel.index].param)
                    .centerInside()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(ivMain)
            }
        })
    }
}
