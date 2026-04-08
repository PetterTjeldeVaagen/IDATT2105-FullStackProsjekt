<script setup>
import { ref } from "vue"
import { useRouter } from 'vue-router'

const router = useRouter()
const resturantCode = ref("")
const resturantName = ref("")
const employeeId = ref(Number(sessionStorage.getItem("employeeId")) || null)

function joinResturant() {
    try {
        if (!employeeId.value) {
            alert("You must be logged in to join a resturant.")
            return
        }

        fetch(`http://localhost:8080/resturant/joinResturant/${resturantCode.value}/${employeeId.value}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${sessionStorage.getItem("token")}`
            }
        })
        .then(response => {
            if (response.ok) {
                router.push('/dashboard')
            } else {
                alert("Failed to join resturant. Please check the code and try again.")
            }
        })
    } catch (error) {
        console.error("Error joining resturant:", error)
        alert("An error occurred while trying to join the resturant. Please try again later.")
    }
}

function createResturant() {
    try {
        console.log("Attempting to create resturant with name:", resturantName.value)
        if (!employeeId.value) {
            alert("You must be logged in to create a resturant.")
            return
        }

        fetch(`http://localhost:8080/resturant/createResturant`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${sessionStorage.getItem("token")}`
            },
            body: JSON.stringify({
                name: resturantName.value,
                employeeId: employeeId.value
            })
        })
        .then(response => {
            if (response.ok) {
                router.push('/dashboard')
            } else {
                alert("Failed to create resturant. Please try again.")
            }
        })
    } catch (error) {
        console.error("Error creating resturant:", error)
        alert("An error occurred while trying to create the resturant. Please try again later.")
    }
}
</script>

<template>
  <div class="join-resturant">
    <h1>Join a Resturant</h1>
    <p>To join a resturant, please contact your manager and ask for the resturant code.</p>
    <p>Once you have the code, please enter it below to join the resturant.</p>
    <input v-model="resturantCode" type="text" placeholder="Enter resturant code" />
    <button @click="joinResturant">Join Resturant</button>


    <h2>Don't have a resturant to join?</h2>
    <p>You can create a new resturant by entering a name for your resturant and clicking the "Create Resturant" button.</p>
    <form>
        <label for="resturantName">Create a new Resturant</label>
        <input v-model="resturantName" type="text" placeholder="Enter resturant name" />
        <button type="button" @click="createResturant">Create Resturant</button>
    </form>
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