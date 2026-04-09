import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { createRouter, createMemoryHistory } from 'vue-router'
import Deviations from '@/views/deviations.vue'

global.fetch = vi.fn()

function createTestRouter() {
  return createRouter({
    history: createMemoryHistory(),
    routes: [],
  })
}

describe('deviations.vue', () => {
  it('renders fetched deviations', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => ([
        { deviationId: 1, title: 'Fridge too warm', description: 'The fridge is too warm' },
        { deviationId: 2, title: 'Expired food', description: 'Some food items are expired' }
      ])
    })

    const wrapper = mount(Deviations, {
      global: {
        plugins: [createTestRouter()]
      }
    })
    await flushPromises()

    expect(wrapper.text()).toContain('Fridge too warm')
    expect(wrapper.text()).toContain('Expired food')
  })

  it('handles empty deviation list', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => []
    })

    const wrapper = mount(Deviations, {
      global: {
        plugins: [createTestRouter()]
      }
    })
    await flushPromises()

    expect(wrapper.exists()).toBe(true)
  })
})