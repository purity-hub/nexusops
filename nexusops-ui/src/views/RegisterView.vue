<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElForm } from 'element-plus'
import axios from 'axios'
import { User, Lock, Message } from '@element-plus/icons-vue'

const router = useRouter()

const registerFormRef = ref<InstanceType<typeof ElForm>>()

const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  email: ''
})

const rules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能少于 6 个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator: (rule: any, value: string, callback: any) => {
        if (value !== registerForm.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
  ]
})

const loading = ref(false)

const handleRegister = async () => {
  if (!registerFormRef.value) return

  const valid = await registerFormRef.value.validate().catch(() => false)
  if (!valid) return

  loading.value = true
  try {
    await axios.post('/api/auth/register', {
      username: registerForm.username,
      password: registerForm.password,
      email: registerForm.email
    })
    ElMessage.success('注册成功，请登录')
    router.push('/login')
  } catch (error: any) {
    ElMessage.error(error.response?.data?.message || '注册失败，请重试')
  } finally {
    loading.value = false
  }
}

const goToLogin = () => {
  router.push('/login')
}
</script>

<template>
  <div class="register-container">
    <el-card class="register-card glass" shadow="never">
      <template #header>
        <div class="register-header">
          <h2>NexusOps 注册</h2>
          <p>加入我们，开启智能运维之旅</p>
        </div>
      </template>
      <el-form
        ref="registerFormRef"
        :model="registerForm"
        :rules="rules"
        label-width="100px"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="registerForm.username"
            placeholder="请输入用户名"
            clearable
            size="large"
            @keyup.enter="handleRegister"
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input
            v-model="registerForm.email"
            placeholder="请输入邮箱"
            clearable
            size="large"
            @keyup.enter="handleRegister"
          >
            <template #prefix>
              <el-icon><Message /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="registerForm.password"
            type="password"
            placeholder="请输入密码"
            show-password
            size="large"
            @keyup.enter="handleRegister"
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="registerForm.confirmPassword"
            type="password"
            placeholder="请确认密码"
            show-password
            size="large"
            @keyup.enter="handleRegister"
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
            @click="handleRegister"
            style="width: 100%"
            size="large"
          >
            <el-icon><User /></el-icon>
            <span>注册</span>
          </el-button>
        </el-form-item>
        <el-form-item>
          <div class="login-link">
            已有账号？
            <el-link @click="goToLogin" type="primary">立即登录</el-link>
          </div>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.register-card {
  width: 100%;
  max-width: 450px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border-radius: 12px;
  overflow: hidden;
}

.register-card.glass {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.register-header {
  text-align: center;
  padding: 20px 0;
  background: linear-gradient(135deg, rgba(102, 126, 234, 0.1), rgba(118, 75, 162, 0.1));
}

.register-header h2 {
  margin: 0 0 8px 0;
  color: #409eff;
  font-size: 24px;
  font-weight: 600;
}

.register-header p {
  margin: 0;
  color: #666;
  font-size: 14px;
}

.login-link {
  text-align: center;
  color: var(--text-secondary);
}

/* 响应式设计 */
@media (max-width: 480px) {
  .register-container {
    padding: 10px;
  }

  .register-card {
    max-width: 100%;
  }

  .register-header h2 {
    font-size: 20px;
  }
}
</style>
