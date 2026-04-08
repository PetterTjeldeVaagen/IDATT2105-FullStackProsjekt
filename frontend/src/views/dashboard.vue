<script setup>
import { ref, onMounted } from "vue"
import { useRouter } from "vue-router"
import Navbar from "@/components/navbar.vue"
import taskComponent from "@/components/taskComponent.vue"

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
      throw new Error(text || `HTTP error ${response.status}`)
    }

    tasks.value = await response.json()

    tasks.value.sort((a, b) => new Date(a.finishBy) - new Date(b.finishBy))
  } catch (err) {
    console.error("Error while fetching tasks:", err)
    error.value = err.message
  }
}

const isManager = ref(false)
const resturantName = ref("")
async function getResturantInfo() {
  try {
    const response = await fetch(`http://localhost:8080/restaurant/getResturantIdByEmployeeId/${employeeId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    const restaurantInfo = await response.json()
    resturantName.value = restaurantInfo.name
  } catch (err) {
    console.error("Error while fetching restaurant info:", err)
  }
}

onMounted(() => {
  getTasks()
  getResturantInfo()
})

</script>
<template>
  <div class="dashboard">
    <Navbar />
    <h1>Welcome to the dashboard, {{ username }}!</h1>
    <h2>{{ resturantName }}</h2>

    <div v-if="isManager">

    </div>

    <div id="employeeTasks">
      <h2>Your Tasks</h2>
      <p v-if="error" class="error">{{ error }}</p>
      <div v-else-if="tasks.length === 0">No tasks assigned.</div>
      <ul v-else class="tasks">
        <li v-for="task in tasks" :key="task.taskId">
          <taskComponent :task="task" @taskUpdated="getTasks" />
        </li>
      </ul>
    </div>

  </div>
</template>

<style scoped>
  #employeeTasks {
    margin-left: 1rem;
    border: yellow 1px solid;
    padding: 1rem;
    min-width: 10%;
    max-width: 30%;
    display: flex;
    flex-direction: column;
  }

  .tasks {
    list-style-type: none;
    padding: 0;
    
  }
</style>
