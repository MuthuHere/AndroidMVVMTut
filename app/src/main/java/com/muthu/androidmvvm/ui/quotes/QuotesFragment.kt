package com.muthu.androidmvvm.ui.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.muthu.androidmvvm.R
import com.muthu.androidmvvm.util.Coroutines
import com.muthu.androidmvvm.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {


    override val kodein by kodein()

    private val factory  : QuotesViewModelFactory by instance()


    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        bindUI()
    }


    private fun bindUI() = Coroutines.main {

        viewModel.quotes.await().observe(this, Observer {

            context?.toast(it.size.toString())
        })
    }
}
