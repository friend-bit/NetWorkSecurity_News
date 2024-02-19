import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: ()=>import('@/views/Layout'),
    children:[
      // === Home ===
      {path: 'home', name: 'Home', component: ()=>import('@/views/Home'),},
      // === ValidCode ===
      {path: '/validCode', name: 'ValidCode', component: ()=>import('@/views/conponents/ValidCode'),},
      // === writeNews ===
      {path: 'writeNews', name: 'writeNews', component: ()=>import('@/views/writeNews'),},
      // === User ===
      {path: 'user', name: 'User', component: ()=>import('@/views/user/User'),},
      {path: 'editeUser', name: 'EditeUser', component: ()=>import('@/views/user/editeUser'),},
      // === News ===
      {path: 'news', name: 'News', component: ()=>import('@/views/News/News'),},
      {path: 'editeNews', name: 'EditeNews', component: ()=>import('@/views/News/editeNews'),},
      // === Examine ===
      {path: 'content', name: 'Content', component: ()=>import('@/views/examine/Content'),},
      {path: 'comment', name: 'Comment', component: ()=>import('@/views/examine/Comment'),},
      // === CNVD ===
      {path: 'CNVDManage', name: 'CNVDManage', component: ()=>import('@/views/CVE/CNVDManage'),},
      {path: 'AddCNVD', name: 'AddCNVD', component: ()=>import('@/views/CVE/AddCNVD'),},
      {path: 'editeCNVD', name: 'editeCNVD', component: ()=>import('@/views/CVE/editeCNVD'),},
      // === Sensitive ===
      {path: 'Sensitive', name: 'Sensitive', component: ()=>import('@/views/sensitive/Sensitive'),},
      {path: 'editeSensitive', name: 'editeSensitive', component: ()=>import('@/views/sensitive/editeSensitive'),},
    ]
  },
  {
    path:'/viewContent',
    name:'ViewContent',
    component: ()=>import('@/views/examine/viewContent'),
  },
  {
    path:'/login',
    name:'Login',
    component: ()=>import('@/views/login_regedit/Login'),
  },
  {
    path:'/regedit',
    name:'Regedit',
    component: ()=>import('@/views/login_regedit/Regedit'),
  },
  {
    path:'/forgotPassword',
    name:'ForgotPassword',
    component: ()=>import('@/views/login_regedit/ForgotPassword'),
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
