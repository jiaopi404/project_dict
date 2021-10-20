import CustomError from '@/util/exception/CustomError'
import { Message } from 'element-ui'

export default class FormValidationError extends CustomError{
  /**
   * 构造
   * @param message 信息
   * @param ifShowMessage 要不要显示 Message.error
   * @param context 当前验证的表单域组件
   */
  constructor (message, context, ifShowMessage) {
    super(message);
    this.ifShowMessage = ifShowMessage || false
    this.context = context || null // 组件信息
  }

  // @Override 重写父类方法
  handler () {
    if (this.ifShowMessage && this.message) {
      Message.error(this.message)
    }
    console.log('%c [FormValidationError] ', 'color: #ff3040; font-size: 16px;', this)
  }
}
