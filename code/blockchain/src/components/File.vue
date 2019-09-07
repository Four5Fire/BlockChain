<template>
  <div id="app">
    <el-row class="header">
      <el-col :span="2"><img src="../../static/logo.png" /></el-col>
      <el-col :offset="5" :span="10">
        <el-input v-model="filename" placeholder="请输入文件名">
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-container>
      <el-aside>
        <img src="../../static/avatar.png" />
        <div id="name">{{username}}</div>
        <div id="navs">
          <Nav label="我的文件" :isSelected="sel1" @setSelected="loadFile('own')"></Nav>
          <Nav label="上传文件" :isSelected="sel2" @setSelected="update"></Nav>
          <Nav label="查询文件" :isSelected="sel3" @setSelected="loadFile('overall')"></Nav>
        </div>
      </el-aside>
      <el-main>
        <div id="title"><img src="../../static/home.png"/>文件列表</div>
        <div id="files">
          <el-checkbox-group v-model="checkedFiles" @change="onChangeFiles">
            <el-checkbox v-for="item in files" :label="item.id" :key="item.id">
              <el-row class="file">
                <el-col :span="3"><img src="../../static/file.png"/></el-col>
                <el-col :span="3">{{item.fileName}}</el-col>
                <el-col :span="3">{{item.fileSize}}</el-col>
                <el-col :span="3">{{item.uploadTime}}</el-col>
                <el-col :span="3">
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
  import Nav from '../components/Nav';
  const URL='http://playcall.cn:8687/qingqingshare/file/';
    export default {
      name: "file",
      components:{Nav},
      data(){
        return {
          username: '',
          sel1:false,
          sel2:false,
          sel3:false,
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
            id:"4",
            fileName:"test.txt",
            fileSize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:0//0为不共享，1为共享
          },{
            id:"5",
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
        this.loadFile("own");
        this.sel1=true;
      },
      methods:{
        search(){
          console.log('1');
        },
        update(){
          console.log('update');
          this.sel1=false;
          this.sel2=true;
          this.sel3=false;
        },
        onChangeFiles(){
          console.log(this.checkedFiles);
        },
        loadFile(param){
          console.log("this.sel1:"+this.sel1);
          console.log("this.sel2:"+this.sel2);
          console.log("this.sel3:"+this.sel3);
          console.log(param);
          // axios({
          //   method: 'post',
          //   url: URL + 'showfile',
          //   data: {
          //     "username": this.name,
          //     "purview": param,
          //   },
          //   headers:{
          //     'Content-Type':'application/x-www-form-urlencoded'
          //   },
          // }).then((res) => {
          //   console.log(res);
          //   if (res.code === '200') {
          //     this.files = res.data.data;
          //   } else {
          //     this.$message.error(res.data.msg);
          //   }
          // }).catch((err)=>{
          //   console.log(err);
          //   this.$message.error(err.toString());
          // });
          if(param==='own'){
            this.sel1=true;
            this.sel2=false;
            this.sel3=false;
          }else{
            this.sel1=false;
            this.sel2=false;
            this.sel3=true;
          }
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
    width: 5.5rem;
    margin-top:1.5rem;
    margin-left: 6.4rem;
  }
  .header .el-input{
    margin-top: 2rem;
  }


  .el-aside img{
    width: 6rem;
    margin: 0 auto;
    display: block;
    margin-top: 7rem;
  }
  #name{
    margin: 0 auto;
    margin-top:10px;
    text-align: center;
  }
  #navs{
    margin-top: 80px;
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
    width: 100%;
    line-height: 4rem;
  }
  .file img{
    width: 4rem;
  }

</style>

<style>

  .el-checkbox{
    /*display: block;*/
    margin-left: 30px;
    display: flex;
  }
  .el-checkbox__input{
    vertical-align: middle;
    display: table-cell;

  }
  .el-checkbox__inner {
    border-radius: 50%;
  }
  .el-checkbox__label {
    width: 100%;
  }
</style>
