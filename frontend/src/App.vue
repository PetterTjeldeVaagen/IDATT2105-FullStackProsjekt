<script setup lang="ts">
import { ref } from "vue";

  const API_BASE = "http://localhost:8080";
  const displayText = ref("No data yet");

  function fetchData() {
    fetch(`${API_BASE}/course/getCourse/1`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        displayText.value = `Course #${data.courseId}: ${data.name} - ${data.description}`;
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        displayText.value = `Error: ${error.message}`;
      });
  }
</script>

<template>
  <h1>You did it!</h1>
  <button @click="fetchData">Fetch Data from Backend</button>
  <p id="data-display">
    {{ displayText }}
  </p>
</template>

<style scoped></style>
