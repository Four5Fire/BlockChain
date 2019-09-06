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
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd" placeholder="请输入密码" show-password></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-lock" v-model="pswd2" placeholder="请再次输入密码" show-password></el-input></div>
              <div class="item"><el-input prefix-icon="el-icon-message" v-model="mail" placeholder="请输入邮箱"></el-input></div>
              <div class="item" align="center">
                <el-radio-group v-model="sex">
                  <el-radio-button label="男" border>男</el-radio-button>
                  <el-radio-button label="女" border>女</el-radio-button>
                </el-radio-group>
              </div>
              <div class="item">
                <el-radio v-model="grade" label="primary" size="medium">小学</el-radio>
                <el-radio v-model="grade" label="middle" size="medium">中学</el-radio>
                <el-radio v-model="grade" label="senior" size="medium">大学</el-radio>
              </div>
              <div class="item" align="center"><el-button type="primary" @click="register">注册并登录</el-button></div>
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
              <div class="item" align="center"><el-button type="primary" @click="reset">重置并登录</el-button></div>
            </div>
          </el-collapse-transition>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as axios from "axios";
  import qs from 'qs';
  const URL='http://playcall.cn:8687/qingqingshare/user/';
  export default {
    name: "welcome",
    data() {
      return {
        logi: true,
        regi: true,
        rese: true,
        type: '',//login,register,reset

        name: '',
        mail: '',
        pswd: '',
        pswd2: '',
        sex: '',
        grade: ''
      }
    },
    mounted() {
      this.type = 'login';
    },
    methods: {
      change: function (param) {
        this.name = this.mail = this.pswd = this.pswd2 = '';
        switch (this.type) {
          case 'login':
            this.logi = false;
            break;
          case 'register':
            this.regi = false;
            break;
          case 'reset':
            this.rese = false;
            break;
          default:
            break;
        }
        this.type = param;
      },

      login: function () {
        if (this.name === '') {
          this.$message.error('请输入用户名');
        } else if (this.pswd === '') {
          this.$message.error('请输入密码');
        } else {
          axios({
            method: 'post',
            url: URL + 'login',
            data: {
              "username": this.name,
              "password": this.pswd,
            },
            headers:{
              'Content-Type':'application/x-www-form-urlencoded'
            },
          }).then((res) => {
            console.log(res);
            if(res.code==='200') {
              this.$message.success('登录成功');
              this.$router.push({
                path: '/file',
                query: {
                  "username": this.name
                },
              });
            }else{
              this.$message.error(res.data.msg);
            }
          }).catch((err) => {
            console.log(err);
            this.$message.error(err.toString());
          });
        }
      },

      register: function () {
        console.log(this.sex);
        console.log(this.grade);
        const reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if (this.name === '' || this.name.length < 4 || this.name.length > 10) {
          this.$message.error('请输入长度为4-10的用户名');
        } else if (this.mail === '' || !reg.test(this.mail)) {
          this.$message.error('请输入正确格式的邮箱');
        } else if (this.pswd === '' || this.pswd.length < 8 || this.pswd.length > 16) {
          this.$message.error('请输入长度为8-16的密码');
        } else if (this.pswd !== this.pswd2) {
          this.$message.error('两次输入密码不一致');
        } else if (this.sex === '') {
          this.$message.error('请选择性别');
        } else if (this.grade === '') {
          this.$message.error('请选择年级');
        } else {
          const data={
            "username": this.name,
            "password": this.pswd,
            "emailAddress": this.mail,
            "sex": this.sex,
            "grade": this.grade,
          };
          axios({
            method: 'post',
            url: URL + 'regist',
            data: qs.stringify(data),
            headers:{
              'Content-Type':'multipart/form-data',
            },
            header:
              'Access-Control-Allow-Origin:*',

          }).then((res) => {
            console.log(res);
            if (res.code === '200') {
              this.$message.success('注册成功');
              this.login();
            } else {
              this.$message.error(res.data.msg);
            }
          }).catch((err) => {
            console.log(err);
            this.$message.error(err.toString());
          });
        }
      },

      reset: function () {
        if (this.name === '') {
          this.$message.error('请输入用户名');
        } else if (this.mail === '') {
          this.$message.error('请输入邮箱');
        } else if (this.pswd === '' || this.pswd.length < 8 || this.pswd.length > 16) {
          this.$message.error('请输入长度为8-16的密码');
        } else if (this.pswd !== this.pswd2) {
          this.$message.error('两次输入密码不一致');
        } else {
          axios({
            method: 'post',
            url: URL + 'regist',
            data: {
              "username": this.name,
              "emailAddress": this.mail,
              "password": this.pswd,
            }
          }).then((res) => {
            console.log(res);
            if (res.code === '200') {
              this.$message.success('密码重置成功');
              this.login();
            } else {
              this.$message.error(res.data.msg);
            }
          }).catch((err) => {
            console.log(err);
            this.$message.error(err.toString());
          });
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
    width: 30%;
    height: 100%;
    margin-right: 12%;
    float: right;
  }

  #text{
    margin-top: 10%;
    color: #fff;
    font-size: 4em;
    text-align: center;
    margin-bottom: 20px;
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
    padding-top:70px;
    padding-bottom:40px;
  }

  .form .item{
    width: 95%;
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
