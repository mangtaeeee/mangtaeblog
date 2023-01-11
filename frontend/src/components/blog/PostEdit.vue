<template>
  <table>
    <thead>
    <tr>
      <th>제목</th>
      <th>내용</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><input type="text" v-model="postsList.title"></td>
      <td><input type="text" v-model="postsList.content"></td>
      <td>
        <button @click="PostEdit">수정 완료</button>
      </td>
    </tr>
    </tbody>

  </table>
</template>

<script>
import customAxios from "@/plugins/customAxios";

export default {
    data() {
        return {
            postsList: [],
        };
    },
    methods: {
        read() {
            const postId = this.$route.params.postId;
            customAxios.get("api/posts/" + postId)
                .then(response => {
                    this.postsList = response;
                }).catch(error => {
                    console.log(error);
                });
        },
        PostEdit() {
            const postId = this.$route.params.postId;
            customAxios.patch("api/posts/edit/" + postId, {
                title: this.postsList.title,
                content: this.postsList.content
            }).then(() => {
                this.$router.push({name: "Detail", params: {postId: postId}});
            });
        }
    },
    created() {
        this.read();
    },
};
</script>

<style>

</style>