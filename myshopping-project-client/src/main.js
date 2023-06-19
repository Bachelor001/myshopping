import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Swal from 'sweetalert2';//导入sweetalert2弹框组件
import qs from 'qs';//导入qs函数库

Vue.config.productionTip = true;

//设置axios为Vue的原型属性
Vue.prototype.$axios=axios.create({
  baseURL: 'http://localhost/myshopping/',//设置axios请求的基础路径
  headers: {'X-Requested-With': 'XMLHttpRequest'},//指定axios的请求为一个ajax请求
  withCredentials: true//是否允许使用凭证
});

//将sweetalert2注册为Vue的原型属性
Vue.prototype.$swal=Swal

//将qs库注册为vue的原型属性
Vue.prototype.$qs=qs;

Vue.use(ElementUI);//设置Vue使用ElementUI组件

/*
  添加axios的请求拦截器-拦截axios向服务端发送的所有请求
  实现功能:当客户端向服务端发送请求时，通过拦截器拦截请求并检测用户是否已登录，如果已登录则继续访问服务端
          如果用户未登录则跳转到登录页(不向服务端发送请求<受限操作>)
*/

Vue.prototype.$axios.interceptors.request.use(function (config) {
  //当向服务端发送请求时执行
  //config时一个请求设置对象其中包含请求的相关信息
  //检测axios请求的服务端路径是否未受限路径
  let url = config.url;//获得请求的服务端路径
  //定义匹配放行路径的url地址的正则表达式
  let regUrl = /^book\/|^login$|^register$/;
  
   if(!(regUrl.test(url))){
     console.log("受限路径");
     //处理受限路径
     //从sessionstrage中获得当前登录者的信息
    let curUserStr = window.sessionStorage.getItem("curUser");
    //检测用户是否登录
    if(curUserStr=="" || curUserStr==null){//用户未登录
       
      //取消原有的请求
      let cancel=null;//声明取消请求的变量
      //向config对象中添加一个cancelToken(取消请求令牌)的属性
      config.cancelToken=new axios.CancelToken((c)=>{
        //参数c为一个取消请求的函数
        cancel = c;
      });
      //判断cancel是否为一个函数,如果是函数，它必定是一个取消请求函数
      if(typeof cancel =="function"){
        cancel("请求已取消");//取消请求
      }
      




      Vue.prototype.$swal.fire({                                 
        icon: 'error',
        title: '请登录后访问',
        showConfirmButton: false,
        timer: 1000,//延迟关闭的时间
        didClose:()=>{
          //跳转到登录页
          router.push("/login");
        }
    })
     
    }
   }
  
  
   







  // 在发送请求之前做些什么
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});



new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
