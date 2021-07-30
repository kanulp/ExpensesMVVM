package com.kanulp.expensemvvm.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("bindDrawableRight")
fun bindDrawableRight(view: TextInputEditText, imageUrl: String?) {
    if(imageUrl!=null) {
        GlobalScope.launch {
            val connection: HttpURLConnection = URL(imageUrl).openConnection() as HttpURLConnection
            connection.connect();
            val input: InputStream = connection.inputStream
            var bmp = BitmapFactory.decodeStream(input)
            var img = BitmapDrawable(view.resources, Bitmap.createScaledBitmap(bmp, 200, 100, true))
            withContext(Dispatchers.Main) {
                view.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
            }
        }
    }
}


@BindingAdapter("bindTimeStampDate")
fun bindTimeStampDate(textView: TextView, date: String?) {
    textView.text = convertToDateString(date)
}

fun convertToDateString(date: String?): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter: SimpleDateFormat = SimpleDateFormat("d MMM")
    return formatter.format(parser.parse(date))
}