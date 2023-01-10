<template>
  <div class="form-signin w-100 m-auto">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
             v-model="state.form.userId">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
             v-model="state.form.password">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" @click="submit()">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
    <router-link to="/login" v-if="!$store.state.account.id">로그인</router-link>
    <a to="/login" @click="logout()" v-else>로그아웃</a>
  </div>
</template>

<script>
import axios from "axios";
import {reactive} from "vue";
import store from "@/scripts/store";
import router from "@/router/router";

export default {
    setup() {
        const state = reactive({
            form: {
                userId: "",
                password: ""
            }
        });

        const logout = () => {//eslint-disable-line no-unused-vars
            store.commit("setAccount", 0);
            router.push({path: "/"});
        };

        const submit = () => {
            axios.post("/api/member/login", state.form)
                .then((res => {
                    store.commit("setAccount", res.data);
                    window.alert("로그인 하였습니다.");
                }));
        };
        return {state, submit, logout};
    }
};

</script>

<style scoped>

.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

</style>