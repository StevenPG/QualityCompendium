<template>
  <v-container>
    <!-- TODO add filter in search bar at top, passed down that will only show found projects -->
    <!--<ProjectCard coveragePercentage="65" v-for="project in projects" apiURL="" :key="project.name" v-bind:projectName="project"/>-->
    <ProjectCard coveragePercentage="65" v-for="project in projects" apiURL="http://localhost:8080" :key="project.name" v-bind:projectName="project"/>
  </v-container>
</template>

<script>
import axios from "axios";
import ProjectCard from "./ProjectCard";

export default {
  components: {
    ProjectCard
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
      this.projects = response.data
    })

  },
  data: () => ({
    projects: null,
    groupName: null,
    //apiURL: ""
    apiURL: "http://localhost:8080"
  })
};
</script>