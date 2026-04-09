import { describe, it, expect, beforeEach, vi, afterEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { createRouter, createMemoryHistory } from 'vue-router'
import Dashboard from '@/views/dashboard.vue'

describe('dashboard.vue', () => {
  beforeEach(() => {
    vi.clearAllMocks()
    sessionStorage.clear()

    sessionStorage.setItem('token', 'fake-token')
    sessionStorage.setItem('employeeId', '1')
    sessionStorage.setItem('username', 'Test User')

    vi.stubGlobal(
      'fetch',
      vi.fn(async (input: RequestInfo | URL) => {
        const url = String(input)

        if (url.includes('/resturant/getResturantByEmployeeId/')) {
          return {
            ok: true,
            json: async () => ({
              name: 'Test Restaurant',
              resturantId: 10,
              joinCode: 'ABC123',
            }),
          } as Response
        }

        if (url.includes('/resturant/getManagers/')) {
          return {
            ok: true,
            json: async () => [],
          } as Response
        }

        if (url.includes('/task/getTaskByEmployee/')) {
          return {
            ok: true,
            json: async () => [
              {
                taskId: 1,
                name: 'Check freezer',
                description: 'Daily routine',
                status: 'PENDING',
                finishBy: '2026-04-10',
              },
            ],
          } as Response
        }

        if (url.includes('/course/getCoursesByEmployee/')) {
          return {
            ok: true,
            json: async () => [
              {
                courseId: 1,
                name: 'Hygiene Course',
                description: 'Basic hygiene',
                completionDate: '2026-04-01',
                expirationDate: '2026-12-31',
              },
            ],
          } as Response
        }

        if (url.includes('/deviation/getDeviationByResturant/')) {
          return {
            ok: true,
            json: async () => [
              {
                deviationId: 1,
                title: 'Broken fridge',
                description: 'Fridge door is broken',
                date: '2026-04-09',
              },
            ],
          } as Response
        }

        return {
          ok: true,
          json: async () => [],
        } as Response
      })
    )
  })

  afterEach(() => {
    vi.unstubAllGlobals()
  })

  function createTestRouter() {
    return createRouter({
      history: createMemoryHistory(),
      routes: [
        { path: '/dashboard', name: 'dashboard', component: Dashboard },
      ],
    })
  }

  it('shows user and dashboard data', async () => {
    const router = createTestRouter()
    router.push('/dashboard')
    await router.isReady()

    const wrapper = mount(Dashboard, {
      global: {
        plugins: [router],
      },
    })

    await flushPromises()

    const text = wrapper.text()
    expect(text).toContain('Test User')
    expect(text).toContain('Check freezer')
    expect(text).toContain('Hygiene Course')
    expect(text).toContain('Broken fridge')
    expect(text).toContain('Test Restaurant')
  })
})