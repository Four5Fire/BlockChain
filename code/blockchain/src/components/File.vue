<template>
  <div id="app">
    <el-row class="header">
      <el-col :span="2"><img src="../../static/logo.png" /></el-col>
      <el-col :offset="5" :span="10">
        <el-input v-model="keyword" placeholder="请输入文件名">
          <el-button slot="append" icon="el-icon-search" @click="searchFile"></el-button>
        </el-input>
      </el-col>
    </el-row>
    <el-container>
      <el-aside>
        <img src="../../static/avatar.png" />
        <div class="name">{{username}}</div>
        <div id="navs">
          <Nav label="我的文件" :isSelected="sel1" @setSelected="loadFile('own')"></Nav>
          <Nav label="上传文件" :isSelected="sel2" @setSelected="update"></Nav>
          <Nav label="查询文件" :isSelected="sel3" @setSelected="loadFile('overall')"></Nav>
        </div>
      </el-aside>
      <el-main>
        <div v-if="sel2">
          <el-row class="up">
            <el-col :span="4">请输入标签：</el-col>
            <el-col :span="20"><el-input v-model="tags" placeholder="标签间以“,”分隔"></el-input></el-col>
          </el-row>
          <el-row class="up">
            <el-col :span="4">共享状态：</el-col>
            <el-col :span="20">
              <el-radio-group v-model="shareState">
                <el-radio-button label="1" border>共享</el-radio-button>
                <el-radio-button label="0" border>不共享</el-radio-button>
              </el-radio-group>
            </el-col>
          </el-row>
          <el-row class="up">
            <el-col :span="4">选择文件：</el-col>
            <el-col :span="20">
              <el-upload
                class="upload-demo"
                :before-upload="realUpload"
                drag
                action="suibianxieyige">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
            </el-col>
          </el-row>
        </div>
        <div v-else>
          <div id="title"><img :src="titlesrc"/>{{title}}</div>
          <div id="files">
            <div v-if="files.length<1">暂无文件记录</div>
            <div v-else class="file" @click="funcfile(item)" v-for="item in files" :label="item.id" :key="item.id">
              <el-row>
                <el-col :span="1" :offset="1"><img src="../../static/file.png"/></el-col>
                <el-col :span="5" class="file-info">{{item.filename}}</el-col>
                <el-col :span="5" class="file-info">{{item.filesize}}</el-col>
                <el-col :span="5" class="file-info">{{item.uploadTime}}</el-col>
                <el-col :span="5" class="file-info" v-if="sel1">
                  <div v-if="item.shareState===1" style="color: #606266;">已共享</div>
                  <img v-else src="../../static/share.png" @click="share" />
                </el-col>
              </el-row>
            </div>
          </div>
          <div id="btns" v-if="showBtn">
            <el-button type="primary" @click="del">删除</el-button>
            <el-button type="primary" @click="download">下载</el-button>
          </div>
        </div>
      </el-main>
      <el-aside v-if="showShare" style="font-size: 14px;">
        <img src="../../static/sharefile.png" />
        <div class="share">{{file.filename}}</div>
        <div class="share">{{file.filesize}}</div>
        <div class="share">{{file.uploadTime}}</div>
        <div class="share" style="margin-top: 20px;">
          <el-autocomplete
            v-model="searUser"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入用户名"
            @select="handleSelect"
          ></el-autocomplete>
          <el-button slot="append" icon="el-icon-share" type="primary" @click="shareUser"></el-button>
        </div>
        <div class="share" style="margin-top: 20px;"><el-button type="primary" @click="shareOpen">公开分享</el-button></div>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
  import Nav from '../components/Nav';
  import * as axios from 'axios';
  import qs from 'qs';
  const URL='http://playcall.cn:8687/qingqingshare/file/';
    export default {
      name: "file",
      components:{Nav},
      data(){
        return {
          username: '',
          keyword:'',

          sel1:false,
          sel2:false,
          sel3:false,
          shareState:-1,
          tags:'',

          title:'',
          titlesrc:'',

          showShare:false,
          showBtn:false,
          file:{},

          searUser:'',
          realUser:'',
          users: [],
          timeout:  null,

          files:[{
            id:"1",
            filename:"test.txt",
            filesize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:1//0为不共享，1为共享
          },{
            id:"2",
            filename:"test.txt",
            filesize:"13.2MB",
            uploadTime:"2019-04-28",
            shareState:0//0为不共享，1为共享
          },{
            id:"3",
            filename:"test.txt",
            filesize:"13.2MB",
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
        }
      },
      mounted() {
        this.username = this.$route.query.username;
        this.loadFile("own");
        this.loadAll();
      },
      methods:{
        searchFile() {
          let data =  {
            "keyword": this.keyword,
          };
          axios({
            method: 'post',
            url: URL + 'query',
            data:qs.stringify(data),
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
          }).then((res) => {
            console.log(res);
            if (res.data.code === 200) {
              this.$message.success('查询成功');
              this.sel1 = false;
              this.sel2 = false;
              this.sel3 = true;
              this.showShare = false;
              this.files = res.data.data;
            } else {
              this.$message.warning(res.data.msg);
            }
          }).catch((err) => {
            console.log(err);
            this.$message.error(err.toString());
          });
        },

        update(){
          this.sel1=false;
          this.sel2=true;
          this.sel3=false;
          this.showShare=false;
          this.showBtn=false;
        },

        realUpload(file) {
          if(this.tags===''){
            this.$message.error('请先为文件填写标签');
          }else if(this.shareState===-1){
            this.$message.error('请先选择上传状态');
          }else {
            let fd = new FormData();
            let url = URL + 'upload';
            fd.append('username', this.username);
            fd.append('file', file);
            fd.append('shareState', this.shareState);
            fd.append('tags', this.tags);
            axios.post(url, fd).then((res) => {
              console.log(res);
              if (res.data.code === 200) {
                this.$message.success("上传成功");
                this.sel2=false;
                this.sel1=true;
                this.loadFile('own');
              } else {
                this.$message.warning(res.data.msg);
              }
            }).catch((err) => {
              console.log(err);
              this.$message.error(err.toString());
            });
          }
        },

        loadFile(param){
          this.showBtn=false;
          if(param==='own'){
            this.sel1=true;
            this.sel2=false;
            this.sel3=false;
            this.title="文件列表";
            this.titlesrc="../../static/home.png";
          }else{
            this.sel1=false;
            this.sel2=false;
            this.sel3=true;
            this.title="查询结果";
            this.titlesrc="../../static/search.png";
            this.showShare=false;
          }
          // return ;
          let data = {
            "username": this.username,
            "purview": param,
          };
          axios({
            method: 'post',
            url: URL + 'showfile',
            data: qs.stringify(data),
            headers:{
              'Content-Type':'application/x-www-form-urlencoded'
            },
          }).then((res) => {
            console.log(res);
            if (res.data.code === 200) {
              this.files = res.data.data;
            } else {
              this.$message.warning(res.data.msg);
            }
          }).catch((err)=>{
            console.log(err);
            this.$message.error(err.toString());
          });
        },

        funcfile(param) {
          this.file=param;
          if (this.sel1) {
            this.showBtn = true;
            if (param.shareState === 1) {
              this.showShare = false;
            }
          }else if(this.sel3) {
            this.download();
          }
        },

        del() {
          this.$confirm('是否要删除文件' + this.file.filename, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(() => {
            let   data= {
                "username": this.username,
                "fileId": this.file.id,
              };
            axios({
              method: 'post',
              url: URL + 'delete',
              data:qs.stringify(data),
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
            }).then((res) => {
              console.log(res);
              if (res.data.code === 200) {
                this.$message.success('删除成功');
                this.showBtn = false;
                this.loadFile('own');
              } else {
                this.$message.warning(res.data.msg);
              }
            }).catch((err) => {
              console.log(err);
              this.$message.error(err.toString());
            }).catch(() => {
            });
          });
        },

        download() {
          this.$confirm('是否要下载文件' + this.file.filename, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(() => {
            let data = {
              // "username": this.username,
              "fileId": this.file.id,
            };
            console.log(data);
            axios({
              method: 'post',
              url: URL + 'download',
              data: qs.stringify(data),
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              },
            }).then((res) => {
              console.log(res);
              if (res.data.code === 200) {
                this.$message.success('下载成功');
                this.showBtn = false;
              } else {
                this.$message.warning(res.data.msg);
              }
            }).catch((err) => {
              console.log(err);
              this.$message.error(err.toString());
            }).catch(() => {
            });
          });
        },

        share(){
          this.showShare = true;
          this.showBtn=false;
        },

        loadAll() {
          let data = {
            "username": '',
            only:false,
          };
          axios({
            method: 'post',
            url: 'http://playcall.cn:8687/qingqingshare/user/query',
            data: qs.stringify(data),
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
          }).then((res) => {
            console.log(res);
            if (res.data.code === 200) {
              for(let i=0;i<res.data.data.length;i++) {
                let tmp={
                  value:res.data.data[i].username,
                };
                this.users[i]=tmp;
              }
              console.log(this.users);
            } else {
              this.$message.warning(res.data.msg);
            }
          }).catch((err) => {
            console.log(err);
            this.$message.error(err.toString());
          })
        },

        querySearchAsync(queryString, cb) {
          var users = this.users;
          var results = queryString ? users.filter(this.createUserFilter(queryString)) : users;

          clearTimeout(this.timeout);
          this.timeout = setTimeout(() => {
            cb(results);
          }, 3000 * Math.random());
        },
        createUserFilter(queryString) {
          return (searUser) => {
            return (searUser.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
          };
        },
        handleSelect(item) {
          this.realUser=item.value;
        },

        shareOpen(){
        },

        shareUser(){
          if(this.realUser===this.searUser && this.realUser!==''){

          }else{
            this.$message.warning('请选择分享对象');
          }
        },

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
    margin-top: 5rem;
    margin-bottom: 10px;
  }
  .name{
    margin: 0 auto;
    text-align: center;
  }
  #navs{
    margin-top: 80px;
  }

  .up{
    line-height: 40px;
    font-size: 14px;
    color: #606266;
    margin-top: 20px;
  }
  .el-icon-upload{
    margin-top:120px;
  }

  .el-main{
    border-left: 1px solid rgb(197,197,197);
    border-right: 1px solid rgb(197,197,197);
  }
  #title{
    line-height: 2rem;
    color: #606266;
    margin-bottom: 1rem;
  }
  #title img{
    width: 2rem;
    margin-right:1rem;
  }

  #files{
    background-color: #f6f6f6;
    padding: 20px;
  }
  .file{
    width: 100%;
    line-height: 2rem;
    margin-bottom: 5px;
  }
  .file :hover{
    background-color: rgb(229,243,255);
    cursor: pointer;
  }
  .file-info{
    color: #606266;
  }
  .file img{
    width: 2rem;
  }
  #btns{
    margin-top: 20px;
  }

  .share{
    margin: 0 auto;
    text-align: center;
    color: #606266;
    font-size: 12px;
  }
  .el-select{
    margin: ;
  }

</style>

<style>
  .el-upload-dragger{
    margin-top: 10px;
    width: 300%;
    height: 300px;
  }
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
