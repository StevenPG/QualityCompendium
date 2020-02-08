<template>
  <v-container>
    <GroupCard v-for="group in groups" :key="group" api_url="" v-bind:projectName="group"/>
  </v-container>
</template>

<script>
import axios from "axios";
import GroupCard from "./GroupCard";

export default {
  components: {
    GroupCard
  },
  mounted() {
    // Make call to get project groups names
    axios.get("/api/v2/projectGroups")
    .then(response => {
      this.groups = response.data;
    })
  },
  data: () => ({
    groups: null
  })
};
</script>