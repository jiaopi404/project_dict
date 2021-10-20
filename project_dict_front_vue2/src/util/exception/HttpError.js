import CustomError from '@/util/exception/CustomError'

export default class HttpError extends CustomError{
  constructor (message, context) {
    super(message);
    this.context = context
  }

  handler () {
    console.log('[HttpError]: ', this)
    console.error(this)
  }
}
