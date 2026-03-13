import { ref, watch } from 'vue'

export function useTheme() {
  const theme = ref(localStorage.getItem('theme') || 'light')

  const setTheme = (newTheme: 'light' | 'dark') => {
    theme.value = newTheme
    localStorage.setItem('theme', newTheme)
    document.documentElement.setAttribute('data-theme', newTheme)
  }

  const toggleTheme = () => {
    setTheme(theme.value === 'light' ? 'dark' : 'light')
  }

  // 初始化主题
  setTheme(theme.value as 'light' | 'dark')

  // 监听系统主题变化
  const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)')
  mediaQuery.addEventListener('change', (e) => {
    if (!localStorage.getItem('theme')) {
      setTheme(e.matches ? 'dark' : 'light')
    }
  })

  return {
    theme,
    setTheme,
    toggleTheme
  }
}
