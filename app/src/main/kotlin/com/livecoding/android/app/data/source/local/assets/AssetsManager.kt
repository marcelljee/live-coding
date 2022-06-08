package com.livecoding.android.app.data.source.local.assets

import com.livecoding.android.app.ui.MainApplication
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@ApplicationScope
class AssetsManager @Inject constructor(
    private val application: MainApplication
) {
    fun getFileContentFromAssets(fileName: String): String? {
        val fileContent: String = try {
            val inputStream: InputStream = application.applicationContext.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return fileContent
    }
}