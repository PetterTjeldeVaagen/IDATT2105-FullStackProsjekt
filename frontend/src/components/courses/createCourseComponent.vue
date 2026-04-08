<script setup>
import { ref, onMounted } from "vue"

    const props = defineProps({
        course: {
            type: Object,
            default: null
        }
    })

    const name = ref("")
    const description = ref("")
    const completionDate = ref("")
    const expirationDate = ref("")
    const token = sessionStorage.getItem("token") || ""
    const employeeId = ref(sessionStorage.getItem("employeeId") || "")
    const emit = defineEmits(["courseCreated", "cancel", "courseUpdated"])

    function fillForm(course) {
        if (!course) return

        name.value = course.name || ""
        description.value = course.description || ""
        completionDate.value = course.completionDate ? course.completionDate.split("T")[0] : ""
        expirationDate.value = course.expirationDate ? course.expirationDate.split("T")[0] : ""
    }

    onMounted(() => {
      fillForm(props.course)
    })

    async function createCourse() {
        const courseData = {
            name: name.value,
            description: description.value,
            employeeId: Number(employeeId.value),
            completionDate: completionDate.value,
            expirationDate: expirationDate.value,
        }

        try {
            const response = await fetch("http://localhost:8080/course/createCourse", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(courseData)
            })

            if (!response.ok) {
                const text = await response.text()
                throw new Error(text || `HTTP error ${response.status}`)
            }

            emit('courseCreated')
            emit('cancel')
        } catch (err) {
            console.error("Error while creating course:", err)
        }
    }

    async function updateCourse() {
        const courseData = {
            name: name.value,
            description: description.value,
            completionDate: completionDate.value,
            expirationDate: expirationDate.value,
            employeeId: Number(employeeId.value),
        }

        try {
            const response = await fetch(`http://localhost:8080/course/updateCourse/${props.course.courseId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(courseData)
            })

            if (!response.ok) {
                const text = await response.text()
                throw new Error(text || `HTTP error ${response.status}`)
            }

            emit('courseUpdated')
            emit('cancel')
        } catch (err) {
            console.error("Error while updating course:", err)
        }
    }
</script>

<template>
  <form class="course-creation-box" @submit.prevent="props.course ? updateCourse() : createCourse()">
    <label for="name">Course Name</label>
    <input id="name" v-model="name" type="text" placeholder="Enter course name" required />
    <label for="description">Description</label>
    <textarea id="description" v-model="description" placeholder="Enter course description" required></textarea>
    <label for="completionDate">Completion Date</label>
    <input id="completionDate" v-model="completionDate" type="date" required />
    <label for="expirationDate">Expiration Date</label>
    <input id="expirationDate" v-model="expirationDate" type="date" required />

    <button type="submit">{{ props.course ? "Update Course" : "Create Course" }}</button>
    <button type="button" @click="$emit('cancel')">Cancel</button>
  </form>
</template>

<style scoped>
    .course-creation-box {
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