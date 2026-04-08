<script setup>
import { ref } from "vue"
import createDeviationComponent from "@/components/deviations/createDeviationComponent.vue"

const showCreateDeviation = ref(false)
const emit = defineEmits(["deviationUpdated"])

const props = defineProps({
  deviation: {
    type: Object,
    required: true
  }
})

async function DeleteDeviation() {
  const confirmed = window.confirm(
    `Are you sure you want to delete "${props.deviation.title}"?`
  )

  if (!confirmed) return
  try {
    const token = sessionStorage.getItem("token") || ""
    const response = await fetch(`http://localhost:8080/deviation/deleteDeviation/${props.deviation.deviationId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    emit("deviationUpdated", props.deviation.deviationId)
  } catch (err) {
    console.error("Error while deleting deviation:", err)
  }
}

function EditDeviation() {
  showCreateDeviation.value = true
}

</script>

<template>
 <createDeviationComponent v-if="showCreateDeviation" :deviation="props.deviation" @cancel="showCreateDeviation = false" @deviationUpdated="showCreateDeviation = false; emit('deviationUpdated')" />
  <div class="deviation" v-else>
    <div class="actions" v-if="$route.name === 'deviations'">
      <button @click="EditDeviation">Edit</button>
      <button @click="DeleteDeviation">Delete</button>
    </div>
    <div class="info">
      <h2>{{ props.deviation.title }}</h2>
      <p>{{ props.deviation.description }}</p>
      <p>Registered On: {{ new Date(props.deviation.date).toLocaleDateString() }}</p>
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