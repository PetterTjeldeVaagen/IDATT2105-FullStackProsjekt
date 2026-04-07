<script setup>
    import { ref } from "vue"
    const frequencyOptions = [
        { value: 'daily', label: 'Daily' },
        { value: 'weekly', label: 'Weekly' },
        { value: 'biweekly', label: 'Biweekly' },
        { value: 'monthly', label: 'Monthly' },
        { value: 'bimonthly', label: 'Bimonthly' },
        { value: 'quarterly', label: 'Quarterly' },
        { value: 'everySixMonths', label: 'Every Six Months' },
        { value: 'yearly', label: 'Yearly' },
        { value: 'biannually', label: 'Biannually' }
    ]

    const name = ref("")
    const description = ref("")
    const finishBy = ref("")
    const recurring = ref(false)
    const recurringFrequency = ref("daily")
    const token = sessionStorage.getItem("token") || ""
    const employeeId = ref(sessionStorage.getItem("employeeId") || "")
    const category = ref("")
    const emit = defineEmits(["taskCreated", "cancel"])

    console.log("token fra createTask:", token)
    console.log("employeeId fra createTask:", employeeId.value)

    async function createTask() {
        const taskData = {
            name: name.value,
            description: description.value,
            finishBy: finishBy.value,
            recurring: recurring.value,
            recurringFrequency: recurringFrequency.value,
            assignedTo: Number(employeeId.value),
            status: "PENDING",
            category: category.value
        }

        try {
            console.log("taskData:", JSON.stringify(taskData, null, 2))
            const response = await fetch("http://localhost:8080/task/createTask", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(taskData)
            })

            if (!response.ok) {
                const text = await response.text()
                throw new Error(text || `HTTP error ${response.status}`)
            }

            emit('taskCreated')
            emit('cancel')
        } catch (err) {
            console.error("Error while creating task:", err)
        }
    }
</script>

<template>
  <form class="task-creation-box" @submit.prevent="createTask">
    <label for="name">Task Name</label>
    <input id="name" v-model="name" type="text" placeholder="Enter task name" required />
    <label for="description">Description</label>
    <textarea id="description" v-model="description" placeholder="Enter task description" required></textarea>
    <label for="finishBy">Finish By</label>
    <input id="finishBy" v-model="finishBy" type="date" required />

    <label for="category">Category</label>
    <input id="category" v-model="category" type="text" placeholder="Enter task category" required />

    <label for="recurring">Recurring</label>
    <input for="recurring" type="checkbox" id="recurring" v-model="recurring" />

    <label for="recurringFrequency">Recurring Frequency</label>
    <select id="recurringFrequency" v-model="recurringFrequency" :disabled="!recurring">
      <option v-for="option in frequencyOptions" :key="option.value" :value="option.value">
        {{ option.label }}
      </option>
    </select>

    <button type="submit">Create Task</button>
    <button type="button" @click="$emit('cancel')">Cancel</button>
  </form>
</template>

<style scoped>
    .task-creation-box {
        display: flex;
        flex-direction: column;
        gap: 1rem;
        padding: 1rem;
        background-color: #5c5757;
        border-radius: 8px;
        border: #e3db02 solid 3px;
        position: fixed;
        margin-top: 12rem;
        margin-bottom: 12rem;
        margin-left: 35rem;
        margin-right: 35rem;
        inset: 0;
        font-family: Arial, Helvetica, sans-serif;

        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 9999;
    }
</style>