<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  *{margin:0;padding:0}

  #app {
    position: absolute;
    width: 100%;
    height: 100%;
    padding: 0;
    overflow: hidden;
  }
</style>
