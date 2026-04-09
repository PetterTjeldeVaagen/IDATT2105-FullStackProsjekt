import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { createRouter, createMemoryHistory } from 'vue-router'
import Tasks from '@/views/tasks.vue'

global.fetch = vi.fn()

function createTestRouter() {
  return createRouter({
    history: createMemoryHistory(),
    routes: [
      { path: '/tasks', name: 'tasks', component: Tasks },
    ],
  })
}

describe('tasks.vue', () => {
  beforeEach(() => {
    vi.clearAllMocks()
    sessionStorage.clear()
    sessionStorage.setItem('token', 'fake-token')
    sessionStorage.setItem('employeeId', '1')
  })

  it('renders fetched tasks', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => ([
        {
          taskId: 1,
          name: 'Clean kitchen',
          description: 'Do it today',
          status: 'PENDING',
          finishBy: '2026-04-10',
        },
        {
          taskId: 2,
          name: 'Check inventory',
          description: 'Before closing',
          status: 'COMPLETED',
          finishBy: '2026-04-11',
        }
      ])
    })

    const router = createTestRouter()
    router.push('/tasks')
    await router.isReady()

    const wrapper = mount(Tasks, {
      global: {
        plugins: [router],
      },
    })

    await flushPromises()

    expect(wrapper.text()).toContain('Clean kitchen')
    expect(wrapper.text()).toContain('Check inventory')
  })
})