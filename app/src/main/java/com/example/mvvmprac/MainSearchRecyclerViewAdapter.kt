package com.example.mvvmprac

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.koin.dsl.module.applicationContext

class MainSearchRecyclerViewAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    data class ImageItem(var imageUrl:String, var documentUrl:String)

    class ImageHolder(parent:ViewGroup):RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_image, parent, false)){
        private val imageView : ImageView = itemView.findViewById(R.id.item_main_image_view)
        fun onBind(item:ImageItem){
            itemView.run{
                Picasso.with(context).load(item.imageUrl).placeholder(R.drawable.ic_image_black_24dp).into(imageView)
                imageView.setOnClickListener {
                    ContextCompat.startActivity(context,Intent(Intent.ACTION_VIEW, Uri.parse(item.documentUrl)),null)
                }
            }
        }
    }

    private val imageItemList = ArrayList<ImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageHolder)?.onBind(imageItemList[position])
    }

    override fun getItemCount() = imageItemList.size

    fun addImageItem(imageUrl:String, documentUrl: String){
        imageItemList.add(ImageItem(imageUrl,documentUrl))
    }
}