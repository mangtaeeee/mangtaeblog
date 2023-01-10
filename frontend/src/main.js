import { createApp } from "vue";
import store from "@/scripts/store";
import App from "./App.vue";
import Router from "./router/router";


const app = createApp(App);

app.use(Router).use(store);
app.mount("#app");
