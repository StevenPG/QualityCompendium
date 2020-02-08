<template>
  <v-app>
    <v-content>
      <Title v-bind:apiurl="API_URL"/>
      <router-link :to="{ name: 'home' }"></router-link>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import Title from "./components/Title";

export default {
  name: "App",
  components: {
    Title
  },
  data: () => ({
    API_URL: ""
  })
};
</script>

<style lang="scss">
.rated {
  display: block;
  height: 40px;
  width: 40px;
  line-height: 40px;
  border-radius: 20px;
  background-color: gray;
  color:white;
  text-align:center;
  font-weight: 500;
  font-size: 20px;
}

.quality-gate {
  display: block;
  height: 40px;
  width: 100px;
  line-height: 40px;
  border-radius: 10px;
  background-color: gray;
  color:white;
  text-align:center;
  font-weight: 500;
  font-size: 20px;
}
</style>
