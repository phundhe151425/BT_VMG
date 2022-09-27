<template>
  <div>
    <div :key="todo.id" v-for="todo in todos">
      <TodoItem v-bind:todos="todos" v-on:delete-todo="$emit('delete-todo', todo.id)" v-bind:todo="todo"/>
    </div>
  </div>
</template>

<script>
import TodoItem from "@/components/TodoItem";


export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Todos',
  props:['todos'],
  components:{TodoItem}
}
</script>

<style scoped>

</style>