<script setup>
import { ref } from "vue"
import { useRouter } from 'vue-router'
</script>

<template>
  <div class="join-resturant">
    <h1>Join a Resturant</h1>
    <p>To join a resturant, please contact your manager and ask for the resturant code.</p>
    <p>Once you have the code, please enter it below to join the resturant.</p>
    <input v-model="resturantCode" type="text" placeholder="Enter resturant code" />
    <button @click="joinResturant">Join Resturant</button>
  </div>
</template>

<style scoped>
    .join-resturant {
        padding-left: 1rem;
    }

    button {
        width: 10rem;
        margin-top: 1.2rem;
        padding: 0.8rem;
        margin-left: 1rem;
        border: none;
        background: #e3db02;
        color: #2e2727;
        font-size: 1rem;
        border-radius: 8px;
        cursor: pointer;
    }
</style>