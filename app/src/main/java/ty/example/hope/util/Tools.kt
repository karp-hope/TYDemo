package ty.example.hope.util

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

/**
 * @description: Tools
 **
 * @author: hope
 **
 * @create: 2019-03-19 18:11
 */
class Tools {

    companion object {

        @JvmStatic
        fun readInputStream(inputStream: InputStream): ByteArray{

            var bytes = ByteArray(1024)
            var bis = BufferedInputStream(inputStream)

            var baos = ByteArrayOutputStream()
            var bos = BufferedOutputStream(baos)
            var length = bis.read(bytes)
            try {
                while (length > 0){
                    bos.write(bytes, 0, length)
                    length = bis.read(bytes)
                }
                bos.flush()
                return baos.toByteArray()
            }finally {
                bis.close()
                bos.close()
                baos.close()
            }
            return ByteArray(0)
        }
    }
}