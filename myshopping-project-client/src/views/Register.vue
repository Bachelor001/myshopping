<template>
    <div class="win-reg">
    <div class="register-container">
        <h2>用户注册</h2>
        <div class="register-form">
            <el-form ref="form" :model="user" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="user.user_name" placeholder="请输入用户名"></el-input>
                </el-form-item>

                <el-form-item label="密 码">
                    <el-input placeholder="请输入密码" v-model="user.user_password" show-password></el-input>
                </el-form-item>

                <el-form-item label="性别">
                    <el-radio-group v-model="user.user_sex" size="medium" >
                        <el-radio-button label="1" >男</el-radio-button>
                        <el-radio-button label="0">女</el-radio-button>                        
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="爱好">
                   <el-checkbox-group v-model="user_hobbys_arr" size="medium">
                        <el-checkbox-button v-for="hobby in hobbys" :label="hobby" :key="hobby">{{hobby}}</el-checkbox-button>
                    </el-checkbox-group>
                </el-form-item>

                <el-form-item label="出生日期">
                   <el-date-picker
                    v-model="user.user_birthday"
                    type="date"
                    placeholder="选择日期"
                    value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>


                <el-form-item label="E-mail">
                   <el-input v-model="user.user_email" placeholder="请输入E-mail"></el-input>
                </el-form-item>

                <el-form-item label="地址">
                   <el-input v-model="user.user_address" placeholder="请输入地址"></el-input>
                </el-form-item>
                
            </el-form>
            
        </div>
        <div >
            <el-button type="primary" style="width:100%;margin-bottom:10px;height:50px" @click="register">注册</el-button>
        </div>
    </div>
    </div>
</template>
<script>

export default {
    data(){
        return{
            user:{},
            user_hobbys_arr:[],//绑定复选框的数组
            hobbys:["篮球","足球","听音乐","网游"]
        }
    },
    methods:{
        /**
         * 注册函数
         */
        register(){
            //将ser_hobbys_arr数组中的元素转换为字符串,再将字符串存到user.user_hobbys中
            if(this.user_hobbys_arr.length!=0){
                let user_hobbys="";
                for(let hobby of this.user_hobbys_arr){
                    user_hobbys=user_hobbys+"_"+hobby;
                }
                this.user.user_hobbys=user_hobbys.substring(1);
                
            }    



            this.$axios
                .post('register',this.user)
                .then(response=>{
                    let result = response.data;
                    if(result.success){
                        this.$swal.fire({                                 
                                    icon: 'success',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000,//延迟关闭的时间
                                    didClose:()=>{//弹窗关闭后要执行的函数
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
                    console.log(err);
                })
        }
    }
}
</script>
<style scoped>

.win-reg {
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
    .register-container{
        margin:30px auto;
        width: 500px;
        border: 0px solid red;
        border-radius: 10px;
          background-color: ghostwhite;
        box-shadow: 0px 0px 10px 5px gray;
        padding: 5px;
    }
    .register-container h2{
        text-align: center;
    }
    .register-form{
        padding-right:50px;
    }

    
</style>