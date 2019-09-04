<template>
  <div id="app">
    <el-row class="header">
      <el-col :offset="1" :span="2"><img src="../../static/logo.png" /></el-col>
      <el-col :offset="4" :span="10">
        <el-input v-model="filename" placeholder="请输入文件名">
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-container>
      <el-aside>
        <img src="../../static/avatar.png" />
        <div id="name">{{username}}</div>
        <div id="square">我的文件</div><div id="triangle"></div>
      </el-aside>
      <el-main>
      aaa
      </el-main>
      <el-aside>
        aa
      </el-aside>
    </el-container>
  </div>
</template>

<script>
    export default {
      name: "file",
      data(){
        return {
          username: '',
          filename:'',
        }
      },
      mounted() {
        this.username = this.$route.query.username;
      },
      methods:{
        search(){
          console.log('1');
        }
      }
    }</script>

<style scoped>
  .el-container{
    height: 100%;
  }

  .header{
    height: 6rem;
    overflow: hidden;
    background-color: rgb(37,56,114);
  }
  .header img{
    width: 6rem;
  }
  .header .el-input{
    margin-top: 2rem;
  }

  .el-aside{
    width: 200px;
  }
  .el-aside img{
    width: 6rem;
    margin: 0 auto;
    display: block;
    margin-top: 7rem;
  }
  #name{
    margin: 0 auto;
    text-align: center;
  }
  #square{
    margin-top:10rem;
    background-color: rgb(35,67,114);
    height: 50px;
    width: 120px;
    text-align: center;
    line-height: 50px;
    color: white;
    font-weight: bold;
  }
  #triangle{
    border-top:25px solid transparent;
    border-left: 50px solid rgb(35,67,114);
    border-bottom:25px solid transparent;
    margin-left: 120px;
    margin-top:-50px;
  }

  .el-main{
    border-left: 1px solid rgb(197,197,197);
    border-right: 1px solid rgb(197,197,197);
  }
</style>
