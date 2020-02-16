<template>
  <v-app>
    <v-content>
      <router-link :to="{ name: 'home' }"></router-link>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: "App",
  data: () => ({
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
