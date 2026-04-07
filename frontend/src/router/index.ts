import Dashboard from '@/views/dashboard.vue'
import Login from '@/views/logIn.vue'
import Register from '@/views/register.vue'
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
      path: '/register',
      name: 'register',
      component: Register
    },
    {
    path: '/dashboard',
    name: 'dashboard',
    component: Dashboard
  },
],
})

export default router
