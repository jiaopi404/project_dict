import CustomError from '@/util/exception/CustomError'

export default class HttpError extends CustomError{
  constructor (message, code) {
    super(message);
    this.code = code // http 错误码
  }

  toString () {
    return `[HttpError]: code: ${this.code}, message: ${this.message}`
  }

  handler () {
    console.error(this)
    window.alert('http错误: ' + this.toString())
  }

  getMessage () {
    return this.message
  }
}
