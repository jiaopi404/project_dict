export default class ErrorHandler {
  static handlers = [] // Array<Error>

  static register (errorClass) {
    ErrorHandler.handlers.push(errorClass) // 添加错误类型
  }

  /**
   * 主，处理方法
   * @param err 错误对象
   */
  static handle (err) {
    let isHandled
    for (const errorClass of ErrorHandler.handlers) {
      if (err instanceof errorClass) {
        if (err.handler) { // 实现了 handler 方法
          err.handler()
          isHandled++
          return
        } else { // 默认的 handler 方法
          ErrorHandler.defaultHandler(err)
          isHandled++
          return
        }
      }
    }
    if (!isHandled) {
      console.log('%c [ErrorNoHandler] ', 'color: #ff3040; font-size: 16px;', this)
    }
  }

  static defaultHandler (err) {
    console.log('%c [DefaultHandler] ', 'color: #ff3040; font-size: 16px;', err)
  }
}
