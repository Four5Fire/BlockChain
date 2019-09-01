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
          this.logi=true;
          this.regi=true;
          this.rese=true;
          this.name=this.mail=this.pswd=this.pswd='';
          switch (this.type){
            case 'login':this.logi=false;break;
            case 'register':this.regi=false;break;
            case 'reset':this.rese=false;break;
            default:break;
          }
          this.type = param;
        },
        login: function () {
          this.$router.push({path: '/File'});
        },
        register:function(){

        },
        reset:function () {

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
    width: 40%;
    height: 100%;
    margin-right: 10%;
    float: right;
  }

  #text{
    margin-top: 10%;
    color: #fff;
    font-size: 4em;
    text-align: center;
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
    padding-top:50px;
    padding-bottom:50px;
  }

  .form .item{
    margin: 0 auto;
    margin-top:10px;
    margin-bottom:20px;
  }

  .item .link{
    margin: 0 auto;
    text-decoration: underline;
    text-align: center;
    font-size: 12px;
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
