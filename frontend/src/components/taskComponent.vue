<script setup>
import { ref } from "vue"
import createTaskComponent from "@/components/createTaskComponent.vue"

const showCreateTask = ref(false)
const emit = defineEmits(["taskUpdated"])

const props = defineProps({
  task: {
    type: Object,
    required: true
  }
})

async function DeleteTask() {
  const confirmed = window.confirm(
    `Are you sure you want to delete "${props.task.name}"?`
  )

  if (!confirmed) return
  try {
    const token = sessionStorage.getItem("token") || ""
    const response = await fetch(`http://localhost:8080/task/deleteTask/${props.task.taskId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    emit("taskUpdated", props.task.taskId)
  } catch (err) {
    console.error("Error while deleting task:", err)
  }
}

function EditTask() {
  showCreateTask.value = true
}

async function CompleteTask() {
  try {
    const token = sessionStorage.getItem("token") || ""
    const taskData = {
      name: props.task.name,
      description: props.task.description,
      finishBy: props.task.finishBy,
      recurring: props.task.recurring,
      recurringFrequency: props.task.recurringFrequency,
      assignedTo: props.task.assignedTo?.employeeId,
      status: "COMPLETED",
      category: props.task.category
    }
    const response = await fetch(`http://localhost:8080/task/updateTask/${props.task.taskId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify(taskData)
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    emit("taskUpdated")
  } catch (err) {
    console.error("Error while completing task:", err)
  }
}
</script>

<template>
 <createTaskComponent v-if="showCreateTask" :task="props.task" @cancel="showCreateTask = false" @taskUpdated="showCreateTask = false; emit('taskUpdated')" />
  <div class="task">
    <div class="actions">
      <button @click="EditTask">Edit</button>
      <button @click="DeleteTask">Delete</button>
      <button @click="CompleteTask">Complete</button>
    </div>
    <div class="info">
      <h2>{{ props.task.name }}</h2>
      <p>{{ props.task.description }}</p>
      <p>Status: {{ props.task.status }}</p>
      <p>Finish By: {{ new Date(props.task.finishBy).toLocaleDateString() }}</p>
    </div>
  </div>
</template>

<style scoped>
    .task {
        background-color: #5c5757;
        border: 1px solid #e3db02;
        border-radius: 8px;
        padding: 1rem;
        margin-bottom: 1rem;
        display: flex;
        font-family: Arial, Helvetica, sans-serif;
        min-width: 20%;
        max-width: 30%;
        width: fit-content;
    }

    .info {
      float: left;
      margin-left: 2rem;
      margin-right: 2rem;
    }

    .actions {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .actions button {
      background-color: #e3db02;
      color: #000000;
      border: black solid 1px;
      padding: 0.5rem 1rem;
      border-radius: 6px;
      font-size: 1rem;
      cursor: pointer;
      margin-bottom: 0.5rem;
    }
</style>