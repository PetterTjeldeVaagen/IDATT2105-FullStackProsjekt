import Dashboard from '@/views/dashboard.vue'
import Login from '@/views/logIn.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
    path: '/dashboard',
    name: 'dashboard',
    component: Dashboard
  },
],
})

export default router
