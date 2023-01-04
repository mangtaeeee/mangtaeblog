<template>
    <div>
      <table>
              <thead>
                  <tr>
                      <th>제목</th>
                      <th>내용</th>
                      <th>작성일시 </th>
                      <th>조회수 </th>
                  </tr>
              </thead>
              <tbody>
                <tr>
                      <td>{{ postsList.title }}</td>
                      <td>{{ postsList.content }}</td>
                      <td>{{ postsList.createDate }}</td>
                      <td>{{ postsList.view }}</td>
                      <td><button @click="EditPost">글 수정</button></td>
                      <td><button @click="DeletePost">글 삭제</button></td>
                </tr>
              </tbody>
              <h1>댓글</h1>
              <tfoot>
                <tr>
                    <td>내용:<input type="text" v-model="CommentCreate.content" /></td>
                    <td>작성자:<input type="text" v-model="CommentCreate.memberId"/></td>
                    <button @click="CommentWrite">작성</button>
                </tr>
                <tr v-for="cm in comment" :key="cm.id">
                    <td>내용 :{{ cm.content }}</td><br />
                    <td>작성자 :{{ cm.userId }}</td><br />
                    <td>작성일 :{{ cm.createDate }}</td>
                    <td><button @click="DeleteComment(cm.id)">댓글삭제</button></td>
                </tr>
            </tfoot>
          </table>
    </div>
  </template>
  
<script>
import customAxios from "@/plugins/customAxios";


export default {
    name: "PostDetail",
    data(){
        return {
            postsList : [],
            comment: [],
            CommentCreate:[{
                content : "",
                memberId: "",
            }]
        };
    },
    methods: {
        read() {
            const postId = this.$route.params.postId;
            customAxios.get("api/posts/"+postId)
                .then(response => {
                    this.postsList = response;
                    this.comment = response.comments;
                }).catch(error => {
                    console.log(error.response.data.validation.content);
                });
        },

        DeletePost() {
            const postId = this.$route.params.postId;
            customAxios.delete("api/posts/"+postId)
                .then(() => {
                    alert("글이 삭제되었습니다.");
                    this.$router.push( {name : "home"}); 
                });
         
        },
        EditPost(){
            const postId = this.$route.params.postId;
            this.$router.push({ name: "Edit", params: { postId: postId } });
        },

        CommentWrite(){
            const postId = this.$route.params.postId;
            customAxios.post("api/posts/"+postId+"/comments",{
                content : this.CommentCreate.content,
                memberId : this.CommentCreate.memberId
            })
                .then(() => {
                    this.$router.go(0);
                })
                .catch((error => {
                    alert(error.response.data.validation.content);
                }));
        },
    
        DeleteComment(commentId) {
            const postId = this.$route.params.postId;
            customAxios.delete("api/posts/"+postId+"/comments/"+ commentId)
                .then(() => {
                    alert("댓글이 삭제되었습니다.");
                    this.$router.go(0);
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