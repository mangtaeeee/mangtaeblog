import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        name: "home",
        component: () => import("../components/blog/MainPage"),
    },
    {
        path: "/:id",
        name: "Detail",
        component: () => import("../components/blog/PostDetail"),
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;