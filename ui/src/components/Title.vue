<template>
  <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp" app color="blue darken-3" dark>
    <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
      <span class="hidden-sm-and-down">QualityCompendium</span>
    </v-toolbar-title>
    <v-text-field
      flat
      solo-inverted
      hide-details
      prepend-inner-icon="mdi-magnify"
      label="Filter (Under Construction)"
      v-bind:value="this.searchValue"
      class="hidden-sm-and-down"
    />
    <v-spacer />
    <!-- Update as API verifications -->
    <div v-if="apiHealthy">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-icon
            large
            color="green"
            v-on="on"
            v-bind:style="{cursor: selectedCursor}"
          >mdi-check-circle</v-icon>
        </template>
        <span>Connected to qc API</span>
      </v-tooltip>
    </div>
    <div v-if="!apiHealthy">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-icon
            large
            color="red"
            v-on="on"
            v-bind:style="{cursor: selectedCursor}"
          >mdi-check-circle</v-icon>
        </template>
        <span>Failed to Connect to qc API</span>
      </v-tooltip>
    </div>

    <div v-if="sonarHealthy">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-icon
            v-if="sonarHealthy"
            large
            color="green"
            v-on="on"
            v-bind:style="{cursor: selectedCursor}"
          >mdi-check-circle</v-icon>
        </template>
        <span v-if="sonarHealthy">QC API Connected to SonarQube</span>
      </v-tooltip>
    </div>

    <div v-if="!sonarHealthy">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-icon
            v-if="!sonarHealthy"
            large
            color="red"
            v-on="on"
            v-bind:style="{cursor: selectedCursor}"
          >mdi-check-circle</v-icon>
        </template>
        <span v-if="!sonarHealthy">QC API Disconnected from SonarQube</span>
      </v-tooltip>
    </div>

    <v-btn icon large>
      <v-avatar size="32px" item>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-img v-on="on" src="https://cdn.vuetifyjs.com/images/logos/logo.svg" alt="Vuetify" />
          </template>
          <span>Made with Vuetify</span>
        </v-tooltip>
      </v-avatar>
    </v-btn>
  </v-app-bar>
</template>

<script>
import axios from "axios";

export default {
  props: ['apiurl'],
  mounted() {
    // Local Testing

    axios.get(this.apiurl + "/actuator/health").then(response => {
      if (response.status == 200) {
        this.apiHealthy = true;
      } else {
        this.apiHealthy = false;
      }
    });

    axios
      .get(this.apiurl + "/api/v2/sonar-connection")
      .then(response => {
        if (response.status == 200) {
          this.sonarHealthy = true;
        } else {
          this.sonarHealthy = false;
        }
      })
      .catch(err => {
        /* eslint-disable no-console */
        console.log(err);
      });
  },
  data: () => ({
    apiHealthy: false,
    sonarHealthy: false,
    searchValue: null,
    sonarqubeURL: null,
    selectedCursor: "pointer"
  })
};
</script>