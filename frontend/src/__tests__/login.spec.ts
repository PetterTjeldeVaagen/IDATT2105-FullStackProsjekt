import { describe, it, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import LogIn from '@/views/logIn.vue'

global.fetch = vi.fn()

describe('logIn.vue', () => {
  it('renders login form', () => {
    const wrapper = mount(LogIn)

    expect(wrapper.find('input[type="email"]').exists()).toBe(true)
    expect(wrapper.find('input[type="password"]').exists()).toBe(true)
    expect(wrapper.find('button').exists()).toBe(true)
  })

  it('submits login form successfully', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: true,
      json: async () => ({
        token: 'fake-token',
        employeeId: 1,
        email: 'test@example.com',
        name: 'Test User'
      })
    })

    const wrapper = mount(LogIn)

    await wrapper.find('input[type="email"]').setValue('test@example.com')
    await wrapper.find('input[type="password"]').setValue('password123')
    await wrapper.find('form').trigger('submit.prevent')

    await flushPromises()

    expect(fetch).toHaveBeenCalled()
  })

  it('shows error message on failed login', async () => {
    ;(fetch as any).mockResolvedValueOnce({
      ok: false,
      json: async () => ({
        message: 'Wrong password or email'
      })
    })

    const wrapper = mount(LogIn)

    await wrapper.find('input[type="email"]').setValue('wrong@example.com')
    await wrapper.find('input[type="password"]').setValue('wrongpass')
    await wrapper.find('form').trigger('submit.prevent')

    await flushPromises()

    expect(wrapper.text()).toContain('Wrong password or email')
  })
})