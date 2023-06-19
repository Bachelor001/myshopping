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

                        <el-badge :value="12" class="item">
                            <el-button type="success" icon="el-icon-shopping-cart-full"  @click="$router.push('/showCar')">购物车</el-button>
                        </el-badge>
                        
                       
                    </div>
                </div>    
            </div>
            
        </el-header>
      <el-main>
          <div class="desc-div">
            <h2 align="center">商品详情</h2>
            <div class="goodsDesc">
                <div class="baseInfo">
                    <div class="photo">
                        <el-image :src="require('@/assets/images/books/'+book.book_image)">
                            <div slot="placeholder" class="image-slot">
                                加载中<span class="dot">...</span>
                            </div>
                        </el-image>
                    </div>
                    <div class="bookInfo">
                        <p>书名:{{book.book_name}}</p>
                        <p>价格:{{book.book_price}}</p>
                        <p>
                             <el-input-number size="medium" v-model="car.car_count" :min="1"></el-input-number>
                             <el-button type="warning" icon="el-icon-shopping-cart-full" style="margin-left:3px" @click="addCar" :disabled="curUser==null">加入购物车</el-button>
                        </p>
                    </div>
                </div>
                <div class="bookDesc">
                    <p>图书详情:</p>
                    {{book.book_desc}}
                </div>
                <div class="book-comment">
                    <p>图书评论</p>

<!-- v-for="comment in commentList" :key="comment.comment_id" -->
                    <div class="comment-info"  v-for="comment in commentList" :key="comment.comment_id">
                        <div class="comment-base-info">
                            <label style="display:inline-block;width:70%">评论人:{{comment.user_name}}</label>
                            <label style="margin-right:20px">评论时间:{{comment.comment_time}}</label>
                        </div>
                        <div class="comment-content">
                            {{comment.comment_message}}
                        </div>
                    </div>

                     <div class="comment-info" v-if="commentList.length==0">
                        
                        <div class="comment-content">
                           暂无评论
                        </div>
                    </div>

                    <div class="pagination">
                        <el-pagination
                            background
                            :page-size="pageParam.pageSize"
                            :current-page.sync="pageParam.pageNum"
                            layout="prev, pager, next,jumper,total"                           
                            :total="total"
                            @current-change="queryCommentByBookId(book.book_id)">
                        </el-pagination>
                    </div>

                </div>
            </div>
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
            curUser:null,//当前登陆者对象
           book:{},//图书对象
           car:{
            car_count:1,//加入购物车的数量
           },
           
           commentList:[],//图书评论数组
           /**
            * 分页参数
            */
           pageParam:{
               pageSize:5,//每页显示的行数
               pageNum:1//设置当前页
           },
           total:0,//总行数
           pageSizes:[5,15,20]//设置每页显示行数列表的值
        }
    },
    methods:{
         getCurUser(){
            this.curUser = JSON.parse(window.sessionStorage.getItem("curUser"));
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
        queryBookById(book_id){
            this.$axios
                    .get('book/queryBookById?book_id='+book_id)
                    .then(response=>{
                        this.book = response.data;
                    })
                    .catch(err=>{
                        alert(err);
                    });
        },

        /**
         * 添加购物车
        
        addCar(){
            //将book_id添加到car对象中
            this.car.book_id=this.book.book_id;

            this.$axios
                    .post('car/addCar',this.car)
                    .then(response=>{
                        let result = response.data;
                        if(result.success){//添加成功

                        }else{//添加失败

                        }
                    })
                    .catch(err=>{alert(err)});
        }, */

        addCar(){
            //将book_id添加到car对象中
            this.car.book_id=this.book.book_id;
            this.car.book_name=this.book.book_name;
            this.car.book_price=this.book.book_price;
            this.car.book_image=this.book.book_image;
            this.$axios
                    .post('car/addCar',this.car)
                    .then(response=>{
                        let result = response.data;
                        if(result.success){//添加成功
                            //  this.$message({
                            //     message: result.message,
                            //     type: 'success',
                            //     duration:1000
                            // });


                            this.$swal.fire({                                 
                                    icon: 'success',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000,//延迟关闭的时间
                                    
                                })
                        }else{//添加失败
                        
                             this.$message({
                                message: result.message,
                                type: 'error',
                                duration:1000
                            });
                        }
                    })
                    .catch(err=>{
                        console.log(err);
                    });
        },




        /**
         * 根据图书编号获得该图书的所有评论信息
         */
        queryCommentByBookId(book_id){
            // this.$axios
            //     .get('book/queryCommentByBookId?book_id='+book_id,{
            //         params:this.pageParam
                    
            //     })
            this.pageParam.book_id=book_id;//向对象中添加了一个属性
            this.$axios
                .get('book/queryCommentByBookId',{
                    params:this.pageParam
                })
                .then(response=>{
                    let map = response.data;    
                    this.total = map.total;//获得总记录数
                    this.commentList = map.commentList;//获得查询的具体数据
                })
                .catch(err=>{
                    console.log(err);
                })
        }
        
    },
    created(){
        this.getCurUser();
        //获得Index.vue传入的查询参数
        let book_id = this.$route.query.book_id; 
        this.queryBookById(book_id);
        this.queryCommentByBookId(book_id);
        
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
        background-color: #fff;
        color: #333;
    }


  
    .desc-div{
        width:100%;
        border: 0px solid red;
    }
    .goodsDesc{
        
        width: 80%;
        margin:0 auto;
        border:1px solid blue;

    }
    .baseInfo{
        display: flex;
    }

    .baseInfo .photo{
        width: 50%;
        text-align: center;
        margin-bottom:10px;
    }
    .bookDesc>p{
        width:100%;
        height: 40px;
        line-height: 40px;
        padding-left:10px;
        box-sizing:border-box;
        color: #fff;
        background-color:darkgray;

    }

   
    .book-comment>p{
        width:100%;
        height: 40px;
        line-height: 40px;
        padding-left:10px;
        box-sizing:border-box;
        color: #fff;
        background-color:darkgray;
        margin-bottom: 0px;
    }
    .comment-base-info{
        width:100%;
        height: 40px;
        line-height: 40px;
        padding-left:10px;
        box-sizing:border-box;
        color: #fff;
        background-color:yellowgreen

    }

    .comment-content{
        padding:15px 10px;
    }
</style>