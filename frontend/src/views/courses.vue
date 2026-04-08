<script setup>
import Navbar from '@/components/navbar.vue';
import courseComponent from '@/components/courses/courseComponent.vue';
import createCourseComponent from '@/components/courses/createCourseComponent.vue';
import { onMounted, ref } from 'vue';

const courses = ref([])
const showCreateCourse = ref(false)
const error = ref("")

async function getCourses() {
  try {
    const token = sessionStorage.getItem("token") || ""
    const employeeId = sessionStorage.getItem("employeeId") || ""
    const response = await fetch(`http://localhost:8080/course/getCoursesByEmployee/${employeeId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP-feil ${response.status}`)
    }

    courses.value = await response.json()
    courses.value.sort((a, b) => new Date(a.dateRegistered) - new Date(b.dateRegistered))
  } catch (err) {
    console.error("Error while fetching courses:", err)
    error.value = err.message
  }
}

onMounted(() => {
    getCourses()
})
</script>

<template>
  <div class="courses">
    <Navbar />
    <h1>Course Management</h1>

    <button class="yellowButton" @click="showCreateCourse = true">New Course</button>

     <p v-if="error" class="error">{{ error }}</p>

    <div id="employeeCourses">
      <div class="courseLists">
        <h2>My registered Courses</h2>
        <ul class="courses">
          <li v-for="course in courses" :key="course.courseId">
            <courseComponent :course="course" @courseUpdated="getCourses" />
          </li>
        </ul>
      </div>
    </div>

    <createCourseComponent v-if="showCreateCourse" @cancel="showCreateCourse = false" @courseCreated="getCourses" />
  </div>
</template>

<style scoped>
  .courses {
      list-style-type: none;
      padding: 1rem;
    }

    .yellowButton {
      background-color: #e3db02;
      color: #000000;
      border: none;
      padding: 0.5rem 1rem;
      border-radius: 6px;
      font-size: 1rem;
      cursor: pointer;
      margin-left: 1rem;
      margin-bottom: 1rem;
    }

    #employeeCourses {
      display: flex;
      gap: 2rem;
      margin-left: 1rem;
      align-items: flex-start;
    }

    .courseLists {
      border: yellow 3px solid;
      border-radius: 8px;
      padding: 1rem;
      width: 32%;
      height: 30rem;
      overflow-y: auto;
      box-sizing: border-box;
      list-style: none;
      margin: 0;
      padding: 1rem;
    }

    .courseLists li {
      width: 100%;
      margin-bottom: 1rem;
    }
</style>