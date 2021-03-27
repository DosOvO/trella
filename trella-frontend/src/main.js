import '@/assets/css/tailwind.css'
import Vue from 'vue'
import App from './App.vue'
import ScrollOut from "scroll-out"

Vue.config.productionTip = false

import "@/assets/css/style.css"

new Vue({
  render: h => h(App),
}).$mount('#app')

ScrollOut({
  cssProps: true,
  threshold: 0.2
});
