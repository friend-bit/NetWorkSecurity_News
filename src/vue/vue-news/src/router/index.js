import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/homeView'
  },
  {
    path: '/validCode',
    name: 'ValidCode',
    component: ()=>import('@/views/conponents/ValidCode'),
  },
  // === home ===
  {
    path: '/homeView',
    name: 'HomeView',
    component: ()=>import('@/views/HomeView'),
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('@/login_regedit/Login'),
  },
  {
    path: '/',
    name: 'Layout',
    component: ()=>import('@/views/Layout'),
    children:[
      // === immediately ===
      {path: 'immediately', name: 'Immediately', component: ()=>import('@/views/immediately'),},
      // === personal ===
      {path: 'personal', name: 'Personal', component: ()=>import('@/views/Personal'),},
      // === articleContent ===
      {path: 'articleContent/:id', name: 'ArticleContent', component: ()=>import('@/views/articleContent'),},
      // === policyLaw ===
      {path: 'policyLaw', name:'PolicyLaw', component: ()=>import('@/views/policyLaw'),},
      // === hackEvent ===
      {path: 'hackEvent', name:'HackEvent', component: ()=>import('@/views/hackEvent'),},
      // === securityTechnology ===
      {path:'securityTechnology',name:'SecurityTechnology',component: ()=>import('@/views/securityTechnology')},

      {path:'modifyPersonal',name:'modifyPersonal',component:()=>import('@/views/modifyPersonal')}
    ]
  },
  {
    path: '/regedit',
    name: 'Regedit',
    component: ()=>import('@/login_regedit/regedit'),
  },
  {
    path: '/404',
    name: '404',
    component: ()=>import('@/views/404'),
  },
  {
    path:'/ForgotPassword',
    name:'ForgotPassword',
    component: ()=>import('@/login_regedit/ForgotPassword')
  },
  {
    path:'/CommentList',
    name:'CommentList',
    component: ()=>import('@/views/Comment/CommentList')
  },
  {
    path:'/UploadFile',
    name:'UploadFile',
    component: ()=>import('@/views/uploadFile')
  },
  {
    path: '/SearchNews',
    name: 'SearchNews',
    component: ()=>import('@/views/SearchNews')
  },
  {
    path:'/SearchNewsIndex',
    name:'SearchNewsIndex',
    component: ()=>import('@/views/SearchNewsIndex')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
