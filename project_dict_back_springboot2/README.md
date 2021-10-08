# readme

## 1. 整合 DataSource

### 1. 整合数据源 `HikariCP` ，似乎就是 JDBC

1. 持久层框架
- Hibernate
- JPA
- MyBatis

2. 数据源
- C3P0
- dbcp
- druid 1.x 时代
- BoneCP

`HikariCP` 日语光速
- SpringBoot 官方首推默认
- 性能高效，速度快
- 代码精简优化

3. 两个依赖
`spring-boot-starter-jdbc` and `mysql-connector-java`
   
其中 `mysql-connector-java` 可用于 mysql 和 MariaDB (原作者开发的另一个数据库，可从 mysql 平滑迁移；
mysql 已经被收购，以后可能会闭源)

4. 安装 mysql docker 8.0.26

`docker run -p 3307:3306 --name mysql8 -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/var/log/mysql -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0.26`

```bash
docker run -p 3307:3306 --name mysql8 \
 -v $PWD/conf:/etc/mysql/conf.d \
 -v $PWD/logs:/var/log/mysql \
 -v $PWD/data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=123456 \
 -d mysql:8.0.26
```

### 2. 整合 HikariCP 数据源，yml 设置

```yaml
  datasource:                                   # 数据源相关的配置节点
    type: com.zaxxer.hikari.HikariDataSource    # 指定数据源的类型 HikariDataSource
    # Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
    driver-class-name: com.mysql.cj.jdbc.Driver    # mysql / MariaDB driver，被禁用了
    url: jdbc:mysql://47.108.156.216:3307/sb_demo_001?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true
    username: root
    password: 123456
    hikari:
      connection-timeout: 30000       # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生 SQLException, 默认30分钟
      minimum-idle: 5                 # 最小连接数
      maximum-pool-size: 20           # 最大连接数
      auto-commit: true               # 自动提交
      idle-timeout: 600000            # 连接超时的最大时长（毫秒），超时则被释放（retired），默认 10 分钟
      pool-name: DataSourceHikariCP   # 连接池名字
      max-lifetime: 1800000           # 连接的生命时长（毫秒），超时而且没被使用则释放（retired)，默认 30 分钟
      connection-test-query: SELECT 1
```

### 3. 整合 MyBatis (tk.mybatis)

1. 引入依赖:
- `mybatis starter` 主依赖
  
```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
```

yml配置：
```yaml
#========================================================
#整合mybatis,设置别名，识别mapper文件
mybatis:
  type-aliases-package: com.jiaopi404.pojo # 所有数据库表逆向后所一一映射的实体类 Entity/Bean/POJO
  mapper-locations: classpath:mybatis/mapper/*.xml     # 所有 mapper 映射的文件所在目录位置
```

- `mapper starter` mapper 工具，可以使 pojo 与 数据库表进行关联，省去很多工作

```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
```

<div style="color: red;">注意：</div>
主类添加 `@MapperScan(basePackages = "com.jiaopi404.mapper")`
注解来自于 `tk.mybatis.spring.annotation.MapperScan;` 注意不要混淆

yml 配置

```yaml
# ========================================================
# 通用的 mapper 配置
mapper:
  mappers: com.jiaopi404.my.mapper.MyMapper # 所有 mapper 都需要实现的接口
  not-empty: false # 在进行数据库操作的时候，判断一个属性是否为空的时候，是否需要自动追加不为空字符串的判断；
                   # 如：username != null 后面不需要 username != ''
  identity: MYSQL  # mapper 的标识符，类似语言

```

### 4. PageHelper 分页助手

- `pagehelper-spring-boot-starter` 分页助手

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.12</version>
</dependency>
```

yaml 配置

```yaml
# ========================================================
# 分页插件助手的配置 PageHelper
pagehelper:
  helper-dialect: mysql           # 语言，类似上面 mapper.identity
  support-methods-arguments: true # 支持方法的参数，个人理解为 某个Bean 的方法需要 PageHelper 的参数时可以获取的到
```


## 2. MyBatis 代码自动成成

### 1. code-template-master, 还有改动的余地；

### 2. mybatis-generator 工具 （暂未实验）

### 3. IDEA mybatis 插件，可生成代码

## 3. 使用 Hibernate 的验证框架对参数进行验证

### 1. 引入包 `spring-boot-starter-validate`

### 2. BO 加入相应的注解 NotNull or NotEmpty and so on

### 3. controller 在接收参数时添加验证 @valid, 之后添加额外参数 BindingResult bindingResult

- 解析 bindingResult
- 使用异常处理的方式处理 bindingResult 的错误

```java
public static void validate (BindingResult bindingResult) throws ValidationException {
    Map<String, String> map = new HashMap<>();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
        map.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    if (!map.isEmpty()) {
        throw GraceException.validationError(map, "表单验证错误");
    }
}
```

### 4. 输出错误信息，返回 ResultV0.error(map)

## 4. 根据 Example 或根据 对象 查询

1. 根据条件查询: 两种方式

1.1 使用 Example 和 selectByExample

1.2 使用 对象 和 select(Obj obj)

## 5. 根据 Example 或 根据 主键 进行更新

```java
testTableMapper.updateByPrimaryKey() // 根据主键进行更新
testTableMapper.updateByPrimaryKeySelective() // 更新，自动选择，根据主键更新属性不为null的值
testTableMapper.updateByExample()
testTableMapper.updateByExampleSelective()
```

## 6. 删除操作，三种方式

```java
testTableMapper.delete(testTable)
testTableMapper.deleteByExample(example)
testTableMapper.deleteByPrimaryKey(id)
```

## 7. 事务操作

事务的类型:

- `REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED)`
  - 默认值，有事务的话加入事务，没有事务的话新建事务
- `SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS)`
  - 当前存在事务，则加入事务，如果没有事务，则以没有事务的方式执行，某些查询以这种方式进行
- `MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY)`
  - 当前存在事务，加入；如果没有事务，则抛出异常；必须以事务方式进行
- `REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW)`
  - 当前存在事务，则挂起，起一个新的事务进行
- `NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED)`
  - 非事务方式运行，存在事务则挂起
- `NEVER(TransactionDefinition.PROPAGATION_NEVER)`
  - 非事务方式运行，存在事务则抛出异常
- `NESTED(TransactionDefinition.PROPAGATION_NESTED)`
  - 如果有事务，则子事务方式运行；没有事务，类似 required

## 8. 使用自定义 sql 进行查询

1. 工具生成的 xml 和 mapper 一般是使用逆向工具自动生成的，因此不建议直接在文件上修改，因此在同目录下（为了 mapper 和 mybatis 能够扫描到）创建
自定义的 xml 和 mapper 仓库
   
2. 写 sql 注意格式；

3. 具体写法参考如下

```xml
  <select id="getTestTableByNameLike" parameterType="java.lang.String" resultType="com.jiaopi404.pojo.TestTable">
    select
        *
    from test_table
    where
          `name`
    like concat('%',#{myName},'%')
  </select>
```

以上，如果使用 `resultMap` 属性，可以定义如下的 resultMap 结点

```xml
<resultMap id="BaseResultMap" type="com.jiaopi404.pojo.TestTable">
    <id column="id" jdbcType="VARCHAR" property="id" />
    <result column="name" jdbcType="VARCHAR" property="name" />
    <result column="age" jdbcType="INTEGER" property="age" />
    <result column="update_time" jdbcType="TIME_WITH_TIMEZONE" property="updateTime" />
</resultMap>
<!--    这个相当于要查询的列的，做一个集合, 之后语句就可以 include[refid="定义的名字"]-->
<sql id="Base_Column_List">
    id, `name`, age, update_time
</sql>
<select id="getTestTableByNameLike" parameterType="java.lang.String" resultMap="BaseResultMap">
  select
  <include refid="Base_Column_List" />
  from test_table
  where
  `name`
  like concat('%',#{name,jdbcType=VARCHAR},'%')
</select>
```

或者使用 `@Select` 注解

```java
@Select("select * from test_table where `name` like concat('%',#{myName},'%')")
List<TestTable> testGetTestTableByNameLike(String myName);
```

注意：
1. like 的写法，需要使用 concat，原因暂且未知
2. 返回的都是集合~

## 9. 整合 druid 数据源

- `druid-spring-boot-sarter` 依赖包

- `springboot.datasource.type=com.alibaba.druid.pool.DruidDataSource` yml 配置

## 10. myBatis 开启 sql 日志打印功能

- yml `mybatis-configuration-logImpl` 配置

## 11. AOP 监控 Service 的执行时间

1. 创建配置类
- `Component 注解` 保证被扫描到
- `Aspect 注解` 切面

2. 创建 通知

- AOP 的通知类型
  - 前置通知
  - 后置通知
  - 环绕通知 Around
  - 异常通知
  - 最终通知
  
- 代码
  
```java
@Around(value = "execution(* com.jiaopi404.service.impl..*.*(..))")
public Object serviceExecDurationLogger (ProceedingJoinPoint joinPoint) throws Throwable {
    // 计算时间
    log.info("[===正在执行方法:===]{}.{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
    Long startTime = System.currentTimeMillis(); // 当前时间毫秒数
    Object result = joinPoint.proceed();
    Long endTime = System.currentTimeMillis(); // 结束后毫秒数
    Long timeSpan = endTime - startTime;
    String msg = "[===方法执行时间：===]" + joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + ": " + timeSpan;
    if (timeSpan > 3000) {
        log.error(msg);
    } else if (timeSpan > 2000) {
        log.warn(msg);
    } else {
        log.info(msg);
    }
    return result;
}
```

- execution 的写法 `execution(* com.jiaopi404.service.impl..*.*(..))`
类似方法的定义：返回值 方法名（参数）
  
## 12. Thymeleaf 模板引擎

### 1. 依赖: `spring-boot-starter-thymeleaf`

### 2. 配置：

```yaml
thymeleaf:                            # thymeleaf 的配置
  prefix: classpath:/templates/html/  # 配置 html 文件路径，注意最后加 /
  suffix: .html                       # 配置后缀，默认 .html
```

### 3. 使用

1. `@Controller` 注解

2. 返回 `String`，代表返回的页面

3. 参数之一：`Model`
  1. `model.addAttribute("attr", value)`

### 4. 模板语法

1. 属性
- `th:text` 渲染文本
- `th:value` `<input th:value="${value}"`

2. 模板值 `${value}`

3. 支持各种函数，如 `#strings` `#dates`
- `<span th:text="${#strings.contains(someText, 't')}">`
- `<span th:text="${#dates.format(someDate)}">`

4. 条件判断

- `th:if=${}`
- `th:switch=${someValue}`
  - `th:case="1"`
  - `th:case="2"`
  - `th:case="*"`
  
5. 循环列表 `each`

```html
<div>
  <!--渲染 ListString-->
    <span th:each="str : ${strList}">
        <span th:text="${str}"></span>
    </span>
</div>
<div>
  <!--渲染 Map<String, String>-->
  <span th:each="kv : ${someMap}"> <!-- 顺序不固定 -->
        <span th:text="${kv.key}"></span>
        <span th:text="${kv.value}"></span>
    </span>
</div>
```

循环时的变量操作：`status`

```html
<div>
    <span th:each="str, status : ${strList}">
        <span th:text="${str}"></span><br />
        index: <span th:text="${status.index}"></span>
    </span>
</div>
```

支持：`count, index, odd, even, first, last, size` 等

6. 操作 request 和 session

- `controller` 可接收参数 `HttpServeltRequest request`
- 函数体中可以设置 `request` 的属性，带入下一个视图
  - `request.setAttribute("attr1", "value1")`
  
- 函数体中操作 `session`
  - `request.getSession()` 返回 HttpSession
  - `request.getSession().setAttribute("attr2", "value2")`
  
- 页面中使用：`#request` 和 `#session`
  - `${#request.getAttribute()}`
  - `${#session.getAttribute()}`

### 5. 生成静态 HTML, `Thymeleaf` 中比较重要的功能；

1. 注入类 `TemplateEngine templateEngine`

2. 调用方法 `void templateEngine.process(String template, Context context, Writer writer)`
  - `template` thymeleaf 模板
  - `org.thymeleaf.context.Context`
  - `Writer out = new FileWriter(String path)`
  - 注意关闭 `Writer` `out.close()`

## 13. `junit` 单元测试

1. `spring-boot-starter-test` 依赖

2. `SpringBootTest` 类注解

3. `@Test` 方法注解

## 14. `spring.profiles.active` 切换不同的 `application-xx.yml 文件`，会整合到主文件

## 15. `actuator` 检查项目运行状态

## 16. 打包为 `jar`

1. 构建依赖

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

2. 设置打 jar 包

```xml
<packaging>jar</packaging>
```

3. 打包完后，会在 target下，运行时 `java -jar xxx.jar`
