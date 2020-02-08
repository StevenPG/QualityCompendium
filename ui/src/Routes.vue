<script>
const routes = {
  "/": "Groups",
  "/projects": "Projects"
};

export default {
  data() {
    currentRoute: window.location.pathname;
  },
  computed: {
    ViewComponent() {
      const matchingView = routes[this.currentRoute];
      return matchingView
        ? require("./pages/" + matchingView + ".vue")
        : require("./pages/404.vue");
    }
  },
  render(h) {
    return h(this.ViewComponent);
  }
};
</script>