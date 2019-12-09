package com.arun.journal.util

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.Priority
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder


object FrescoUtils {
    /**
     * Setup Fresco for dynamic image loading
     * @param url the url as [String]
     * @param view the [SimpleDraweeView] in which image from [url] will be shown
     */
    fun setFresco(url: String, view: SimpleDraweeView) {
        val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
            .setRequestPriority(Priority.HIGH)
            .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
            .build()
        Fresco.getImagePipeline().prefetchToBitmapCache(imageRequest, view.context)
        view.controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(imageRequest)
            .setOldController(view.controller)
            .build()
    }
}
