<template>
  <v-row>
    <v-col cols="8" offset="2">
      <v-row>
        <v-card
          class="mx-auto elevation-8 ma-2 hover"
          min-width="100%"
          v-bind:loading="loading"
          v-bind:style="{cursor: selectedCursor}"
          raised
        >
          <router-link
            :to="{name: 'projects', params: {groupName: this.projectGroup}}"
            tag="div"
            v-bind:projectGroup="this.projectGroup"
            class="d-flex flex-no-wrap justify-lg-space-around"
          >
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-4">{{ projectName }}</div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Quality</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span
                    class="rated"
                    v-bind:style="{ backgroundColor: releasabilityRatingColor}"
                  >{{ releasabilityRating }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8"></div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Reliability</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span
                    class="rated"
                    v-bind:style="{ backgroundColor: reliabilityRatingColor}"
                  >{{ reliabilityRating }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8"></div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Security</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span
                    class="rated"
                    v-bind:style="{ backgroundColor: securityRatingColor}"
                  >{{ securityRating }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item three-line>
              <v-list-item-content>
                <div class="overline mb-8"></div>
                <v-list-item-title class="headline mb-1 d-flex justify-center">Maintainability</v-list-item-title>
                <v-list-item-subtitle class="d-flex justify-center">
                  <span
                    class="rated"
                    v-bind:style="{ backgroundColor: maintainabilityRatingColor}"
                  >{{ maintainabilityRating }}</span>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </router-link>
        </v-card>
      </v-row>

    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  props: ["projectName", "api_url"],
  created() {
    this.projectGroup = this.projectName;
    this.apiURL = this.api_url

  },
  mounted() {
    this.loading = true;

    axios
      .get(
        this.apiURL + "/api/v2/projectGroups/" +
          this.projectGroup +
          "/measures"
      )
      .then(response => {
        var securityCount = 0;
        var securitySum = 0;
        var reliabilityCount = 0;
        var reliabilitySum = 0;
        var maintainabilityCount = 0;
        var maintainabilitySum = 0;
        var qualityGatePassing = 0;
        var qualityGateFailing = 0;

        // Look at each response per card
        response.data.forEach(result => {
          result.baseComponent.measures.forEach(measure => {
            if (measure.metric == "security_rating") {
              securityCount = parseFloat(securityCount) + 1;
              securitySum = parseFloat(securitySum) + parseFloat(measure.value);
            }
            if (measure.metric == "reliability_rating") {
              reliabilityCount = parseFloat(reliabilityCount) + 1;
              reliabilitySum =
                parseFloat(reliabilitySum) + parseFloat(measure.value);
            }
            if (measure.metric == "sqale_rating") {
              maintainabilityCount = parseFloat(maintainabilityCount) + 1;
              maintainabilitySum =
                parseFloat(maintainabilitySum) + parseFloat(measure.value);
            }
            if (measure.metric == "alert_status") {
              if (measure.value == "OK") {
                qualityGatePassing = parseInt(qualityGatePassing) + 1;
              } else {
                qualityGateFailing = parseInt(qualityGateFailing) + 1;
              }
            }
          });
        });

        this.releasabilityRating = this.calculateReleasability(
          qualityGatePassing,
          qualityGateFailing
        );
        this.reliabilityRating = this.calculateReleasabilityAverage(
          reliabilityCount / reliabilitySum
        );
        this.securityRating = this.calculateReleasabilityAverage(
          maintainabilityCount / maintainabilitySum
        );
        this.maintainabilityRating = this.calculateReleasabilityAverage(
          securityCount / securitySum
        );

        this.setReliabilityColor(this.reliabilityRating);
        this.setSecurityColor(this.reliabilityRating);
        this.setMaintainabilityColor(this.reliabilityRating);

        this.loading = false;
      });
  },
  methods: {
    setReliabilityColor(rating) {
      switch (rating) {
        case "A":
          this.reliabilityRatingColor = "green";
          break;
        case "B":
          this.reliabilityRatingColor = "yellow";
          break;
        case "C":
          this.reliabilityRatingColor = "orange";
          break;
        case "D":
          this.reliabilityRatingColor = "orangered";
          break;
        case "E":
          this.reliabilityRatingColor = "red";
          break;
      }
      return rating;
    },
    setSecurityColor(rating) {
      switch (rating) {
        case "A":
          this.securityRatingColor = "green";
          break;
        case "B":
          this.securityRatingColor = "yellow";
          break;
        case "C":
          this.securityRatingColor = "orange";
          break;
        case "D":
          this.securityRatingColor = "orangered";
          break;
        case "E":
          this.securityRatingColor = "red";
          break;
      }
      return rating;
    },
    setMaintainabilityColor(rating) {
      switch (rating) {
        case "A":
          this.maintainabilityRatingColor = "green";
          break;
        case "B":
          this.maintainabilityRatingColor = "yellow";
          break;
        case "C":
          this.maintainabilityRatingColor = "orange";
          break;
        case "D":
          this.maintainabilityRatingColor = "orangered";
          break;
        case "E":
          this.maintainabilityRatingColor = "red";
          break;
      }
      return rating;
    },
    calculateReleasabilityAverage(average) {
      switch (Math.floor(average)) {
        case 1:
          this.releasabilityRatingColor = "green";
          return "A";
        case 2:
          this.releasabilityRatingColor = "yellow";
          return "B";
        case 3:
          this.releasabilityRatingColor = "orange";
          return "C";
        case 4:
          this.releasabilityRatingColor = "orangered";
          return "D";
        case 5:
          this.releasabilityRatingColor = "red";
          return "E";
      }
    },
    calculateReleasability: function(totalPassing, totalFailing) {
      // Handle divide by zero dumbness
      if (totalPassing + totalFailing == 0) {
        return "-";
      } else {
        var totalProjects = totalPassing + totalFailing;
        var projectRatio = totalPassing / totalProjects;
        if (projectRatio > 0.8) {
          return "A";
        } else if (projectRatio > 0.6) {
          return "B";
        } else if (projectRatio > 0.4) {
          return "C";
        } else if (projectRatio > 0.2) {
          return "D";
        } else if (projectRatio <= 0.2) {
          return "E";
        } else {
          return "Unable to Calculate Ratio";
        }
        // A: > 80%
        // B: > 60%
        // C: > 40%
        // D: > 20%
        // E: <= 20%
      }
    },
    calculateRating: function(incoming_integer) {
      switch (incoming_integer) {
        case "1.0":
          return "A";
        case "2.0":
          return "B";
        case "3.0":
          return "C";
        case "4.0":
          return "D";
        case "5.0":
          return "E";
      }
    }
  },
  data: () => ({
    releasabilityRating: null,
    reliabilityRating: null,
    securityRating: null,
    maintainabilityRating: null,
    qualityGateStatus: null,
    loading: false,
    projectGroup: null,
    selectedCursor: "pointer",
    releasabilityRatingColor: "gray",
    securityRatingColor: "gray",
    maintainabilityRatingColor: "gray",
    reliabilityRatingColor: "gray",
    apiURL: null
  })
};
</script>