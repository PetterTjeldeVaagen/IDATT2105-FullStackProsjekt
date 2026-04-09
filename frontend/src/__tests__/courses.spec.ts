import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { createRouter, createMemoryHistory } from 'vue-router'
import Course from '@/views/courses.vue'

global.fetch = vi.fn()

function createTestRouter() {
  return createRouter({
    history: createMemoryHistory(),
    routes: [],
  })
}

describe('courses.vue', () => {
  it('renders fetched courses', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => ([
        { courseId: 1, name: 'Hygiene Basics', description: 'Learn the basics of hygiene', expirationDate: '2024-12-31' },
        { courseId: 2, name: 'Advanced Hygiene', description: 'Deep dive into hygiene practices', expirationDate: '2025-12-31' }
      ])
    })

    const wrapper = mount(Course, {
      global: {
        plugins: [createTestRouter()]
      }
    })
    await flushPromises()

    expect(wrapper.text()).toContain('Hygiene Basics')
    expect(wrapper.text()).toContain('Advanced Hygiene')
  })

  it('handles empty course list', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => []
    })

    const wrapper = mount(Course, {
      global: {
        plugins: [createTestRouter()]
      }
    })
    await flushPromises()

    expect(wrapper.exists()).toBe(true)
  })
})