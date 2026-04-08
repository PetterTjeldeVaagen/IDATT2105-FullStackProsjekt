<script setup>
import { ref, onMounted } from "vue"
import { useRouter } from "vue-router"
import Navbar from "@/components/navbar.vue"
import taskComponent from "@/components/tasks/taskComponent.vue"

const router = useRouter()

const token = ref(sessionStorage.getItem("token") || "")
const employeeId = ref(sessionStorage.getItem("employeeId") || "")
const email = ref(sessionStorage.getItem("email") || "")
const username = ref(sessionStorage.getItem("username") || "")

const tasks = ref([])
const error = ref("")

async function getTasks() {
  console.log("Fetching tasks for employeeId:", employeeId.value)
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
    const response = await fetch(`http://localhost:8080/resturant/getResturantByEmployeeId/${employeeId.value}`, {
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
    try {
      const managerResponse = await fetch(`http://localhost:8080/resturant/getManagers/${restaurantInfo.resturantId}`, {
        headers: {
          Authorization: `Bearer ${token.value}`
        }
      })

      if (!managerResponse.ok) {
        const text = await managerResponse.text()
        throw new Error(text || `HTTP error ${managerResponse.status}`)
      }

      const managers = await managerResponse.json()
      isManager.value = managers.some(manager => manager.employeeId === Number(employeeId.value))
      console.log("Is manager:", isManager.value)
    } catch (err) {
      console.error("Error while fetching managers:", err)
    }
  } catch (err) {
    console.error("Error while fetching restaurant info:", err)
  }

}

onMounted(() => {
  getResturantInfo()
  getTasks()
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
      <h2>Your pending and overdue Tasks</h2>
      <p v-if="error" class="error">{{ error }}</p>
      <ul v-else class="tasks">
        <li v-for="task in tasks.filter(task => task.status === 'PENDING' || task.status === 'OVERDUE')" :key="task.taskId">
          <taskComponent :task="task" @taskUpdated="getTasks" />
        </li>
      </ul>
    </div>

  </div>
</template>

<style scoped>

  h2 {
    margin-left: 1rem;
  }

  #employeeTasks {
    margin-left: 1rem;
    border: yellow 2px solid;
    border-radius: 8px;
    padding: 1rem;
    min-width: 20%;
    max-width: 30%;
  }

  .tasks {
    list-style-type: none;
    padding: 0;
    margin: 0;
    max-height: 20rem;
    max-width: 100%;
    overflow-y: auto;
  }
</style>
