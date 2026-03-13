<script setup lang="ts">
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://vuejs.org/api/sfc-script-setup.html#script-setup
</script>

<template>
  <div class="home-container">
    <div class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">NexusOps</h1>
        <p class="hero-subtitle">云原生一体化运维平台</p>
        <p class="hero-description">
          让应用运维更智能、更高效。提供从应用部署、配置管理、监控告警到智能运维的一体化解决方案。
        </p>
        <div class="hero-actions">
          <el-button type="primary" size="large" class="cta-button">
            开始使用
          </el-button>
          <el-button size="large" plain class="secondary-button">
            了解更多
          </el-button>
        </div>
      </div>
      <div class="hero-visual">
        <div class="feature-cards">
          <div class="feature-card glass">
            <div class="feature-icon">🚀</div>
            <h3>一键部署</h3>
            <p>支持多种部署策略，快速上线应用</p>
          </div>
          <div class="feature-card glass">
            <div class="feature-icon">📊</div>
            <h3>全方位监控</h3>
            <p>实时监控应用性能和系统状态</p>
          </div>
          <div class="feature-card glass">
            <div class="feature-icon">🔧</div>
            <h3>配置管理</h3>
            <p>多环境配置，支持热更新和版本控制</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.home-container {
  min-height: calc(100vh - 64px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--spacing-xl);
}

.hero-section {
  max-width: 1200px;
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--spacing-2xl);
  align-items: center;
}

.hero-content {
  text-align: left;
}

.hero-title {
  font-size: var(--font-size-3xl);
  font-weight: 800;
  margin: 0 0 var(--spacing-md) 0;
  background: linear-gradient(135deg, var(--primary-color), #f093fb);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  line-height: 1.2;
}

.hero-subtitle {
  font-size: var(--font-size-xl);
  color: var(--text-regular);
  margin: 0 0 var(--spacing-lg) 0;
  font-weight: 500;
}

.hero-description {
  font-size: var(--font-size-lg);
  color: var(--text-secondary);
  margin: 0 0 var(--spacing-xl) 0;
  line-height: 1.6;
  max-width: 500px;
}

.hero-actions {
  display: flex;
  gap: var(--spacing-md);
  flex-wrap: wrap;
}

.cta-button {
  background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
  border: none;
  font-weight: 600;
  min-width: 140px;
}

.secondary-button {
  border-color: var(--primary-color);
  color: var(--primary-color);
  font-weight: 600;
  min-width: 140px;
}

.hero-visual {
  display: flex;
  justify-content: center;
}

.feature-cards {
  display: grid;
  grid-template-columns: 1fr;
  gap: var(--spacing-lg);
  max-width: 300px;
}

.feature-card {
  padding: var(--spacing-lg);
  border-radius: var(--radius-lg);
  text-align: center;
  transition: all var(--transition-normal);
  border: 1px solid var(--glass-border);
}

.feature-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--glass-shadow);
}

.feature-icon {
  font-size: 2rem;
  margin-bottom: var(--spacing-md);
}

.feature-card h3 {
  margin: 0 0 var(--spacing-sm) 0;
  font-size: var(--font-size-lg);
  font-weight: 600;
  color: var(--text-primary);
}

.feature-card p {
  margin: 0;
  color: var(--text-secondary);
  font-size: var(--font-size-sm);
  line-height: 1.5;
}

/* 响应式设计 */
@media (max-width: 1024px) {
  .hero-section {
    grid-template-columns: 1fr;
    gap: var(--spacing-xl);
    text-align: center;
  }

  .hero-content {
    text-align: center;
  }

  .hero-description {
    max-width: none;
  }

  .hero-actions {
    justify-content: center;
  }
}

@media (max-width: 768px) {
  .home-container {
    padding: var(--spacing-lg);
  }

  .hero-title {
    font-size: 2.5rem;
  }

  .hero-subtitle {
    font-size: var(--font-size-lg);
  }

  .hero-description {
    font-size: var(--font-size-base);
  }

  .feature-cards {
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    max-width: none;
  }
}

@media (max-width: 480px) {
  .hero-actions {
    flex-direction: column;
    align-items: center;
  }

  .cta-button,
  .secondary-button {
    width: 100%;
    max-width: 280px;
  }

  .feature-cards {
    grid-template-columns: 1fr;
  }
}
</style>
