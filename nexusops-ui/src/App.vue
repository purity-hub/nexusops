<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { useTheme } from './composables/useTheme'

const { theme, toggleTheme } = useTheme()
</script>

<template>
  <div id="app">
    <header class="app-header">
      <nav class="nav-container">
        <div class="nav-brand">
          <h1>NexusOps</h1>
        </div>
        <div class="nav-right">
          <el-button
            @click="toggleTheme"
            size="small"
            circle
            :icon="theme === 'light' ? 'Moon' : 'Sunny'"
            class="theme-toggle"
          />
          <ul class="nav-menu">
            <li><RouterLink to="/" class="nav-link">首页</RouterLink></li>
            <li><RouterLink to="/login" class="nav-link">登录</RouterLink></li>
            <li><RouterLink to="/register" class="nav-link">注册</RouterLink></li>
            <li><RouterLink to="/about" class="nav-link">关于</RouterLink></li>
          </ul>
        </div>
      </nav>
    </header>

    <main class="main-content">
      <RouterView v-slot="{ Component }">
        <transition name="page" mode="out-in">
          <component :is="Component" />
        </transition>
      </RouterView>
    </main>
  </div>
</template>

<style scoped>
#app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.app-header {
  background: var(--glass-bg);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-bottom: 1px solid var(--glass-border);
  position: sticky;
  top: 0;
  z-index: 100;
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 var(--spacing-lg);
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 64px;
}

.nav-brand h1 {
  margin: 0;
  font-size: var(--font-size-2xl);
  font-weight: 700;
  background: linear-gradient(135deg, var(--primary-color), #f093fb);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.nav-right {
  display: flex;
  align-items: center;
}

.theme-toggle {
  margin-right: var(--spacing-md);
}

.nav-menu {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  gap: var(--spacing-lg);
}

.nav-link {
  color: var(--text-primary);
  text-decoration: none;
  font-weight: 500;
  padding: var(--spacing-sm) var(--spacing-md);
  border-radius: var(--radius-md);
  transition: all var(--transition-fast);
  position: relative;
}

.nav-link:hover {
  color: var(--primary-color);
  background: rgba(64, 158, 255, 0.1);
}

.nav-link.router-link-active {
  color: var(--primary-color);
  background: rgba(64, 158, 255, 0.1);
}

.nav-link.router-link-active::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 20px;
  height: 2px;
  background: var(--primary-color);
  border-radius: var(--radius-full);
}

.main-content {
  flex: 1;
  padding: var(--spacing-xl);
}

/* 页面切换动画 */
.page-enter-active,
.page-leave-active {
  transition: all var(--transition-normal) ease-in-out;
}

.page-enter-from {
  opacity: 0;
  transform: translateX(20px);
}

.page-leave-to {
  opacity: 0;
  transform: translateX(-20px);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .nav-container {
    padding: 0 var(--spacing-md);
    height: 56px;
  }

  .nav-brand h1 {
    font-size: var(--font-size-xl);
  }

  .nav-menu {
    gap: var(--spacing-md);
  }

  .main-content {
    padding: var(--spacing-lg);
  }
}

@media (max-width: 480px) {
  .nav-container {
    flex-direction: column;
    height: auto;
    padding: var(--spacing-md);
  }

  .nav-menu {
    margin-top: var(--spacing-md);
    flex-wrap: wrap;
    justify-content: center;
  }

  .nav-link {
    padding: var(--spacing-xs) var(--spacing-sm);
    font-size: var(--font-size-sm);
  }
}
</style>
