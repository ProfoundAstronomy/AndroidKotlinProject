package base.viewModel

import androidx.lifecycle.ViewModel
import callBack.liveData.event.EventLiveData

/**
 * @description: ViewModel的基类,使用ViewModel类
 * @data:2023/3/16
 * @author:Mr.Hu
 * @github:https://github.com/ProfoundAstronomy/AndroidKotlinProject
 */
open class BaseViewModel : ViewModel(){
    /**
     * Kotlin open 在函数名中的使用:
     * Kotlin 中的所有函数在本质上默认也都是 final的,当函数本质上是 final时，我们不能 override 一个函数
     * 函数的 override 是在子类中重新定义基类函数的过程。
     * 所以说，在父类的对应函数前需要加关键字 open，与此同时，在子类中的这个函数前，我们必须使用 override 修饰符
     *
     * 缩写： 父类 ViewModel() 是抽象类,本身也是 final类(默认)，BaseViewModel 想要继承就得加 open关键字
     */

    val loadingChange : UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框
     */
    inner class UiLoadingChange {
        /**
         * inner关键字修饰表示内部类
         */
        //显示加载框
        val showDialog by lazy { EventLiveData<String>() }
        //隐藏加载框
        val hideDialog by lazy { EventLiveData<Boolean>() }
        //关闭加载框
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }
}