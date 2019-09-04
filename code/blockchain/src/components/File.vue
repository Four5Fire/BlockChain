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
        <div id="title"><img src="../../static/home.png"/>文件列表</div>
        <div id="files">
          <el-checkbox-group v-model="checkedFiles" @change="onChangeFiles">
            <el-checkbox v-for="item in files" :label="item.id" :key="item.id">
              <el-row class="file">
                <el-col span="3"><img src="../../static/file.png"/></el-col>
                <el-col span="3">{{item.fileName}}</el-col>
                <el-col span="3">{{item.fileSize}}</el-col>
                <el-col span="3">{{item.uploadTime}}</el-col>
                <el-col span="3">
                  <div v-if="item.shareState===0">已共享</div>
                  <img style="width: 3rem" src="../../static/share.png" v-else />
                </el-col>
              </el-row>
            </el-checkbox>
          </el-checkbox-group>
        </div>

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
          files:[{
            id:"1",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:1//0为不共享，1为共享
          },{
            id:"2",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:0//0为不共享，1为共享
          },{
            id:"3",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:1//0为不共享，1为共享
          },{
            id:"2",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:0//0为不共享，1为共享
          },{
            id:"3",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:0//0为不共享，1为共享
          },],
          checkedFiles:[],
        }
      },
      mounted() {
        this.username = this.$route.query.username;
      },
      methods:{
        search(){
          console.log('1');
        },
        onChangeFiles(){
          console.log(this.checkedFiles);
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
  #title{
    line-height: 3rem;
  }
  #title img{
    width: 3rem;
  }
  #files{
  }
  .file{
    line-height: 4rem;
  }
  .file img{
    width: 4rem;
  }

</style>

<style>

  .el-checkbox{
    display: block;
    margin-left: 30px;
  }
  .el-checkbox__inner {
    display: block;
    line-height: 8rem;
    float: left;
    border-radius: 50%;
  }
</style>
