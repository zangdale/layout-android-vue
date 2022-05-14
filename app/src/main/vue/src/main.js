// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui';
import layoutDynamicUI from 'layout-dynamic-ui';
import Vant from 'vant';


import 'element-ui/lib/theme-chalk/index.css';
import 'layout-dynamic-ui/lib/index.css';
import 'vant/lib/index.css';
Vue.use(Vant);
Vue.use(ElementUI);
Vue.use(layoutDynamicUI);


router.afterEach((to, from, next) => {
	window.scrollTo(0, 0);
});

import androidVue from '@/android-vue.js';
Vue.use(androidVue);


Vue.config.productionTip = false

window.addEventListener('popstate', function(e) {
	router.isBack = true
}, false)

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	components: {
		App
	},
	template: '<App/>'
})
