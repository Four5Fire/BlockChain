<template>
  <div id="nav" @click="changeStyle">
    <div id="square" :style="'background-color:'+ color">{{label}}</div>
    <div id="triangle" :style="'borderLeftColor:'+color"></div>
  </div>
</template>

<script>
    export default {
      name: "Nav",
      props:{
        label:String,
        isSelected:Boolean,
      },
      data(){
        return{
          color:'#234372'
        }
      },
      methods:{
        changeStyle(){
          this.$emit('setSelected');
        }
      },
      watch:{
        isSelected(val){
          if(val===true){
            this.color='#3a76d2';
          }else{
            this.color='#234372';
          }
        }
      }

    }
</script>

<style scoped>
  #nav :hover{
    cursor: pointer;
  }
  #square{
    margin-top: 20px;
    height: 50px;
    width: 120px;
    text-align: center;
    line-height: 50px;
    color: white;
    font-weight: bold;
  }
  #triangle{
    border-top:25px solid transparent;
    border-left: 50px solid;
    border-bottom:25px solid transparent;
    margin-left: 120px;
    margin-top:-50px;
  }
</style>
