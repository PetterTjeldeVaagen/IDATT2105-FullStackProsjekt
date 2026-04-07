<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref(sessionStorage.getItem("username") || "")

function logOut() {
  sessionStorage.clear()
  router.push("/")
}
</script>

<template>
  <nav class="navbar">
    <div class="navbar-left">
      <div class="logo">RESTurant Manager</div>
      <span class="username">{{ username }}</span>
    </div>

    <ul class="nav-links">
      <li><RouterLink to="/dashboard">Dashboard</RouterLink></li>
      <li><RouterLink to="/tasks">Tasks</RouterLink></li>
      <li><RouterLink to="/deviations">Deviations</RouterLink></li>
      <li><RouterLink to="/courses">Courses</RouterLink></li>
    </ul>

    <button class="logout-btn" @click="logOut">Log out</button>
  </nav>
</template>

<style scoped>
.navbar {
  width: 100%;
  margin: 0;
  padding-bottom: 0;
  padding-top: 1.5rem;
  background-color: #2e2727;
  color: #e3db02;
  font-size: 2rem;
  font-family: Arial, Helvetica, sans-serif;

  display: flex;
  justify-content: space-between;
  align-items: stretch;
  box-sizing: border-box;
}

.navbar-left {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-left: 1rem;
  margin-bottom: 1.5rem;
}

.logo {
  font-weight: bold;
  font-size: 2.5rem;
}

.username {
  color: white;
  font-size: 1.5rem;
}

.nav-links {
  list-style: none;
  display: flex;
  align-items: end;
  gap: 0.3rem;
  margin: 0;
  padding: 0;
}

.nav-links li {
  display: flex;
}

.nav-links a {
  display: block;
  padding: 0.5rem 1rem;
  text-decoration: none;
  color: #e3db02;
  background-color: #5c5757;
  border: 2px solid #e3db02;
  border-radius: 10px 10px 0 0;
  border-bottom: none;
  transition: background-color 0.2s ease;
}

.nav-links a:hover {
  background-color: #555;
}

.nav-links a.router-link-active {
  background-color: #2e2727;
  font-weight: bold;
}

.logout-btn {
  height: fit-content;
  margin-right: 2rem;
  background-color: #e3db02;
  border: none;
  border-radius: 3px;
  font-size: 1.5rem;
  color: black;
  cursor: pointer;
}

</style>