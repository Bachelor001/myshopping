<template>
  <div class="win-logoin">
    <div class="container" id="login-container">
      <div class="title">在线商城</div>
      <div class="login-input">
        <label>用户名:</label>
        <input type="text" v-model="user.username"/>
      </div>
      <div class="login-input">
        <label>密&nbsp;&nbsp;码：</label>
        <input type="password" v-model="user.password"/>
      </div>
      <div class="login-button">
        <button @click.prevent="login">登录</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
        user:{},//当前登录的对象
    };
  },
  methods: {
    //登录方法
    login() {

      //加载条
      const loading=this.$loading({
        lock: true,
        text: "登录中，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });


      //发送登录请求到服务端
      this.$axios
            .post('login',this.$qs.stringify(this.user))//this.$qs.stringify(this.user)将payload请求序列化为formdata请求
            .then(response=>{
                //关闭加载条
                loading.close();
                  let result = response.data;  
                  if(result.success){
                        this.$swal.fire({                                 
                                    icon: 'success',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000,//延迟关闭的时间
                                    didClose:()=>{//弹窗关闭后要执行的函数
                                    //将当前登录者的信息存入到sessionStorage中
                                    let curUser = result.data;
                                    window.sessionStorage.setItem("curUser",JSON.stringify(curUser));



                                        this.$router.push("/");
                                    }
                                })
                  }else{
                      this.$swal.fire({                                 
                                    icon: 'error',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000//延迟关闭的时间
                                    
                                })
                  }
                  
                 
            })
            .catch(err=>{
                alert(err);
            });

     
      
    }
  }
};
</script>
<style scoped>
.win-logoin {
  position: absolute;
  top: 0px;
  bottom: 0px;
  margin: 0px;
  left: 0px;
  right: 0px;
  padding: 0px;
  display: flex;
  background-color: #dddddd;
  /*设置弹性布局*/
  flex-direction: row;
  /*设置水平方向*/
  justify-content: center;
  /*根据direction的方向设置居中*/
  align-items: center;
  /*根据direction的反方向设置居中*/
}

.container {
  width: 800px;
  height: 500px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px 5px black;
  display: flex;
  background-color: ghostwhite;
  /*设置弹性布局*/
  flex-direction: column;
  /*列布局*/
  align-items: center;
}

.title {
  font-size: 60px;
  font-weight: bold;
  color: red;
  text-shadow: 5px 5px 5px #a9a9a9;
  letter-spacing: 15px;
  margin: 50px 0;
}

.login-input {
  position: relative;
  width: 300px;
  height: 50px;
  border: 0px solid red;
  margin: 5px 0;
}

.login-input > label {
  box-sizing: border-box;
  position: absolute;
  border: 0px solid red;
  width: 80px;
  height: 50px;
  line-height: 50px;
  letter-spacing: 2px;
  padding-left: 10px;
  color: #666666;
}

.login-input > input {
  box-sizing: border-box;
  border-radius: 5px;
  border: 1px solid #dddddd;
  padding-left: 80px;
  outline: none;
  font-size: 18px;
  width: 100%;
  height: 100%;
}

.login-button {
  position: relative;
  width: 300px;
  height: 50px;
  margin: 15px 0;
}

.login-button > button {
  border: 0;
  width: 100%;
  height: 100%;
  border-radius: 5px;
  border: 1px solid #dddddd;
  outline: none;
  background-color: white;
  letter-spacing: 15px;
  padding-left: 15px;
  color: #666666;
  font-size: 18px;
}

.login-button > button:hover {
  cursor: pointer;
  background-color: cornflowerblue;
  color: white;
}

.login-button > button:active {
  font-weight: bold;
  outline: none;
  border: 0;
  box-shadow: 0px 0px 8px 1px #7088ff;
}
</style>