package facaicai.kotlin.project.ba

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import base.viewModel.BaseViewModel

/**
 * @description:
 * @data:2023/3/18
 * @author:Mr.Hu
 * @github:https://github.com/ProfoundAstronomy/AndroidKotlinProject
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun initData() {

    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }
}