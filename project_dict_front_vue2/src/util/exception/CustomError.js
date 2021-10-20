export default class CustomError extends Error {
  constructor (message, context) {
    super(message);
    this.context = context
  }

  // 需要后代 重写
  handler () {
    console.log('%c [CustomError] ', 'color: #ff3040; font-size: 16px;', this)
  }
}