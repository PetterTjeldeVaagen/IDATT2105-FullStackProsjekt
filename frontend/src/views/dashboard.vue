<script setup>
import { ref } from "vue"
const token = ref("")
token.value = sessionStorage.getItem("email") || ""
</script>

<template>
  <div class="dashboard">
    <h1>Velkommen til dashboardet!</h1>
    <p>Dette er en beskyttet side som krever innlogging.</p>
    <p>Din token: {{ token }}</p>
  </div>
</template>