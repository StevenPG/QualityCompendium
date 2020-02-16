<template>
  <v-container>
    <Title @filtered="onFilterChange" v-bind:apiurl="API_URL"/>
    <ProjectCard coveragePercentage="65" v-for="project in projects" apiURL="" :key="project" v-bind:projectName="project"/>
  </v-container>
</template>

<script>
import axios from "axios";
import ProjectCard from "./ProjectCard";
import Title from "../../components/Title";

export default {
  components: {
    ProjectCard,
    Title
  },
  props: ['projectGroup'],
  created() {
    this.groupName = this.$route.params.groupName;
  },
  mounted() {
    // Make call to get project names
    axios
    .get(this.apiURL + "/api/v2/projectGroups/" + this.groupName + "/projectKeys")
    .then(response => {
      this.allprojects = response.data;
      this.projects = this.allprojects;
    })

  },
  methods: {
    onFilterChange(event) {
      this.filterContent = event
      this.projects = []
      for(let project in this.allprojects){
        if(this.allprojects[project].toUpperCase().includes(this.filterContent.toUpperCase())){
          this.projects.push(this.allprojects[project])
        }
      }
    }
  },
  data: () => ({
    allprojects: null,
    projects: null,
    groupName: null,
    filterContent: "",
    API_URL: "",
    apiURL: ""
  })
};
</script>