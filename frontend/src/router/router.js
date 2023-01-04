import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        name: "home",
        props: true,
        component: () => import("../components/blog/MainPage"),
    },
    {
        path: "/:postId",
        name: "Detail",
        component: () => import("../components/blog/PostDetail"),
        props: true
    },
    {  
        path: "/write",
        name: "Write",
        component: () => import("../components/blog/PostWrite"),
        props: true
    },
    {
        path: "/:postId",
        name: "Edit",
        component: () => import("../components/blog/PostEdit"),
        props: true
    },
    {
        path: "/*",
        name: "404",
        component: () => import("../components/exception/NotFound"),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;