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
    // let isHandled
    for (const errorClass of ErrorHandler.handlers) {
      if (err instanceof errorClass) {
        if (err.handler) { // 实现了 handler 方法
          err.handler()
          // isHandled++
          return
        } else { // 默认的 handler 方法
          ErrorHandler.defaultHandler(err)
          // isHandled++
          return
        }
      }
    }
    // if (!isHandled) {
    //   console.log('这个错误没有 handle 住')
    //   console.dir(err)
    //   // element-ui 的 form.validate 抛出的是一个字符串，这样就无法获取到 track 信息了，所以直接不处理
    // }
  }

  static defaultHandler (err) {
    console.log('默认的 错误 handler 方法: ', err)
  }
}
