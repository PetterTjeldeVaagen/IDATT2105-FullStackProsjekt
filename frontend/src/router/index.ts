import Dashboard from '@/views/dashboard.vue'
import Login from '@/views/logIn.vue'
import Register from '@/views/register.vue'
import Tasks from '@/views/tasks.vue'
import deviations from '@/views/deviations.vue'
import courses from '@/views/courses.vue'
import joinResturant from '@/views/joinResturant.vue'
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
    {
      path: '/tasks',
      name: 'tasks',
      component: Tasks
    },
    
    {
      path: '/deviations',
      name: 'deviations',
      component: deviations
    },
    {
      path: '/courses',
      name: 'courses',
      component: courses
    },
    {
      path: '/joinResturant',
      name: 'joinResturant',
      component: joinResturant
    }

  ],
})

export default router
