<template>
  <v-container>
    <Title @filtered="onFilterChange" v-bind:apiurl="API_URL"/>
    <GroupCard v-for="group in groups" :key="group" api_url="" v-bind:projectName="group"/>
  </v-container>
</template>

<script>
import axios from "axios";
import GroupCard from "./GroupCard";
import Title from "../../components/Title";

export default {
  components: {
    GroupCard,
    Title
  },
  mounted() {
    // Make call to get project groups names
    axios.get(this.apiURL + "/api/v2/projectGroups")
    .then(response => {
      this.allgroups = response.data;
      this.groups = this.allgroups;
    })
  },
  methods: {
    onFilterChange(event) {
      this.filterContent = event
      this.groups = []
      for(let group in this.allgroups){
        if(this.allgroups[group].toUpperCase().includes(this.filterContent.toUpperCase())){
          this.groups.push(this.allgroups[group])
        }
      }
    }
  },
  data: () => ({
    allgroups: null,
    groups: null,
    filterContent: "",
    API_URL: "",
    apiURL: ""
  })
};
</script>