import Vue from 'vue'
import ErrorHandler from '@/util/exception/ErrorHandler'
import CustomError from '@/util/exception/CustomError'
import HttpError from '@/util/exception/HttpError'
import FormValidationError from '@/util/exception/FormValidationError'

// 拓展类
ErrorHandler.register(HttpError)
ErrorHandler.register(FormValidationError)
// 自定义错误类
ErrorHandler.register(CustomError)
// 错误基类
ErrorHandler.register(Error)

Vue.config.productionTip = false

Vue.config.errorHandler = function (err) {
  ErrorHandler.handle(err)
}

// window.onerror = function (err) {
//   console.log('windows， js 的异常捕获', err)
//   console.error(err)
//   ErrorHandler.handle(err)
// }
window.addEventListener('error', function (errEvent) {
  // 首先阻止掉默认行为
  if (errEvent instanceof Event) {
    errEvent.preventDefault() // 阻止到默认的控制台行为
  }
  if (errEvent instanceof ErrorEvent) { // 如果是错误事件，会有 error 对象
    ErrorHandler.handle(errEvent.error) // 处理错误
  } else {
    console.log('未知类型的错误事件', errEvent)
  }
})
