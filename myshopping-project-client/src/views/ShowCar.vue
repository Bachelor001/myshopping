<template>
    <div class="showCar-Container">
        <div class="car-list">
            <el-table
                :data="carList"
                border
                style="width: 100%"
                empty-text="暂无商品"
                @selection-change="getSum">
                
                <el-table-column
                    type="selection"
                    align="center">
                </el-table-column>

                <el-table-column
                    type="index"
                    label="序号"
                    align="center">
                </el-table-column>

                <el-table-column
                    prop="book_name"
                    label="商品名称"
                    align="center">
                </el-table-column>

                <el-table-column
                    prop="book_price"
                    label="单价"
                    align="center">
                </el-table-column>

                <el-table-column
                    label="商品照片"
                    align="center">
                    <template slot-scope="scope">
                        <img :src="require('@/assets/images/books/'+scope.row.book_image)"/>
                    </template>
                </el-table-column>

                <el-table-column
                    prop="car_count"
                    label="购买数量"
                    align="center">
                    <template slot-scope="scope">
                    <el-input-number size="small" v-model="scope.row.car_count" :min="1" @change="changeCarCount(scope.row)"></el-input-number>
                    </template>
                    
                
                </el-table-column>

                <el-table-column
                    prop="total"
                    label="合计"
                    align="center">
                    <template slot-scope="scope">
                        {{scope.row.book_price*scope.row.car_count}}
                    </template>
                </el-table-column>

                 <el-table-column                  
                    label="操作"
                    align="center">
                    <template slot-scope="scope">
                        <el-popconfirm
                            confirm-button-text='确认'
                            cancel-button-text='取消'
                            icon="el-icon-info"
                            icon-color="red"
                            title="确认删除购物车商品吗?"
                            @confirm="delCar(scope.row.book_id)"
                            >
                            <el-button type="danger" icon="el-icon-delete" slot="reference" >删除</el-button>
                        </el-popconfirm>
                    </template>
                </el-table-column>

            </el-table>
        </div>

        <div class="car-sum">
           <label>总价:</label>     
           <label style="font-size:20px;color:red">¥{{sum}}</label>
           <el-button type="warning" style="margin-left:10px;margin-right:30px;" :disabled="saleBooks.length==0" @click="gotoConfirmSale">购买</el-button>
        </div>


    </div>
</template>

<script>
export default {
    data(){
        return{
            carList:[],//购物车列表
            sum:0,//要购买的商品的总价格
            saleBooks:[],//要购买商品的数组
        }
    },
    methods:{
        /**
         * 获得购物车信息
         */
        getCarInfo(){
            this.$axios
            .get('car/getCarInfo')
            .then(response=>{
                this.carList = response.data;
            })
            .catch(err=>{alert(err)});
        },
        /**
         * 修改购物车中商品的数量
         */
        changeCarCount(row){
            console.log(row.book_price,row.car_count);
            this.$axios
                .put('car/updateCarCount',{
                    "book_id":row.book_id,
                    "car_count":row.car_count
                })
                .then(response=>{
                    let result = response.data;
                    if(result.success){
                       // this.getCarInfo();
                       row.total=row.book_price*row.car_count;

                        this.sum=0;
                        //改变总价格，如果修改数量的商品是要购买才修改总价
                        for(let saleBook of this.saleBooks){
                          
                                this.sum = this.sum + saleBook.book_price*saleBook.car_count;
                           
                        }



                    }
                })
                .catch(err=>{
                    alert(err);
                });
        },
        /**
         * 删除购物车中的商品
         */
        delCar(book_id){
            this.$axios
                .delete('car/delCar?book_id='+book_id)
                .then(response=>{
                    let result = response.data;
                    if(result.success){
                        this.getCarInfo();
                    }
                });
        },
        /**
         * 获得总价格
         */
        getSum(selection){
            
            //将选择的商品赋给saleBooks数组
            this.saleBooks=selection;
            //将总价清零
            this.sum = 0;
            //遍历选择的项并计算总价格
            for(let row of selection){
                this.sum = this.sum + row.book_price*row.car_count;
            }
            
        },
        /**
         * 跳转到确认购买视图
         */
        gotoConfirmSale(){
            this.$router.push({path:'/confirmSale',query:{"saleBooks":this.saleBooks}});
        }
       
    },
    created(){
        this.getCarInfo();
    }
}
</script>

<style scoped>
    .car-sum{
        margin:20px auto;
        width: 100%;
       
        text-align:right;
        border: 0px solid red;
    }
</style>