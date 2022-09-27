<template>
  <div id="app">
    <Blog/>
  </div>
</template>

<script>
import Blog from "@/components/Blog";
export default {
  name: 'App',
  components: {
    Blog
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
