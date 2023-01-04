<template>
  <div>
        <h3 class="center">전체글</h3> <button><router-link :to="{name:'Write'}">글쓰기</router-link></button>
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
                      <td><router-link :to="{name:'Detail',params:{postId:post.id}}">{{ post.title }}</router-link></td>
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
            comments: [],
        };
    },
    mounted() {
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