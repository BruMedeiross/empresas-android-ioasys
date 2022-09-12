package com.brunadev.empresas_android_ioasys.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.brunadev.empresas_android_ioasys.R
import com.brunadev.empresas_android_ioasys.mydata.model.Company
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CompanyItem(val company: Company):Item<CompanyItem.CompanyViewHolder>(){

    class CompanyViewHolder (view:View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View) = CompanyViewHolder(itemView)

    override fun bind(viewHolder: CompanyViewHolder, position: Int) {

        viewHolder.itemView.findViewById<TextView>(R.id.name_company).text = company.name
        viewHolder.itemView.findViewById<TextView>(R.id.type_company).text = company.type.enterpriseTypeName
        viewHolder.itemView.findViewById<TextView>(R.id.local_company).text = company.country

        val imageView =  viewHolder.itemView.findViewById<ImageView>(R.id.img_company)
        val img = "https://empresas.ioasys.com.br${company.photo}"
        Picasso.get().load(img).into(imageView)
    }

    override fun getLayout() = R.layout.item_company
}



