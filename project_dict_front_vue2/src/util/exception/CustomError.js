export default class CustomError extends Error {
  constructor (message) {
    super(message);
  }

  // 需要后代 重写
  handler () {
    console.log('handler 方法')
  }
}