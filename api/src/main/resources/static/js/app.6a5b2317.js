(function(t){function e(e){for(var i,n,s=e[0],o=e[1],c=e[2],p=0,d=[];p<s.length;p++)n=s[p],Object.prototype.hasOwnProperty.call(r,n)&&r[n]&&d.push(r[n][0]),r[n]=0;for(i in o)Object.prototype.hasOwnProperty.call(o,i)&&(t[i]=o[i]);u&&u(e);while(d.length)d.shift()();return l.push.apply(l,c||[]),a()}function a(){for(var t,e=0;e<l.length;e++){for(var a=l[e],i=!0,s=1;s<a.length;s++){var o=a[s];0!==r[o]&&(i=!1)}i&&(l.splice(e--,1),t=n(n.s=a[0]))}return t}var i={},r={app:0},l=[];function n(e){if(i[e])return i[e].exports;var a=i[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,n),a.l=!0,a.exports}n.m=t,n.c=i,n.d=function(t,e,a){n.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},n.t=function(t,e){if(1&e&&(t=n(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(n.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var i in t)n.d(a,i,function(e){return t[e]}.bind(null,i));return a},n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,"a",e),e},n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},n.p="/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],o=s.push.bind(s);s.push=e,s=s.slice();for(var c=0;c<s.length;c++)e(s[c]);var u=o;l.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var i=a("2b0e"),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app",[a("v-content",[a("router-link",{attrs:{to:{name:"home"}}}),a("router-view")],1)],1)},l=[],n={name:"App",data:function(){return{}}},s=n,o=(a("5c0b"),a("2877")),c=a("6544"),u=a.n(c),p=a("7496"),d=a("a75b"),g=Object(o["a"])(s,r,l,!1,null,null,null),v=g.exports;u()(g,{VApp:p["a"],VContent:d["a"]});var y=a("f309");i["a"].use(y["a"]);var b=new y["a"]({}),m=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("Title",{attrs:{apiurl:t.API_URL},on:{filtered:t.onFilterChange}}),t._l(t.groups,(function(t){return a("GroupCard",{key:t,attrs:{api_url:"",projectName:t}})}))],2)},h=[],f=(a("caad"),a("2532"),a("bc3a")),C=a.n(f),R=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-row",[a("v-col",{attrs:{cols:"8",offset:"2"}},[a("v-row",[a("v-card",{staticClass:"mx-auto elevation-8 ma-2 hover",style:{cursor:t.selectedCursor},attrs:{"min-width":"100%",loading:t.loading,raised:""}},[a("router-link",{staticClass:"d-flex flex-no-wrap justify-lg-space-around",attrs:{to:{name:"projects",params:{groupName:this.projectGroup}},tag:"div",projectGroup:this.projectGroup}},[a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-4"},[t._v(t._s(t.projectName))]),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Quality")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.releasabilityRatingColor}},[t._v(t._s(t.releasabilityRating))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8"}),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Reliability")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.reliabilityRatingColor}},[t._v(t._s(t.reliabilityRating))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8"}),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Security")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.securityRatingColor}},[t._v(t._s(t.securityRating))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8"}),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Maintainability")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.maintainabilityRatingColor}},[t._v(t._s(t.maintainabilityRating))])])],1)],1)],1)],1)],1)],1)],1)},_=[],j=(a("4160"),a("159b"),{props:["projectName","api_url"],created:function(){this.projectGroup=this.projectName,this.apiURL=this.api_url},mounted:function(){var t=this;this.loading=!0,C.a.get(this.apiURL+"/api/v2/projectGroups/"+this.projectGroup+"/measures").then((function(e){var a=0,i=0,r=0,l=0,n=0,s=0,o=0,c=0;e.data.forEach((function(t){t.baseComponent.measures.forEach((function(t){"security_rating"==t.metric&&(a=parseFloat(a)+1,i=parseFloat(i)+parseFloat(t.value)),"reliability_rating"==t.metric&&(r=parseFloat(r)+1,l=parseFloat(l)+parseFloat(t.value)),"sqale_rating"==t.metric&&(n=parseFloat(n)+1,s=parseFloat(s)+parseFloat(t.value)),"alert_status"==t.metric&&("OK"==t.value?o=parseInt(o)+1:c=parseInt(c)+1)}))})),t.releasabilityRating=t.calculateReleasability(o,c),t.reliabilityRating=t.calculateReleasabilityAverage(r/l),t.securityRating=t.calculateReleasabilityAverage(n/s),t.maintainabilityRating=t.calculateReleasabilityAverage(a/i),t.setReliabilityColor(t.reliabilityRating),t.setSecurityColor(t.reliabilityRating),t.setMaintainabilityColor(t.reliabilityRating),t.loading=!1}))},methods:{setReliabilityColor:function(t){switch(t){case"A":this.reliabilityRatingColor="green";break;case"B":this.reliabilityRatingColor="yellow";break;case"C":this.reliabilityRatingColor="orange";break;case"D":this.reliabilityRatingColor="orangered";break;case"E":this.reliabilityRatingColor="red";break}return t},setSecurityColor:function(t){switch(t){case"A":this.securityRatingColor="green";break;case"B":this.securityRatingColor="yellow";break;case"C":this.securityRatingColor="orange";break;case"D":this.securityRatingColor="orangered";break;case"E":this.securityRatingColor="red";break}return t},setMaintainabilityColor:function(t){switch(t){case"A":this.maintainabilityRatingColor="green";break;case"B":this.maintainabilityRatingColor="yellow";break;case"C":this.maintainabilityRatingColor="orange";break;case"D":this.maintainabilityRatingColor="orangered";break;case"E":this.maintainabilityRatingColor="red";break}return t},calculateReleasabilityAverage:function(t){switch(Math.floor(t)){case 1:return this.releasabilityRatingColor="green","A";case 2:return this.releasabilityRatingColor="yellow","B";case 3:return this.releasabilityRatingColor="orange","C";case 4:return this.releasabilityRatingColor="orangered","D";case 5:return this.releasabilityRatingColor="red","E"}},calculateReleasability:function(t,e){if(t+e==0)return"-";var a=t+e,i=t/a;return i>.8?"A":i>.6?"B":i>.4?"C":i>.2?"D":i<=.2?"E":"Unable to Calculate Ratio"},calculateRating:function(t){switch(t){case"1.0":return"A";case"2.0":return"B";case"3.0":return"C";case"4.0":return"D";case"5.0":return"E"}}},data:function(){return{releasabilityRating:null,reliabilityRating:null,securityRating:null,maintainabilityRating:null,qualityGateStatus:null,loading:!1,projectGroup:null,selectedCursor:"pointer",releasabilityRatingColor:"gray",securityRatingColor:"gray",maintainabilityRatingColor:"gray",reliabilityRatingColor:"gray",apiURL:null}}}),k=j,A=a("b0af"),x=a("62ad"),w=a("da13"),L=a("5d23"),V=a("0fd9"),S=Object(o["a"])(k,R,_,!1,null,null,null),P=S.exports;u()(S,{VCard:A["a"],VCol:x["a"],VListItem:w["a"],VListItemContent:L["a"],VListItemSubtitle:L["b"],VListItemTitle:L["c"],VRow:V["a"]});var D=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app-bar",{attrs:{"clipped-left":t.$vuetify.breakpoint.lgAndUp,app:"",color:"blue darken-3",dark:""}},[a("v-toolbar-title",{staticClass:"ml-0 pl-4",staticStyle:{width:"300px"}},[a("span",{staticClass:"hidden-sm-and-down"},[t._v("QualityCompendium")])]),a("v-text-field",{staticClass:"hidden-sm-and-down",attrs:{flat:"","solo-inverted":"","hide-details":"","prepend-inner-icon":"mdi-magnify",label:"Filter"},nativeOn:{keyup:function(e){return t.onChange(e)}},model:{value:t.searchValue,callback:function(e){t.searchValue=e},expression:"searchValue"}}),a("v-spacer"),a("div",{staticClass:"text-center"},[a("v-dialog",{attrs:{width:"500"},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-btn",t._g({staticClass:"ma-2",attrs:{color:"primary",dark:""}},i),[t._v("Add Config")])]}}]),model:{value:t.addDialog,callback:function(e){t.addDialog=e},expression:"addDialog"}},[a("v-card",[a("v-card-title",{staticClass:"headline grey lighten-2",attrs:{"primary-title":""}},[t._v("Add a ProjectPage Entry")]),a("div",{staticClass:"ma-10"},[a("v-text-field",{attrs:{label:"Project Page Name to Add"},model:{value:t.ppname,callback:function(e){t.ppname=e},expression:"ppname"}})],1),a("div",{staticClass:"ma-10"},[a("v-text-field",{attrs:{label:"Comma Seperated List of projectkeys"},model:{value:t.ppkeys,callback:function(e){t.ppkeys=e},expression:"ppkeys"}})],1),a("v-divider"),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"primary",text:""},on:{click:[t.submitAdd,function(e){t.addDialog=!1}]}},[t._v("Submit")])],1)],1)],1)],1),a("div",{staticClass:"text-center"},[a("v-dialog",{attrs:{width:"500"},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-btn",t._g({staticClass:"ma-2",attrs:{color:"primary",dark:""}},i),[t._v("Delete Config")])]}}]),model:{value:t.deleteDialog,callback:function(e){t.deleteDialog=e},expression:"deleteDialog"}},[a("v-card",[a("v-card-title",{staticClass:"headline grey lighten-2",attrs:{"primary-title":""}},[t._v("Delete a ProjectPage Entry")]),a("div",{staticClass:"ma-10"},[a("v-text-field",{attrs:{label:"Project Page Name to Delete"},model:{value:t.deleteppname,callback:function(e){t.deleteppname=e},expression:"deleteppname"}})],1),a("v-divider"),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"primary",text:""},on:{click:[t.submitDelete,function(e){t.deleteDialog=!1}]}},[t._v("Submit")])],1)],1)],1)],1),t.apiHealthy?a("div",[a("v-tooltip",{attrs:{bottom:""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-icon",t._g({style:{cursor:t.selectedCursor},attrs:{large:"",color:"green"}},i),[t._v("mdi-check-circle")])]}}],null,!1,2628154436)},[a("span",[t._v("Connected to qc API")])])],1):t._e(),t.apiHealthy?t._e():a("div",[a("v-tooltip",{attrs:{bottom:""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-icon",t._g({style:{cursor:t.selectedCursor},attrs:{large:"",color:"red"}},i),[t._v("mdi-check-circle")])]}}],null,!1,3237327372)},[a("span",[t._v("Failed to Connect to qc API")])])],1),t.sonarHealthy?a("div",[a("v-tooltip",{attrs:{bottom:""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[t.sonarHealthy?a("v-icon",t._g({style:{cursor:t.selectedCursor},attrs:{large:"",color:"green"}},i),[t._v("mdi-check-circle")]):t._e()]}}],null,!1,43800767)},[t.sonarHealthy?a("span",[t._v("QC API Connected to SonarQube")]):t._e()])],1):t._e(),t.sonarHealthy?t._e():a("div",[a("v-tooltip",{attrs:{bottom:""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[t.sonarHealthy?t._e():a("v-icon",t._g({style:{cursor:t.selectedCursor},attrs:{large:"",color:"red"}},i),[t._v("mdi-check-circle")])]}}],null,!1,778360534)},[t.sonarHealthy?t._e():a("span",[t._v("QC API Disconnected from SonarQube")])])],1),a("v-btn",{attrs:{icon:"",large:""}},[a("v-avatar",{attrs:{size:"32px",item:""}},[a("v-tooltip",{attrs:{bottom:""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-img",t._g({attrs:{src:"https://cdn.vuetifyjs.com/images/logos/logo.svg",alt:"Vuetify"}},i))]}}])},[a("span",[t._v("Made with Vuetify")])])],1)],1)],1)},N=[],E={props:["apiurl","searchfield"],mounted:function(){var t=this;this.searchValue=this.searchfield,C.a.get(this.apiurl+"/actuator/health").then((function(e){200==e.status?t.apiHealthy=!0:t.apiHealthy=!1})),C.a.get(this.apiurl+"/api/v2/sonar-connection").then((function(e){200==e.status?t.sonarHealthy=!0:t.sonarHealthy=!1})).catch((function(t){console.log(t)}))},methods:{onChange:function(t){this.$emit("filtered",this.searchValue)},submitAdd:function(){C.a.post(this.apiurl+"/api/v2/addconfig",{pagename:this.ppname,projectKeys:this.ppkeys}).then((function(t){console.log(t.status)}))},submitDelete:function(){C.a.post(this.apiurl+"/api/v2/deleteconfig",{pagename:this.deleteppname}).then((function(t){console.log(t.status)}))}},data:function(){return{apiHealthy:!1,sonarHealthy:!1,searchValue:"",sonarqubeURL:null,selectedCursor:"pointer",addDialog:!1,deleteDialog:!1,ppname:"",ppkeys:"",deleteppname:""}}},U=E,F=a("40dc"),I=a("8212"),O=a("8336"),G=a("99d9"),H=a("169a"),M=a("ce7e"),T=a("132d"),B=a("adda"),q=a("2fa4"),$=a("8654"),Q=a("2a7f"),K=a("3a2f"),J=Object(o["a"])(U,D,N,!1,null,null,null),z=J.exports;u()(J,{VAppBar:F["a"],VAvatar:I["a"],VBtn:O["a"],VCard:A["a"],VCardActions:G["a"],VCardTitle:G["b"],VDialog:H["a"],VDivider:M["a"],VIcon:T["a"],VImg:B["a"],VSpacer:q["a"],VTextField:$["a"],VToolbarTitle:Q["a"],VTooltip:K["a"]});var W={components:{GroupCard:P,Title:z},mounted:function(){var t=this;C.a.get(this.apiURL+"/api/v2/projectGroups").then((function(e){t.allgroups=e.data,t.groups=t.allgroups}))},methods:{onFilterChange:function(t){for(var e in this.filterContent=t,this.groups=[],this.allgroups)this.allgroups[e].toUpperCase().includes(this.filterContent.toUpperCase())&&this.groups.push(this.allgroups[e])}},data:function(){return{allgroups:null,groups:null,filterContent:"",API_URL:"",apiURL:""}}},X=W,Y=a("a523"),Z=Object(o["a"])(X,m,h,!1,null,null,null),tt=Z.exports;u()(Z,{VContainer:Y["a"]});var et=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("Title",{attrs:{apiurl:t.API_URL},on:{filtered:t.onFilterChange}}),t._l(t.projects,(function(t){return a("ProjectCard",{key:t,attrs:{coveragePercentage:"65",apiURL:"",projectName:t}})}))],2)},at=[],it=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-row",[a("v-col",{attrs:{cols:"8",offset:"2"}},[a("v-row",[a("v-card",{staticClass:"mx-auto elevation-4 ma-2",attrs:{loading:t.loading,raised:""}},[a("div",{staticClass:"d-flex flex-no-wrap justify-lg-space-around"},[a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-4"},[t._v(t._s(t.formattedProjectName))]),a("div",{staticClass:"mb-4"},[t._v(t._s(t.coveragePercentage))]),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Status")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"quality-gate",style:{backgroundColor:t.releasabilityRatingColor}},[t._v(t._s(t.qualityGateStatus))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8",staticStyle:{color:"white"}},[t._v("_")]),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Reliability")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.reliabilityRatingColor}},[t._v(t._s(t.reliabilityRatingAsLetter))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8",staticStyle:{color:"white"}},[t._v("_")]),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Security")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.securityRatingColor}},[t._v(t._s(t.securityRatingAsLetter))])])],1)],1),a("v-list-item",{attrs:{"three-line":""}},[a("v-list-item-content",[a("div",{staticClass:"overline mb-8"},[a("a",{attrs:{href:t.projectLink}},[t._v("SonarQube Project Link")])]),a("v-list-item-title",{staticClass:"headline mb-1 d-flex justify-center"},[t._v("Maintainability")]),a("v-list-item-subtitle",{staticClass:"d-flex justify-center"},[a("span",{staticClass:"rated",style:{backgroundColor:t.maintainabilityRatingColor}},[t._v(t._s(t.maintainabilityRatingAsLetter))])])],1)],1)],1)])],1)],1)],1)},rt=[],lt={props:["projectName","apiURL"],methods:{calculateReleasabilityAverage:function(t){switch(Math.floor(t)){case 1:return"A";case 2:return"B";case 3:return"C";case 4:return"D";case 5:return"E"}},calculateAlphabeticalRating:function(t){switch(Math.floor(t)){case 1:return"A";case 2:return"B";case 3:return"C";case 4:return"D";case 5:return"E"}},setReliabilityColor:function(t){switch(t){case"A":this.reliabilityRatingColor="green";break;case"B":this.reliabilityRatingColor="yellow";break;case"C":this.reliabilityRatingColor="orange";break;case"D":this.reliabilityRatingColor="orangered";break;case"E":this.reliabilityRatingColor="red";break}},setSecurityColor:function(t){switch(t){case"A":this.securityRatingColor="green";break;case"B":this.securityRatingColor="yellow";break;case"C":this.securityRatingColor="orange";break;case"D":this.securityRatingColor="orangered";break;case"E":this.securityRatingColor="red";break}},setMaintainabilityColor:function(t){switch(t){case"A":this.maintainabilityRatingColor="green";break;case"B":this.maintainabilityRatingColor="yellow";break;case"C":this.maintainabilityRatingColor="orange";break;case"D":this.maintainabilityRatingColor="orangered";break;case"E":this.maintainabilityRatingColor="red";break}}},mounted:function(){var t=this;this.loading=!0,this.projectName.length>29?this.formattedProjectName=this.projectName.substring(0,29)+"...":this.formattedProjectName=this.projectName,C.a.get(this.apiURL+"/api/v2/sonar-host").then((function(e){t.projectLink=e.data+"/dashboard?id="+t.projectName})),C.a.get(this.apiURL+"/api/v2/projectKeys/"+this.projectName+"/measures").then((function(e){e.data.baseComponent.measures.forEach((function(e){"coverage"==e.metric&&(t.coveragePercentage=e.value+"% Coverage"),"security_rating"==e.metric&&(t.securityRating=e.value,t.securityRatingAsLetter=t.calculateAlphabeticalRating(e.value),t.setSecurityColor(t.securityRatingAsLetter)),"reliability_rating"==e.metric&&(t.reliabilityRating=e.value,t.reliabilityRatingAsLetter=t.calculateAlphabeticalRating(e.value),t.setReliabilityColor(t.reliabilityRatingAsLetter)),"sqale_rating"==e.metric&&(t.maintainabilityRating=e.value,t.maintainabilityRatingAsLetter=t.calculateAlphabeticalRating(e.value),t.setMaintainabilityColor(t.maintainabilityRatingAsLetter)),"alert_status"==e.metric&&("OK"==e.value?(t.qualityGateStatus="Passing",t.releasabilityRatingColor="green"):(t.qualityGateStatus="Failing",t.releasabilityRatingColor="red"))})),t.releasabilityRating=t.calculateReleasabilityAverage((parseFloat(t.securityRating)+parseFloat(t.reliabilityRating)+parseFloat(t.maintainabilityRating))/3),t.loading=!1}))},data:function(){return{coveragePercentage:null,formattedProjectName:null,loading:!1,releasabilityRating:null,reliabilityRating:null,securityRating:null,maintainabilityRating:null,reliabilityRatingAsLetter:null,securityRatingAsLetter:null,maintainabilityRatingAsLetter:null,qualityGateStatus:null,projectLink:null,releasabilityRatingColor:"gray",reliabilityRatingColor:"gray",securityRatingColor:"gray",maintainabilityRatingColor:"gray"}}},nt=lt,st=Object(o["a"])(nt,it,rt,!1,null,null,null),ot=st.exports;u()(st,{VCard:A["a"],VCol:x["a"],VListItem:w["a"],VListItemContent:L["a"],VListItemSubtitle:L["b"],VListItemTitle:L["c"],VRow:V["a"]});var ct={components:{ProjectCard:ot,Title:z},props:["projectGroup"],created:function(){this.groupName=this.$route.params.groupName},mounted:function(){var t=this;C.a.get(this.apiURL+"/api/v2/projectGroups/"+this.groupName+"/projectKeys").then((function(e){t.allprojects=e.data,t.projects=t.allprojects}))},methods:{onFilterChange:function(t){for(var e in this.filterContent=t,this.projects=[],this.allprojects)this.allprojects[e].toUpperCase().includes(this.filterContent.toUpperCase())&&this.projects.push(this.allprojects[e])}},data:function(){return{allprojects:null,projects:null,groupName:null,filterContent:"",API_URL:"",apiURL:""}}},ut=ct,pt=Object(o["a"])(ut,et,at,!1,null,null,null),dt=pt.exports;u()(pt,{VContainer:Y["a"]});var gt=a("8c4f");i["a"].config.productionTip=!1,i["a"].use(gt["a"]);var vt=[{name:"home",path:"/",component:tt,props:!0},{name:"projects",path:"/:groupName/projects",component:dt,props:!0}],yt=new gt["a"]({routes:vt});new i["a"]({vuetify:b,router:yt,render:function(t){return t(v)}}).$mount("#app")},"5c0b":function(t,e,a){"use strict";var i=a("7694"),r=a.n(i);r.a},7694:function(t,e,a){}});
//# sourceMappingURL=app.6a5b2317.js.map