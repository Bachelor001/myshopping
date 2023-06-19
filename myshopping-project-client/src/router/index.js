import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";//导入Index.vue组件
import BookDesc from "../views/BookDesc.vue";
import ShowCar from "../views/ShowCar.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import ConfirmSale from "../views/ConfirmSale.vue";
Vue.use(VueRouter);

const routes = [
  {//配置Index路由
    path:"/",//根路由
    name:"index",
    component:Index
  },{//设置图书详情路由
    //path:"/bookDesc/:book_id",//:book_id等同于占位符
    path:"/bookDesc",
    name:"bookDescName",
    component:BookDesc
  },{
    path:"/showCar",
    name:"showCar",
    component:ShowCar
  },{
    path:"/login",
    name:"login",
    component:Login
  },{
    path:"/register",
    name:"register",
    component:Register
  },{
    path:"/confirmSale",
    name:"confirmSale",
    component:ConfirmSale
  }
];

const router = new VueRouter({
  routes,
});

export default router;
