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
      label="Filter"
      v-model="searchValue"
      @keyup.native="onChange"
      class="hidden-sm-and-down"
    />
    <v-spacer />
    <div class="text-center">
      <v-dialog v-model="addDialog" width="500">
        <template v-slot:activator="{ on }">
          <v-btn class="ma-2" color="primary" dark v-on="on">Add Config</v-btn>
        </template>
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>Add a ProjectPage Entry</v-card-title>
          <div class="ma-10">
            <v-text-field v-model="ppname" label="Project Page Name to Add"></v-text-field>
          </div>
          <div class="ma-10">
            <v-text-field v-model="ppkeys" label="Comma Seperated List of projectkeys"></v-text-field>
          </div>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text v-on:click="submitAdd" @click="addDialog = false">Submit</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
    <div class="text-center">
      <v-dialog v-model="deleteDialog" width="500">
        <template v-slot:activator="{ on }">
          <v-btn class="ma-2" color="primary" dark v-on="on">Delete Config</v-btn>
        </template>
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>Delete a ProjectPage Entry</v-card-title>
          <div class="ma-10">
            <v-text-field v-model="deleteppname" label="Project Page Name to Delete"></v-text-field>
          </div>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              v-on:click="submitDelete"
              @click="deleteDialog = false"
            >Submit</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
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
  props: ["apiurl", "searchfield"],
  mounted() {
    // Local Testing
    this.searchValue = this.searchfield;

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
  methods: {
    // eslint-disable-next-line no-unused-vars
    onChange(event) {
      this.$emit("filtered", this.searchValue);
    },

    submitAdd() {
      axios
        .post(this.apiurl + "/api/v2/addconfig", {
          pagename: this.ppname,
          projectKeys: this.ppkeys
        })
        .then(response => {
          console.log(response.status);
        });
    },

    submitDelete() {
      axios
        .post(this.apiurl + "/api/v2/deleteconfig", {
          pagename: this.deleteppname
        })
        .then(response => {
          console.log(response.status);
        });
    }
  },
  data: () => ({
    apiHealthy: false,
    sonarHealthy: false,
    searchValue: "",
    sonarqubeURL: null,
    selectedCursor: "pointer",
    addDialog: false,
    deleteDialog: false,
    ppname: "",
    ppkeys: "",
    deleteppname: ""
  })
};
</script>