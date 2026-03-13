<template>
  <div class="about-container">
    <div class="about-content">
      <h1 class="about-title">关于 NexusOps</h1>
      <div class="about-grid">
        <div class="about-card glass">
          <h2>🎯 我们的使命</h2>
          <p>让云原生应用运维变得简单、高效、智能。通过提供一体化的运维解决方案，帮助企业降低技术门槛，提升运维效率。</p>
        </div>
        <div class="about-card glass">
          <h2>🚀 核心特性</h2>
          <ul>
            <li>一键部署与自动化运维</li>
            <li>多环境配置管理</li>
            <li>实时监控与智能告警</li>
            <li>容器化与微服务支持</li>
          </ul>
        </div>
        <div class="about-card glass">
          <h2>💡 技术栈</h2>
          <div class="tech-stack">
            <span class="tech-badge">Spring Boot 3</span>
            <span class="tech-badge">Vue 3</span>
            <span class="tech-badge">Docker</span>
            <span class="tech-badge">Kubernetes</span>
            <span class="tech-badge">PostgreSQL</span>
          </div>
        </div>
        <div class="about-card glass">
          <h2>📞 联系我们</h2>
          <p>有任何问题或建议，欢迎通过以下方式联系我们：</p>
          <div class="contact-info">
            <p>📧 Email: contact@nexusops.com</p>
            <p>🐙 GitHub: github.com/purity-hub/nexusops</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-container {
  min-height: calc(100vh - 64px);
  padding: var(--spacing-xl);
  display: flex;
  align-items: center;
  justify-content: center;
}

.about-content {
  max-width: 1200px;
  width: 100%;
}

.about-title {
  text-align: center;
  font-size: var(--font-size-3xl);
  font-weight: 800;
  margin-bottom: var(--spacing-2xl);
  background: linear-gradient(135deg, var(--primary-color), #f093fb);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.about-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: var(--spacing-xl);
}

.about-card {
  padding: var(--spacing-xl);
  border-radius: var(--radius-lg);
  transition: all var(--transition-normal);
  border: 1px solid var(--glass-border);
}

.about-card:hover {
  transform: translateY(-4px);
  box-shadow: var(--glass-shadow);
}

.about-card h2 {
  margin: 0 0 var(--spacing-md) 0;
  font-size: var(--font-size-xl);
  font-weight: 600;
  color: var(--text-primary);
}

.about-card p {
  margin: 0 0 var(--spacing-md) 0;
  color: var(--text-secondary);
  line-height: 1.6;
}

.about-card ul {
  margin: 0;
  padding-left: var(--spacing-lg);
  color: var(--text-secondary);
}

.about-card li {
  margin-bottom: var(--spacing-sm);
}

.tech-stack {
  display: flex;
  flex-wrap: wrap;
  gap: var(--spacing-sm);
}

.tech-badge {
  background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
  color: white;
  padding: var(--spacing-xs) var(--spacing-sm);
  border-radius: var(--radius-md);
  font-size: var(--font-size-sm);
  font-weight: 500;
}

.contact-info p {
  margin: var(--spacing-sm) 0;
  color: var(--text-regular);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .about-container {
    padding: var(--spacing-lg);
  }

  .about-title {
    font-size: 2.5rem;
    margin-bottom: var(--spacing-xl);
  }

  .about-grid {
    grid-template-columns: 1fr;
    gap: var(--spacing-lg);
  }

  .about-card {
    padding: var(--spacing-lg);
  }
}

@media (max-width: 480px) {
  .about-container {
    padding: var(--spacing-md);
  }

  .about-title {
    font-size: 2rem;
  }

  .tech-stack {
    justify-content: center;
  }
}
</style>
