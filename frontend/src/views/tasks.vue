<script setup>
import Navbar from '@/components/navbar.vue';
import taskComponent from '@/components/taskComponent.vue';
import { onMounted, ref } from 'vue';
const tasks = ref([])
const error = ref("")

async function getTasks() {
  try {
    const token = sessionStorage.getItem("token") || ""
    const employeeId = sessionStorage.getItem("employeeId") || ""
    const response = await fetch(`http://localhost:8080/task/getTaskByEmployee/${employeeId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP-feil ${response.status}`)
    }

    tasks.value = await response.json()
    tasks.value.sort((a, b) => new Date(a.finishBy) - new Date(b.finishBy))
  } catch (err) {
    console.error("Feil ved henting av oppgaver:", err)
    error.value = err.message
  }
}

function createTask() {
  console.log("Create task clicked")
}

onMounted(() => {
  getTasks()
})
</script>

<template>
  <div class="tasks">
    <Navbar />
    <h1>Task Management</h1>

    <button class="yellowButton" @click="createTask">New task</button>

     <p v-if="error" class="error">{{ error }}</p>

    <ul id="tasks">
      <li v-for="task in tasks" :key="task.id">
        <taskComponent :task="task" />
      </li>
    </ul>
  </div>
</template>

<style scoped>
    #tasks {
        list-style-type: none;
        padding: 1rem;
    }

    .yellowButton {
        background-color: #e3db02;
        color: #000000;
        border: none;
        padding: 0.5rem 1rem;
        border-radius: 6px;
        font-size: 1rem;
        cursor: pointer;
        margin-left: 1rem;
    }
</style>