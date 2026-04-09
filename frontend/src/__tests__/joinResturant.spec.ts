import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import JoinResturant from '@/views/joinResturant.vue'

global.fetch = vi.fn()

describe('joinResturant.vue', () => {
  it('renders join restaurant view', async () => {
    const wrapper = mount(JoinResturant)

    expect(wrapper.find('input').exists()).toBe(true)
    expect(wrapper.find('button').exists()).toBe(true)
  })
})