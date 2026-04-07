<script setup>

defineProps({
  task: {
    name: String,
    description: String,
    status: String,
    finishBy: String
}})
</script>

<template>
  <div class="task">
    <h2>{{ task.name }}</h2>
    <p>{{ task.description }}</p>
    <p>Status: {{ task.status }}</p>
    <p>Finish By: {{ new Date(task.finishBy).toLocaleDateString() }}</p>
  </div>
</template>

<style scoped>
    .task {
        background-color: #5c5757;
        border: 1px solid #e3db02;
        border-radius: 8px;
        padding-left: 1rem;
        margin-bottom: 1rem;
    }
</style>