<template>
  <div id="app">
    <div id="right">
      <div id="text">Welcome</div>
      <div id="border"></div>
      <div id="forms">
        <div v-if="logi===true">
          <el-collapse-transition>
            <div class="form" v-show="type==='login'">
              <div class="item"><el-input prefix-icon="el-icon-user" v-model="name" placeholder="请输入用户名"></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd" placeholder="请输入密码" show-password></el-input></div>
              <div class="item" align="center"><el-button type="primary" @click="login">登录</el-button></div>
              <div class="item"><div class="link" @click="change('register')">点击注册</div></div>
              <div class="item"><div class="link" @click="change('reset')">找回密码</div></div>
            </div>
          </el-collapse-transition>
        </div>
        <div v-if="regi===true">
          <el-collapse-transition>
            <div class="form" v-show="type==='register'">
              <div class="item"><el-input prefix-icon="el-icon-user" v-model="name" placeholder="请输入用户名"></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-message" v-model="mail" placeholder="请输入邮箱"></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd" placeholder="请输入密码" show-password></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd2" placeholder="请再次输入密码" show-password></el-input></div>
              <div class="item" align="center"><el-button type="primary" @click="register">注册</el-button></div>
            </div>
          </el-collapse-transition>
        </div>
        <div v-if="rese===true">
          <el-collapse-transition>
            <div class="form" v-show="type==='reset'">
              <div class="item"><el-input prefix-icon="el-icon-user" v-model="name" placeholder="请输入用户名"></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-message" v-model="mail" placeholder="请输入邮箱"></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd" placeholder="请输入密码" show-password></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd2" placeholder="请再次输入密码" show-password></el-input></div>
              <div class="item" align="center"><el-button type="primary" @click="reset">重置密码</el-button></div>
            </div>
          </el-collapse-transition>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  // import * as axios from "axios";
    const URL='';
    export default {
      name: "Welcome",
      data() {
        return {
          logi: true,
          regi:true,
          rese:true,
          type: '',//login,register,reset

          name:'',
          mail:'',
          pswd:'',
          pswd2:'',
        }
      },
      mounted() {
        this.type='login';
      },
      methods: {
        change: function (param) {
          // this.logi=true;
          // this.regi=true;
          // this.rese=true;
          this.name=this.mail=this.pswd=this.pswd2='';
          switch (this.type){
            case 'login':this.logi=false;break;
            case 'register':this.regi=false;break;
            case 'reset':this.rese=false;break;
            default:break;
          }
          this.type = param;
        },

        login: function () {
          if(this.name===''){
            this.$message.error('请输入用户名');
          }else if(this.pswd===''){
            this.$message.error('请输入密码');
          }else {
            this.$message.success('登录成功');
            this.$router.push({path: '/File'});
            //   axios({
            //     method: 'post',
            //     url: URL+'login',
            //     data:{
            //       "username":this.name,
            //       "password":this.pswd,
            //     }
            //   }).then((res) => {
            //     this.$message.success('登录成功');
            //     this.$router.push({path: '/File'});
            //   }).catch((err)=>{
            //     console.log(err);
            //     this.$message.error(err.msg);
            //   });
          }
        },

        register:function(){
          const reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
          if(this.name===''||this.name.length<4||this.name.length>10){
            this.$message.error('请输入长度为4-10的用户名');
          }else if(this.mail===''||reg.test(this.mail)){
            this.$message.error('请输入正确格式的邮箱');
          }else if(this.pswd===''||this.pswd.length<8||this.pswd.length>16){
            this.$message.error('请输入长度为8-16的密码');
          }else if(this.pswd!==this.pswd2) {
            this.$message.error('两次输入密码不一致');
          }else {
            this.$message.success('注册成功');
            this.login();
          //   axios({
          //     method: 'post',
          //     url: URL + 'regist',
          //     data: {
          //       "username": this.name,
          //       "password": this.pswd,
          //       "emailAddress":this.mail,
          //     }
          //   }).then((res) => {
          //     console.log(res);
          //     if(res.code==='200') {
          //       this.$message.success('注册成功');
          //       this.change('login');
          //     }else{
          //       this.$message.error(res.msg);
          //     }
          //   }).catch((err) => {
          //     console.log(err);
          //     this.$message.error(err.msg);
          //   });
          }
        },

        reset:function () {
          if(this.name===''){
            this.$message.error('请输入用户名');
          }else if(this.mail===''){
            this.$message.error('请输入邮箱');
          }else if(this.pswd===''||this.pswd.length<8||this.pswd.length>16){
            this.$message.error('请输入长度为8-16的密码');
          }else if(this.pswd!==this.pswd2) {
            this.$message.error('两次输入密码不一致');
          }else {
            this.$message.success('密码重置成功');
            this.login();
          //   axios({
          //     method: 'post',
          //     url: URL + 'regist',
          //     data: {
          //       "username": this.name,
          //       "emailAddress":this.mail,
          //       "password": this.pswd,
          //     }
          //   }).then((res) => {
          //     console.log(res);
          //     if(res.code==='200') {
          //       this.$message.success('密码重置成功');
          //       this.change('login');
          //     }else{
          //       this.$message.error(res.msg);
          //     }
          //   }).catch((err) => {
          //     console.log(err);
          //     this.$message.error(err.msg);
          //   });
          }
        }
      }
    }
</script>

<style scoped>
  #app{
    background: url("../../static/back.png");
    background-repeat: no-repeat;
    background-size:100% 100%;
  }

  #right{
    width: 27%;
    height: 100%;
    margin-right: 12%;
    float: right;
  }

  #text{
    margin-top: 10%;
    color: #fff;
    font-size: 4em;
    text-align: center;
    font-family:"MV Boli";
  }

  #border{
    background: url("../../static/border.png");
    background-repeat: no-repeat;
    background-size:100% 100%;
    height: 60px;
  }

  #forms{
    background: url("../../static/formbg.png");
    background-repeat: no-repeat;
    background-size:100% 100%;
    width: 70%;
    margin:0 auto;
    margin-top:-30px;
  }

  .form{
    transition: 3s;
    width: 80%;
    margin: 0 auto;
    padding:20px;
    padding-top:80px;
    padding-bottom:50px;
  }

  .form .item{
    width: 90%;
    margin: 0 auto;
    margin-top:10px;
    margin-bottom:20px;
  }

  .item .link{
    margin: 0 auto;
    text-decoration: underline;
    text-align: center;
    font-size: 13px;
  }

  .item .link:hover{
    cursor: pointer;
  }
</style>

<style>
  .el-input__inner{
    background: rgba(0,0,0,0);
    border: 0;
    border-radius: 0;
    border-bottom: 1px solid rgb(187,187,187);

  }

  .el-input__inner :focus{
    background: rgba(0,0,0,0);
  }
</style>
