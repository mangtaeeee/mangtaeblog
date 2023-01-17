<template>
  <div>
    <div id="header">
      <h3 class="center">전체글</h3>
    </div>
    <div id="body">
      <ul>
        <li v-for="post in posts" :key="post.id">
          <router-link :to="{name:'Detail',params:{postId:post.id}}">{{ post.title }}({{ post.view }})</router-link>
          {{ post.createDate }}
        </li>
      </ul>
    </div>

    <button>
      <router-link :to="{name:'Write'}">글쓰기</router-link>
    </button>
    <button>
      <router-link :to="{name:'Login'}">로그인</router-link>
    </button>

  </div>
</template>

<script>
import customAxios from "@/plugins/customAxios";


export default {
    name: "MainPage",
    data() {
        return {
            posts: [],
            comments: [],
        };
    },
    mounted() {
        customAxios.get("api/api/posts/list?page=1&size=5")
            .then(response => {
                this.posts = response;
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