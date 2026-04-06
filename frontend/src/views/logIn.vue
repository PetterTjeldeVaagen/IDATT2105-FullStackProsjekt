<template>
  <div class="login-container">
    <form class="login-box" @submit.prevent="login">
      <h1>Logg inn</h1>

      <div>
        <p>ola@test.no</p>
        <p>Ola Nordmann</p>
        <p>pass123</p>
      </div>

      <label for="email">E-post</label>
      <input
        id="email"
        v-model="email"
        type="email"
        placeholder="Skriv inn epostadresse"
        required
      />

      <label for="username">Brukernavn</label>
      <input
        id="username"
        v-model="username"
        type="text"
        placeholder="Skriv inn brukernavn"
        required
      />

      <label for="password">Passord</label>
      <input
        id="password"
        v-model="password"
        type="password"
        placeholder="Skriv inn passord"
        required
      />

      <button type="submit">Logg inn</button>

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

const login = async () => {
  error.value = ""

  try {
    const response = await fetch("http://localhost:8080/auth/login", {
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
      throw new Error(data.message || "Innlogging feilet")
    }

    sessionStorage.setItem("token", data.token)
    sessionStorage.setItem("employeeId", data.employeeId)
    sessionStorage.setItem("email", data.email)
 
    router.push('/dashboard')
 
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
  background: #f4f4f4;
}

.login-box {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  width: 320px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
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
  background: #42b883;
  color: white;
  font-size: 1rem;
  border-radius: 8px;
  cursor: pointer;
}

button:hover {
  background: #369f6e;
}

.error {
  color: red;
  margin-top: 1rem;
  text-align: center;
}
</style>