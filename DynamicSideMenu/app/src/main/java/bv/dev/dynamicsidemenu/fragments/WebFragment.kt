package bv.dev.dynamicsidemenu.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bv.dev.dynamicsidemenu.R
import bv.dev.dynamicsidemenu.models.MainViewModel

class WebFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var webViewMain: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_web, container, false)
        webViewMain = root.findViewById(R.id.webViewMain)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Toast.makeText(activity, getString(R.string.text_loading), Toast.LENGTH_LONG).show()

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                webViewMain.loadUrl(it[viewModel.index].param)
                webViewMain.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url)
                        return true
                    }
                }
            }
        })
    }

}
