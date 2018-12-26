package ty.example.hope

import org.junit.Assert
import org.junit.Test
import ty.example.hope.syntax.SyntaxKotlin

/**
 * @description: SyntaxTest
 **
 * @author: hope
 **
 * @create: 2018-12-25 10:32
 */
class SyntaxTest {

    @Test
    fun testConst(){
        var temp1 = SyntaxKotlin()

        Assert.assertEquals(0, SyntaxKotlin.Companion.index)

        SyntaxKotlin.index = 10

        val value = SyntaxKotlin.Companion
        Assert.assertEquals(10, value.index)
    }
}