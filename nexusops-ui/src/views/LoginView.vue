<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElForm } from 'element-plus'
import axios from 'axios'
import { useAuthStore } from '@/stores/auth'
import { User, Lock, Key } from '@element-plus/icons-vue'

const router = useRouter()
const authStore = useAuthStore()

const loginFormRef = ref<InstanceType<typeof ElForm>>()

const loginForm = reactive({
  username: '',
  password: ''
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

const handleLogin = async () => {
  if (!loginFormRef.value) return

  const valid = await loginFormRef.value.validate().catch(() => false)
  if (!valid) return

  loading.value = true
  try {
    const response = await axios.post('/api/auth/login', loginForm)
    const token = response.data.data.token
    authStore.setToken(token)
    ElMessage.success('登录成功')
    router.push('/')
  } catch (error) {
    ElMessage.error('登录失败，请检查用户名和密码')
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="login-container">
    <el-card class="login-card glass" shadow="never">
      <template #header>
        <div class="login-header">
          <h2>NexusOps 登录</h2>
          <p>云原生一体化运维平台</p>
        </div>
      </template>
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="rules"
        label-width="80px"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            clearable
            size="large"
            @keyup.enter="handleLogin"
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            show-password
            size="large"
            @keyup.enter="handleLogin"
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleLogin"
            style="width: 100%"
            size="large"
          >
            <el-icon><Key /></el-icon>
            <span>登录</span>
          </el-button>
        </el-form-item>
        <el-form-item>
          <div class="register-link">
            还没有账号？
            <router-link to="/register" class="register-link-text">立即注册</router-link>
          </div>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 400px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  overflow: hidden;
}

.login-card.glass {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.login-header {
  text-align: center;
  padding: 20px 0;
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.1), rgba(118, 75, 162, 0.1));
}

.login-header h2 {
  margin: 0 0 8px 0;
  color: #409eff;
  font-size: 24px;
  font-weight: 600;
}

.login-header p {
  margin: 0;
  color: #666;
  font-size: 14px;
}

.register-link {
  text-align: center;
  margin-top: 10px;
}

.register-link-text {
  color: #409eff;
  font-weight: 600;
  text-decoration: none;
}

.register-link-text:hover {
  text-decoration: underline;
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
    font-size: 20px;
  }
}
</style>
