<template>
    <div class="showCar-Container">
        <div class="car-list">
            <el-table :data="saleBooks" border style="width: 100%" @selection-change="getSum">



                <el-table-column type="index" label="序号" align="center">
                </el-table-column>

                <el-table-column prop="book_name" label="商品名称" align="center">
                </el-table-column>

                <el-table-column prop="book_price" label="单价" align="center">
                </el-table-column>

                <el-table-column label="商品照片" align="center">
                    <template slot-scope="scope">
                        <img :src="require('@/assets/images/books/' + scope.row.book_image)" />
                    </template>
                </el-table-column>

                <el-table-column prop="car_count" label="购买数量" align="center">

                </el-table-column>

                <el-table-column prop="total" label="合计" align="center">
                    <template slot-scope="scope">
                        {{ scope.row.book_price * scope.row.car_count }}
                    </template>
                </el-table-column>



            </el-table>
        </div>

        <div class="car-sum">
            <label>总价:</label>
            <label style="font-size:20px;color:red">¥{{ sum }}</label>
            <el-button type="warning" style="margin-left:10px;" :disabled="saleBooks.length == 0"
                @click="dialog = true">购买</el-button>
            <el-button type="warning" style="margin-left:10px;margin-right:30px;" @click="gotoShowCar">返回购物车</el-button>
        </div>

        <div>
            <el-drawer title="添加联系人信息"  :visible.sync="dialog" direction="ltr"
                custom-class="demo-drawer" ref="drawer">
                <div class="demo-drawer__content">
                    <el-form :model="order">
                        <el-form-item label="收货人姓名" :label-width="formLabelWidth">
                            <el-input v-model="order.order_to_name" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="收货人联系电话" :label-width="formLabelWidth">
                            <el-input v-model="order.order_to_phone" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="收货人联系地址" :label-width="formLabelWidth">
                            <el-input v-model="order.order_to_address" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-form>
                    <div class="demo-drawer__footer">
                        <el-button @click="cancelForm">取 消</el-button>
                        <el-button type="primary" @click="$refs.drawer.closeDrawer();sale()" :loading="loading">{{ loading ?
                            '提交中...' : '确 定' }}</el-button>
                    </div>
                </div>
            </el-drawer>

        </div>

    </div>
</template>

<script>
export default {
    data() {
        return {
            table: false,
            dialog: false,
            loading: false,
            order: {},
            timer:null,
            sum: 0,//要购买的商品的总价格
            saleBooks: [],//要购买商品的数组
            formLabelWidth: '80px'
        }
    },
    methods: {


        /**
         * 获得总价格
         */
        getSum() {
            for (let saleBook of this.saleBooks) {
                this.sum = this.sum + saleBook.book_price * saleBook.car_count;
            }

        },
        /**
         * 返回购物车
         */
        gotoShowCar() {
            this.$router.push("/showCar");
        },
        // /**
        //  * 订单信息
        //  */
        // handleClose(done) {
        //     if (this.loading) {
        //         return;
        //     }
        //     this.$confirm('确定要提交表单吗？')
        //         .then(_ => {
        //             this.loading = true;
        //             this.timer = setTimeout(() => {
        //                 done();
        //                 // 动画关闭需要一定的时间
        //                 setTimeout(() => {
        //                     this.loading = false;
        //                 }, 400);
        //             }, 2000);
        //         })
        //         .catch(_ => { });
        // },
        /**
         * 确定按钮事件
         */
        cancelForm() {
            this.loading = false;
            this.dialog = false;
            clearTimeout(this.timer);
        },
        sale(){
            let book_ids=[];
            for(let saleBook of this.saleBooks){
                book_ids.push(saleBook.book_id);
            }

            //将订单信息封装到orderParams对象中
            let orderParam={"order":this.order,"book_ids":book_ids};

           this.$axios.post("order/addOrder",orderParam)
                .then(response=>{
                    this.$swal.fire({                                 
                                    icon: 'success',
                                    title: result.message,
                                    showConfirmButton: false,
                                    timer: 1000,//延迟关闭的时间
                                    didClose:()=>{
                                        this.loading = false;
                                        this.dialog = false;
                                        clearTimeout(this.timer);
                                    }
                                })
                })
                .catch(err=>{
                    console.log(err);
                });    
        }
    }, created() {
        this.saleBooks = this.$route.query.saleBooks;
        this.getSum();

    }

}


</script>

<style scoped>
.car-sum {
    margin: 20px auto;
    width: 100%;

    text-align: right;
    border: 0px solid red;
}
</style>