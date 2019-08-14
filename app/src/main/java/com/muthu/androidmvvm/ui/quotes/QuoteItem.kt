package com.muthu.androidmvvm.ui.quotes

import com.muthu.androidmvvm.R
import com.muthu.androidmvvm.data.db.entities.Quote
import com.muthu.androidmvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
   private val myQuote: Quote
) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.quote = myQuote
    }
}