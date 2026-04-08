<script setup>
import { ref } from "vue"
import createDeviationComponent from "@/components/deviations/createDeviationComponent.vue"

const showCreateDeviation = ref(false)
const emit = defineEmits(["employeeRemoved"])

const props = defineProps({
  employee: {
    type: Object,
    required: true
  }
})

async function RemoveEmployee() {
  const confirmed = window.confirm(
    `Are you sure you want to remove "${props.employee.name}"?`
  )

  if (!confirmed) return
  
  try {
    const resturantId = sessionStorage.getItem("restaurantId") || ""
    const removalData = {
        employeeId: props.employee.employeeId,
        resturantId: resturantId
    }

    const token = sessionStorage.getItem("token") || ""
    const response = await fetch(`http://localhost:8080/resturant/removeEmployee`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json"
      },
        body: JSON.stringify(removalData)
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    emit("employeeRemoved")
    console.log(`Employee ${props.employee.name} removed successfully.`)
  } catch (err) {
    console.error("Error while removing employee:", err)
  }
}
</script>

<template>
 <createDeviationComponent v-if="showCreateDeviation" :deviation="props.deviation" @cancel="showCreateDeviation = false" @deviationUpdated="showCreateDeviation = false; emit('deviationUpdated')" />
  <div class="deviation" v-else>
    
    <div class="info">
      <h2>{{ props.employee.name }}</h2>
      <p>Email: {{ props.employee.email }}</p>
      <p>Role: {{ props.employee.role }}</p>
      <p>Phone: {{ props.employee.phoneNumber }}</p>
    </div>
    <div class="actions" v-if="props.employee.role !== 'MANAGER'">
      <button @click="RemoveEmployee">Remove</button>
    </div>
  </div>
</template>

<style scoped>
    .deviation {
        background-color: #5c5757;
        border: 1px solid #e3db02;
        border-radius: 8px;
        padding: 1rem;
        margin-bottom: 1rem;
        display: flex;
        font-family: Arial, Helvetica, sans-serif;
        width: 90%;
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