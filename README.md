# NexusOps - 云原生一体化运维平台

> 让应用运维更智能、更高效

!https://img.shields.io/badge/license-Apache%202.0-blue.svg
!https://img.shields.io/badge/Java-8%2B-orange.svg
!https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg
!https://img.shields.io/badge/PostgreSQL-15%2B-blue.svg
!https://img.shields.io/badge/Docker-Support-blue.svg
!https://img.shields.io/badge/Kubernetes-Support-blue.svg

## 🎯 项目简介

**NexusOps** 是一个现代化的云原生运维平台，旨在帮助中小型企业实现从传统架构到云原生的平滑过渡。平台提供从应用部署、配置管理、监控告警到智能运维的一体化解决方案，显著降低云原生技术的使用门槛。

### ✨ 核心特性

- **🚀 一键部署**：支持Spring Boot应用、Docker容器一键部署
- **🔧 配置中心**：多环境配置管理，支持热更新和版本回滚
- **📊 全方位监控**：应用性能、链路追踪、日志分析三位一体
- **🤖 智能运维**：基于AI的异常检测和根因分析
- **⚡ 高可用架构**：支持蓝绿发布、金丝雀发布等高级部署策略
- **🔐 企业级安全**：多租户隔离、RBAC权限控制、操作审计

## 🏗️ 系统架构

### 整体架构图
```
┌─────────────────────────────────────────────────────────────┐
│                        Web控制台 (Vue3)                        │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│                     API网关 (Spring Cloud Gateway)            │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│   用户服务    │  │   应用服务    │  │   监控服务    │  │   告警服务    │
│  (Auth)     │  │  (App)      │  │  (Monitor)  │  │  (Alert)   │
└─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│               注册中心 (Nacos) + 配置中心 (Nacos)                │
└─────────────────────────────────────────────────────────────┘
                              │
┌─────────────────────────────────────────────────────────────┐
│          数据层: PostgreSQL, Redis, Elasticsearch, Kafka       │
└─────────────────────────────────────────────────────────────┘
```

### 技术栈

| 类别 | 技术选型 | 说明 |
|------|---------|------|
| **后端框架** | Spring Boot 2.7.18 | 主框架 |
|  | Spring Cloud 2021.0.8 | 微服务框架 |
|  | Spring Security 6.x | 安全认证 |
| **数据存储** | PostgreSQL 15 | 主数据库 |
|  | Redis 7.x | 缓存/分布式锁 |
|  | Elasticsearch 8.x | 日志搜索 |
|  | InfluxDB 2.x | 时序数据 |
| **中间件** | Apache Kafka 3.x | 消息队列 |
|  | Nacos 2.2.x | 注册/配置中心 |
|  | SkyWalking 9.x | APM监控 |
| **云原生** | Docker 24.x | 容器化 |
|  | Kubernetes 1.28+ | 容器编排 |
|  | Prometheus | 监控采集 |
|  | Grafana | 数据可视化 |
| **前端** | Vue 3 + TypeScript | 前端框架 |
|  | Element Plus | UI组件库 |
|  | Vite | 构建工具 |

## 🚀 快速开始

### 环境要求

- JDK 8+
- Maven 3.8+
- Docker 20.10+ & Docker Compose
- Node.js 18+ (仅前端开发需要)
- PostgreSQL 15+
- Redis 7+

### 1. 本地开发环境启动

#### 使用 Docker Compose 启动基础设施
```bash
# 克隆项目
git clone https://github.com/yourusername/nexusops.git
cd nexusops

# 启动基础服务
docker-compose -f docker/docker-compose-dev.yml up -d

# 验证服务状态
docker-compose ps
```

#### 后端服务启动
```bash
# 安装依赖
mvn clean install

# 启动注册中心
cd nexusops-nacos
mvn spring-boot:run

# 启动网关服务
cd nexusops-gateway
mvn spring-boot:run

# 启动用户服务
cd nexusops-auth
mvn spring-boot:run

# 启动应用服务
cd nexusops-app
mvn spring-boot:run
```

#### 前端启动
```bash
cd nexusops-ui
npm install
npm run dev
```

### 2. 访问地址

| 服务 | 地址 | 默认账号 |
|------|------|----------|
| 控制台 | http://localhost:3000 | admin/admin123 |
| API文档 | http://localhost:8080/swagger-ui.html | - |
| Nacos控制台 | http://localhost:8848/nacos | nacos/nacos |
| Grafana | http://localhost:3001 | admin/admin |
| SkyWalking UI | http://localhost:8080 | - |

## 📁 项目结构

```
nexusops/
├── docs/                    # 文档目录
├── docker/                  # Docker配置
├── nexusops-auth/          # 认证授权服务
├── nexusops-app/           # 应用管理服务
├── nexusops-monitor/       # 监控服务
├── nexusops-alert/         # 告警服务
├── nexusops-gateway/       # API网关
├── nexusops-common/        # 公共模块
│   ├── common-core/        # 核心工具类
│   ├── common-web/         # Web相关
│   ├── common-mybatis/     # 数据访问
│   └── common-redis/       # Redis配置
├── nexusops-ui/            # 前端项目
├── sql/                    # 数据库脚本
├── docker-compose.yml      # 开发环境
├── docker-compose-prod.yml # 生产环境
├── pom.xml                 # 父POM
└── README.md               # 本文档
```

## 🔧 核心功能

### 1. 应用管理
- ✅ 应用生命周期管理（创建、部署、启停）
- ✅ 多环境支持（开发、测试、生产）
- ✅ 多种部署策略（滚动更新、蓝绿发布）
- ✅ 应用编排（支持Docker Compose、K8s YAML）

### 2. 配置管理
- ✅ 多环境配置管理
- ✅ 配置版本控制
- ✅ 敏感信息加密
- ✅ 配置热更新
- 🔄 配置权限审批（开发中）

### 3. 监控告警
- ✅ 基础资源监控（CPU、内存、磁盘）
- ✅ JVM监控
- ✅ 链路追踪
- ✅ 日志聚合
- 🔄 智能告警（开发中）

### 4. 用户权限
- ✅ 多租户隔离
- ✅ RBAC权限控制
- ✅ 操作审计
- ✅ OAuth2.0登录

## 🧪 测试

### 单元测试
```bash
# 运行所有单元测试
mvn test

# 运行指定模块测试
cd nexusops-auth
mvn test
```

### 集成测试
```bash
# 启动测试环境
docker-compose -f docker/docker-compose-test.yml up -d

# 运行集成测试
mvn verify
```

### API测试
```bash
# 使用Postman导入docs/api/NexusOps.postman_collection.json
```

## 🐳 容器化部署

### 构建镜像
```bash
# 构建所有服务镜像
mvn clean package
docker build -t nexusops-gateway:latest ./nexusops-gateway
docker build -t nexusops-auth:latest ./nexusops-auth
# ... 其他服务类似
```

### Kubernetes部署
```bash
# 部署到K8s集群
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/config/
kubectl apply -f k8s/deployment/
kubectl apply -f k8s/service/
```

## 📈 监控指标

平台暴露的监控端点：
- `/actuator/health` - 健康检查
- `/actuator/prometheus` - Prometheus指标
- `/actuator/metrics` - 应用指标
- `/actuator/loggers` - 日志级别管理

## 🤝 如何贡献

我们非常欢迎贡献！请参阅CONTRIBUTING.md。

1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

## 📄 许可证

本项目采用 Apache License 2.0 许可证 - 查看 LICENSE 文件了解详情。

## 📞 联系

- 问题反馈：https://github.com/yourusername/nexusops/issues
- 项目文档：https://github.com/yourusername/nexusops/wiki
- 邮箱：your.email@example.com

## 🙏 致谢

感谢以下开源项目：
- https://spring.io/projects/spring-boot
- https://vuejs.org/
- https://skywalking.apache.org/
- https://prometheus.io/

---

**星星是支持的开源动力！** ⭐ 如果这个项目对你有帮助，请给它一个Star！

> 注意：本项目仍在积极开发中，API可能会发生变化。建议用于学习和技术研究。