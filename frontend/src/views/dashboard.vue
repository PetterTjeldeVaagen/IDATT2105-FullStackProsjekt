<script setup>
import { ref, onMounted } from "vue"
import { useRouter } from "vue-router"
import Navbar from "@/components/navbar.vue"
import taskComponent from "@/components/tasks/taskComponent.vue"
import courseComponent from "@/components/courses/courseComponent.vue"
import deviationComponent from "@/components/deviations/deviationComponent.vue"
import employeeComponent from "@/components/employees/employeeComponent.vue"

const router = useRouter()

const token = ref(sessionStorage.getItem("token") || "")
const employeeId = ref(sessionStorage.getItem("employeeId") || "")
const email = ref(sessionStorage.getItem("email") || "")
const username = ref(sessionStorage.getItem("username") || "")

const tasks = ref([])
const courses = ref([])
const allCourses = ref([])
const employees = ref([])
const todaysTasks = ref([])
const deviations = ref([])
const error = ref("")

async function getTasks() {
  try {
    const response = await fetch(`http://localhost:8080/task/getTaskByEmployee/${employeeId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    tasks.value = await response.json()

    tasks.value.sort((a, b) => new Date(a.finishBy) - new Date(b.finishBy))
  } catch (err) {
    console.error("Error while fetching tasks:", err)
    error.value = err.message
  }
}

const isManager = ref(false)
const resturantName = ref("")
const resturantId = ref(sessionStorage.getItem("restaurantId") || "")
const joinCode = ref("")
async function getResturantInfo() {
  try {
    const response = await fetch(`http://localhost:8080/resturant/getResturantByEmployeeId/${employeeId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    const resturantInfo = await response.json()

    resturantName.value = resturantInfo.name
    resturantId.value = resturantInfo.resturantId
    sessionStorage.setItem("restaurantId", resturantInfo.resturantId)
    joinCode.value = resturantInfo.joinCode

    try {
      const managerResponse = await fetch(`http://localhost:8080/resturant/getManagers/${resturantId.value}`, {
        headers: {
          Authorization: `Bearer ${token.value}`
        }
      })

      if (!managerResponse.ok) {
        const text = await managerResponse.text()
        throw new Error(text || `HTTP error ${managerResponse.status}`)
      }

      const managers = await managerResponse.json()
      isManager.value = managers.some(manager => manager.employeeId === Number(employeeId.value))
    } catch (err) {
      console.error("Error while fetching managers:", err)
    }
  } catch (err) {
    console.error("Error while fetching restaurant info:", err)
  }

}

async function getCourses() {
  try {
    const response = await fetch(`http://localhost:8080/course/getCoursesByEmployee/${employeeId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    courses.value = await response.json()
    courses.value.sort((b, a) => new Date(b.expirationDate) - new Date(a.expirationDate))
  } catch (err) {
    console.error("Error while fetching courses:", err)
    error.value = err.message
  }
}

async function getDeviations() {
  try {
    const response = await fetch(`http://localhost:8080/deviation/getDeviationByResturant/${resturantId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    deviations.value = await response.json()
    deviations.value.sort((b, a) => new Date(b.deviationDate) - new Date(a.deviationDate))
  } catch (err) {
    console.error("Error while fetching deviations:", err)
    error.value = err.message
  }
}

async function getEmployees() {
  try {
    const response = await fetch(`http://localhost:8080/employee/getEmployeesByResturant/${resturantId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    employees.value = await response.json()
  } catch (err) {
    console.error("Error while fetching employees:", err)
    error.value = err.message
  }
}

async function getAllCourses() {
  try {
    const response = await fetch(`http://localhost:8080/course/getCoursesByResturant/${resturantId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    allCourses.value = await response.json()
  } catch (err) {
    console.error("Error while fetching all courses:", err)
    error.value = err.message
  }
}

async function getTodaysTask() {
  try {
    const response = await fetch(`http://localhost:8080/task/getTasksByResturant/${resturantId.value}`, {
      headers: {
        Authorization: `Bearer ${token.value}`
      }
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `HTTP error ${response.status}`)
    }

    todaysTasks.value = await response.json()
    const now = new Date()
    todaysTasks.value = todaysTasks.value.filter(task => {
      const finishBy = new Date(task.finishBy)
      return finishBy.toDateString() === now.toDateString()
    })
  } catch (err) {
    console.error("Error while fetching today's tasks:", err)
  }
}

onMounted(async () => {
  await getResturantInfo()
  if(isManager.value) {
    await getEmployees()
    await getAllCourses()
    await getTodaysTask()
  } else {
    await getTasks()
    await getCourses()
  }
  await getDeviations()
})

</script>
<template>
  <div class="dashboard">
    <Navbar />
    <h1>Welcome to the dashboard, {{ username }}!</h1>
    <h2>{{ resturantName }}</h2>

    <div id="managerSection" v-if="isManager">
      <div id="joinCodeCard" class="dashboardCard">
        <h2>Restaurant join code: {{ joinCode }}</h2>
      </div>

      <div id="managerGrid">
          <div id="taskForToday" class="dashboardCard">
            <h2>Tasks that need to be finished today</h2>
            <p v-if="error" class="error">{{ error }}</p>
            <ul v-else class="tasks">
              <li v-for="task in todaysTasks" :key="task.taskId">
                <taskComponent :task="task" @taskUpdated="getTodaysTask" />
              </li>
            </ul>
          </div>

          <div id="managerDeviations" class="dashboardCard">
            <h2>Recently registered deviations</h2>
            <p v-if="error" class="error">{{ error }}</p>
            <ul v-else class="deviations">
              <li v-for="deviation in deviations" :key="deviation.deviationId">
                <deviationComponent :deviation="deviation" @deviationUpdated="getDeviations" />
              </li>
            </ul>
          </div>

          <div id="coursesClosestToExpiring" class="dashboardCard">
            <h2>Employees' courses close to expiring</h2>
            <p v-if="error" class="error">{{ error }}</p>
            <ul v-else class="courses">
              <li v-for="course in allCourses" :key="course.courseId">
                <courseComponent :course="course" @courseUpdated="getAllCourses" />
              </li>
            </ul>
          </div>

          <div id="employeeList" class="dashboardCard">
            <h2>Employees in your restaurant</h2>
            <p v-if="error" class="error">{{ error }}</p>
            <ul v-else class="employees">
              <li v-for="employee in employees" :key="employee.employeeId">
                <employeeComponent :employee="employee" @employeeRemoved="getEmployees" />
              </li>
            </ul>
          </div>
        </div>
      </div>

    <div id="notManager" v-if="!isManager">
        <div id="employeeTasks">
          <h2>Your pending and overdue tasks</h2>
          <p v-if="error" class="error">{{ error }}</p>
          <ul v-else class="tasks">
            <li v-for="task in tasks.filter(task => task.status === 'PENDING' || task.status === 'OVERDUE')" :key="task.taskId">
              <taskComponent :task="task" @taskUpdated="getTasks" />
            </li>
          </ul>
        </div>

        <div id="employeeCourses">
          <h2>Your completed courses</h2>
          <p v-if="error" class="error">{{ error }}</p>
          <ul v-else class="courses">
            <li v-for="course in courses" :key="course.courseId">
              <courseComponent :course="course" @courseUpdated="getCourses" />
            </li>
          </ul>
        </div>

      <div class="recentDeviations">
        <h2>Recently registered deviations</h2>
        <p v-if="error" class="error">{{ error }}</p>
        <ul v-else class="deviations">
          <li v-for="deviation in deviations" :key="deviation.deviationId">
            <deviationComponent :deviation="deviation" @deviationUpdated="getDeviations" />
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.dashboard {
  padding: 1rem;
  box-sizing: border-box;
  min-height: 100vh;
}

h2 {
  margin: 0 0 1rem 0;
  padding-left: 0;
}

#managerSection {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

#joinCodeCard {
  max-width: 24rem;
}

#managerGrid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-auto-rows: minmax(260px, 1fr);
  gap: 1rem;
  height: calc(100vh - 220px);
}

#notManager {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas:
    "tasks deviations"
    "courses deviations";
  gap: 1rem;
  height: calc(100vh - 220px);
}

#employeeTasks {
  grid-area: tasks;
}

#employeeCourses {
  grid-area: courses;
}

#notManager .recentDeviations {
  grid-area: deviations;
}

.dashboardCard,
#employeeTasks,
#employeeCourses,
#notManager .recentDeviations,
#taskForToday,
#managerDeviations,
#coursesClosestToExpiring,
#employeeList,
#joinCodeCard {
  border: yellow 2px solid;
  border-radius: 8px;
  padding: 1rem;
  box-sizing: border-box;
  min-width: 0;
  min-height: 0;
  display: flex;
  flex-direction: column;
}

.tasks,
.courses,
.deviations,
.employees {
  list-style-type: none;
  padding: 0;
  margin: 0;
  overflow-y: auto;
  flex: 1;
  min-height: 0;
}

.employees li {
  padding: 0.5rem 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.15);
}
</style>
