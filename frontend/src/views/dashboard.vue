<script setup>
import { ref, onMounted } from "vue"
import { useRouter } from "vue-router"
import Navbar from "@/components/navbar.vue"

const router = useRouter()

const token = ref(sessionStorage.getItem("token") || "")
const employeeId = ref(sessionStorage.getItem("employeeId") || "")
const email = ref(sessionStorage.getItem("email") || "")
const username = ref(sessionStorage.getItem("username") || "")

const tasks = ref([])
const error = ref("")

async function getTasks() {
  try {
    const response = await fetch(`http://localhost:8080/task/getTaskByEmployee/${employeeId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP-feil ${response.status}`)
    }

    tasks.value = await response.json()
  } catch (err) {
    console.error("Feil ved henting av oppgaver:", err)
    error.value = err.message
  }
}

onMounted(() => {
  getTasks()
})

</script>
<template>
  <div class="dashboard">
    <Navbar />
    <h1>Welcome to the dashboard, {{ username }}!</h1>
    <ul id="tasks">
      <li v-for="task in tasks" :key="task.id">{{ task.name }}</li>
    </ul>
  </div>
</template>