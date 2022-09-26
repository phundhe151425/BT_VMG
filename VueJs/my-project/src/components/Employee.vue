<template>

      <el-table
        :data="employees"
        border
        style="width: 100%">
        <el-table-column
          prop="id"
          label="ID"
          >
        </el-table-column>
        <el-table-column
          prop="firstName"
          label="FirstName"
          >
        </el-table-column>
        <el-table-column
          prop="lastName"
          label="LastName"
          >
        </el-table-column>
        <el-table-column
          prop="email"
          label="Email"
          >
        </el-table-column>
      </el-table>
</template>

<script>

import EmployeeService from '../services/EmployeeService';
export default{
    name: '',
    data(){
        return{
            employees : []
        }

    },
  methods :{
        getEmployees(){
            console.log("start")
            EmployeeService.getEmployees().then((response) => {
                console.log(response)
                this.employees = response.data;
            })
        }
    },
    created(){
        this.getEmployees()
    }
}
</script>

<style>

</style>
