<template>
    <div>
      <table>
              <thead>
                  <tr>
                      <th>제목</th>
                      <th>작성일시</th>
                      <th>조회수</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="post in posts" :key="post.id">
                      <td>{{ post.title }}</td>
                      <td>{{ post.createDate }}</td>
                      <td>{{ post.view }}</td>
                  </tr>
              </tbody>
          </table>
         
    </div>
  </template>
  
<script>
import customAxios from "@/plugins/customAxios";
export default {
    name: "MainPage",
    data(){
        return {
            posts : [],
  
        };
    },
    methods: {
    
    },
    created() {
        customAxios.get("api/posts?page=1&size=5")
            .then(response => {
                this.posts= response;
            })
            .catch(error => {
                console.log(error);
            })
        ;
    }
    
  
};
</script>
  
  <style>
  
  </style>