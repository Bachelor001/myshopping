<template>
    <div class="index-container">
      <el-container>
        <el-header height="80px">
            <div class="header-div">
                <div class="logo-div">
                    <div>
                       <img :src="require('@/assets/logo.png')" style="width:180px;height:70px;border:0px solid red"/>     
                    </div>
                    <div style="padding-top:5px">
                        <label>在线购物商城</label>
                    </div>
                    
                    
                </div>
                <div class="personal-div">
                    <div v-if="curUser==null">
                       <el-button type="success"  @click="$router.push('/login')">登录</el-button>  
                       <el-button type="warning"  @click="$router.push('/register')">注册</el-button>   
                    </div>
                    <div v-else>
                        <el-dropdown @command="handleCommand">
                            <el-button type="warning" icon="el-icon-user">
                                {{curUser.user_name}}<i class="el-icon-arrow-down el-icon--right"></i>
                            </el-button>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="1">个人中心</el-dropdown-item>
                                <el-dropdown-item command="2">查看订单</el-dropdown-item>
                                <el-dropdown-item command="0">退出</el-dropdown-item>
                               
                            </el-dropdown-menu>
                        </el-dropdown>

                        <el-badge :value="carCount" class="item">
                            <el-button type="success" icon="el-icon-shopping-cart-full"  @click="$router.push('/showCar')">购物车</el-button>
                        </el-badge>
                        
                       
                    </div>
                </div>    
            </div>
            
        </el-header>
        <el-main>
            <div class="bookList">
                <el-row>
                    <el-col :span="6" v-for="book in books" :key="book.book_id" style="margin-bottom:10px;"  :offset="0">
                        <el-card :body-style="{ padding: '0px' }" style="margin-left:10px">
                        <img :src="require('@/assets/images/books/'+book.book_image)" class="image">
                        <div>
                            <div style="display:flex">
                               <div class="book-desc" style="width:70%; font-size:12px">
                                    <div>
                                        <el-tooltip class="item" effect="dark" :content="book.book_name" placement="top">
                                           
                                                <!-- <span>书名:{{subBookName(book.book_name)}}</span> -->
                                                <!-- <router-link :to="'/bookDesc/'+book.book_id">书名:{{subBookName(book.book_name)}}</router-link> -->
                                                <!-- <router-link :to="{name:'bookDescName',params:{'book_id':book.book_id}}">书名:{{subBookName(book.book_name)}}</router-link> -->
                                                <router-link :to="{path:'bookDesc',query:{'book_id':book.book_id}}">书名:{{subBookName(book.book_name)}}</router-link>
                                            <!-- <router-link :to="'/bookDesc?book='+book">书名:{{subBookName(book.book_name)}}</router-link> -->
                                        </el-tooltip>
                                        
                                    </div>   
                                    <div>
                                    <span>单价:¥{{book.book_price}}</span>
                                    </div>
                                </div>
                                <div class="book-car"  style="width:30%;padding-top:10px">
                                    <span class="el-icon-shopping-cart-full" style="color:orange;font-size:46px"></span>   
                                </div>  

                            </div>

                            <div style="display:flex" class="pay">
                                <div style="padding:0px 10px;width:60%;height:40px;line-height:40px;">
                                     <el-input-number  size="small" :min="1" v-model="num"></el-input-number>                                 
                                </div>
                                <div style="width:40%">
                                    <el-button type="warning" style="width:100%">购买</el-button>
                                </div>
                            </div>
                            
                        </div>
                        </el-card>
                    </el-col>

                     
                </el-row>

            </div>
        </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </div>
</template>
<script>
export default {
    data(){
        return{
            books:[],//商品数组
            num:1,//购买数量
            curUser:null,//当前登陆者对象
            carCount:0,//当前用户购物车中商品的数量
        }
    },
    methods:{
        getCurUser(){
            this.curUser = JSON.parse(window.sessionStorage.getItem("curUser"));
            if(this.curUser!=null){
                //获得当前用户的购物车中商品数量
                this.getCarCount();
            }
        },
        /**
         * 获得购物车中商品数量
         */
        getCarCount(){
            this.$axios
                .get('car/getCarCount')
                .then(response=>{
                    this.carCount=response.data;
                })
                .catch(err=>{
                    console.log(err);
                });
        },
        /**
         * 用户下拉菜单指令处理函数
         */
        handleCommand(command){
            switch(command){
                case "0":
                    this.$axios
                        .delete("/exit")
                        .then(response=>{
                            let result = response.data;        
                            if(result.success){
                                this.$swal.fire({                                 
                                    icon: 'success',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000,//延迟关闭的时间
                                    didClose:()=>{//弹窗关闭后要执行的函数
                                        //删除sessionStorage中的数据
                                        window.sessionStorage.removeItem("curUser");
                                        //重置curUser属性
                                        this.curUser = null;
                                    }
                                })
                                
                            }
                        })
                    break;
                case "1":
                    alert("个人中心");
                    break;
                case "2":
                    alert("查看订单");
                    break;
                    

            }
        },
        /**
         * 获得商品信息
         */
        getBooks(){
            this.$axios
                    .get('book/getBooks')
                    .then(response=>{
                        this.books = response.data;
                        
                    })
                    .catch(err=>{
                        //alert(err);
                    });    
        },
        /**
         * 截取图书名称的函数
         * 函数要求书名最多10个字符
         */
        subBookName(bookName){
            if(bookName.length>=15){
                bookName = bookName.substring(0,15);
            }
            return bookName;
        }
    },
    created(){
        this.getCurUser();
        this.getBooks();
    }
}
</script>
<style scoped>
    .index-container{
        margin: 0px auto;
        width: 80%;
    }
    .el-header, .el-footer {
        background-color:lightskyblue;
        color: #333;     
        
        line-height: 80px;
    }

    .header-div{
        display: flex;
        width: 100%;
        height: 100%;
    }
    .header-div>div{
        border: 0px solid red;
        width: 50%;
        height: 100%;
    }


    .logo-div{
        display: flex;
    }
    .logo-div label{
        
        font-size: 50px;
        font-family:"华文行楷";
        font-weight: bold;
        color: red;

    }

    .personal-div{
        width: 100%;
        height: 100%;
        text-align: center;
    }
    .personal-div .item {
        border: 0px solid red;;
        height: 40px;
        line-height: 40px;;
        margin-top: -3px;
        margin-left:10px;
        padding-top:0px;
        margin-right: 35px;
        }
    .el-main {
        
        color: #333;
    }

    
    

    /**卡片样式 */   
  
  .book-desc div {
    margin: 10px auto;
    padding-left: 10px;
  }

 

  .image {
    width: 300px;
    height: 260px;
    display: block;
  }

  .pay{
      margin:5px;
  }


  
</style>
