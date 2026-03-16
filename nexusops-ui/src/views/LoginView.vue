<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElForm } from 'element-plus'
import axios from 'axios'
import { useAuthStore } from '@/stores/auth'
import { User, Lock, Key, InfoFilled } from '@element-plus/icons-vue'

const router = useRouter()
const authStore = useAuthStore()

const loginFormRef = ref<InstanceType<typeof ElForm>>()

const loginForm = reactive({
  username: '',
  password: '',
  remember: false
})

const rules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能少于 6 个字符', trigger: 'blur' }
  ]
})

const loading = ref(false)
const showPassword = ref(false)

// 密码强度计算
const passwordStrength = computed(() => {
  const password = loginForm.password
  let strength = 0
  if (password.length >= 6) strength++
  if (/[a-z]/.test(password)) strength++
  if (/[A-Z]/.test(password)) strength++
  if (/[0-9]/.test(password)) strength++
  if (/[^A-Za-z0-9]/.test(password)) strength++
  return strength
})

const passwordStrengthText = computed(() => {
  const strength = passwordStrength.value
  if (strength <= 1) return { text: '弱', color: '#f56c6c' }
  if (strength <= 3) return { text: '中', color: '#e6a23c' }
  return { text: '强', color: '#67c23a' }
})

const handleLogin = async () => {
  if (!loginFormRef.value) return

  const valid = await loginFormRef.value.validate().catch(() => false)
  if (!valid) return

  loading.value = true
  try {
    const response = await axios.post('/api/auth/login', {
      username: loginForm.username,
      password: loginForm.password
    })
    const token = response.data.data.token
    authStore.setToken(token)

    if (loginForm.remember) {
      localStorage.setItem('remember', 'true')
    }

    ElMessage.success('登录成功，欢迎回来！')
    router.push('/')
  } catch (error: any) {
    const errorMessage = error.response?.data?.message || '登录失败，请检查用户名和密码'
    ElMessage.error(errorMessage)
  } finally {
    loading.value = false
  }
}

const goToRegister = () => {
  router.push('/register')
}

const goToForgotPassword = () => {
  ElMessage.info('忘记密码功能开发中，请联系管理员')
}

// 初始化记住的用户名
const initRememberedUser = () => {
  const remembered = localStorage.getItem('remember')
  if (remembered === 'true') {
    const savedUsername = localStorage.getItem('savedUsername')
    if (savedUsername) {
      loginForm.username = savedUsername
      loginForm.remember = true
    }
  }
}

initRememberedUser()
</script>

<template>
  <div class="login-container">
    <!-- 背景动画 -->
    <div class="background-animation">
      <div class="floating-shape shape-1"></div>
      <div class="floating-shape shape-2"></div>
      <div class="floating-shape shape-3"></div>
    </div>

    <el-card class="login-card glass" shadow="never">
      <template #header>
        <div class="login-header">
          <div class="logo-container">
            <div class="logo">
              <el-icon size="32" color="#409eff"><Key /></el-icon>
            </div>
            <h2>NexusOps</h2>
          </div>
          <p>智能运维平台</p>
        </div>
      </template>

      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="rules"
        label-width="80px"
        status-icon
        class="login-form"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            clearable
            size="large"
            @keyup.enter="handleLogin"
            class="animated-input"
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
            v-model="loginForm.password"
            :type="showPassword ? 'text' : 'password'"
            placeholder="请输入密码"
            size="large"
            @keyup.enter="handleLogin"
            class="animated-input"
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
            <template #suffix>
              <el-icon @click="showPassword = !showPassword" class="password-toggle">
                <component :is="showPassword ? 'Hide' : 'View'" />
              </el-icon>
            </template>
          </el-input>

          <!-- 密码强度指示器 -->
          <div v-if="loginForm.password" class="password-strength">
            <div class="strength-bar">
              <div
                class="strength-fill"
                :style="{ width: (passwordStrength / 5) * 100 + '%', backgroundColor: passwordStrengthText.color }"
              ></div>
            </div>
            <span class="strength-text" :style="{ color: passwordStrengthText.color }">
              密码强度: {{ passwordStrengthText.text }}
            </span>
          </div>
        </el-form-item>

        <el-form-item>
          <div class="form-options">
            <el-checkbox v-model="loginForm.remember" size="large">
              记住我
            </el-checkbox>
            <el-link @click="goToForgotPassword" type="info" size="small">
              忘记密码？
            </el-link>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleLogin"
            style="width: 100%"
            size="large"
            class="login-button"
          >
            <el-icon><Key /></el-icon>
            <span>{{ loading ? '登录中...' : '登录' }}</span>
          </el-button>
        </el-form-item>

        <el-form-item>
          <div class="register-link">
            <span>还没有账号？</span>
            <el-link @click="goToRegister" type="primary" size="large">
              立即注册
            </el-link>
          </div>
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="login-footer">
          <div class="social-login">
            <span>其他登录方式</span>
            <div class="social-buttons">
              <el-button circle size="small" disabled>
                <el-icon><InfoFilled /></el-icon>
              </el-button>
              <el-button circle size="small" disabled>
                <el-icon><InfoFilled /></el-icon>
              </el-button>
            </div>
          </div>
          <div class="footer-links">
            <el-link href="#" size="small">隐私政策</el-link>
            <span class="divider">|</span>
            <el-link href="#" size="small">服务条款</el-link>
          </div>
        </div>
      </template>
    </el-card>
  </div>
</template>

<style scoped>
.login-container {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
  overflow: hidden;
}

/* 背景动画 */
.background-animation {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
}

.floating-shape {
  position: absolute;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
  animation: float 6s ease-in-out infinite;
}

.shape-1 {
  width: 80px;
  height: 80px;
  top: 10%;
  left: 10%;
  animation-delay: 0s;
}

.shape-2 {
  width: 60px;
  height: 60px;
  top: 60%;
  right: 10%;
  animation-delay: 2s;
}

.shape-3 {
  width: 100px;
  height: 100px;
  bottom: 20%;
  left: 20%;
  animation-delay: 4s;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0px) rotate(0deg);
  }
  50% {
    transform: translateY(-20px) rotate(180deg);
  }
}

.login-card {
  position: relative;
  z-index: 10;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border-radius: 16px;
  overflow: hidden;
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.login-card.glass {
  background: rgba(255, 255, 255, 0.15);
}

.login-header {
  text-align: center;
  padding: 24px 0;
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.1), rgba(118, 75, 162, 0.1));
}

.logo-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 8px;
}

.logo {
  margin-right: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48px;
  height: 48px;
  border-radius: 12px;
  background: linear-gradient(135deg, #409eff, #66b1ff);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

.login-header h2 {
  margin: 0;
  color: #409eff;
  font-size: 28px;
  font-weight: 700;
  background: linear-gradient(135deg, #409eff, #f093fb);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.login-header p {
  margin: 8px 0 0 0;
  color: #666;
  font-size: 16px;
  font-weight: 500;
}

.login-form {
  padding: 0 8px;
}

.animated-input {
  transition: all 0.3s ease;
}

.animated-input:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.password-toggle {
  cursor: pointer;
  color: #909399;
  transition: color 0.3s ease;
}

.password-toggle:hover {
  color: #409eff;
}

.password-strength {
  margin-top: 8px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.strength-bar {
  flex: 1;
  height: 4px;
  background: #e4e7ed;
  border-radius: 2px;
  overflow: hidden;
}

.strength-fill {
  height: 100%;
  transition: all 0.3s ease;
}

.strength-text {
  font-size: 12px;
  font-weight: 500;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.login-button {
  background: linear-gradient(135deg, #409eff, #66b1ff);
  border: none;
  font-weight: 600;
  transition: all 0.3s ease;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(64, 158, 255, 0.4);
}

.register-link {
  text-align: center;
  margin-top: 16px;
}

.register-link span {
  color: var(--text-secondary);
  margin-right: 8px;
}

.login-footer {
  padding: 16px 24px;
  background: rgba(255, 255, 255, 0.05);
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.social-login {
  text-align: center;
  margin-bottom: 12px;
}

.social-login span {
  color: var(--text-secondary);
  font-size: 14px;
}

.social-buttons {
  display: flex;
  justify-content: center;
  gap: 8px;
  margin-top: 8px;
}

.footer-links {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  font-size: 12px;
}

.divider {
  color: var(--text-placeholder);
}

/* 响应式设计 */
@media (max-width: 480px) {
  .login-container {
    padding: 10px;
  }

  .login-card {
    max-width: 100%;
  }

  .login-header h2 {
    font-size: 24px;
  }

  .logo-container {
    flex-direction: column;
    gap: 8px;
  }

  .logo {
    margin-right: 0;
  }

  .form-options {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }

  .social-buttons {
    flex-wrap: wrap;
  }
}

/* 暗黑模式适配 */
[data-theme="dark"] .login-card.glass {
  background: rgba(24, 24, 27, 0.15);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

[data-theme="dark"] .login-header {
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.2), rgba(118, 75, 162, 0.2));
}

[data-theme="dark"] .login-footer {
  background: rgba(24, 24, 27, 0.05);
  border-top: 1px solid rgba(255, 255, 255, 0.05);
}
</style>
