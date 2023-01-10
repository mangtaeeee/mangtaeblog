<template>
  <div>
    <h1>제목</h1>
    <input type="text" v-model="PostCreate.title">
    <h1>내용</h1>
    <input type="text" v-model="PostCreate.content">
    <h1>작성자</h1>
    <input type="text" v-model="PostCreate.memberId">
    <button @click="write">저장</button>
  </div>
  
</template>

<script>
import customAxios from "@/plugins/customAxios";
export default {

    name:"PostWrite",
    data() {
        return {
            PostCreate:[{
                title: "",
                content: "",
                memberId:"",
            }],
        };
    },
    methods: {
        write(){
            customAxios.post("api/posts/post",{
                title : this.PostCreate.title,
                content : this.PostCreate.content,
                memberId : this.PostCreate.memberId
            })
                .then(() => {
                    this.$router.push( {name : "MainPage"});
                })
                .catch(error => {
                    alert(error.response.data.message);
                    
                });
        }
    }

};
</script>

<style>

</style>