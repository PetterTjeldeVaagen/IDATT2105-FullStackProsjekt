import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import register from '@/views/register.vue'

global.fetch = vi.fn()

describe('register.vue', () => {
  it('renders register form', () => {
    const wrapper = mount(register)

    expect(wrapper.find('input[type="email"]').exists()).toBe(true)
    expect(wrapper.find('input[id="username"]').exists()).toBe(true)
    expect(wrapper.find('input[type="password"]').exists()).toBe(true)
    expect(wrapper.find('button').exists()).toBe(true)
  })

  it('submits register form successfully', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => ({
        token: 'fake-token',
        employeeId: 1,
        email: 'test@example.com',
        name: 'Test User'
      })
    })

    const wrapper = mount(register)

    await wrapper.find('input[type="email"]').setValue('test@example.com')
    await wrapper.find('input[id="username"]').setValue('Test User')
    await wrapper.find('input[type="password"]').setValue('password123')
    await wrapper.find('form').trigger('submit.prevent')

    await flushPromises()

    expect(fetch).toHaveBeenCalled()
  })
})