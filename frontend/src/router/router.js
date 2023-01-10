import { createRouter, createWebHistory } from "vue-router";
import MainPage from "@/components/blog/MainPage.vue";
import LoginPage from "@/components/login/LoginPage.vue";
import PostDetail from "@/components/blog/PostDetail.vue";
import PostWrite from "@/components/blog/PostWrite.vue";
import PostEdit from "@/components/blog/PostEdit.vue";

const routes = [
    {   path: "/", name: "MainPage",component: MainPage},
    {   path: "/login", name: "Login", component: LoginPage},
    {   path: "/:postId", name: "Detail", component:PostDetail, props: true},
    {   path: "/write", name: "Write", component: PostWrite, props: true},
    {   path: "/:postId", name: "Edit", component: PostEdit, props: true},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;