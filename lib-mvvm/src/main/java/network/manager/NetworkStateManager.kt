package network.manager

import callBack.liveData.event.EventLiveData

/**
 * @description: 网络变化管理者
 * @data:2023/3/17
 * @author:Mr.Hu
 * @github:https://github.com/ProfoundAstronomy/AndroidKotlinProject
 */
class NetworkStateManager private constructor(){
    /**
     *  constructor() 主构造写法
     */

    val mNetworkStateCallback = EventLiveData<NetState>()

    /// 类的内部object 前加上 companion， 可以做成属于这个类的Singleton
    companion object{
        /// 通过by lazy,实现单例创建了NetworkStateManager()对象
        val  instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NetworkStateManager()
        }
    }
}