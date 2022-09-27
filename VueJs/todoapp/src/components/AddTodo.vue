<template>
  <div>
    <div class="container">
      <br><br>
      <h1 class="text-center">Todos App</h1>
      <br><br>
      <form @submit="addTodo">
        <div class="form-group">
          <input type="text" v-model="title" placeholder="title" required class="form-control">
        </div>
        <div class="form-group">
            <button class="btn btn-danger btn-block">
                Add Todo
            </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name:'AddTodo',
    data(){
      return{
        title:""
      }
    },
    methods:{
      addTodo(e){
        e.preventDefault()

        const newTodo ={
          id: Date.now(),
          title:this.title,
          completed:false
        }

        // send event
        this.$emit("add-todo", newTodo)

        // clear out input field
        this.title =""
      }
    }
  }
</script>

<style scoped>

</style>