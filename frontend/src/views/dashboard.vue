<script setup>
import { ref, onMounted } from "vue"
import { useRouter } from "vue-router"

const router = useRouter()

const token = ref(sessionStorage.getItem("token") || "")
const employeeId = ref(sessionStorage.getItem("employeeId") || "")
const email = ref(sessionStorage.getItem("email") || "")
const tasks = ref([])
const error = ref("")

function logOut() {
  token.value = ""
  sessionStorage.clear()
  router.push("/")
}

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
    <h1>Velkommen til dashboardet!</h1>
    <p>Dette er en beskyttet side som krever innlogging.</p>
    <p>Din token: {{ token }}</p>
  </div>

  <div>
    <ul id="tasks">
      <li v-for="task in tasks" :key="task.id">{{ task.name }}</li>
    </ul>
  </div>

  <button @click="logOut">Logg ut</button>
</template>