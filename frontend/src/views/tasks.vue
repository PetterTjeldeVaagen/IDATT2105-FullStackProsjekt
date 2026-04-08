<script setup>
import Navbar from '@/components/navbar.vue';
import taskComponent from '@/components/taskComponent.vue';
import createTaskComponent from '@/components/createTaskComponent.vue';
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
    for (const task of tasks.value) {
      if(task.status === "PENDING" && new Date(task.finishBy) < new Date()) {
        task.status = "OVERDUE"
      }
    }
  } catch (err) {
    console.error("Feil ved henting av oppgaver:", err)
    error.value = err.message
  }
}


const showCreateTask = ref(false)

onMounted(() => {
  getTasks()
})
</script>

<template>
  <div class="tasks">
    <Navbar />
    <h1>Task Management</h1>

    <button class="yellowButton" @click="showCreateTask = true">New task</button>

     <p v-if="error" class="error">{{ error }}</p>

    <div id="employeeTasks">
      <div class="taskLists">
        <h2>Pending Tasks</h2>
        <ul class="tasks">
          <li v-for="task in tasks.filter(task => task.status === 'PENDING')" :key="task.taskId">
            <taskComponent :task="task" @taskUpdated="getTasks" />
          </li>
        </ul>
      </div>

      <div class="taskLists">
        <h2>Overdue Tasks</h2>
        <ul class="tasks">
          <li v-for="task in tasks.filter(task => task.status === 'OVERDUE')" :key="task.taskId">
            <taskComponent :task="task" @taskUpdated="getTasks" />
          </li>
        </ul>
      </div>

      <div class="taskLists">
        <h2>Completed Tasks</h2>
        <ul class="tasks">
          <li v-for="task in tasks.filter(task => task.status === 'COMPLETED')" :key="task.taskId">
            <taskComponent :task="task" @taskUpdated="getTasks" />
          </li>
        </ul>
      </div>
    </div>

    <createTaskComponent v-if="showCreateTask" @cancel="showCreateTask = false" @taskCreated="getTasks" />
  </div>
</template>

<style scoped>
    .tasks {
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
      margin-bottom: 1rem;
    }

    #employeeTasks {
      display: flex;
      gap: 2rem;
      margin-left: 1rem;
      align-items: flex-start;
    }

    .taskLists {
      border: yellow 3px solid;
      border-radius: 8px;
      padding: 1rem;
      width: 32%;
      height: 30rem;
      overflow-y: auto;
      box-sizing: border-box;
      list-style: none;
      margin: 0;
      padding: 1rem;
    }

    .taskList li {
      width: 100%;
      margin-bottom: 1rem;
    }

</style>