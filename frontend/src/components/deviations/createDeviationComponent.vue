<script setup>
import { ref, onMounted } from "vue"

    const props = defineProps({
        deviation: {
            type: Object,
            default: null
        }
    })

    const title = ref("")
    const description = ref("")
    const date = ref("")
    const token = sessionStorage.getItem("token") || ""
    const employeeId = ref(sessionStorage.getItem("employeeId") || "")
    const emit = defineEmits(["deviationCreated", "cancel", "deviationUpdated"])

    function fillForm(deviation) {
        if (!deviation) return

        title.value = deviation.title || ""
        description.value = deviation.description || ""
        date.value = deviation.date ? deviation.date.split("T")[0] : ""
    }

    onMounted(() => {
      fillForm(props.deviation)
    })

    async function createDeviation() {
        const deviationData = {
            title: title.value,
            description: description.value,
            registeredBy: Number(employeeId.value),
            date: date.value,
        }

        try {
            const response = await fetch("http://localhost:8080/deviation/createDeviation", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(deviationData)
            })

            if (!response.ok) {
                const text = await response.text()
                throw new Error(text || `HTTP error ${response.status}`)
            }

            emit('deviationCreated')
            emit('cancel')
        } catch (err) {
            console.error("Error while creating deviation:", err)
        }
    }

    async function updateDeviation() {
        const deviationData = {
            title: title.value,
            description: description.value,
            date: date.value,
            registeredBy: Number(employeeId.value),
        }

        try {
            const response = await fetch(`http://localhost:8080/deviation/updateDeviation/${props.deviation.deviationId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(deviationData)
            })

            if (!response.ok) {
                const text = await response.text()
                throw new Error(text || `HTTP error ${response.status}`)
            }

            emit('deviationUpdated')
            emit('cancel')
        } catch (err) {
            console.error("Error while updating deviation:", err)
        }
    }
</script>

<template>
  <form class="deviation-creation-box" @submit.prevent="props.deviation ? updateDeviation() : createDeviation()">
    <label for="title">Deviation Name</label>
    <input id="title" v-model="title" type="text" placeholder="Enter deviation name" required />
    <label for="description">Description</label>
    <textarea id="description" v-model="description" placeholder="Enter deviation description" required></textarea>
    <label for="date">Time of deviation</label>
    <input id="date" v-model="date" type="date" required />

    <button type="submit">{{ props.deviation ? "Update Deviation" : "Create Deviation" }}</button>
    <button type="button" @click="$emit('cancel')">Cancel</button>
  </form>
</template>

<style scoped>
    .deviation-creation-box {
        display: flex;
        flex-direction: column;
        gap: 1rem;
        padding: 1rem;
        background-color: #5c5757;
        border-radius: 8px;
        border: #e3db02 solid 3px;
        position: fixed;
        
        margin: auto;
        min-width: 10%;
        width: fit-content;
        height: fit-content;
        inset: 0;
        font-family: Arial, Helvetica, sans-serif;

        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 9999;
    }
</style>