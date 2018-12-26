package ty.example.hope.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import ty.example.hope.R
import ty.example.hope.util.ConstConfigure
import java.lang.reflect.Type
import java.nio.channels.OverlappingFileLockException

/**
 * @description: FloatingActionBtnPlus
 **
 * @author: hope
 **
 * @create: 2018-12-24 14:45
 */
class FloatingActionBtnPlus: ViewGroup{
    var mPosition = 0
    var mAnimation = 0
    var mBackgroundColor = 0
    var mFabColor: ColorStateList? = null
    var mIcon: Drawable? = null
    var mAnimationDuration = 0

    var mFirstEnter = true

    //FloatingActionButton需要添加依赖库，低版本中不存在
    var mSwitchFab: FloatingActionButton? = null

    var mStatus = false

    val mSwitchFabRotateVal = 45F
    var mBackView: View? = null

    companion object{
        const val POS_LEFT_TOP: Int = 0
        const val POS_LEFT_BOTTOM: Int = 1
        const val POS_RIGHT_TOP = 2
        const val POS_RIGHT_BOTTOM = 3

        const val ANIM_FADE = 0
        const val ANIM_SCALE = 1
        const val ANIM_BOUNCE = 2
        const val ANIM_ZHIHU = 3

        const val ANIM_DURATION = 150
    }


    /**
     * init代码块是包含在主构造函数中的，即使没有主构造函数，init也是默认的被调用了
     * 在其他的次要构造函数之前(因为次要的都首先调用了主构造函数了)
     * init还可以有多个，初始化顺序根据写代码的顺序
     */
    init {
        println("init function")
    }

    /**
     * 当有次要的构造函数的时候，如果存在主构造函数，需要delegate到主构造函数，
     * 如果没有的话，就不用
     */
    constructor(context: Context): super(context)

    //这个构造函数还是需要，不然会报错误
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
        Log.i(ConstConfigure.TAG, "constructor(context: Context, attrs: AttributeSet)")
    }

    //允许给构造函数参数设置一个默认的值，这样省了一个构造函数了
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        Log.i(ConstConfigure.TAG, "constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)")
        getAttributes(context, attrs)
        settingsView(context)
    }

    /**
     * 在kotlin中，如果父类的某个成员需要在子类中复写，需要明确的写上open 修饰符的前缀，
     *
     * 子类中的覆盖也必须使用override来标记上
     *
     * 另外在属性的覆盖上面，可以使用一个var覆盖val属性，而不能相反
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (mFirstEnter) {
            layoutParams.width = measuredWidth
            layoutParams.height = measuredHeight - 1

            mFirstEnter = false
        }
        Log.d(ConstConfigure.TAG, "width:" + layoutParams.width +
                ", height:" + layoutParams.height)

        if (changed) {
            layoutSwitchFab()
            layoutBackView()

//            var childCount = childCount
//            var j = childCount - 2
//            for(i in 0 until j step 1){
//                var childView = (FabTagLayout)getChildAt(i + 2)
//
//
//            }


        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var count = childCount
        for(i in 0 until childCount){
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 这样当悬浮窗展开的时候，其他的地方无法接收onTouch事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mStatus
    }

    fun layoutBackView(){
        mBackView = getChildAt(0)
        mBackView?.layout(0, 0, measuredWidth, measuredHeight)
    }



    fun getAttributes(context: Context, attrs: AttributeSet){
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.FloatingActionBtnPlus)
        mPosition = typeArray.getInt(R.styleable.FloatingActionBtnPlus_position, POS_RIGHT_BOTTOM)
        mAnimation = typeArray.getInt(R.styleable.FloatingActionBtnPlus_animationMode, ANIM_SCALE)
        mBackgroundColor = typeArray.getColor(R.styleable.FloatingActionBtnPlus_mBackgroundColor, Color.TRANSPARENT)
        ///ColorStateList是用来设置一系列颜色，比如点击的时候颜色的变化等的
        mFabColor = typeArray.getColorStateList(R.styleable.FloatingActionBtnPlus_switchFabColor)
        mIcon = typeArray.getDrawable(R.styleable.FloatingActionBtnPlus_switchFabIcon)
        mAnimationDuration = typeArray.getInt(R.styleable.FloatingActionBtnPlus_animationDuration, ANIM_DURATION)

        typeArray.recycle()
    }

    private fun settingsView(context: Context){
        Log.i(ConstConfigure.TAG, "settingsView")
        var backView = View(context)

        backView.setBackgroundColor(mBackgroundColor)
        backView.alpha = 0F
        addView(backView)

        //添加floatingactionbutton
        mSwitchFab = FloatingActionButton(context)
        //null object  Safe calls
        mSwitchFab?.let { backgroundTintList = mFabColor }
        mSwitchFab?.setImageDrawable(mIcon)

        Log.i(ConstConfigure.TAG, "add switch fab begin")
        addView(mSwitchFab)

    }

    fun layoutSwitchFab(){
        var l = 0
        var t = 0

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            l = dp2px(16)
            t = dp2px(16)
        }

        var width = mSwitchFab?.measuredWidth
        var height = mSwitchFab?.measuredHeight

        when (mPosition) {
            POS_LEFT_BOTTOM -> {
                //!!把可能为null的转变成了不为null的对象
                t = measuredHeight - height!! - t
            }
            POS_RIGHT_TOP -> {
                l = measuredWidth - width!! -l
            }
            POS_RIGHT_BOTTOM -> {
                l = measuredWidth - width!! - l
                t = measuredHeight - height!! - t
            }
            else -> {

            }
        }
        //设置悬浮窗的layout
        mSwitchFab?.layout(l, t, l + width!!, t + height!!)

        bindSwitchFabEvent()

    }

    fun bindSwitchFabEvent(){
        mSwitchFab?.setOnClickListener{
            rotateSwitchFab()
            showBackground()
            changeStatus()

            if(mStatus){
                openItems()
            }else
                closeItems()
        }
    }

    fun openItems(){
        when (mAnimation) {
            ANIM_BOUNCE -> bounce()
            ANIM_FADE -> fade()
            ANIM_SCALE -> scale()
            else -> {
                throw IllegalStateException()
            }
        }
    }

    /**
     * 在kotlin中没有三目运算符，只能用if else来替换
     * 下面是转动悬浮窗中间的那个+ 号, rotation表示沿z轴旋转
     * https://www.jianshu.com/p/48d79eaf3470
     */
    fun rotateSwitchFab(){
        var animator: ObjectAnimator? = null
        if(mStatus){
            animator = ObjectAnimator.ofFloat(mSwitchFab, "rotation", mSwitchFabRotateVal, 0F)
        }else{
            animator = ObjectAnimator.ofFloat(mSwitchFab, "rotation", 0F, mSwitchFabRotateVal)
        }

        animator?.duration = ANIM_DURATION.toLong()
        //用来修饰动画运动的速率，比如先加速再减速等，LinearInterpolator 是匀速
        animator?.interpolator = LinearInterpolator()
        animator.start()
    }

    fun showBackground(){
        var animator: ObjectAnimator? = null
        if(mStatus)
            animator = ObjectAnimator.ofFloat(mBackView, "alpha", 0.9F, 0F)
        else
            animator = ObjectAnimator.ofFloat(mBackView, "alpha", 0F, 0.9F)

        animator?.duration = ANIM_DURATION.toLong()
        animator.start()
    }

    fun changeStatus(){
        if(mStatus)
            mStatus = false
        else
            mStatus = true
    }

    fun dp2px(value: Int): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(),
                resources.displayMetrics).toInt()
    }

    fun scale(){
        for(i in 2 until childCount step 1){
            var view = getChildAt(i)
            view.visibility = View.VISIBLE
            var scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0F, 1F)
            var scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0F, 1F)
            var alpha = ObjectAnimator.ofFloat(view, "alpha", 0F, 1F)
            var animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleX, scaleY, alpha)
            animatorSet.duration = mAnimationDuration.toLong()
            animatorSet.startDelay = (i * 40).toLong()
            animatorSet.interpolator = OvershootInterpolator()
            animatorSet.start()
        }
    }

    /**
     * 弹出来的效果,从第二个开始是因为那个floatingbtn和中间的那个image吧？
     */
    fun bounce(){
        for(i in 2 until childCount step 1){
            var view = getChildAt(i)
            view.visibility = View.VISIBLE

            //AnimatorSet用来表示同时开始的动画
            var translationY = ObjectAnimator.ofFloat(view, "translationY", 50F, 0F)
            var alpha = ObjectAnimator.ofFloat(view, "alpha",0F, 1F)
            var animator = AnimatorSet()
            animator.playTogether(translationY, alpha)
            animator.duration = mAnimationDuration.toLong()
            // BounceInterpolator 反弹插补器
            //（在动画结束的时候回弹几下，如果是竖直向下运动的话，就是玻璃球下掉弹几下的效果
            animator.interpolator = BounceInterpolator()
            animator.start()
        }
    }

    fun closeItems(){
        //区间的语法详见如下
        //https://www.kotlincn.net/docs/reference/ranges.html
        for(i in 2 until childCount step 1){
            var aplha = ObjectAnimator.ofFloat(getChildAt(i), "alpha", 1F, 0F)
            var animator = AnimatorSet()
            animator.playTogether(aplha)
            animator.duration = mAnimationDuration.toLong()
            animator.start()

            hideChild(animator, getChildAt(i))
        }
    }

    /**
     * 设置listener的一些写法
     */
    private fun hideChild(animatorSet: AnimatorSet, childView: View) {
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                childView.visibility = View.INVISIBLE
            }
        })
    }

    /**
     * 渐变的效果
     */
    private fun fade(){
        for(i in 2 until childCount){
            var view = getChildAt(i)
            view.visibility = View.VISIBLE
            var objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0F, 1F)
            objectAnimator.duration = mAnimationDuration.toLong()
            objectAnimator.startDelay = (i * 40).toLong()
            //OvershootInterpolator 超出插补器（向前跑直到越界一点后，再往回跑）
            objectAnimator.interpolator = OvershootInterpolator()
            objectAnimator.start()
        }
    }
}