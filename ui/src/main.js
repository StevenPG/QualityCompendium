import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';

import GroupCards from './components/project-groups/GroupCards'
import ProjectCards from "./components/projects/ProjectCards";
import VueRouter from "vue-router";

Vue.config.productionTip = false

Vue.use(VueRouter)

const routes = [
  { name: 'home', path: '/', component: GroupCards, props: true },
  { name: 'projects', path: '/:groupName/projects', component: ProjectCards,  props: true}
]

const router = new VueRouter({
  routes
})

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
