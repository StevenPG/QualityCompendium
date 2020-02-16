<template>
  <v-row>
    <v-col cols="8" offset="2">
      <v-row>
        <v-card class="mx-auto elevation-4 ma-2" v-bind:loading="loading" raised>
          <div class="d-flex flex-no-wrap justify-lg-space-around">
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-4">{{ formattedProjectName }}</div>
                <div class="mb-4">{{ coveragePercentage }}</div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Status</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span v-bind:style="{ backgroundColor: releasabilityRatingColor}" class="quality-gate">{{ qualityGateStatus }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8" style="color: white">_</div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Reliability</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span class="rated" v-bind:style="{ backgroundColor: reliabilityRatingColor}">{{ reliabilityRatingAsLetter }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8" style="color: white">_</div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Security</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span class="rated" v-bind:style="{ backgroundColor: securityRatingColor}">{{ securityRatingAsLetter }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8">
                  <a v-bind:href="projectLink">SonarQube Project Link</a>
                </div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Maintainability</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span class="rated" v-bind:style="{ backgroundColor: maintainabilityRatingColor}">{{ maintainabilityRatingAsLetter }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </div>
        </v-card>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  props: ["projectName", "apiURL"],
  methods: {
    calculateReleasabilityAverage(average) {
      switch (Math.floor(average)) {
        case 1:
          return "A";
        case 2:
          return "B";
        case 3:
          return "C";
        case 4:
          return "D";
        case 5:
          return "E";
      }
    },
    calculateAlphabeticalRating(value) {
      switch (Math.floor(value)) {
        case 1:
          return "A";
        case 2:
          return "B";
        case 3:
          return "C";
        case 4:
          return "D";
        case 5:
          return "E";
      }
    },
    setReliabilityColor(value) {
      switch (value) {
        case "A":
          this.reliabilityRatingColor = "green"
          break;
        case "B":
          this.reliabilityRatingColor = "yellow"
          break;
        case "C":
          this.reliabilityRatingColor = "orange"
          break;
        case "D":
          this.reliabilityRatingColor = "orangered"
          break;
        case "E":
          this.reliabilityRatingColor = "red"
          break;
      }
    },
    setSecurityColor(value) {
      switch (value) {
        case "A":
          this.securityRatingColor = "green"
          break;
        case "B":
          this.securityRatingColor = "yellow"
          break;
        case "C":
          this.securityRatingColor = "orange"
          break;
        case "D":
          this.securityRatingColor = "orangered"
          break;
        case "E":
          this.securityRatingColor = "red"
          break;
      }
    },
    setMaintainabilityColor(value) {
      switch (value) {
        case "A":
          this.maintainabilityRatingColor = "green"
          break;
        case "B":
          this.maintainabilityRatingColor = "yellow"
          break;
        case "C":
          this.maintainabilityRatingColor = "orange"
          break;
        case "D":
          this.maintainabilityRatingColor = "orangered"
          break;
        case "E":
          this.maintainabilityRatingColor = "red"
          break;
      }
    }
  },
  // On mount of card, go out and grab the content, don't bring in as props
  mounted() {
    this.loading = true;

    // Shorten project names
    if(this.projectName.length > 29){
      this.formattedProjectName = this.projectName.substring(0, 29) + "..."
    } else {
      this.formattedProjectName = this.projectName
    }

    axios
      .get(this.apiURL + "/api/v2/sonar-host")
      .then(response => {
        this.projectLink = response.data + "/dashboard?id=" + this.projectName
      })

    // Make call
    axios
      .get(
        this.apiURL + "/api/v2/projectKeys/" +
          this.projectName +
          "/measures"
      )
      .then(response => {
        response.data.baseComponent.measures.forEach(measure => {
          if (measure.metric == "coverage") {
            this.coveragePercentage = measure.value + "% Coverage";
          }
          if (measure.metric == "security_rating") {
            this.securityRating = measure.value;
            this.securityRatingAsLetter = this.calculateAlphabeticalRating(
              measure.value
            );
            this.setSecurityColor(this.securityRatingAsLetter)
          }
          if (measure.metric == "reliability_rating") {
            this.reliabilityRating = measure.value;
            this.reliabilityRatingAsLetter = this.calculateAlphabeticalRating(
              measure.value
            );
            this.setReliabilityColor(this.reliabilityRatingAsLetter)
          }
          if (measure.metric == "sqale_rating") {
            this.maintainabilityRating = measure.value;
            this.maintainabilityRatingAsLetter = this.calculateAlphabeticalRating(
              measure.value
            );
            this.setMaintainabilityColor(this.maintainabilityRatingAsLetter)
          }
          if (measure.metric == "alert_status") {
            if(measure.value == "OK"){
              this.qualityGateStatus = "Passing"
              this.releasabilityRatingColor = "green"
            } else {
              this.qualityGateStatus = "Failing"
              this.releasabilityRatingColor = "red"
            }
          }
        });

        // Calculate Releasability
        this.releasabilityRating = this.calculateReleasabilityAverage(
          (parseFloat(this.securityRating) +
            parseFloat(this.reliabilityRating) +
            parseFloat(this.maintainabilityRating)) /
            3.0
        );
        this.loading = false;
      });
  },
  data: () => ({
    coveragePercentage: null,
    formattedProjectName: null,
    loading: false,
    releasabilityRating: null,
    reliabilityRating: null,
    securityRating: null,
    maintainabilityRating: null,
    reliabilityRatingAsLetter: null,
    securityRatingAsLetter: null,
    maintainabilityRatingAsLetter: null,
    qualityGateStatus: null,
    projectLink : null,
    releasabilityRatingColor: "gray",
    reliabilityRatingColor: "gray",
    securityRatingColor: "gray",
    maintainabilityRatingColor: "gray"
  })
};
</script>