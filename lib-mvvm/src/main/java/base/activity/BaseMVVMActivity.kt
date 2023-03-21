package base.activity

import android.app.usage.NetworkStatsManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import base.viewModel.BaseViewModel
import ext.getVmClazz
import facaicai.kotlin.lib_mvvm.R
import network.manager.NetState
import network.manager.NetworkStateManager

/**
 * @description:
 * @data:2023/3/16
 * @author:Mr.Hu
 * @github:https://github.com/ProfoundAstronomy/AndroidKotlinProject
 */
abstract class BaseMVVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改
     */
    private var isUserDb = false

    lateinit var mViewModel : VM

    /**
     * 加载布局id
     */
    abstract fun layoutId() : Int

    /**
     * 初始化视图
     */
    abstract fun initView(savedInstanceState: Bundle?)

    open fun initDataLoad(){}

    open fun initImmersionBar() {}

    abstract fun showLoading(message: String = getString(R.string.request_net_tips))

    abstract fun hideLoading()

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        if (!isUserDb){
            setContentView(layoutId())
        }else{
            initDataBind()
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?){
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        initImmersionBar()
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this, Observer {
            onNetworkStateChanged(it)
        })
        initDataLoad()
    }

    open fun onNetworkStateChanged(netState: NetState){}

    private fun createViewModel() : VM{
        return  ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    private fun registerUiChange(){
        mViewModel.loadingChange.showDialog.observeInActivity(this, Observer {
            showLoading(it)
        })

        mViewModel.loadingChange.hideDialog.observeInActivity(this, Observer {
            hideLoading()
        })

        mViewModel.loadingChange.dismissDialog.observeInActivity(this, Observer {
            dismissLoading()
        })
    }

    /**
     * Kotlin的vararg args: String参数转换成Java的 @NotNull String... args
       Kotlin的spread伸展操作符*args转换成Java的(String[])Arrays.copyOf(args, args.length)
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel){
        viewModels.forEach {viewModel ->
            viewModel.loadingChange.showDialog.observeInActivity(this, Observer {
                showLoading(it)
            })
            viewModel.loadingChange.hideDialog.observeInActivity(this, Observer {
                hideLoading()
            })
            viewModel.loadingChange.dismissDialog.observeInActivity(this, Observer {
                dismissLoading()
            })
        }
    }


    /**
     * 供子类BaseVmDbActivity 初始化DataBinding操作
     */
    open fun initDataBind() {}
}