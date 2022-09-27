<template>
    <el-table
        :data="blogs"
        style="width: 100%"
        :row-class-name="tableRowClassName">
      <el-table-column
          prop="id"
          label="ID"
          width="180">
      </el-table-column>
      <el-table-column
          prop="title"
          label="Title"
          width="180">
      </el-table-column>
      <el-table-column
          prop="author.name"
          label="Author">
      </el-table-column>
      <el-table-column
          v-slot="scope"
          label="Cover">
        <template  >
            <img v-for="(cover, index) in scope.row.covers" :key="index" v-bind:src="'http://localhost:8080/image/' + cover.name" width="50px">
        </template>
      </el-table-column>
      <el-table-column
          prop="content"
          label="Content">
      </el-table-column>
      <el-table-column
          prop="category.name"
          label="Category">
      </el-table-column>

    </el-table>



</template>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>

<script>
import BlogService  from "@/services/BlogService";

export default {
  name: 'app',
  data(){
    return{
      blogs : []
    }
  },
  methods:{
    getBlogs(){
      BlogService.getBlogs().then((response)=>{
        console.log(response.data)
        this.blogs  = response.data
      })
    },
    handleClick() {
      console.log('click');
    },
    tableRowClassName({rowIndex}) {
      if (rowIndex % 2 == 0) {
        return 'warning-row';
      }
      return '';
    }

  },
  created(){
    this.getBlogs()
  }
}


</script>

