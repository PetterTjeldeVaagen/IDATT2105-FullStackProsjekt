<script setup>
    import { ref } from 'vue'

</script>

<template>
  <nav class="navbar">
    <div class="navbar-container">
      <div class="logo">MyApp</div>
      <ul class="nav-links">
        <li><a v-on:click="">Dashboard</a></li>
        <li><a href="#">Tasks</a></li>
        <li><a href="#">Deviations</a></li>
        <li><a href="#">Courses</a></li>
      </ul>
      <div class="user-profile">
        <span class="username"></span>
      </div>  
    </div>
  </nav>
</template>