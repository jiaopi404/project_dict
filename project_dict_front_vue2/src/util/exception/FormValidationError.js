import CustomError from '@/util/exception/CustomError'
import { Message } from 'element-ui'

export default class FormValidationError extends CustomError{
  /**
   * 构造
   * @param message 信息
   * @param ifShowMessage 要不要显示 Message.error
   * @param vm 当前验证的表单域组件
   */
  constructor (message, ifShowMessage, vm) {
    super(message);
    this.ifShowMessage = ifShowMessage || false
    this.vm = vm || null // 组件信息
  }

  toString () {
    return `[FormValidationError]: code: ${this.code}, message: ${this.message}, ifShowMessage: ${this.ifShowMessage}`
  }

  // @Override 重写父类方法
  handler () {
    if (this.ifShowMessage && this.message) {
      Message.error(this.message)
    }
    console.log('表单验证错误，组件：', this.vm)
  }
}
