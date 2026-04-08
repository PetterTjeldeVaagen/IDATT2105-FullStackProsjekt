<script setup>
import { ref } from "vue"
import createCourseComponent from "@/components/courses/createCourseComponent.vue"

const showCreateCourse = ref(false)
const emit = defineEmits(["courseUpdated"])

const props = defineProps({
  course: {
    type: Object,
    required: true
  }
})

async function DeleteCourse() {
  const confirmed = window.confirm(
    `Are you sure you want to delete "${props.course.name}"?`
  )

  if (!confirmed) return
  try {
    const token = sessionStorage.getItem("token") || ""
    const response = await fetch(`http://localhost:8080/course/deleteCourse/${props.course.courseId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    emit("courseUpdated", props.course.courseId)
  } catch (err) {
    console.error("Error while deleting course:", err)
  }
}

function EditCourse() {
  showCreateCourse.value = true
}

</script>

<template>
 <createCourseComponent v-if="showCreateCourse" :course="props.course" @cancel="showCreateCourse = false" @courseUpdated="showCreateCourse = false; emit('courseUpdated')" />
  <div class="course" v-else>
    <div class="actions" v-if="$route.name === 'courses'">
      <button @click="EditCourse">Edit</button>
      <button @click="DeleteCourse">Delete</button>
    </div>
    <div class="info">
      <h2>{{ props.course.name }}</h2>
      <p>{{ props.course.description }}</p>
      <p>Completed On: {{ new Date(props.course.completionDate).toLocaleDateString() }}</p>
      <p>Expires On: {{ new Date(props.course.expirationDate).toLocaleDateString() }}</p>
    </div>
  </div>
</template>

<style scoped>
    .course {
        background-color: #5c5757;
        border: 1px solid #e3db02;
        border-radius: 8px;
        padding: 1rem;
        margin-bottom: 1rem;
        display: flex;
        font-family: Arial, Helvetica, sans-serif;
        width: 90%;
    }

    .info {
      float: left;
      margin-left: 2rem;
      margin-right: 2rem;
    }

    .actions {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .actions button {
      background-color: #e3db02;
      color: #000000;
      border: black solid 1px;
      padding: 0.5rem 1rem;
      border-radius: 6px;
      font-size: 1rem;
      cursor: pointer;
      margin-bottom: 0.5rem;
    }
</style>