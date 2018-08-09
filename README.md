## web项目全局错误处理包含页面和API的统一错误处理
- 配置项目异常统一处理方法和返回页面

### 支持thymeleaf
这里使用thymeleaf示例，在类`com.knight.configdemo.config.WebMVCConfig`中是配置thymeleaf的相关配置都是模板代码。

### 定义API响应风格
通过自定义类实现，在类`com.knight.configdemo.base.ApiResponse`中

### web错误全局处理Controller
在spring boot中通过实现类`org.springframework.boot.autoconfigure.web.ErrorController`处理全局错误；详情见类`com.knight.configdemo.base.AppErrorController`
在上面的类中配置错误页面处理和API接口处理

### 添加异常页面
properties文件中已经配置了thymeleaf的路径，在templates目录下新建404.html、403.html、500.html文件
