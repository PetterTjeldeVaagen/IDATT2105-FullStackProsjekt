<template>
  <div class="login-container">
    <form class="login-box" @submit.prevent="register">
      <h1>RESTurant Manager</h1>

      <label for="email">Email</label>
      <input
        id="email"
        v-model="email"
        type="email"
        placeholder="Enter your email"
        required
      />

      <label for="username">Username</label>
      <input
        id="username"
        v-model="username"
        type="text"
        placeholder="Enter your username"
        required
      />

      <label for="password">Password</label>
      <input
        id="password"
        v-model="password"
        type="password"
        placeholder="Enter your password"
        required
      />

      <button type="submit">Register</button>

      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useRouter } from 'vue-router'

const router = useRouter()

const email = ref("")
const username = ref("")
const password = ref("")
const error = ref("")

const register = async () => {
  error.value = ""

  try {
    const response = await fetch("http://localhost:8080/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email.value,
        username: username.value,
        password: password.value
      })
    })

    const data = await response.json()

    if (!response.ok) {
      throw new Error(data.message || "Registration failed")
    }

    sessionStorage.setItem("token", data.token)
    sessionStorage.setItem("employeeId", data.employeeId)
    sessionStorage.setItem("email", data.email)
    sessionStorage.setItem("username", data.username)
 
    router.push('/joinResturant')
 
  } catch (err) {
    error.value = err.message
  }
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #2e2727;
  font-family: Arial, sans-serif;
  color: #e3db02;
}

.login-box {
  background: #2e2727;
  padding: 2rem;
  border-radius: 12px;
  width: 320px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.3);
}

h1 {
  margin-bottom: 1rem;
  text-align: center;
}

label {
  display: block;
  margin-top: 1rem;
  margin-bottom: 0.3rem;
}

input {
  width: 100%;
  padding: 0.7rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-sizing: border-box;
}

button {
  width: 100%;
  margin-top: 1.2rem;
  padding: 0.8rem;
  border: none;
  background: #e3db02;
  color: #2e2727;
  font-size: 1rem;
  border-radius: 8px;
  cursor: pointer;
}


.error {
  color: red;
  margin-top: 1rem;
  text-align: center;
}
</style>