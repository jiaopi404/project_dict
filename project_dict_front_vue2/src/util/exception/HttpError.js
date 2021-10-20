import CustomError from '@/util/exception/CustomError'

export default class HttpError extends CustomError{
  constructor (message, context) {
    super(message);
    this.context = context
  }

  handler () {
    console.log('%c [HttpError] ', 'color: #ff3040; font-size: 16px;', this)
  }
}
