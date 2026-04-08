<script setup>
import Navbar from '@/components/navbar.vue';
import deviationComponent from '@/components/deviations/deviationComponent.vue';
import createDeviationComponent from '@/components/deviations/createDeviationComponent.vue';
import { onMounted, ref } from 'vue';

const deviations = ref([])
const showCreateDeviation = ref(false)
const error = ref("")

async function getDeviations() {
  try {
    const token = sessionStorage.getItem("token") || ""
    const employeeId = sessionStorage.getItem("employeeId") || ""
    const response = await fetch(`http://localhost:8080/deviation/getDeviationByEmployee/${employeeId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP-feil ${response.status}`)
    }

    deviations.value = await response.json()
    deviations.value.sort((a, b) => new Date(a.dateRegistered) - new Date(b.dateRegistered))
  } catch (err) {
    console.error("Error while fetching deviations:", err)
    error.value = err.message
  }
}

onMounted(() => {
    getDeviations()
})
</script>

<template>
  <div class="deviations">
    <Navbar />
    <h1>Deviation Management</h1>

    <button class="yellowButton" @click="showCreateDeviation = true">New deviation</button>

     <p v-if="error" class="error">{{ error }}</p>

    <div id="employeeDeviations">
      <div class="deviationLists">
        <h2>My registered Deviations</h2>
        <ul class="deviations">
          <li v-for="deviation in deviations" :key="deviation.deviationId">
            <deviationComponent :deviation="deviation" @deviationUpdated="getDeviations" />
          </li>
        </ul>
      </div>
    </div>

    <createDeviationComponent v-if="showCreateDeviation" @cancel="showCreateDeviation = false" @deviationCreated="getDeviations" />
  </div>
</template>

<style scoped>
  .deviations {
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

    #employeeDeviations {
      display: flex;
      gap: 2rem;
      margin-left: 1rem;
      align-items: flex-start;
    }

    .deviationLists {
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

    .deviationLists li {
      width: 100%;
      margin-bottom: 1rem;
    }
</style>